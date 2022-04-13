package com.sep2zg4.viamarket.client;

import com.sep2zg4.viamarket.client.model.MarketplaceModelManager;

import java.rmi.RemoteException;

public class Start
{
  public static void main(String[] args) throws RemoteException
  {
    MarketplaceModelManager model = new MarketplaceModelManager();
    model.login();
  }
}
