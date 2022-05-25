package com.sep2zg4.viamarket.servermodel;

import com.sep2zg4.viamarket.model.Listing;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 *
 * @author Igor Bulinski
 * @version 1.0 - May 2022
 */

public interface ReadMap extends Remote
{
  /**
   * method to get a concurrent hashmap with all listings stored by <Category,ArrayList<Listings from category>>
   * @return concurrent hashmap with all listings stored by <Category,ArrayList<Listings from category>>
   * @throws RemoteException if error in Server side
   */
  ConcurrentHashMap<String, ArrayList<Listing>> getListings() throws
      RemoteException;

  ConcurrentHashMap<String, ArrayList<Listing>> getWishlist() throws
      RemoteException;
}
