package org.ko.zookeeper.zkclient;

import org.junit.jupiter.api.Test;
import org.ko.zookeeper.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.List;

public class ZkClientCrudTests {

    final static Logger logger = LoggerFactory.getLogger(ZkClientCrudTests.class);

    @Test
    void testZkClientCrud() {
        ZkClientCrud<User> zkClientCrud = new ZkClientCrud<>(new SimpleZkSerializer(), User.class);
        String path="/root";
        zkClientCrud.deleteRecursive(path);
        zkClientCrud.createPersistent(path,"hi");
     /*  zkClientCrud.createPersistent(path+"/a/b/c",true);//递归创建 但是不能设在value
       //zkClientCrud.createPersistent(path,"hi");
        logger.info(zkClientCrud.readData(path));
        //更新
        zkClientCrud.writeData(path,"hello");
        logger.info(zkClientCrud.readData(path));
        logger.info(String.valueOf(zkClientCrud.getChildren(path)));
        //子节点
        List<String> list=zkClientCrud.getChildren(path);
        for(String child:list){
            logger.info("子节点:"+child);
        }*/

        User user = new User();
        user.setId(1);
        user.setName("K.O");
        user.setAge(18);
        user.setEmail("ko.shen@hotmail.com");
        zkClientCrud.writeData(path, user);
        System.out.println(zkClientCrud.readData(path).getName());;
    }


    @Test void testIsNumber() {
        System.out.println(isNumber("123a"));
    }

    @Test
    public void testWriteZk() {
        ZkClientCrud<String> zkClientCrud = new ZkClientCrud<>(new SimpleZkSerializer(), String.class);
        zkClientCrud.createPersistent("/root/servers/service3", "1");
//        zkClientCrud.createPersistent("/root/servers/service2", "2");
    }

    @Test
    public void testGetChilds() {
        ZkClientCrud<String> zkClientCrud = new ZkClientCrud<>(new SimpleZkSerializer(), String.class);
        List<String> children = zkClientCrud.getChildren("/root/servers");
        children.forEach(System.out::println);
    }

    private boolean isNumber(String n) {
        try {
            new BigDecimal(n);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

}
