package com.sep2zg4.viamarket.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteMarketplace extends Remote
{
  boolean login(String username, String password) throws RemoteException;
}
