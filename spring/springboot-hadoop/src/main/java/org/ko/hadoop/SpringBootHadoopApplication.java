package org.ko.hadoop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.hadoop.fs.FsShell;

/**
 * 使用spring boot 访问 HDFS
 */
@SpringBootApplication
public class SpringBootHadoopApplication implements CommandLineRunner {

    @Autowired
    private FsShell fsShell;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootHadoopApplication.class, args);
    }

    public void run(String... args) throws Exception {
        fsShell.lsr("/springhdfs").forEach(fs -> System.out.println("> " + fs.getPath()));
    }
}
