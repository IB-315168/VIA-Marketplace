package com.sep2zg4.viamarket.server.dao;

import com.sep2zg4.viamarket.model.Listing;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

public final class SuperDAO implements Runnable
{
  private static SuperDAO INSTANCE;
  private ListingDAO listingDAO;
  private UserDAO userDAO;
  private CategoryDAO categoryDAO;
  private Connection connection;
  private final ConcurrentHashMap<String, ArrayList<Listing>> listingsReference;

  private SuperDAO(Connection connection, ListingDAO listingDAO,
      UserDAO userDAO, CategoryDAO categoryDAO,
      ConcurrentHashMap<String, ArrayList<Listing>> listingsReference)
  {
    this.listingDAO = listingDAO;
    this.userDAO = userDAO;
    this.categoryDAO = categoryDAO;
    this.connection = connection;
    this.listingsReference = listingsReference;
  }

  public static SuperDAO getInstance(Connection connection,
      ListingDAO listingDAO, UserDAO userDAO, CategoryDAO categoryDAO,
      ConcurrentHashMap<String, ArrayList<Listing>> listingsReference)
      throws SQLException
  {
    if (INSTANCE == null)
    {
      INSTANCE = new SuperDAO(connection, listingDAO, userDAO, categoryDAO,
          listingsReference);
    }

    return INSTANCE;
  }

  @Override public void run()
  {
    try
    {
      updateChanges();
    }
    catch (SQLException e)
    {
      throw new RuntimeException(e);
    }
    catch (RemoteException e)
    {
      throw new RuntimeException(e);
    }
  }

  private void updateChanges() throws SQLException, RemoteException
  {
    while (true)
    {
      for (String category : categoryDAO.getAll())
      {
        System.out.println(category);
        listingsReference.put(category, new ArrayList<>());
      }
      for (Listing listing : listingDAO.getAll())
      {
        String query = "SELECT name FROM category WHERE idCategory = (SELECT idCategory FROM listing WHERE id = ?)";
        PreparedStatement selectStatement = connection.prepareStatement(query);
        selectStatement.setInt(1, listing.getId());
        ResultSet res = selectStatement.executeQuery();
        if (res.next())
        {
          listingsReference.get(res.getString(1)).add(listing);
        }
      }
      try
      {
        synchronized (INSTANCE) {
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
