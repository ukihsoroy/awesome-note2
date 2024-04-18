package org.ko.netty.t4;

import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

public class T1SocketClient {

    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("localhost", 8080);

        OutputStream out = socket.getOutputStream();
        out.write("数据1".getBytes());
        out.write("数据2".getBytes());
        out.write("数据3".getBytes());

        TimeUnit.SECONDS.sleep(1);

        out.write("数据4".getBytes());
        out.write("数据5".getBytes());

        socket.close();
    }
}
