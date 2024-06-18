package io.ukihsoroy.gatling.strategy;

import io.ukihsoroy.gatling.constants.DatePatternConst;
import io.ukihsoroy.gatling.entity.Task;
import io.ukihsoroy.gatling.types.TaskStatusType;
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
import java.util.Map;

/**
 * 对分区分组查看分区是否正常写入
 * @author K.O
 */
@Component
public class CountGroupPTGatlingStrategy extends AbstractGatlingStrategy {

    private Logger logger = LoggerFactory.getLogger(CountGroupPTGatlingStrategy.class);

    private static final String COUNT_GROUP_PT_NAME = "countGroupPTGatlingStrategy";

    private static final String MC_GROUP_PT_SQL = "SELECT pt as pt, COUNT(1) FROM {0} WHERE pt is not null GROUP BY pt ORDER BY pt";

    private static final String MC_GROUP_YMD_SQL = "SELECT CONCAT(year, month, day) pt, COUNT(1) FROM {0} WHERE year IS NOT NULL GROUP BY year, month, day ORDER BY year, month, day";

    @Override
    public void executor(Task task) {
        //保存为测试中
        task.setStartTime(new Date());
        task.setTaskStatus(TaskStatusType.TESTING.getStatus());
        taskRepository.save(task);

        //获取jdbc client
        JdbcTemplate originJdbcTemplate = jdbcTemplates.get(task.getOriginDatasourceName());

        //构建查询sql
        String originSql = meshExecutorSql(task.getOriginTableType(), task.getOriginTableName());

        //查询数据条目
        List<Map<String, Object>> maps = originJdbcTemplate.queryForList(originSql);

        //比较结果
        try {
            String report = objectMapper.writeValueAsString(maps);
            task.setTaskReport(report);
        } catch (IOException e) {
            e.printStackTrace();
        }
        task.setTaskStatus(TaskStatusType.REPORT.getStatus());
        task.setEndTime(new Date());
        taskRepository.save(task);
    }

    private String meshExecutorSql(String tableType, String tableName) {
        switch (tableType.toUpperCase()) {
            case "MC_ALL":
            case "MC_DELTA":
            case "MC_STD":
            case "MC_ARCHIVE":
                return MessageFormat.format(MC_GROUP_PT_SQL, tableName);
            case "MC_BASE":
            case "MC_LOG":
            case "MC_LOG_DELTA":
                return MessageFormat.format(MC_GROUP_YMD_SQL, tableName);
            default:
                throw new RuntimeException("table match error.");
        }
    }

    @Override
    public void exportReport() {

    }
}
