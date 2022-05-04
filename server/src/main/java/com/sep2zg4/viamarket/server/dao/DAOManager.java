package com.sep2zg4.viamarket.server.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAOManager
{
  public enum Table {Listing, User}
  private static ThreadLocal<DAOManager> INSTANCE;
  private Connection connection;

  private DAOManager() throws SQLException
  {
    DriverManager.registerDriver(new org.postgresql.Driver());
  }

  public static DAOManager getInstance() throws SQLException
  {
    if (INSTANCE == null) {
      INSTANCE = new ThreadLocal<DAOManager>() {
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
    if(connection == null || connection.isClosed()) {
      connection = DriverManager.getConnection("url",
          "user", "password");
    }
  }

  public void close() throws SQLException
  {
    if(connection != null || !connection.isClosed()) {
      connection.close();
    }
  }

  // Function responsible for retrieving Object-specific DAO, to be implemented once aforementioned are implemented
/*  public Dao getDao(Table t) throws SQLException
  {
    if(connection == null || connection.isClosed()) {
      this.open();
    }

    switch (t) {
      case Listing:
//        return new ListingDao(connection);
      case User:
//        return new UserDao(connection);
      default:
        throw new SQLException("Table does not exist");
    }
  }*/
}
