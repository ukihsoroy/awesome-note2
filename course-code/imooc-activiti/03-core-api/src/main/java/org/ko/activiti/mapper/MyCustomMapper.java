package org.ko.activiti.mapper;

import org.activiti.engine.task.Task;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * description: MyCustomMapper <br>
 * date: 2020/2/16 14:00 <br>
 *
 * @author K.O <br>
 * @version 1.0 <br>
 */
public interface MyCustomMapper {

    @Select("SELECT * FROM ACT_RU_TASK")
    public List<Map<String, Object>> findAll();
}
