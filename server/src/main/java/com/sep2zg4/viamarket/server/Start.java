package com.sep2zg4.viamarket.server;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Start
{
  public static void main(String[] args)
      throws RemoteException, AlreadyBoundException
  {
    Registry registry = LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
    RemoteMarketplace comm = new RemoteMarketplaceImplementation();
    registry.bind("comm", comm);
    System.out.println("Server running on " + Registry.REGISTRY_PORT);
  }
}
