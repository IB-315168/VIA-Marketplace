package com.sep2zg4.viamarket.servermodel;

import com.sep2zg4.viamarket.model.Listing;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Remote interface for RMI
 *
 * @author Igor Bulinski
 * @version 1.0 - April 2022
 */
public interface RemoteMarketplace extends Remote
{
  boolean login(int studentNumber, String password)
      throws RemoteException, SQLException;

  Listing getListingById(String id) throws SQLException;
  ConcurrentHashMap<String, ArrayList<Listing>> getAllListing()
      throws SQLException;
  void createListing(Listing listing) throws SQLException;
  void updateListing(Listing listing) throws SQLException;
  void deleteListing(Listing listing) throws SQLException;
}
