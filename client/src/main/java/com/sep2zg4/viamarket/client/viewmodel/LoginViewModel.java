package com.sep2zg4.viamarket.client.viewmodel;

import com.sep2zg4.viamarket.client.model.MarketplaceModel;
import javafx.beans.property.Property;

/**
 * A viewmodel for Login view
 *
 * @author Wojtek Rusinski
 * @version 1.0 - April 2022
 */

public class LoginViewModel
{
  private Property<String> username, password;
  private MarketplaceModel model;

  /**
   * Constructor for LoginViewModel taking Model as an argument
   *
   * @param model Model Manager reference
   */
  public LoginViewModel(MarketplaceModel model)
  {
    this.model = model;
  }

  public void setUsername(Property<String> username)
  {
    this.username = username;
  }

  public void setPassword(Property<String> password)
  {
    this.password = password;
  }

  public Property<String> getUserName()
  {
    return username;
  }

  public Property<String> getUserPassword()
  {
    return password;
  }

  public void login()
  {
  }
}
