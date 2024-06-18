package org.ko.jdbc;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Unit test for simple App.
 */
public class JdbcHelperTests {

    @Test
    public void query () {
        String sql = "SELECT ar.id, ar.city_path, ar.city, ar.shorten, ar.create_date FROM art_region ar";
        JdbcHelper helper = JdbcHelper.getInstance();
        PreparedStatement statement = null;
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void update() {
        String sql = "update t_update set count = count + 1 where name = 'ko'";
        JdbcHelper helper = JdbcHelper.getInstance();
        try {
            PreparedStatement preparedStatement = helper.getConnection().prepareStatement(sql);
            preparedStatement.executeUpdate();
            helper.free(null, null, helper.getConnection());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void threadUpdate() throws InterruptedException {
        ExecutorService executorService = Executors.newWorkStealingPool();
        List<Thread> threads = new ArrayList<>();
        String sql = "update t_update set count = count + 1 where class = 'ko'";
        JdbcHelper helper = JdbcHelper.getInstance();
        Connection connection = helper.getConnection();
        for (int i = 0; i < 100; i++) {
            threads.add(new Thread(() -> {
                System.out.println("run---");
                for (int j = 0; j < 100; j++) {
                    try {
                        PreparedStatement preparedStatement = connection.prepareStatement(sql);
                        preparedStatement.executeUpdate();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }));
        }
        helper.free(null, null, helper.getConnection());
        threads.forEach(executorService::submit);

        TimeUnit.SECONDS.sleep(300);
    }
}
