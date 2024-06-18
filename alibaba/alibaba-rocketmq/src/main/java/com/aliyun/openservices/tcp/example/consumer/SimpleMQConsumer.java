/**
 * Copyright (C) 2010-2016 Alibaba Group Holding Limited
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.aliyun.openservices.tcp.example.consumer;

import java.util.Properties;

import com.aliyun.openservices.ons.api.Consumer;
import com.aliyun.openservices.ons.api.ONSFactory;
import com.aliyun.openservices.ons.api.PropertyKeyConst;
import com.aliyun.openservices.tcp.example.MqConfig;
import com.aliyun.openservices.ons.api.PropertyValueConst;

/**
 * MQ 接收消息示例 Demo
 */
public class SimpleMQConsumer implements Runnable{

    public static void main(String[] args) {
        int i = 0;
        while (i < 10) {
            new Thread(new SimpleMQConsumer()).start();
            i ++;
        }

        //等待固定时间防止进程退出
        try {
            Thread.sleep(200000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        Properties consumerProperties = new Properties();
        consumerProperties.setProperty(PropertyKeyConst.GROUP_ID, MqConfig.GROUP_ID);
        consumerProperties.setProperty(PropertyKeyConst.AccessKey, MqConfig.ACCESS_KEY);
        consumerProperties.setProperty(PropertyKeyConst.SecretKey, MqConfig.SECRET_KEY);
        consumerProperties.setProperty(PropertyKeyConst.NAMESRV_ADDR, MqConfig.NAMESRV_ADDR);
//        consumerProperties.setProperty(PropertyKeyConst.MessageModel, PropertyValueConst.BROADCASTING);
        Consumer consumer = ONSFactory.createConsumer(consumerProperties);
        consumer.subscribe(MqConfig.TOPIC, MqConfig.TAG, new MessageListenerImpl());
        consumer.start();
        System.out.println(Thread.currentThread().getName() + " Consumer start success.");
    }
}
