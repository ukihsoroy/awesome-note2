package org.ko.netty.t1;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.ko.netty.t1.initializer.ClientChannelInitializer;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class HelloWorldClient {

    private  int port;
    private  String address;

    public HelloWorldClient(int port,String address) {
        this.port = port;
        this.address = address;
    }

    public void start(){
        EventLoopGroup group = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .handler(new ClientChannelInitializer());

        try {
            Channel channel = bootstrap.connect(address,port).sync().channel();
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            for(;;){
                String msg = reader.readLine();
                if(msg == null){
                    continue;
                }
                channel.writeAndFlush(msg + "\r\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            group.shutdownGracefully();
        }

    }

    public static void main(String[] args) {
        HelloWorldClient client = new HelloWorldClient(7788,"127.0.0.1");
        client.start();
    }
}