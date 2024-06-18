package org.ko.netty.t4.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.ko.netty.t4.bean.GpsInfo;

/**
 * 业务处理Handler
 */
public class KHandler extends ChannelInboundHandlerAdapter {


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //读取数据
//        ByteBuf request = ByteBuf.class.cast(msg);
//        byte[] bytes = new byte[request.readableBytes()];
//        request.readBytes(bytes);
//        System.out.println("收到数据：" + new String(bytes));

        GpsInfo gpsInfo = GpsInfo.class.cast(msg);
        System.out.println("车牌号: " + gpsInfo.getCatNo());



    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        super.channelReadComplete(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        ctx.close();
    }
}
