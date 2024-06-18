package org.ko.web.chapter2;

import org.ko.web.chapter2.asm.Metaspace;
import org.ko.web.chapter2.bean.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 内存溢出
 */
@RestController
public class MemoryController {

    private List<User> users = new ArrayList<>();
    private List<Class<?>> classes = new ArrayList<>();

    /**
     * -Xmx32M  -Xms32M
     * @return
     */
    @GetMapping("heap")
    public String heap() {
        int i = 0;
        for (;;) {
            users.add(new User(i++, UUID.randomUUID().toString()));
        }
    }

    /**
     * -XX:MetaspaceSize=32M -XX:MaxMetaspaceSize=32M
     * @return
     */
    @GetMapping("nonheap")
    public String nonheap() {
        for (;;) {
            classes.addAll(Metaspace.createClasses());
        }
    }

}
