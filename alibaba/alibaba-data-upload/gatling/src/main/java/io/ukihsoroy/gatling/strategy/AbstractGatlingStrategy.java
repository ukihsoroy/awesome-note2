package io.ukihsoroy.gatling.strategy;

import io.ukihsoroy.gatling.entity.Task;
import io.ukihsoroy.gatling.repository.TaskRepository;
import io.ukihsoroy.gatling.repository.TestingStrategyRepository;
import io.ukihsoroy.gatling.repository.UploadTableRepository;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Map;

/**
 * 策略基础类
 * @author K.O
 */
public abstract class AbstractGatlingStrategy implements IGatlingStrategy {

    @Autowired
    protected TaskRepository taskRepository;

    @Autowired
    protected TestingStrategyRepository testingStrategyRepository;

    @Autowired
    protected UploadTableRepository uploadTableRepository;

    @Autowired
    protected Map<String, JdbcTemplate> jdbcTemplates;


    @Autowired
    protected ObjectMapper objectMapper;

    /**
     * 执行测试
     * @throws Exception
     */
    @Override
    public abstract void executor(Task task);

    /**
     * 导出测试报告
     * @throws Exception
     */
    @Override
    public abstract void exportReport();


}
