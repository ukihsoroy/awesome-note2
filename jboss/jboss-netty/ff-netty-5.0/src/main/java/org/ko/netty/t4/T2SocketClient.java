package org.ko.netty.t4;

import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

public class T2SocketClient {

    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("localhost", 8080);

        OutputStream out = socket.getOutputStream();

        //滴滴 LBS 应用 地理位置
        //上报GPS位置
        //车牌
        //经度
        //纬度

        //使用http性能消耗太大 所以使用自定义RPC协议
        //http: request body 包太大
        //车牌 + 经度 + 纬度

        String carNo = "辽B4445";
        String lon = "112.9";
        String lat = "28.2";

        String body = carNo + lon + lat;

        //扩大数据量
        for (int i = 0; i < 10; i++) {
            body += body;
        }

        out.write(body.getBytes());

        socket.close();
    }
}
