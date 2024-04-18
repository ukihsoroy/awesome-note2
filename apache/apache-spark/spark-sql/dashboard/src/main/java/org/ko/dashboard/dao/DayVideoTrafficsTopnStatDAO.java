package org.ko.dashboard.dao;

import org.ko.dashboard.domain.DayVideoTrafficsTopnStat;
import org.ko.dashboard.domain.DayVideoTrafficsTopnStatExample;
import org.ko.dashboard.domain.DayVideoTrafficsTopnStatKey;
import org.springframework.stereotype.Repository;

/**
 * DayVideoTrafficsTopnStatDAO继承基类
 */
@Repository
public interface DayVideoTrafficsTopnStatDAO extends MyBatisBaseDao<DayVideoTrafficsTopnStat, DayVideoTrafficsTopnStatKey, DayVideoTrafficsTopnStatExample> {
}