package com.sep2zg4.viamarket.client.model;

import com.sep2zg4.viamarket.client.model.comm.ClientMarketplaceCommunicator;
import com.sep2zg4.viamarket.model.Listing;
import com.sep2zg4.viamarket.model.User;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 * Implementation of the {@link MarketplaceModel}
 *
 * @author Igor Bulinski, Wojtek Rusinski
 * @version 1.0 - April 2022
 */
public class MarketplaceModelManager implements MarketplaceModel
{
  private User currentUserRef;
  private HashMap<String, ArrayList<Listing>> listings;
  private ClientMarketplaceCommunicator client;
  private User currentUser;

  /**
   * Constructor creating a {@link ClientMarketplaceCommunicator} object and establishing connection
   * @throws RemoteException
   */
  public MarketplaceModelManager() throws RemoteException
  {
    client = new ClientMarketplaceCommunicator("localhost", Registry.REGISTRY_PORT, this);
    listings = new HashMap<>();
  }

  /**
   * 2-argument method for passing credentials to the {@link ClientMarketplaceCommunicator#login(int, String)} method and handling the result
   *
   * @param username username of the user
   * @param password matching password
   * @return result of {@link com.sep2zg4.viamarket.client.model.comm.ClientMarketplaceCommunicator#login(int, String)}
   * @throws RemoteException
   */
  public boolean login(int username, String password)
      throws RemoteException, NotBoundException, SQLException
  {
    if(client.login(username,password)!=null){
      currentUser = client.login(username,password);
      return true;
    }
    return false;
  }

  public User getCurrentUser(){
    return currentUser;
  };

  /**
   * Method used for creating a listing
   * @param listing Listing that is being created
   * @throws SQLException
   * @throws RemoteException
   */
  public void createListing(Listing listing)
      throws SQLException, RemoteException
  {
    client.createListing(listing);
  }

  /**
   * Method used for deleting a listing
   * @param listing Listing that is being deleted
   * @throws SQLException
   * @throws RemoteException
   */
  public void deleteListing(Listing listing)
      throws SQLException, RemoteException
  {
    client.deleteListing(listing);
  }

  /**
   * Method used for updating a listing
   * @param listing Listing that is being updated
   * @throws SQLException
   * @throws RemoteException
   */
  public void updateListing(Listing listing)
      throws SQLException, RemoteException
  {
    client.updateListing(listing);
  }

  public HashMap<String, ArrayList<Listing>> getListings()
  {
    return listings;
  }

  public void setListings(HashMap<String, ArrayList<Listing>> listings)
  {
    this.listings = listings;
  }

  @Override public ArrayList<Listing> getAllListings()
  {
    ArrayList<Listing> allListings = new ArrayList<>();
    for(String s : listings.keySet()) {
      allListings.addAll(listings.get(s));
    }
    return allListings;
  }

  @Override public ArrayList<String> getAllCategories()
  {
    return new ArrayList<>(listings.keySet());
  }
}
