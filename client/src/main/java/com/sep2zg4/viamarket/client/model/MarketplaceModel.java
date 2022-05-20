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
 * @author Igor Bulinski, Wojtek Rusinski, Dércio Fernandes
 * @version 1.0 - April 2022
 */
public interface MarketplaceModel
{

  /**
   * method to login
   * @param username                 Student Number
   * @param password                 Student password
   * @return true if successfully login, false if unsuccessfully login
   * @throws RemoteException if error in Server
   * @throws NotBoundException if
   * @throws SQLException if error in SQL
   */
  boolean login(int username, String password)
      throws RemoteException, NotBoundException, SQLException;

  /**
   * method to get current logged user
   * @return User object of current logged user.
   */
  User getCurrentUser();

  /**
   * method to get current selected user listing
   * @return Listing object of current selected listing
   */
  Listing getCurrentSelectedUserListing();

  /**
   * method to set current selected user listing
   * @param currentSelectedUserListing                    Listing object of current selected user listing
   */
  void setCurrentSelectedUserListing(Listing currentSelectedUserListing);

  /**
   * method to get listings of specified category
   * @param categoryName                                   Category name
   * @return List of Listings with same category passed on parameters
   */
  ArrayList<Listing> getCategoryListing(String categoryName);

  /**
   * method to create a listing
   * @param listing                                        Listing object
   * @throws SQLException if error in SQL
   * @throws RemoteException if error in Server side
   */
  void createListing(Listing listing)
      throws SQLException, RemoteException;

  /**
   * method to delete a listing
   * @param listing                                         Listing object
   * @throws SQLException if error in SQL
   * @throws RemoteException if error in Server side
   */
  void deleteListing(Listing listing)
      throws SQLException, RemoteException;

  /**
   * method to update a listing
   * @param listing                                         Listing object
   * @throws SQLException if error in SQL
   * @throws RemoteException if error in Server side
   */
  void updateListing(Listing listing)
      throws SQLException, RemoteException;

  /**
   * method to get all listings
   * @return hashmap with category and list of listing from that category
   */
  HashMap<String, ArrayList<Listing>> getListings();

  /**
   * method to set all listings
   * @param listings                                         Hashmap with category and list of listings from that category
   */
  void setListings(HashMap<String, ArrayList<Listing>> listings);

  /**
   * method to get all listings
   * @return list of all listings
   */
  ArrayList<Listing> getAllListings();

  /**
   * method to get all categories
   * @return list of all categories
   */
  ArrayList<String> getAllCategories();

  void addPropertyChangeListener(PropertyChangeListener listener);
  void removePropertyChangeListener(PropertyChangeListener listener);
  void trigger();

  /**
   * method to get all user listings
   * @return list of all user listings
   */
  ArrayList<Listing> getUserListings();

  /**
   * method to delete a category
   * @param category                          Category name
   * @throws SQLException if error in SQL
   * @throws RemoteException if error in Server side
   */
  void deleteCategory(String category) throws SQLException, RemoteException;

  /**
   * method to create a category
   * @param categoryName                       Category name
   * @throws SQLException if error in SQL
   * @throws RemoteException if error in Server side
   */
  void createCategory(String categoryName) throws SQLException, RemoteException;
}
