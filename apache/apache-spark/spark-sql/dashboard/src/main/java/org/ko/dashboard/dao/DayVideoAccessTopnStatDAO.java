package org.ko.dashboard.dao;

import org.ko.dashboard.domain.DayVideoAccessTopnStat;
import org.ko.dashboard.domain.DayVideoAccessTopnStatExample;
import org.ko.dashboard.domain.DayVideoAccessTopnStatKey;
import org.springframework.stereotype.Repository;

/**
 * DayVideoAccessTopnStatDAO继承基类
 */
@Repository
public interface DayVideoAccessTopnStatDAO extends MyBatisBaseDao<DayVideoAccessTopnStat, DayVideoAccessTopnStatKey, DayVideoAccessTopnStatExample> {
}