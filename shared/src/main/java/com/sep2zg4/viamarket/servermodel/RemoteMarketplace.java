package com.sep2zg4.viamarket.servermodel;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Remote interface for RMI
 *
 * @author Igor Bulinski
 * @version 1.0 - April 2022
 */
public interface RemoteMarketplace extends Remote
{
  boolean login(String username, String password) throws RemoteException;
}
