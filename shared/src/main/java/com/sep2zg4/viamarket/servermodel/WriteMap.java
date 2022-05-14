package com.sep2zg4.viamarket.servermodel;

import com.sep2zg4.viamarket.model.Listing;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

public interface WriteMap extends ReadMap
{
  void write(ConcurrentHashMap<String, ArrayList<Listing>> listingsReference) throws
      RemoteException;
}