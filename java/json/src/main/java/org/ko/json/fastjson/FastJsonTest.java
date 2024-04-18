package org.ko.json.fastjson;

import com.alibaba.fastjson.JSON;
import org.ko.bean.Person;

import java.util.Map;

public class FastJsonTest {

    public static void main(String[] args) {
        Map<String, String> map = Map.class.cast(JSON.parse("{\"name\": \"K.O\"}"));
        System.out.println(map.get("name"));

        Person person = JSON.parseObject("{\"name\": \"K.O\", \"birthday\": \"2018-01-01\"}", Person.class);
        System.out.println(person.getName());
    }

}

