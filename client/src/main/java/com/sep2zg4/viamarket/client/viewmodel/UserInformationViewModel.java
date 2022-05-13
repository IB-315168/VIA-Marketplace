package com.sep2zg4.viamarket.client.viewmodel;

import com.sep2zg4.viamarket.client.model.MarketplaceModel;
import com.sep2zg4.viamarket.model.Listing;
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

  /**
   * Constructor for UserInformationViewModel taking Model as an argument
   *
   * @param model Model Manager reference
   */
  public UserInformationViewModel(MarketplaceModel model)
  {
    this.model = model;
  }

  public ObservableList<Listing> getUserListings()
  {
    return userListings;
  }
  /** Method for deleting listing**/
  public  void deleteListing(Listing listing) throws SQLException, RemoteException {
    model.deleteListing(listing);
  }
}
