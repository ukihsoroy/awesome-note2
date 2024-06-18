# mybatis-common-mapper

### 1. pom配置

1. 引入**spring boot**, **mybatis-spring**, **tk.mybatis**, **h2测试数据库** 

```xml
<!--Spring boot parent-->
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>1.5.11.RELEASE</version>
    <relativePath/>
</parent>

<dependencies>
    <!--boot 测试包-->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
    </dependency>

    <!--mybatis spring boot starter POMS-->
    <dependency>
        <groupId>org.mybatis</groupId>
        <artifactId>mybatis-spring</artifactId>
        <version>1.3.2</version>
    </dependency>

    <!--tk mybatis common mapper starter POMS-->
    <dependency>
        <groupId>tk.mybatis</groupId>
        <artifactId>mapper-spring-boot-starter</artifactId>
        <version>1.2.3</version>
    </dependency>

    <!--h2 测试数据库-->
    <dependency>
        <groupId>com.h2database</groupId>
        <artifactId>h2</artifactId>
        <scope>runtime</scope>
    </dependency>
</dependencies>
```



### 2. yml配置

```yml
spring:
  datasource:
    schema: import.sql #h2数据库初始化sql
mybatis:
  mapper-locations: classpath:mappers/*.xml #mapper xml path
  type-aliases-package: org.ko.mybatis.domain #开启mybatis alias
  configuration:
    map-underscore-to-camel-case: true #开启自动下划线转驼峰

mapper:
  not-empty: true #
  before: true #
  mappers: tk.mybatis.mapper.common.Mapper #配置的mapper
```



### 3. 定义领域对象和mapper接口

```java
import org.apache.ibatis.type.JdbcType;
import tk.mybatis.mapper.annotation.ColumnType;

import javax.persistence.Id;
import java.io.Serializable;

public class Country implements Serializable {

    private static final long serialVersionUID = 6569081236403751407L;

    @Id
    @ColumnType(jdbcType = JdbcType.BIGINT)
    private Long id;

    private String countryCode;

    private String countryName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
}
```

```java
import org.ko.mybatis.domain.Country;

import java.util.List;

public interface CountryMapper extends tk.mybatis.mapper.common.Mapper<Country> {

    List<Country> findAll();
}
```

- domain域对象主键要指定@Id注解
- mapper接口集成tk.Mapper<T>接口，泛型是域对象



### 4. mapper xml

```xml
<mapper namespace="org.ko.mybatis.mapper.CountryMapper">
    <select id="findAll" resultType="Country">
        select * from country
    </select>
</mapper>
```

- xml中只需要实现自定义接口



### 5. 启动类

```java
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("org.ko.mybatis.mapper")
public class MyBatisApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyBatisApplication.class, args);
    }

}
```

- 启动类中@MapperScan要使用tk.mybatis.spring.annotation.MapperScan重写注解，不能使用mybatis中的注解。


### 6. 测试类

```java
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
```


