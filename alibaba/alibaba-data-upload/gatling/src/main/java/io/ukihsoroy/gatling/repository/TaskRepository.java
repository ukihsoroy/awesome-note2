package io.ukihsoroy.gatling.repository;

import io.ukihsoroy.gatling.entity.Task;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * 任务查询
 * @author K.O
 */
public interface TaskRepository extends PagingAndSortingRepository<Task, Integer> {

    /**
     * 通过批次号和任务状态查找任务
     * @param executorBatch
     * @param taskStatus
     * @param enable
     * @return
     */
    List<Task> findTasksByExecutorBatchAndTaskStatusAndEnable(String executorBatch,
                                                              Integer taskStatus,
                                                              Integer enable);

}
