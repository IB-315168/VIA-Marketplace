package com.sep2zg4.viamarket.server.listingaccess;

import com.sep2zg4.viamarket.model.Listing;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

public interface WriteMap extends ReadMap
{
  void write(ConcurrentHashMap<String, ArrayList<Listing>> listingsReference);
}
