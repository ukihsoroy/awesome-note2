package org.ko.netty.t4.handler;


import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.ko.netty.t4.bean.GpsInfo;

import java.util.List;

/**
 * 解码器
 */
public class KDecode extends ByteToMessageDecoder {

    /**
     * 数据缓冲区
     */
    private ByteBuf msgCache = Unpooled.buffer();

    /**
     *
     * @param channelHandlerContext
     * @param byteBuf 接受到的数据
     * @param list 处理后的数据
     * @throws Exception
     */
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        // 逻辑性强 伪代码
        // 1. 解析正常情况-辽B6666112.928.2
        // 2. 异常-粘包-辽B6666112.928.2辽B6666112.928.2
        // 3. 异常-拆包-辽B6666112.928.2辽B6666112.928.2辽B6
        // 666112.928.2

        // 消息数据不够的时候-缓冲起来, 等待下一个数据再合并
        // 数据有多余的时候, 要把多余的数据存起来, 等待下一个数据再合并

        // 1- 合并
        //接受合并
        ByteBuf message;
        int size = msgCache.readableBytes();

        //有缓冲
        if (size > 0) {
            //合并
            message = Unpooled.buffer();
            message.writeBytes(msgCache);
            message.writeBytes(byteBuf);
        } else {
            message = byteBuf;
        }

        // 2- 解析
        //辽B6666112.928.2
        //长度固定
        //辽B6666-->7字节
        //112.9--->5字节
        //28.2--->4字节
        int s = message.readableBytes();
        int counter = s / 17;

        for (int i = 0; i < counter; i++) {

            GpsInfo gpsInfo = new GpsInfo();

            //车牌
            byte[] carNo = new byte[8];
            message.readBytes(carNo);
            gpsInfo.setCatNo(new String(carNo));

            //经度
            byte[] lon = new byte[5];
            message.readBytes(lon);
            gpsInfo.setLon(new String(lon));

            //纬度
            byte[] lat = new byte[4];
            message.readBytes(lat);
            gpsInfo.setLat(new String(lat));

            list.add(gpsInfo);
        }


        // 3- 缓冲
        // 剩下的数据缓冲起来
        int msgCount = message.readableBytes();

        if (msgCount > 0) {
            System.out.println("多余的数据" + msgCount);
            msgCache.clear();
            msgCache.writeBytes(message);
        }






    }

}
