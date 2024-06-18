package org.ko.concurrency.inmutable;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.ko.concurrency.annoations.NotThreadSafe;
import org.ko.concurrency.annoations.ThreadSafe;

import java.util.Collections;
import java.util.Map;

@ThreadSafe
@Slf4j
public class ImmutableExample2 {

    private static Map<Integer, Integer> map = Maps.newHashMap();

    static {
        map.put(1, 2);
        map.put(3, 4);
        map.put(5, 6);
        map = Collections.unmodifiableMap(map);
    }

    public static void main(String[] args) {
        map.put(1, 3);
        log.info("map(1): {}", map.get(1));

    }

}
