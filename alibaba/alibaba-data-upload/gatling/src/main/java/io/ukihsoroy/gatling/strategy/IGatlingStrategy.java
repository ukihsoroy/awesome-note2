package io.ukihsoroy.gatling.strategy;

import io.ukihsoroy.gatling.entity.Task;

/**
 * 策略基础类
 * @author K.O
 */
public interface IGatlingStrategy {

    /**
     * 执行测试
     * @throws Exception
     */
    void executor(Task task);

    /**
     * 导出测试报告
     * @throws Exception
     */
    void exportReport();

}
