package com.sep2zg4.viamarket.server.listingaccess;

import com.sep2zg4.viamarket.client.model.MarketplaceModel;
import com.sep2zg4.viamarket.model.Listing;
import com.sep2zg4.viamarket.servermodel.ReadMap;
import com.sep2zg4.viamarket.servermodel.ReadWriteAccess;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class RMIListingsReader implements Runnable
{
  private MarketplaceModel model;
  private ReadWriteAccess lock;

  public RMIListingsReader(ReadWriteAccess lock,MarketplaceModel model) {
    this.lock = lock;
    this.model = model;
  }

  public void pullUpdate() {
    synchronized (this) {
      notify();
    }
  }

  @Override public void run()
  {
    readToMap();
  }

  private void readToMap() {
    while (true) {
      ReadMap read = lock.acquireRead();
      HashMap<String, ArrayList<Listing>> copy = new HashMap<>();
      ConcurrentHashMap<String, ArrayList<Listing>> storage = read
          .getListings();
      for(String s : storage.keySet()) {
        copy.put(s, storage.get(s));
      }
      model.setListings(copy);
      lock.releaseRead();
      try
      {
        synchronized (this) {
          wait();
        }
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
