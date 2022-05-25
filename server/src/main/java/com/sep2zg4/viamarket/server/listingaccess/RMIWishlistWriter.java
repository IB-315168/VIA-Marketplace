package com.sep2zg4.viamarket.server.listingaccess;

import com.sep2zg4.viamarket.model.Listing;
import com.sep2zg4.viamarket.server.dao.CategoryDAO;
import com.sep2zg4.viamarket.server.dao.ListingDAO;
import com.sep2zg4.viamarket.server.dao.UserDAO;
import com.sep2zg4.viamarket.server.dao.WishlistDAO;
import com.sep2zg4.viamarket.servermodel.ReadWriteAccess;
import com.sep2zg4.viamarket.servermodel.WriteMap;
import dk.via.remote.observer.RemotePropertyChangeSupport;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

public final class RMIWishlistWriter implements Runnable
{
  private static RMIWishlistWriter INSTANCE;
  private ListingDAO listingDAO;
  private UserDAO userDAO;
  private CategoryDAO categoryDAO;
  private WishlistDAO wishlistDAO;
  private Connection connection;
  private ReadWriteAccess lock;
  private RemotePropertyChangeSupport<String> support;

  private RMIWishlistWriter(ReadWriteAccess lock, Connection connection, ListingDAO listingDAO,
      UserDAO userDAO, CategoryDAO categoryDAO, WishlistDAO wishlistDAO, RemotePropertyChangeSupport<String> support)
  {
    this.lock = lock;
    this.listingDAO = listingDAO;
    this.userDAO = userDAO;
    this.categoryDAO = categoryDAO;
    this.wishlistDAO = wishlistDAO;
    this.connection = connection;
    this.support = support;
  }

  public static RMIWishlistWriter getInstance(ReadWriteAccess lock, Connection connection,
      ListingDAO listingDAO, UserDAO userDAO, CategoryDAO categoryDAO, WishlistDAO wishlistDAO, RemotePropertyChangeSupport<String> support)
  {
    if (INSTANCE == null)
    {
      INSTANCE = new RMIWishlistWriter(lock, connection, listingDAO, userDAO, categoryDAO, wishlistDAO, support);
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
      ConcurrentHashMap<String, ArrayList<Listing>> currentWishlist = new ConcurrentHashMap<>();
      for (String category : categoryDAO.getAll())
      {
        currentWishlist.put(category, new ArrayList<>());
      }
      currentWishlist.put("<none>", new ArrayList<>());

      wishlistDAO.setCurrentStudentNumber(315236);
      for (Integer idListing : wishlistDAO.getAll())
      {
        currentWishlist.get(listingDAO.getById(idListing).getCategoryName()).add(listingDAO.getById(idListing));
      }
      write.writeWishlist(currentWishlist);


      System.out.println("Writer Wishlist done");
      //System.out.println(currentListings.get("Misc").get(currentListings.get("Misc").size()-1));
      lock.releaseWrite();
      support.firePropertyChange("dbupdate", "0", "2");
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
