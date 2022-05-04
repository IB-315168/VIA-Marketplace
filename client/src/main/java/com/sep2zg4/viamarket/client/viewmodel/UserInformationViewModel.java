package com.sep2zg4.viamarket.client.viewmodel;

import com.sep2zg4.viamarket.client.model.MarketplaceModel;
import javafx.beans.property.Property;

/**
 * A viewmodel for user information
 *
 * @author Wojtek Rusinski
 * @version 1.0 - April 2022
 */

public class UserInformationViewModel
{
  private MarketplaceModel model;
  private Property userListings;

  /**
   * Constructor for UserInformationViewModel taking Model as an argument
   *
   * @param model Model Manager reference
   */
  public UserInformationViewModel(MarketplaceModel model)
  {
    this.model = model;
  }

  public void setUserListings(Property userListings)
  {
    this.userListings = userListings;
  }

  public Property getUserListings()
  {
    return userListings;
  }
}
