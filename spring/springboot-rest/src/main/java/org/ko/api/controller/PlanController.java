package org.ko.api.controller;

import org.ko.api.bo.PlanBo;
import org.ko.api.command.PlanCommand;
import org.ko.api.entity.Plan;
import com.panhai.common.model.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.ko.api.service.PlanService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.util.List;


@Api(value = "PlanController", description = "计划接口")
@RestController
@RequestMapping("plan")
public class PlanController {

    @Autowired private PlanService planService;

    @GetMapping
    @ApiOperation("查询列表")
    public Result<List<PlanBo>> list (
        @ApiParam("查询列表参数") @ModelAttribute PlanCommand command,
        @ApiParam(value = "页数") @RequestParam(value = "page", required = false, defaultValue = "1") int page,
        @ApiParam(value = "条数") @RequestParam(value = "limit", required = false, defaultValue = "10") int limit) {
        return planService.list(command, page, limit);
    }

    @GetMapping("{id}")
    @ApiOperation("通过ID获取详情")
    public Result<PlanBo> detail (
        @ApiParam("申报单ID") @PathVariable("id") String id) {
        return planService.detail(id);
    }

    @PostMapping
    @ApiOperation("新增计划")
    public Result save (
        @ApiParam("申报单业务对象") @RequestBody PlanBo planBo) {
        return planService.save(planBo);
    }

    @PutMapping
    @ApiOperation("更新计划")
    public Result update (
        @ApiParam("申报单业务对象") @RequestBody PlanBo planBo) {
        return planService.update(planBo);
    }

    @DeleteMapping("{id}")
    @ApiOperation("通过ID删除")
    public Result remove (
        @ApiParam("申报单ID") @PathVariable("id") String id,
        @ApiParam("版本号") @RequestParam("versionN") @NotBlank(message = "版本号不能为空") Long versionN) {
        return planService.remove(id, versionN);
    }

    @GetMapping("export")
    @ApiOperation("导出计划Excel")
    public void export (@ModelAttribute PlanCommand command) {
        planService.export(command);
    }

}