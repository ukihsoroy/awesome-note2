package org.ko.dashboard.service;

import org.ko.dashboard.domain.CityTimes;
import org.ko.dashboard.domain.DayVideoAccessTopnStat;
import org.ko.dashboard.domain.DayVideoCityAccessTopnStat;
import org.ko.dashboard.domain.DayVideoTrafficsTopnStat;

import java.util.List;

public interface DayVideoStatService {

    List<DayVideoAccessTopnStat> findDayVideoAccessStat(String day);

    List<DayVideoCityAccessTopnStat> findDayVideoCityStat(String day);

    List<DayVideoTrafficsTopnStat> findDayVideoTrafficsStat(String day);

    List<CityTimes> findDayCityTimes(String day);
}
