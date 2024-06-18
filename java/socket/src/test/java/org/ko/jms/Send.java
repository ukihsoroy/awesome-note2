package org.ko.jms;

import org.junit.Test;
import sun.misc.Queue;

import javax.naming.InitialContext;

public class Send {
    @Test
    public void send () {
        try {
            //1.获取JNDI的上下文
            InitialContext context = new InitialContext();

            //2.获取消息对应的queue
            Queue queue = (Queue)context.lookup("java:commp/env/queue/queue0");

            //3.获取队列的连接工程QueueConnectionFactory
                    
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
