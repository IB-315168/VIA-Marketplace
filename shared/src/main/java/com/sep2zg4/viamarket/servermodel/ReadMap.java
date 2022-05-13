package com.sep2zg4.viamarket.servermodel;

import com.sep2zg4.viamarket.model.Listing;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

public interface ReadMap
{
  ConcurrentHashMap<String, ArrayList<Listing>> getListings();
}
