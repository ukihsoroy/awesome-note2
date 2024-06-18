package org.ko.mybatis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.ko.mybatis.domain.Country;
import org.ko.mybatis.mapper.CountryMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MapperTest {

    private static final Logger _LOGGER = LoggerFactory.getLogger(MapperTest.class);

    @Autowired private CountryMapper countryMapper;

    @Test
    public void whenQueryCountrySuccess () {
        List<Country> countryList = countryMapper.selectAll();
        _LOGGER.info("country count: {}", countryList.size());
        assert countryList.size() > 0;
    }

    @Test
    public void whenInsertCountrySuccess () {
        //1. 插入
        Country country = new Country();
        country.setId(133337L);
        country.setCountryCode("DL");
        country.setCountryName("大连");
        int ret = countryMapper.insert(country);
        _LOGGER.info("insert country result: {}", ret);
        assert ret > 0;

        //2. 查询
        Country c = countryMapper.selectByPrimaryKey(133337L);
        _LOGGER.info("insert country name: {}", c.getCountryName());
    }

    @Test
    public void whenUpdateCountrySuccess () {
        //1. 更新
        Country country = new Country();
        country.setId(133337L);
        country.setCountryCode("DL");
        country.setCountryName("美丽的海滨城市大连");
        int ret = countryMapper.updateByPrimaryKeySelective(country);
        assert ret > 0;
        _LOGGER.info("update country result: {}", ret);

        //2. 查询
        Country c = countryMapper.selectByPrimaryKey(133337L);
        _LOGGER.info("update country name: {}", c.getCountryName());
    }

    @Test
    public void whenDeleteCountrySuccess () {
        //1. 删除
        int ret = countryMapper.deleteByPrimaryKey(1L);
        assert ret > 0;

        //2. 查询 183
        List<Country> countryList = countryMapper.selectAll();
        _LOGGER.info("country count: {}", countryList.size());
        assert countryList.size() == 182;
    }

    @Test
    public void whenSelectByExampleSuccess () {
        Example e = new Example(Country.class);
        e.createCriteria()
                .andLessThanOrEqualTo("id", 100L)
                .andGreaterThan("id", 50L);
        List<Country> countries = countryMapper.selectByExample(e);
        _LOGGER.info("select by example result count: {}", countries.size());
        assert countries.size() == 50;
    }
}
