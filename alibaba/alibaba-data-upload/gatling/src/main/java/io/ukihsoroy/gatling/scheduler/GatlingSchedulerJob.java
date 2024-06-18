package io.ukihsoroy.gatling.scheduler;

import io.ukihsoroy.gatling.constants.DatePatternConst;
import io.ukihsoroy.gatling.entity.Task;
import io.ukihsoroy.gatling.entity.TestingStrategy;
import io.ukihsoroy.gatling.entity.UploadTable;
import io.ukihsoroy.gatling.repository.TaskRepository;
import io.ukihsoroy.gatling.repository.TestingStrategyRepository;
import io.ukihsoroy.gatling.repository.UploadTableRepository;
import io.ukihsoroy.gatling.strategy.IGatlingStrategy;
import io.ukihsoroy.gatling.types.EnableType;
import io.ukihsoroy.gatling.types.TaskStatusType;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 定时处理每天任务
 * @author K.O
 */
@Component
public class GatlingSchedulerJob {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TestingStrategyRepository testingStrategyRepository;

    @Autowired
    private UploadTableRepository uploadTableRepository;

    @Autowired
    private Map<String, IGatlingStrategy> gatlingStrategies;

    @Scheduled(cron = "1 * * * * ?")
    public void executor() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.DAY_OF_MONTH, -1);
        String batchNo = DateFormatUtils.format(calendar.getTime(), DatePatternConst.yyyyMMdd);

        List<TestingStrategy> strategies = testingStrategyRepository.findTestingStrategiesByEnable(EnableType.YES.getValue());

        if (!CollectionUtils.isEmpty(strategies)) {
            strategies.forEach(testingStrategy -> {
                String dataSyncType = testingStrategy.getDataSyncType();
                String originTableType = testingStrategy.getOriginTableType();
                String targetTableType = testingStrategy.getTargetTableType();
                List<UploadTable> tables = uploadTableRepository.findUploadTablesByDataSyncTypeAndOriginTableTypeAndTargetTableTypeAndAndEnable(
                        dataSyncType,
                        originTableType,
                        targetTableType,
                        EnableType.YES.getValue());
                if (!CollectionUtils.isEmpty(tables)) {
                    List<Task> tasks = tables.stream().map(uploadTable -> {
                        Task task = new Task();
                        BeanUtils.copyProperties(uploadTable, task);
                        task.setTaskStatus(TaskStatusType.PRE.getStatus());
                        task.setExecutorBatch(batchNo);
                        task.setGmtCreated(new Date());
                        task.setVersion(1);
                        task.setEnable(EnableType.YES.getValue());
                        task.setTestingStrategyName(testingStrategy.getStrategyName());
                        return task;
                    }).collect(Collectors.toList());
                    taskRepository.saveAll(tasks);
                }
            });
        }

        List<Task> tasks = taskRepository.findTasksByExecutorBatchAndTaskStatusAndEnable(batchNo, TaskStatusType.PRE.getStatus(), EnableType.YES.getValue());
        if (!CollectionUtils.isEmpty(tasks)) {
            tasks.forEach(task -> gatlingStrategies.get(task.getTestingStrategyName()).executor(task));
        }

    }

}
