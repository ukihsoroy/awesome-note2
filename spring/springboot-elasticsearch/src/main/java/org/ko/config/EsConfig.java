package org.ko.config;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Configuration
public class EsConfig {

    @Bean
    public TransportClient client () throws UnknownHostException {

        /*
            Dns地址
            InetAddress-->指向ES POST
            ES TCP 端口
         */
        InetSocketTransportAddress node =
                new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300);

        /*
            指定集群名称
         */
        Settings settings = Settings.builder().put("cluster.name", "ko").build();

        /*
           Settings.EMPTY ES client配置
         */
        TransportClient client = new PreBuiltTransportClient(settings);
        /*
            添加地址
            可以添加很多节点...
         */
        client.addTransportAddress(node);
        return client;

    }
}
