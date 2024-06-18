package org.ko.socket.rmi;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RemoteServerImpl extends UnicastRemoteObject implements RemoteServer{

    protected RemoteServerImpl() throws RemoteException {
        super();
    }

    public String say() throws RemoteException {
        return "Server say Hello World!";
    }

    public static void main (String[] args) {
        try {
            RemoteServerImpl remoteServer = new RemoteServerImpl();
            Naming.rebind("Remote Hello", remoteServer);
        } catch (Exception re) {
            re.printStackTrace();
        }
    }
}
