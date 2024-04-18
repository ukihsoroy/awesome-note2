package org.ko.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * description: Server <br>
 * date: 2020/3/14 15:55 <br>
 *
 * @author K.O <br>
 * @version 1.0 <br>
 */
public class Server {

    private ServerSocket serverSocket;

    public Server(int port) {
        try {
            this.serverSocket = new ServerSocket(port);
            System.out.println("服务器启动成功，端口：" + port);
        } catch (IOException e) {
            System.out.println("服务器启动失败");
        }
    }

    public void start() {
        new Thread(new Runnable() {
            public void run() {
                doStart();
            }
        }).start();
    }

    private void doStart() {
        while (true) {
                Socket client = null;
                try {
                    client = serverSocket.accept();
                    new ClientHandler(client).start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }

}
