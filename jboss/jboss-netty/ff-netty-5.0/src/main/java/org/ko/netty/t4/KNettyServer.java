package org.ko.netty.t4;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.ko.netty.t4.handler.KDecode;
import org.ko.netty.t4.handler.KHandler;

public class KNettyServer {

    public static void main(String[] args) throws Exception {
        //1- 线程定义

        //---两个线程 1-处理连接
        EventLoopGroup acceptGroup = new NioEventLoopGroup();

        //---两个线程 2-read io 处理数据的线程池
        EventLoopGroup readGroup = new NioEventLoopGroup();

        try {
            //server 引导器创建服务端
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(acceptGroup, readGroup);
            //添加管道
            bootstrap.channel(NioServerSocketChannel.class);

            //2- 职责链定义 一个请求交给谁去处理
            bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    //责任链
                    ChannelPipeline pipeline = socketChannel.pipeline();
                    //解码器
                    pipeline.addLast(new KDecode());
                    //来一个请求打印出内容 需要一个handler
                    pipeline.addLast("", new KHandler());
                }
            });

            //3- 绑定端口
            System.out.println("bind 8080");
            bootstrap.bind(8080).sync().channel().closeFuture().sync();

        }finally {
//            acceptGroup.shutdownGracefully();
//            readGroup.shutdownGracefully();
        }






    }
}
