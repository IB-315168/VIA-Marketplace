package com.sep2zg4.viamarket.server.dao;

public class SuperDAO
{
  private ListingDAO listingDAO;
  private UserDAO userDAO;
  private CategoryDAO categoryDAO;

  public SuperDAO(ListingDAO listingDAO, UserDAO userDAO, CategoryDAO categoryDAO) {
    this.listingDAO = listingDAO;
    this.userDAO = userDAO;
    this.categoryDAO = categoryDAO;
  }


}
