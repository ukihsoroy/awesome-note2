package org.ko.rxjava.util;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Date;

public class DateUtils {

    public static final String DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static String nowTime() {
        return DateFormatUtils.format(new Date(), DEFAULT_PATTERN);
    }
}
