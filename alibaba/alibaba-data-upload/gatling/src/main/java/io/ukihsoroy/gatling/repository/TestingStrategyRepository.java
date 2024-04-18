package io.ukihsoroy.gatling.repository;

import io.ukihsoroy.gatling.entity.TestingStrategy;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * 测试策略
 * @author K.O
 */
public interface TestingStrategyRepository extends PagingAndSortingRepository<TestingStrategy, Integer> {

    /**
     * 通过查询全部的任务
     * @param enable
     * @return
     */
    List<TestingStrategy> findTestingStrategiesByEnable(Integer enable);


}
