package com.sep2zg4.viamarket.client.model.comm;

import com.sep2zg4.viamarket.model.Listing;
import com.sep2zg4.viamarket.servermodel.RemoteMarketplace;

import java.rmi.NoSuchObjectException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;

/**
 * Communicator class for handling connection
 *
 * @author Igor Bulinski, Wojtek Rusinski
 * @version 1.0 - April 2022
 */
public class ClientMarketplaceCommunicator extends UnicastRemoteObject
{
  private RemoteMarketplace communicator;
  private String host;
  private int port;

  /**
   * 2-argument constructor creating a ClientMarketplaceCommunicator object and establishing connection to the server
   * @param host address of the server
   * @param port port of the server-app
   * @throws RemoteException
   */
  public ClientMarketplaceCommunicator(String host, int port) throws
      RemoteException
  {
    this.host = host;
    this.port = port;
  }

  /**
   * 2-argument method for establishing connection to the server and locating the remote object
   * @throws NotBoundException
   */
  private void connect()
      throws RemoteException, NotBoundException
  {
    Registry registry = LocateRegistry.getRegistry(host, port);
    communicator = (RemoteMarketplace) registry.lookup("comm");
  }

  /**
   * 2-argument method for logging in
   * @param username username of the user
   * @param password matching password
   * @return result of {@link com.sep2zg4.viamarket.servermodel.RemoteMarketplace#login(int, String)}
   * @throws RemoteException
   */
  public boolean login(int username, String password)
      throws RemoteException, NotBoundException, SQLException
  {
    try {
      connect();
    } catch (Exception e) {
      close();
      throw e;
    }
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

  /**
   * Method used for creating a listing
   * @param listing Listing that is being created
   * @throws RemoteException
   * @throws SQLException
   */
  public void createListing(Listing listing)
      throws RemoteException, SQLException
  {
      communicator.createListing(listing);
  }

  /**
   * Method used for updating a listing
   * @param listing Listing that is being updated
   * @throws RemoteException
   * @throws SQLException
   */
  public void updateListing(Listing listing)
      throws RemoteException, SQLException
  {
      communicator.updateListing(listing);
  }

  /**
   * Method used for deleting a listing
   * @param listing Listing that is being deleted
   * @throws RemoteException
   * @throws SQLException
   */
  public void deleteListing(Listing listing)
      throws RemoteException, SQLException
  {
      communicator.deleteListing(listing);
  }
}
