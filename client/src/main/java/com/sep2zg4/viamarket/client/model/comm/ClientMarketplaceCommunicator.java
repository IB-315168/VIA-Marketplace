package com.sep2zg4.viamarket.client.model.comm;

import com.sep2zg4.viamarket.client.model.MarketplaceModel;
import com.sep2zg4.viamarket.model.Listing;
import com.sep2zg4.viamarket.model.User;
import com.sep2zg4.viamarket.servermodel.ReadWriteAccess;
import com.sep2zg4.viamarket.servermodel.RemoteMarketplace;
import dk.via.remote.observer.RemotePropertyChangeEvent;
import dk.via.remote.observer.RemotePropertyChangeListener;
import javafx.application.Platform;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
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
    implements RemotePropertyChangeListener<String>
{
  private RemoteMarketplace communicator;
  private MarketplaceModel model;
  private String host;
  private int port;
  private RMIListingsReader reader;
  private ReadWriteAccess lock;

  /**
   * 2-argument constructor creating a ClientMarketplaceCommunicator object and establishing connection to the server
   *
   * @param host address of the server
   * @param port port of the server-app
   * @throws RemoteException
   */
  public ClientMarketplaceCommunicator(String host, int port,
      MarketplaceModel model) throws RemoteException
  {
    this.host = host;
    this.port = port;
    this.model = model;
  }

  /**
   * 2-argument method for establishing connection to the server and locating the remote object
   *
   * @throws NotBoundException
   */
  private void connect() throws RemoteException, NotBoundException
  {
    Registry registry = LocateRegistry.getRegistry(host, port);
    communicator = (RemoteMarketplace) registry.lookup("comm");
    lock = (ReadWriteAccess) registry.lookup("lock");
    reader = new RMIListingsReader(lock, model);
    Thread t = new Thread(reader);
    t.start();
    communicator.addRemotePropertyChangeListener(this);
  }

  /**
   * 2-argument method for logging in
   *
   * @param username username of the user
   * @param password matching password
   * @return result of {@link com.sep2zg4.viamarket.servermodel.RemoteMarketplace#login(int, String)}
   * @throws RemoteException
   */
  public User login(int username, String password)
      throws RemoteException, NotBoundException, SQLException
  {
    try
    {
      connect();
    }
    catch (Exception e)
    {
      close();
      throw e;
    }
    return communicator.login(username, password);
  }

  /**
   * No-argument method for closing communicator
   *
   * @throws NoSuchObjectException if the communicator has not been exported
   */
  public void close() throws RemoteException
  {
    communicator.removeRemotePropertyChangeListener(this);
    UnicastRemoteObject.unexportObject(this, true);
  }

  /**
   * Method used for creating a listing
   *
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
   *
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
   *
   * @param listing Listing that is being deleted
   * @throws RemoteException
   * @throws SQLException
   */
  public void deleteListing(Listing listing)
      throws RemoteException, SQLException
  {
    communicator.deleteListing(listing);
  }

  public void trigger() throws RemoteException
  {
    communicator.exampleMethod();
  }

  @Override public void propertyChange(RemotePropertyChangeEvent<String> evt)
      throws RemoteException
  {
    reader.pullUpdate();
  }
}
