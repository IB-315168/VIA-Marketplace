package com.sep2zg4.viamarket.server.listingaccess;

import com.sep2zg4.viamarket.model.Listing;
import com.sep2zg4.viamarket.server.dao.CategoryDAO;
import com.sep2zg4.viamarket.server.dao.ListingDAO;
import com.sep2zg4.viamarket.server.dao.UserDAO;
import com.sep2zg4.viamarket.servermodel.ReadWriteAccess;
import com.sep2zg4.viamarket.servermodel.WriteMap;
import dk.via.remote.observer.RemotePropertyChangeEvent;
import dk.via.remote.observer.RemotePropertyChangeSupport;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public final class RMIListingsWriter implements Runnable
{
  private static RMIListingsWriter INSTANCE;
  private ListingDAO listingDAO;
  private UserDAO userDAO;
  private CategoryDAO categoryDAO;
  private Connection connection;
  private ReadWriteAccess lock;
  private RemotePropertyChangeSupport<String> support;

  private RMIListingsWriter(ReadWriteAccess lock, Connection connection, ListingDAO listingDAO,
      UserDAO userDAO, CategoryDAO categoryDAO, RemotePropertyChangeSupport<String> support)
  {
    this.lock = lock;
    this.listingDAO = listingDAO;
    this.userDAO = userDAO;
    this.categoryDAO = categoryDAO;
    this.connection = connection;
    this.support = support;
  }

  public static RMIListingsWriter getInstance(ReadWriteAccess lock, Connection connection,
      ListingDAO listingDAO, UserDAO userDAO, CategoryDAO categoryDAO, RemotePropertyChangeSupport<String> support)
      throws SQLException
  {
    if (INSTANCE == null)
    {
      INSTANCE = new RMIListingsWriter(lock, connection, listingDAO, userDAO, categoryDAO, support);
    }

    return INSTANCE;
  }

  public void pushUpdate() {
    synchronized (this) {
      notify();
    }
  }

  @Override public void run()
  {
    try
    {
      updateChanges();
    }
    catch (SQLException | RemoteException e)
    {
      throw new RuntimeException(e);
    }
  }

  private synchronized void updateChanges() throws SQLException, RemoteException
  {
    while(true) {
      WriteMap write = lock.acquireWrite();
      ConcurrentHashMap<String, ArrayList<Listing>> currentListings = new ConcurrentHashMap<>();
      for (String category : categoryDAO.getAll())
      {
        currentListings.put(category, new ArrayList<>());
      }
      for (Listing listing : listingDAO.getAll())
      {
        String query = "SELECT name FROM category WHERE idCategory = (SELECT idCategory FROM listing WHERE id = ?)";
        PreparedStatement selectStatement = connection.prepareStatement(query);
        selectStatement.setInt(1, listing.getId());
        ResultSet res = selectStatement.executeQuery();
        if (res.next())
        {
          String categoryName = res.getString(1);
          listing.setCategoryName(categoryName);
          currentListings.get(categoryName).add(listing);
        }
      }
      write.write(currentListings);
      System.out.println("Writer done");
      System.out.println(currentListings.get("Misc").get(currentListings.get("Misc").size()-1));
      lock.releaseWrite();
      support.firePropertyChange("dbupdate", "0", "1");
      try
      {
        synchronized (this) {
          wait();
        }
      }
      catch (InterruptedException e)
      {
        throw new RuntimeException(e);
      }
    }
  }
}
