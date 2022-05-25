package com.sep2zg4.viamarket.client.viewmodel;

import com.sep2zg4.viamarket.client.model.MarketplaceModel;
import com.sep2zg4.viamarket.model.Listing;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.rmi.RemoteException;
import java.sql.SQLException;

/**
 * A viewmodel for user information
 *
 * @author Wojtek Rusinski
 * @version 1.0 - April 2022
 */

public class UserInformationViewModel
{
  private MarketplaceModel model;
  private ObservableList<Listing> userListings;
  private ObservableList<Listing> userWishlist;

  /**
   * Constructor for UserInformationViewModel taking Model as an argument
   *
   * @param model Model Manager reference
   */
  public UserInformationViewModel(MarketplaceModel model)
  {
    this.model = model;
    userListings = FXCollections.observableList(model.getUserListings());
    userWishlist =  FXCollections.observableList(model.getUserWishlist());
    model.addPropertyChangeListener(evt -> setUserListings());
    model.addPropertyChangeListener(evt -> setUserWishlist());
  }

  public void setCurrentSelectedUserListing(Listing listing) {
    model.setCurrentSelectedUserListing(listing);
  }
  public void setCurrentSelectedUserWishlistItem(Listing listing) {
    //model.setCurrentSelectedUserWishlistItem(listing);
  }
  public void setUserListings() {
    userListings.setAll(model.getUserListings());
  }
  public void setUserWishlist() {
    userWishlist.setAll(model.getUserWishlist());
  }

  public ObservableList<Listing> getUserListings()
  {
    return userListings;
  }
  /** Method for getting wishlist**/
  public ObservableList<Listing> getUserWishlist()
  {
    return userWishlist;
  }
  /** Method for deleting listing**/
  public  void deleteListing(Listing listing) throws SQLException, RemoteException {
    model.deleteListing(listing);
  }
  public void deleteWishlistItem(Listing wishlist){
    model.deleteWishlistItem;
  }

  public String getFullName(){
    return model.getFullName();
  }
}
