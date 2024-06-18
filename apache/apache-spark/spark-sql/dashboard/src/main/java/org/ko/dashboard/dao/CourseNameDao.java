package org.ko.dashboard.dao;

import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class CourseNameDao {

    private Map<String, String> contains = new ConcurrentHashMap<>();

    {
        contains.put("2402", "与MySQL的零距离接触");
        contains.put("1309", "WEB在线文件管理器");
        contains.put("3078", "Java Socket应用---通信是这样练成的");
        contains.put("2801", "Kafka流处理平台");
        contains.put("1336", "Spring Boot 发送邮件");
    }

    public String getCourseName (String courseId) {
        if (contains.containsKey(courseId)) {
            return contains.get(courseId);
        }
        return "";
    }
}
