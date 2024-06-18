package org.ko.hadoop.hdfs;

import org.apache.commons.lang.ArrayUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.util.Progressable;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URI;

/**
 * Hadoop HDFS Java API 操作
 */
class HDFSApplicationTests {

    private static final Logger _LOGGER = LoggerFactory.getLogger(HDFSApplicationTests.class);

    private FileSystem fileSystem = null;

    private Configuration configuration = null;

    /**
     * <p>初始化HDFS</p>
     * @throws Exception
     */
    @BeforeEach void setUp () throws Exception {
        _LOGGER.info("HDFSApplicationTests#setUp");
        configuration = new Configuration();
        //修改host文件
        URI uri = new URI("hdfs://ko-hadoop:8020");
        fileSystem = FileSystem.get(uri, configuration, "K.O");
    }

    /**
     * <p>注销HDFS</p>
     * @throws Exception
     */
    @AfterEach void tearDown () throws Exception {
        configuration = null;
        fileSystem = null;
        _LOGGER.info("HDFSApplicationTests#tearDown");
    }

    /**
     * <p>创建文件夹</p>
     * @throws IOException
     */
    @Test void mkdir () throws IOException {
        fileSystem.mkdirs(new Path("/hdfsapi/test"));
    }

    /**
     * <p>创建文件</p>
     * <p>注意DataNode端口是否开启</p>
     * @throws IOException
     */
    @Test void createFile () throws IOException {
        FSDataOutputStream outputStream = fileSystem.create(new Path("/hdfsapi/test/a.txt"));
        outputStream.write("Hello Hadoop!".getBytes());
        outputStream.flush();
        outputStream.close();
    }

    /**
     * <p>读取hdfs中文件数据</p>
     * @throws Exception
     */
    @Test void cat () throws Exception {
        FSDataInputStream inputStream = fileSystem.open(new Path("/hdfsapi/test/a.txt"));
        byte[] b = new byte[1024];
        inputStream.read(b);
        inputStream.close();
        _LOGGER.info("HDFSApplicationTests#cat: {}", new String(b));
    }

    /**
     * <p>重命名文件</p>
     * @throws Exception
     */
    @Test void rename () throws Exception {
        Path oldPath = new Path("/hdfsapi/test/a.txt");
        Path newPath = new Path("/hdfsapi/test/b.txt");
        boolean success = fileSystem.rename(oldPath, newPath);
        assert success;
    }

    /**
     * <p>上传本地文件到HDFS服务器</p>
     * @throws Exception
     */
    @Test void copyFromLocalFile () throws Exception {
        Path localPath = new Path("D:/tmp/hadoop.txt");
        Path hdfsPath = new Path("/");
        fileSystem.copyFromLocalFile(localPath, hdfsPath);
    }

    /**
     * <p>上传本地文件到HDFS服务器, 带进度条</p>
     * @throws Exception
     */
    @Test void copyFromLocalFileWithProgress () throws Exception {
        InputStream in = new BufferedInputStream(
                new FileInputStream(
                        new File("D:/install/VMware-workstation-full-14.1.1-7528167.exe")));

        FSDataOutputStream out =
                fileSystem.create(new Path("/hdfsapi/test/vmware-14.exe"), new Progressable() {
                    public void progress() {
                        System.out.print("-"); //带进度提醒信息
                    }
                });

        IOUtils.copyBytes(in, out, 4096);
    }

    /**
     * <p>下载HDFS文件</p>
     * @throws Exception
     */
    @Test void copyToLocalFile () throws Exception {
        Path hdfsPath = new Path("/hdfsapi/test/b.txt");
        Path localPath = new Path("D:/tmp/h.txt");
        fileSystem.copyToLocalFile(hdfsPath, localPath);
    }

    /**
     * <p>打印HDFS文件列表</p>
     * @throws Exception
     */
    @Test void listFiles () throws Exception {
        FileStatus[] fileStatuses = fileSystem.listStatus(new Path("/hdfsapi/test"));
        if (ArrayUtils.isNotEmpty(fileStatuses)) {
            for (FileStatus fileStatus : fileStatuses) {
                //1. 是否是文件夹
                String isDir = fileStatus.isDirectory() ? "文件夹" : "文件";
                //2. 有几个副本
                short replication = fileStatus.getReplication();
                //3. 文件大小
                long len = fileStatus.getLen();
                //4. 全路径
                String path = fileStatus.getPath().toString();

                //5. 输出
                _LOGGER.info("文件夹还是文件? {}", isDir);
                _LOGGER.info("有{}个副本.", replication);
                _LOGGER.info("文件大小: {}kb", len);
                _LOGGER.info("全路径: {}", path);
            }
        }
    }

    /**
     * <p>删除HDFS文件</p>
     * @throws Exception
     */
    @Test void delete () throws Exception {
        boolean success = fileSystem.delete(new Path("/hdfsapi/test"), true);
        assert success;
    }

}
