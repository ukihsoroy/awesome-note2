package org.ko.api.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.ko.api.bo.PlanBo;
import org.ko.api.command.PlanCommand;
import org.ko.api.entity.Plan;
import org.springframework.stereotype.Repository;
import org.ko.api.dao.PlanMapper;
import java.util.List;

@Repository
@Mapper
public interface PlanRepository extends PlanMapper{

    /**
     * 查询全部数据
     * @param command select param
     * @return 全部任务数据
     */
    List<PlanBo> queryList(PlanCommand command);

    /**
     * 查询详细数据
     * @param id 查询
     * @return 详细数据
     */
    PlanBo queryDetail (@Param("id") String id);

    /**
     * 插入集合
     * @param plans plan list
     * @return insert count
     */
    int insertList(List<Plan> plans);

}