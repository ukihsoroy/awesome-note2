package org.ko.zookeeper.zkclient;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

public class ZkTests {

    private ZkClient zk = new ZkClient("127.0.0.1:2181");;

    private String nodeName = "/myApp";

    @Test
    public void testListener() throws InterruptedException {
        //监听指定节点的数据变化

        zk.subscribeDataChanges(nodeName, new IZkDataListener() {
            @Override
            public void handleDataChange(String s, Object o) throws Exception {
                System.out.println("node data changed!");
                System.out.println("node=>" + s);
                System.out.println("data=>" + o);
                System.out.println("--------------");
            }

            @Override
            public void handleDataDeleted(String s) throws Exception {
                System.out.println("node data deleted!");
                System.out.println("s=>" + s);
                System.out.println("--------------");

            }
        });

        System.out.println("ready!");

        //junit测试时，防止线程退出
        while (true) {
            TimeUnit.SECONDS.sleep(5);
        }
    }


    @Test
    public void testUpdateConfig() throws InterruptedException {
        if (!zk.exists(nodeName)) {
            zk.createPersistent(nodeName);
        }
        for (int i = 0; i < 10; i++) {
            zk.writeData(nodeName, i);
            TimeUnit.SECONDS.sleep(1);
        }
//        zk.delete(nodeName);
//        zk.delete(nodeName);//删除一个不存在的node，并不会报错
    }

}
