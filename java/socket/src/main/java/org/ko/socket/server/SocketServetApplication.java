package org.ko.socket.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServetApplication {
    public static void main (String[] args) {
        try {
            //创建服务端
            ServerSocket server = new ServerSocket(4242);

            while (true) {
                //获取等待连接的客户端
                Socket socket = server.accept();
                //获取客户端IP Host
                String host = socket.getInetAddress().getHostAddress();
                //获取客户端端口号Port
                int port = socket.getLocalPort();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String line = null;
                while ((line = bufferedReader.readLine()) != null) {

                    System.out.println(line);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
