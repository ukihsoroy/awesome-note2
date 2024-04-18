package com.panhai.sys.utils;

import java.util.UUID;

public final class UUIDUtil {

    private UUIDUtil() {}

    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
