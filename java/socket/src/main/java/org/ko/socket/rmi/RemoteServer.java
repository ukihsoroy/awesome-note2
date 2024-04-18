package org.ko.socket.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteServer extends Remote {

    public String say() throws RemoteException;

}
