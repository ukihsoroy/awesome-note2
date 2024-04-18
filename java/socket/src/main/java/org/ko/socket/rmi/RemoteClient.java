package org.ko.socket.rmi;

import java.rmi.Naming;

public class RemoteClient {

    public void go () {
        try {
            RemoteServerImpl remoteServer = RemoteServerImpl.class
                    .cast(Naming.lookup("rmi://127.0.0.1/Remote Hello"));
            String say = remoteServer.say();
            System.out.print(say);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main (String[] args) {
        new RemoteClient().go();
    }
}
