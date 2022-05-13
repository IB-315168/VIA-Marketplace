package com.sep2zg4.viamarket.server;

import com.sep2zg4.viamarket.model.Listing;
import com.sep2zg4.viamarket.model.User;
import com.sep2zg4.viamarket.server.dao.*;
import com.sep2zg4.viamarket.server.listingaccess.MapAccess;
import com.sep2zg4.viamarket.server.listingaccess.RMIListingsReader;
import com.sep2zg4.viamarket.server.listingaccess.ReadWriteAccess;
import com.sep2zg4.viamarket.server.listingaccess.WriteMap;
import com.sep2zg4.viamarket.servermodel.RemoteMarketplace;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Implementation of the Remote interface {@link RemoteMarketplace}
 *
 * @author Igor Bulinski
 * @version 1.0 - April 2022
 */
public class RemoteMarketplaceImplementation extends UnicastRemoteObject
    implements RemoteMarketplace, WriteMap
{

  private DAOManager daoManager = DAOManager.getInstance();
  private ConcurrentHashMap<String, ArrayList<Listing>> listings;
  private UserDAO userDAO;
  private CategoryDAO categoryDAO;
  private ListingDAO listingDAO;
  private final Thread superDAO;
  private ReadWriteAccess lock;
  private LoginHandler loginHandler;

  /**
   * Class constructor
   *
   * @throws RemoteException
   */
  public RemoteMarketplaceImplementation() throws RemoteException, SQLException
  {
    lock = new MapAccess(this);
    listings = new ConcurrentHashMap<>();
    userDAO = (UserDAO) daoManager.getDao(DAOManager.Table.User);
    categoryDAO = (CategoryDAO) daoManager.getDao(DAOManager.Table.Category);
    listingDAO = (ListingDAO) daoManager.getDao(DAOManager.Table.Listing);
    superDAO = new Thread(
        daoManager.getRMIListingsWriter(lock, listingDAO, userDAO, categoryDAO, listings));
    superDAO.start();
    loginHandler = daoManager.getLoginHandler();
  }

  /**
   * 2-argument method checking credentials with which user is trying to log in
   *
   * @param studentNumber student identification sent by the client
   * @param password password sent by the client
   * @return <ul><li>true - if credentials are correct</li><li>false - otherwise</li></ul>
   * @throws RemoteException
   */
  public User login(int studentNumber, String password)
      throws RemoteException, SQLException
  {
    return loginHandler.attemptLogin(studentNumber,password);

  }

  @Override public Listing getListingById(String id)
      throws SQLException, RemoteException
  {
    return listingDAO.getById(id);
  }

  @Override public HashMap<String, ArrayList<Listing>> getAllListing()
      throws SQLException, RemoteException
  {
    HashMap<String, ArrayList<Listing>> listingsCopy = new HashMap<>();
    Thread t = new Thread(new RMIListingsReader(lock, listingsCopy));
    t.start();
    return listingsCopy;
  }

  @Override public void createListing(Listing listing)
      throws SQLException, RemoteException
  {
    listingDAO.create(listing);
    notify();
  }

  @Override public void updateListing(Listing listing)
      throws SQLException, RemoteException
  {
    listingDAO.update(listing);
    notify();
  }

  @Override public void deleteListing(Listing listing)
      throws SQLException, RemoteException
  {
    try
    {
      listingDAO.delete(listing);
    }
    catch (RemoteException e)
    {
      throw new RuntimeException(e);
    }
    notify();
  }

  @Override
  public void createUser(User user) throws SQLException, RemoteException {
    userDAO.create(user);
    notify();
  }

  @Override
  public void updateUser(User user) throws SQLException, RemoteException {
    userDAO.update(user);
    notify();
  }

  @Override
  public void deleteUser(User user) throws SQLException, RemoteException {
    try {
      userDAO.delete(user);
    }
    catch (RemoteException e){
      throw new RuntimeException(e);
    }
    notify();
  }

  @Override
  public User getUserById(String id) throws SQLException, RemoteException {
    return userDAO.getById(id);
  }


  //Debug purpose, showing issues with reading
  public void exampleMethod() {
    synchronized (superDAO) {
      superDAO.notify();
    }
  }

  @Override public ConcurrentHashMap<String, ArrayList<Listing>> getListings()
  {
    return listings;
  }

  @Override public void write(
      ConcurrentHashMap<String, ArrayList<Listing>> listingsReference)
  {
    this.listings = listingsReference;
  }
}
