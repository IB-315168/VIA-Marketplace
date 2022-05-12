package com.sep2zg4.viamarket.client.model;

import com.sep2zg4.viamarket.model.Listing;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;

/**
 * Interface for the model
 *
 * @author Igor Bulinski, Wojtek Rusinski
 * @version 1.0 - April 2022
 */
public interface MarketplaceModel
{
  boolean login(int username, String password)
      throws RemoteException, NotBoundException, SQLException;
  void createListing(Listing listing)
      throws SQLException, RemoteException;

  void deleteListing(Listing listing)
      throws SQLException, RemoteException;

  void updateListing(Listing listing)
      throws SQLException, RemoteException;
}
