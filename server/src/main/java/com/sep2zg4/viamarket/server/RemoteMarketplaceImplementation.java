package com.sep2zg4.viamarket.server;

import com.sep2zg4.viamarket.model.Listing;
import com.sep2zg4.viamarket.server.dao.DAOManager;
import com.sep2zg4.viamarket.servermodel.RemoteMarketplace;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.List;

/**
 * Implementation of the Remote interface {@link RemoteMarketplace}
 *
 * @author Igor Bulinski
 * @version 1.0 - April 2022
 */
public class RemoteMarketplaceImplementation extends UnicastRemoteObject implements
    RemoteMarketplace
{

  DAOManager daoManager = DAOManager.getInstance();

  /**
   * Class constructor
   * @throws RemoteException
   */
  public RemoteMarketplaceImplementation() throws RemoteException, SQLException
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

  @Override public Listing getListingById(String id) throws SQLException
  {
    return (Listing) daoManager.getDao("Listing").getById(id);
  }

  @Override public List getAllListing() throws SQLException
  {
    return daoManager.getDao("Listing").getAll();
  }

  @Override public void createListing(Listing listing) throws SQLException
  {
    daoManager.getDao("Listing").create(listing);
  }

  @Override public void updateListing(Listing listing, String id) throws SQLException
  {
    daoManager.getDao("Listing").update(listing,id);
  }

  @Override public void deleteListing(Listing listing) throws SQLException
  {
    daoManager.getDao("Listing").delete(listing);
  }

}
