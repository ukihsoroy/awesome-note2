package org.ko.json.jackson;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.ko.util.JdbcHelper;

import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JacksonHelperTests {

    private static String FILE_NAME = "city.json";

    @Test
    public void query () {
        String sql = "SELECT ar.id, ar.city_path, ar.city, ar.shorten, ar.create_date FROM art_region ar";
        JdbcHelper helper = JdbcHelper.getInstance();
        PreparedStatement statement;
        try {
            statement = helper.getConnection().prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            List<City> cities = new ArrayList<>();
            while (resultSet.next()) {
                City city = new City();
                city.setId(resultSet.getLong(1));
                city.setCityPath(resultSet.getString(2));
                city.setCity(resultSet.getString(3));
                city.setShorten(resultSet.getString(4));
                city.setCreateDate(resultSet.getDate(5));
                cities.add(city);
            }
            System.out.println(cities.size());
            helper.free(resultSet, statement, helper.getConnection());
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(new File(FILE_NAME), cities);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void jacksonReadJson () {
//        List<City> cities = JacksonHelper.toList(new File("city.json"), City.class);
//        System.out.println(cities.size());
    }

    @Test
    public void formatJson () {
        City city = new City();
        city.setCity("11");
        city.setCityPath("222");
        String json = JacksonHelper.obj2String(city);
        System.out.println(json);
    }

    @Test void whenConverterJsonToMap () {
        String city = "{\n" +
                "  \"id\": 1,\n" +
                "  \"cityPath\": \"中国\",\n" +
                "  \"city\": \"中国\",\n" +
                "  \"shorten\": \"cn\",\n" +
                "  \"createDate\": \"2018-03-08\"\n" +
                "}";
        Map map = JacksonHelper.string2Map(city);
        assert map != null;
        System.out.println(map.get("city"));
    }
}
