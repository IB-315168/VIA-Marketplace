package com.sep2zg4.viamarket.client.model.comm;

import com.sep2zg4.viamarket.server.RemoteMarketplace;

import java.rmi.NoSuchObjectException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ClientMarketplaceCommunicator extends UnicastRemoteObject
{
  private RemoteMarketplace communicator;

  public ClientMarketplaceCommunicator(String host, int port) throws
      RemoteException
  {
    try {
      connect(host, port);
    } catch (Exception e) {
      close();
    }
  }

  private void connect(String host, int port)
      throws RemoteException, NotBoundException
  {
    Registry registry = LocateRegistry.getRegistry(host, port);
    communicator = (RemoteMarketplace) registry.lookup("comm");
  }

  public boolean login(String username, String password) throws RemoteException
  {
    return communicator.login(username, password);
  }

  public void close() throws NoSuchObjectException
  {
    UnicastRemoteObject.unexportObject(this, true);
  }
}
