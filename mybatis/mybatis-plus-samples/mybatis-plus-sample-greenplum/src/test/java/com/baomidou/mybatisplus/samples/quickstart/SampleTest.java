package com.baomidou.mybatisplus.samples.quickstart;

import com.baomidou.mybatisplus.samples.quickstart.entity.Sales;
import com.baomidou.mybatisplus.samples.quickstart.mapper.SalesMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SampleTest {

    @Resource
    private SalesMapper salesMapper;

    @Test
    public void testSelect() {
        List<Sales> userList = salesMapper.selectList(null);
        Assert.assertEquals(1, userList.size());
        userList.forEach(System.out::println);
    }

    @Test
    public void testInsert() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date time = simpleDateFormat.parse("2019-01-01");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        for (int i = 1; i < 10000; i++) {
            Sales sales = new Sales();
            sales.setTransId(i);
            sales.setAmount(new BigDecimal("100"));
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            sales.setDate(calendar.getTime());
            sales.setRegion("DL");
            salesMapper.insert(sales);
        }
    }
}
