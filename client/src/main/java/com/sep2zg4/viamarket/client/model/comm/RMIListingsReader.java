package com.sep2zg4.viamarket.client.model.comm;

import com.sep2zg4.viamarket.client.model.MarketplaceModel;
import com.sep2zg4.viamarket.model.Listing;
import com.sep2zg4.viamarket.servermodel.ReadMap;
import com.sep2zg4.viamarket.servermodel.ReadWriteAccess;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class RMIListingsReader implements Runnable, Serializable
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
    try
    {
      readToMap();
    }
    catch (RemoteException e)
    {
      throw new RuntimeException(e);
    }
  }

  private void readToMap() throws RemoteException
  {
    while (true) {
      ReadMap read = lock.acquireRead();
      HashMap<String, ArrayList<Listing>> copy = new HashMap<>();
      HashMap<String, ArrayList<Listing>> copyWishlist = new HashMap<>();
      ConcurrentHashMap<String, ArrayList<Listing>> storage = read.getListings();
      ConcurrentHashMap<String, ArrayList<Listing>> storageWishlist = read.getWishlist();
      for(String s : storage.keySet()) {
        System.out.println("s: " + s);
        copy.put(s, storage.get(s));
      }

      if(storageWishlist.isEmpty()){
        System.out.println("StorageWIshlist is empty");
      }
      for(String b : storageWishlist.keySet()){
        System.out.println("b: " + b);
        System.out.println(storageWishlist.get(b));
        copyWishlist.put(b,storageWishlist.get(b));
      }
      model.setListings(copy);
      model.setWishlist(copyWishlist);
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
