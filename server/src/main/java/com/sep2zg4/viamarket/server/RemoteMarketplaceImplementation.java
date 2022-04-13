package com.sep2zg4.viamarket.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RemoteMarketplaceImplementation extends UnicastRemoteObject implements
    RemoteMarketplace
{
  public RemoteMarketplaceImplementation() throws RemoteException
  {

  }

  public boolean login(String username, String password) throws RemoteException
  {
    if(username.equals("admin") && password.equals("admin")) {
      return true;
    }

    return false;
  }
}
