package com.sep2zg4.viamarket.server.listingaccess;

import com.sep2zg4.viamarket.model.Listing;
import com.sep2zg4.viamarket.server.dao.CategoryDAO;
import com.sep2zg4.viamarket.server.dao.ListingDAO;
import com.sep2zg4.viamarket.server.dao.UserDAO;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

public final class RMIListingsWriter implements Runnable
{
  private static RMIListingsWriter INSTANCE;
  private ListingDAO listingDAO;
  private UserDAO userDAO;
  private CategoryDAO categoryDAO;
  private Connection connection;
  private ReadWriteAccess lock;

  private RMIListingsWriter(ReadWriteAccess lock, Connection connection, ListingDAO listingDAO,
      UserDAO userDAO, CategoryDAO categoryDAO)
  {
    this.lock = lock;
    this.listingDAO = listingDAO;
    this.userDAO = userDAO;
    this.categoryDAO = categoryDAO;
    this.connection = connection;
  }

  public static RMIListingsWriter getInstance(ReadWriteAccess lock, Connection connection,
      ListingDAO listingDAO, UserDAO userDAO, CategoryDAO categoryDAO)
      throws SQLException
  {
    if (INSTANCE == null)
    {
      INSTANCE = new RMIListingsWriter(lock, connection, listingDAO, userDAO, categoryDAO);
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
        System.out.println(category);
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
          currentListings.get(res.getString(1)).add(listing);
        }
      }
      write.write(currentListings);
      lock.releaseWrite();
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