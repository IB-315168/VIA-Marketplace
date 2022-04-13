package com.sep2zg4.viamarket.client.model;

import com.sep2zg4.viamarket.client.model.comm.ClientMarketplaceCommunicator;

import java.rmi.RemoteException;
import java.rmi.registry.Registry;

public class MarketplaceModelManager
{
  private ClientMarketplaceCommunicator client;

  public MarketplaceModelManager() throws RemoteException
  {
    client = new ClientMarketplaceCommunicator("localhost", Registry.REGISTRY_PORT);
  }

  public void login() throws RemoteException
  {
    if (client.login("admin", "admin"))
    {
      System.out.println("Success - you are logged in.");
    }
    else
    {
      System.out.println("Failed to log in.");
    }
  }
}
