package org.ko.mybatis.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.ko.mybatis.entity.Person;
import org.ko.mybatis.service.PersonService;
import org.ko.mybatis.support.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// rest 接口注解
@RestController
// 人类接口请求映射根目录
@RequestMapping("person")
// swagger 定义接口注释
@Api(tags = "这是一个人类接口")
public class PersonController {

    //service 注入
    @Autowired private PersonService personService;

    //request method: post 映射
    @PostMapping
    //swagger 方法注释
    @ApiOperation("新增一个人类")
    public Response<Integer> insertPerson(@ApiParam("这是人类的实体对象") @RequestBody Person person) {
        Integer count = personService.insertPerson(person);
        return new Response<>(count);
    }

    @GetMapping("age")
    @ApiOperation("通过年龄筛选人类")
    public Response<List<Person>> queryPersonByCondition(@ApiParam("传入人类的年龄") @RequestParam Short age) {
        List<Person> people = personService.queryPersonByCondition(age);
        return new Response<>(people);
    }

    /**
     * exception异常mock
     * @return
     */
    @GetMapping("mock/exp")
    public Integer mockExp () {
        return 1 / 0;
    }

}
