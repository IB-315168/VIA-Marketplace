package com.sep2zg4.viamarket.server;

import com.sep2zg4.viamarket.servermodel.RemoteMarketplace;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Implementation of the Remote interface {@link RemoteMarketplace}
 *
 * @author Igor Bulinski
 * @version 1.0 - April 2022
 */
public class RemoteMarketplaceImplementation extends UnicastRemoteObject implements
    RemoteMarketplace
{
  /**
   * Class constructor
   * @throws RemoteException
   */
  public RemoteMarketplaceImplementation() throws RemoteException
  {

  }

  /**
   * 2-argument method checking credentials with which user is trying to log in
   * @param username username sent by the client
   * @param password password sent by the client
   * @return <ul><li>true - if credentials are correct</li><li>false - otherwise</li></ul>
   * @throws RemoteException
   */
  public boolean login(String username, String password) throws RemoteException
  {
    if(username.equals("admin") && password.equals("admin")) {
      return true;
    }

    return false;
  }
}
