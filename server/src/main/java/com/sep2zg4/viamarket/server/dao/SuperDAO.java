package com.sep2zg4.viamarket.server.dao;

import java.sql.Connection;

public class SuperDAO implements Runnable
{
  private ListingDAO listingDAO;
  private UserDAO userDAO;
  private CategoryDAO categoryDAO;
  private Connection connection;

  public SuperDAO(Connection connection,
      ListingDAO listingDAO, UserDAO userDAO, CategoryDAO categoryDAO) {
    this.listingDAO = listingDAO;
    this.userDAO = userDAO;
    this.categoryDAO = categoryDAO;
    this.connection = connection;
  }

  @Override public void run()
  {

  }
}
