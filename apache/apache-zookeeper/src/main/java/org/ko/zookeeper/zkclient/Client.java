package org.ko.zookeeper.zkclient;

import org.I0Itec.zkclient.ZkClient;

public class Client {

    public static void main(String[] args) {
        ZkClient zkClient = new ZkClient("127.0.0.1:2181", 100000);
        zkClient.writeData("/zktest", "Hello, World!");
        String b = zkClient.readData("/zktest");
        System.out.println(b);
    }

}
