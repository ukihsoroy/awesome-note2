package org.ko.dashboard.service.impl;

import com.github.pagehelper.PageHelper;
import org.ko.dashboard.dao.CourseNameDao;
import org.ko.dashboard.dao.DayVideoAccessTopnStatDAO;
import org.ko.dashboard.dao.DayVideoCityAccessTopnStatDAO;
import org.ko.dashboard.dao.DayVideoTrafficsTopnStatDAO;
import org.ko.dashboard.domain.*;
import org.ko.dashboard.dto.DayVideoAccessTopNStatDTO;
import org.ko.dashboard.service.DayVideoStatService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(rollbackFor = Throwable.class)
public class DayVideoStatServiceImpl implements DayVideoStatService {

    @Autowired
    private DayVideoAccessTopnStatDAO dayVideoAccessTopnStatDAO;

    @Autowired
    private DayVideoCityAccessTopnStatDAO dayVideoCityAccessTopnStatDAO;

    @Autowired
    private DayVideoTrafficsTopnStatDAO dayVideoTrafficsTopnStatDAO;

    @Autowired
    private CourseNameDao courseNameDao;


    @Override
    public List<DayVideoAccessTopnStat> findDayVideoAccessStat(String day) {
        DayVideoAccessTopnStatExample example = new DayVideoAccessTopnStatExample();
        example.createCriteria()
                .andDayEqualTo(day);
        example.setOrderByClause("times desc");
        PageHelper.startPage(1, 5);
        List<DayVideoAccessTopnStat> topnStats = dayVideoAccessTopnStatDAO.selectByExample(example);
        if (!CollectionUtils.isEmpty(topnStats)) {
            return topnStats.stream().map(t -> {
                DayVideoAccessTopNStatDTO topnStatDTO = new DayVideoAccessTopNStatDTO();
                BeanUtils.copyProperties(t, topnStatDTO);
                topnStatDTO.setCourseName(courseNameDao.getCourseName(String.valueOf(t.getCmsId())));
                return topnStatDTO;
            }).collect(Collectors.toList());
        }
        return topnStats;
    }

    @Override
    public List<DayVideoCityAccessTopnStat> findDayVideoCityStat(String day) {
        return dayVideoCityAccessTopnStatDAO.selectByExample(new DayVideoCityAccessTopnStatExample());
    }

    @Override
    public List<DayVideoTrafficsTopnStat> findDayVideoTrafficsStat(String day) {
        return dayVideoTrafficsTopnStatDAO.selectByExample(new DayVideoTrafficsTopnStatExample());
    }

    @Override
    public List<CityTimes> findDayCityTimes(String day) {
        return dayVideoCityAccessTopnStatDAO.findCityTimes(day);
    }
}
