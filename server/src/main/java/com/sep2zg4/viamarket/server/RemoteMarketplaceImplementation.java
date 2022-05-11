package com.sep2zg4.viamarket.server;

import com.sep2zg4.viamarket.model.Listing;
import com.sep2zg4.viamarket.server.dao.*;
import com.sep2zg4.viamarket.servermodel.RemoteMarketplace;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Implementation of the Remote interface {@link RemoteMarketplace}
 *
 * @author Igor Bulinski
 * @version 1.0 - April 2022
 */
public class RemoteMarketplaceImplementation extends UnicastRemoteObject
    implements RemoteMarketplace
{

  private DAOManager daoManager = DAOManager.getInstance();
  private ConcurrentHashMap<String, ArrayList<Listing>> listings;
  private UserDAO userDAO;
  private CategoryDAO categoryDAO;
  private ListingDAO listingDAO;
  private Thread superDAO;

  /**
   * Class constructor
   *
   * @throws RemoteException
   */
  public RemoteMarketplaceImplementation() throws RemoteException, SQLException
  {
    listings = new ConcurrentHashMap<>();
    userDAO = (UserDAO) daoManager.getDao(DAOManager.Table.User);
    categoryDAO = (CategoryDAO) daoManager.getDao(DAOManager.Table.Category);
    listingDAO = (ListingDAO) daoManager.getDao(DAOManager.Table.Listing);
    superDAO = new Thread(
        daoManager.getSuperDao(listingDAO, userDAO, categoryDAO, listings));
    superDAO.start();
  }

  /**
   * 2-argument method checking credentials with which user is trying to log in
   *
   * @param studentNumber student identification sent by the client
   * @param password password sent by the client
   * @return <ul><li>true - if credentials are correct</li><li>false - otherwise</li></ul>
   * @throws RemoteException
   */
  public boolean login(int studentNumber, String password)
      throws RemoteException, SQLException
  {
    daoManager.open();
    Login login = new Login(daoManager.getConnection());//Im not sure if it would be safe to have a get connection but I honestly dont know any other way to get connection through this.
    Boolean status = login.doLogin(studentNumber,password);
    daoManager.close();
    return status;
  }

  @Override public Listing getListingById(String id) throws SQLException
  {
    return listingDAO.getById(id);
  }

  @Override public ConcurrentHashMap<String, ArrayList<Listing>> getAllListing()
  {
    return listings;
  }

  @Override public void createListing(Listing listing) throws SQLException
  {
    listingDAO.create(listing);
  }

  @Override public void updateListing(Listing listing) throws SQLException
  {
    listingDAO.update(listing);
  }

  @Override public void deleteListing(Listing listing) throws SQLException
  {
    listingDAO.delete(listing);
  }

}
