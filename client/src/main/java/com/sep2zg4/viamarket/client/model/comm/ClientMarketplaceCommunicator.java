package com.sep2zg4.viamarket.client.model.comm;

import com.sep2zg4.viamarket.servermodel.RemoteMarketplace;

import java.rmi.NoSuchObjectException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * Communicator class for handling connection
 *
 * @author Igor Bulinski
 * @version 1.0 - April 2022
 */
public class ClientMarketplaceCommunicator extends UnicastRemoteObject
{
  private RemoteMarketplace communicator;

  /**
   * 2-argument constructor creating a ClientMarketplaceCommunicator object and establishing connection to the server
   * @param host address of the server
   * @param port port of the server-app
   * @throws RemoteException
   */
  public ClientMarketplaceCommunicator(String host, int port) throws
      RemoteException
  {
    try {
      connect(host, port);
    } catch (Exception e) {
      close();
    }
  }

  /**
   * 2-argument method for establishing connection to the server and locating the remote object
   * @param host address of the server
   * @param port port of the server-app
   * @throws RemoteException
   * @throws NotBoundException
   */
  private void connect(String host, int port)
      throws RemoteException, NotBoundException
  {
    Registry registry = LocateRegistry.getRegistry(host, port);
    communicator = (RemoteMarketplace) registry.lookup("comm");
  }

  /**
   * 2-argument method for logging in
   * @param username username of the user
   * @param password matching password
   * @return result of {@link com.sep2zg4.viamarket.servermodel.RemoteMarketplace#login(String, String)}
   * @throws RemoteException
   */
  public boolean login(String username, String password) throws RemoteException
  {
    return communicator.login(username, password);
  }

  /**
   * No-argument method for closing communicator
   * @throws NoSuchObjectException if the communicator has not been exported
   */
  public void close() throws NoSuchObjectException
  {
    UnicastRemoteObject.unexportObject(this, true);
  }
}
