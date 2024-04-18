package org.ko.structure.map;


import org.junit.jupiter.api.Test;

public class MapTests {

    @Test
    public void testMapPutAndGet () {
        KOMap<String, String> ko = new KOHashMap<>();
        ko.put("1", "222");
        ko.put("3", "111");
        System.out.println(ko.get("1"));
        System.out.println(ko.get("3"));
    }

}
