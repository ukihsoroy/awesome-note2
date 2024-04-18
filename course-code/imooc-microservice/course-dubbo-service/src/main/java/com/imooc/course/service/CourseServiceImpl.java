package com.imooc.course.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.imooc.course.dto.CourseDTO;
import com.imooc.course.entity.Course;
import com.imooc.course.thrift.ServiceProvider;
import com.imooc.thrift.user.UserInfo;
import com.imooc.thrift.user.dto.TeacherDTO;
import org.apache.thrift.TException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * description: CourseServiceImpl <br>
 * date: 2020/3/1 18:17 <br>
 *
 * @author K.O <br>
 * @version 1.0 <br>
 */
@Service(version = "1.0.0")
public class CourseServiceImpl implements ICourseService {

    @Autowired
    private ServiceProvider serviceProvider;

    private Map<Integer, Course> table = new ConcurrentHashMap<>();

    public CourseServiceImpl() {
        Course course1 = new Course();
        course1.setId(1);
        course1.setTitle("java");
        course1.setDescription("java desc.");
        course1.setTeacherId(1);

        Course course2 = new Course();
        course2.setId(2);
        course2.setTitle("python");
        course2.setDescription("python desc.");
        course2.setTeacherId(1);

        Course course3 = new Course();
        course3.setId(3);
        course3.setTitle("golang");
        course3.setDescription("golang desc.");
        course3.setTeacherId(1);

        table.put(1, course1);
        table.put(2, course2);
        table.put(3, course3);
    }

    @Override
    public List<CourseDTO> courseList() {
        List<CourseDTO> courseDTOS = new ArrayList<>();
        for (Map.Entry<Integer, Course> courseEntry : table.entrySet()) {
            Course course = courseEntry.getValue();
            try {
                UserInfo userInfo = serviceProvider.getUserService().getUserById(course.getTeacherId());
                TeacherDTO teacherDTO = new TeacherDTO();
                BeanUtils.copyProperties(userInfo, teacherDTO);
                teacherDTO.setIntro("intro");
                teacherDTO.setStars(5);
                CourseDTO courseDTO = new CourseDTO();
                BeanUtils.copyProperties(course, courseDTO);
                courseDTO.setTeacher(teacherDTO);
                courseDTOS.add(courseDTO);
            } catch (TException e) {
                e.printStackTrace();
            }
        }
        return courseDTOS;
    }
}
