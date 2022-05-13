package com.sep2zg4.viamarket.server.listingaccess;

import com.sep2zg4.viamarket.model.Listing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class RMIListingsReader implements Runnable
{
  private HashMap<String, ArrayList<Listing>> listingsReference;
  private ReadWriteAccess lock;

  public RMIListingsReader(ReadWriteAccess lock, HashMap<String, ArrayList<Listing>> listingsReference) {
    this.lock = lock;
    this.listingsReference = listingsReference;
  }

  @Override public void run()
  {
    readToMap();
  }

  private void readToMap() {
    ReadMap read = lock.acquireRead();
    HashMap<String, ArrayList<Listing>> copy = new HashMap<>();
    ConcurrentHashMap<String, ArrayList<Listing>> storage = read
        .getListings();
    for(String s : storage.keySet()) {
      copy.put(s, storage.get(s));
    }
    listingsReference = copy;
    lock.releaseRead();
  }
}
