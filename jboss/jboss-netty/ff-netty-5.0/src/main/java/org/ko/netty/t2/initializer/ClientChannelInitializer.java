package org.ko.netty.t2.initializer;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import org.ko.netty.t2.handler.BaseClientHandler;
import org.ko.netty.t2.handler.HwClientHandler;

public class ClientChannelInitializer extends ChannelInitializer<SocketChannel> {

    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();

        pipeline.addLast("decoder", new StringDecoder());
        pipeline.addLast("encoder", new StringEncoder());

        // 客户端的handler
        //先调用handler在ChannnelActive方法中调用fireChannelActive会激活handler1
        pipeline.addLast("handler", new HwClientHandler());
        pipeline.addLast("handler1", new BaseClientHandler());
    }

}
