package org.ko.dashboard.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.ko.dashboard.domain.CityTimes;
import org.ko.dashboard.domain.DayVideoAccessTopnStat;
import org.ko.dashboard.domain.DayVideoCityAccessTopnStat;
import org.ko.dashboard.domain.DayVideoTrafficsTopnStat;
import org.ko.dashboard.service.DayVideoStatService;
import org.ko.dashboard.support.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Validated
@Api(description = "按天统计")
@RequestMapping("video")
public class DayVideoStatController {


    @Autowired
    private DayVideoStatService dayVideoStatService;

    @GetMapping
    @ApiOperation("视频访问Top N排名")
    public Response<List<DayVideoAccessTopnStat>> findDayVideoAccessStat(
            @ApiParam("查询天数") @RequestParam("day") String day) {
        return new Response<>(dayVideoStatService.findDayVideoAccessStat(day));
    }

    @GetMapping("city")
    @ApiOperation("城市课程点击次数统计")
    public Response<List<DayVideoCityAccessTopnStat>> findDayVideoCityStat(
            @ApiParam("查询天数") @RequestParam("day") String day) {
        return new Response<>(dayVideoStatService.findDayVideoCityStat(day));
    }

    @GetMapping("traffics")
    @ApiOperation("按流量统计视频访问量")
    public Response<List<DayVideoTrafficsTopnStat>> findDayVideoTrafficsStat(
            @ApiParam("查询天数") @RequestParam("day") String day) {
        return new Response<>(dayVideoStatService.findDayVideoTrafficsStat(day));
    }

    @GetMapping("city/times")
    @ApiOperation("统计城市信息")
    public Response<List<CityTimes>> findDayCityTimes(
            @ApiParam("查询天数") @RequestParam("day") String day) {
        return new Response<>(dayVideoStatService.findDayCityTimes(day));
    }
}
