package org.ko.socket;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * description: ClientHandler <br>
 * date: 2020/3/14 15:59 <br>
 *
 * @author K.O <br>
 * @version 1.0 <br>
 */
public class ClientHandler {

    public static final int MAX_DATA_LEN = 1024;

    private final Socket socket;

    public ClientHandler(Socket client) {
        socket = client;
    }

    public void start() {
        System.out.println("新客户端接入");
        new Thread(new Runnable() {
            public void run() {
                doStart();
            }
        }).start();
    }

    private void doStart() {
        try {

            InputStream inputStream = socket.getInputStream();

            while (true) {
                byte[] data = new byte[MAX_DATA_LEN];
                int len;
                while ((len = inputStream.read(data)) != -1) {
                    String message = new String(data, 0, len);
                    System.out.println("客户端传来消息：" + message);
                    socket.getOutputStream().write(data);
                }
            }
        } catch (IOException e) {
            System.out.println("error");
        }
    }


}
