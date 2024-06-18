package org.ko.hadoop.spring;

import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * 使用spring hadoop来访问HDFS文件系统
 */
public class SpringHadoopTests {

    private static final Logger _LOGGER = LoggerFactory.getLogger(SpringHadoopTests.class);

    private ApplicationContext context;

    private FileSystem fileSystem;

    /**
     * 创建hdfs文件夹
     * @throws Exception
     */
    @Test void mkdir () throws Exception {
        fileSystem.mkdirs(new Path("/springhdfs"));
    }


    /**
     * <p>读取hdfs中文件数据</p>
     * @throws Exception
     */
    @Test void cat () throws Exception {
        FSDataInputStream inputStream = fileSystem.open(new Path("/springhdfs/hello.txt"));
        byte[] b = new byte[1024];
        inputStream.read(b);
        inputStream.close();
        _LOGGER.info("SpringHadoopTests#cat: {}", new String(b));
    }



    @BeforeEach void setUp () {
        context = new ClassPathXmlApplicationContext("spring-hadoop.xml");
        fileSystem = context.getBean(FileSystem.class);
    }

    @AfterEach void tearDown () throws IOException {
        context = null;
        fileSystem.close();
    }
}
