package io.ukihsoroy.gatling.strategy;

import io.ukihsoroy.gatling.constants.DatePatternConst;
import io.ukihsoroy.gatling.entity.Task;
import io.ukihsoroy.gatling.types.TaskStatusType;
import io.ukihsoroy.gatling.view.CountCompareView;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 条数比较case
 * @author K.O
 */
@Component
public class CountCompareGatlingStrategy extends AbstractGatlingStrategy {

    private Logger logger = LoggerFactory.getLogger(CountCompareGatlingStrategy.class);

    private static final String COUNT_COMPARE_GATLING_NAME = "countCompareGatlingStrategy";

    /**
     * MaxCompute 云上的表
     */
    private static final String MC_PT_SQL = "SELECT COUNT(1) FROM {0} WHERE pt = '{1}'";

    /**
     * MaxCompute StreamX的表
     */
    private static final String MC_YMD_SQL = "SELECT COUNT(1) FROM {0} WHERE year = '{1}' AND month = '{2}' AND day = '{3}'";

    /**
     * PolarDB
     */
    private static final String POLAR_ALL_COUNT_SQL = "SELECT COUNT(1) FROM {0} WHERE DATE_FORMAT({1}, '%Y%m%d') <= '{2}'";

    private static final String POLAR_DELTA_TABLE_COUNT_SQL = "SELECT COUNT(1) FROM {0} WHERE DATE_FORMAT({1}, '%Y%m%d') <= '{2}' AND DATE_FORMAT({3}, '%Y%m%d') > DATE_FORMAT(DATA_SUB('{4}', INTERVAL 1 DAY), '%Y%m%d')";

    @Override
    public void executor(Task task){
            //保存为测试中
        task.setStartTime(new Date());
        task.setTaskStatus(TaskStatusType.TESTING.getStatus());
        taskRepository.save(task);

        //获取jdbc client
        JdbcTemplate originJdbcTemplate = jdbcTemplates.get(task.getOriginDatasourceName());
        JdbcTemplate targetJdbcTemplate = jdbcTemplates.get(task.getTargetDatasourceName());

        //构建查询sql
        String originSql = meshExecutorSql(task.getOriginTableType(), task.getOriginTableName(), task.getExecutorBatch(), task.getIncrement());
        String targetSql = meshExecutorSql(task.getTargetTableType(), task.getTargetTableName(), task.getExecutorBatch(), task.getIncrement());

        //查询数据条目
        Long originCount = originJdbcTemplate.queryForObject(originSql, Long.class);
        Long targetCount = targetJdbcTemplate.queryForObject(targetSql, Long.class);

        //比较结果
        CountCompareView countCompareView = new CountCompareView(originCount, targetCount, originCount != null && originCount.equals(targetCount));
        try {
            String report = objectMapper.writeValueAsString(countCompareView);
            task.setTaskReport(report);
        } catch (IOException e) {
            e.printStackTrace();
        }
        task.setEndTime(new Date());
        task.setTaskStatus(TaskStatusType.REPORT.getStatus());
        taskRepository.save(task);
    }

    private String meshExecutorSql(String tableType, String executorBatch, String tableName, String increment) {
        String year = executorBatch.substring(0, 4);
        String month = executorBatch.substring(4, 6);
        String day = executorBatch.substring(6);
        switch (tableType.toUpperCase()) {
            case "POLAR_ALL":
                return MessageFormat.format(POLAR_ALL_COUNT_SQL, tableName, increment, executorBatch);
            case "POLAR_DELTA":
            case "POLAR_ONLINE":
                return MessageFormat.format(POLAR_DELTA_TABLE_COUNT_SQL, tableName, increment, executorBatch, increment, executorBatch);
            case "MC_ALL":
            case "MC_DELTA":
            case "MC_STD":
            case "MC_ARCHIVE":
                return MessageFormat.format(MC_PT_SQL, tableName, executorBatch);
            case "MC_BASE":
            case "MC_LOG":
            case "MC_LOG_DELTA":
                return MessageFormat.format(MC_YMD_SQL, tableName, year, month, day);
            default:
                throw new RuntimeException("table match error.");
        }
    }


    @Override
    public void exportReport() {

    }

}
