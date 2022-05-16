package com.sep2zg4.viamarket.client.model;

import com.sep2zg4.viamarket.model.Listing;
import com.sep2zg4.viamarket.model.User;

import java.beans.PropertyChangeListener;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

  User getCurrentUser();
  Listing getCurrentSelectedUserListing();
  void setCurrentSelectedUserListing(Listing currentSelectedUserListing);

  void createListing(Listing listing)
      throws SQLException, RemoteException;

  void deleteListing(Listing listing)
      throws SQLException, RemoteException;

  void updateListing(Listing listing)
      throws SQLException, RemoteException;
  HashMap<String, ArrayList<Listing>> getListings();
  void setListings(HashMap<String, ArrayList<Listing>> listings);
  ArrayList<Listing> getAllListings();
  ArrayList<String> getAllCategories();
  void addPropertyChangeListener(PropertyChangeListener listener);
  void removePropertyChangeListener(PropertyChangeListener listener);
  void trigger();
  ArrayList<Listing> getUserListings();
}
