package org.ko.socket;


/**
 * description: Server <br>
 * date: 2020/3/14 15:54 <br>
 *
 * @author K.O <br>
 * @version 1.0 <br>
 */
public class ServerRoot {

    private static final int PORT = 8000;

    public static void main(String[] args) {
        Server server = new Server(PORT);
        server.start();
    }
}
