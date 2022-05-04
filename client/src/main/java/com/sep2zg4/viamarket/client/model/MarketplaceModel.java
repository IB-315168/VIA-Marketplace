package com.sep2zg4.viamarket.client.model;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * Interface for the model
 *
 * @author Igor Bulinski
 * @version 1.0 - April 2022
 */
public interface MarketplaceModel
{
  boolean login(String username, String password)
      throws RemoteException, NotBoundException;
}
