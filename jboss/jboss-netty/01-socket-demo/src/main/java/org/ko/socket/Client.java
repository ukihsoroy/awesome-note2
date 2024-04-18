package org.ko.socket;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

/**
 * description: Client <br>
 * date: 2020/3/14 16:05 <br>
 *
 * @author K.O <br>
 * @version 1.0 <br>
 */
public class Client {

    private static final String HOST = "127.0.0.1";

    private static final int PORT = 8000;

    private static final int SLEEP_TIME = 5000;

    public static void main(String[] args) throws IOException, InterruptedException {
        final Socket socket = new Socket(HOST, PORT);

        new Thread(new Runnable() {
            public void run() {
                System.out.println("客户端启动成功");
                while (true) {
                    try {
                        String message = "hello world.";
                        System.out.println("客户端发送数据：" + message);
                        socket.getOutputStream().write(message.getBytes());
                        TimeUnit.SECONDS.sleep(5);
                    } catch (Exception e) {
                        System.out.println("写入数据出错。");
                    }
                }

            }
        }).start();

        TimeUnit.MINUTES.sleep(5);

    }
}
