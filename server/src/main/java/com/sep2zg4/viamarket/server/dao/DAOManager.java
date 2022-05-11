package com.sep2zg4.viamarket.server.dao;

import com.sep2zg4.viamarket.model.Listing;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

public final class DAOManager
{
  private static ThreadLocal<DAOManager> INSTANCE;
  private Connection connection;

  private DAOManager() throws SQLException
  {
    DriverManager.registerDriver(new org.postgresql.Driver());
  }

  public static DAOManager getInstance() throws SQLException
  {
    if (INSTANCE == null)
    {
      INSTANCE = new ThreadLocal<DAOManager>()
      {
        @Override protected DAOManager initialValue()
        {
          try
          {
            return new DAOManager();
          }
          catch (SQLException e)
          {
            return null;
          }
        }
      };
    }

    return INSTANCE.get();
  }

  public void open() throws SQLException
  {
    if (connection == null || connection.isClosed())
    {
      connection = DriverManager.getConnection(
          "jdbc:postgresql://abul.db.elephantsql.com/unnmkiby", "unnmkiby",
          "9rQAlABdHOKbbTS46V662goUMd2IjnKZ");
    }
  }

  public void close() throws SQLException
  {
    if (connection != null || !connection.isClosed())
    {
      connection.close();
    }
  }

  // Function responsible for retrieving Object-specific DAO, to be implemented once aforementioned are implemented
  public Dao getDao(Table t) throws SQLException
  {
    if (connection == null || connection.isClosed())
    {
      this.open();
    }

    switch (t)
    {
      case Listing:
        return new ListingDAO(connection);
      case User:
        return new UserDAO(connection);
      case Category:
        return new CategoryDAO(connection);
      default:
        throw new SQLException("Table does not exist");
    }
  }

  /*// Our version of the Function
  public Dao getDao(String table) throws SQLException
  {
    if(connection == null || connection.isClosed()) {
      this.open();
    }

    switch (table) {
      case "Listing":
        return new ListingDAO(connection);
      case "User":
        //        return new UserDao(connection);
      default:
        throw new SQLException("Table does not exist");
    }
  }*/

  public SuperDAO getSuperDao(ListingDAO listingDAO, UserDAO userDAO,
      CategoryDAO categoryDAO,
      ConcurrentHashMap<String, ArrayList<Listing>> listingsReference)
      throws SQLException
  {
    return SuperDAO.getInstance(connection, listingDAO, userDAO, categoryDAO,
        listingsReference);
  }

  public enum Table
  {Listing, User, Category}
}
