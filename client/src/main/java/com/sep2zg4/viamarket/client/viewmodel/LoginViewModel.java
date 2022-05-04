package com.sep2zg4.viamarket.client.viewmodel;

import com.sep2zg4.viamarket.client.model.MarketplaceModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * A viewmodel for Login view
 *
 * @author Wojtek Rusinski
 * @version 1.0 - April 2022
 */

public class LoginViewModel
{
  private StringProperty username, password;
  private MarketplaceModel model;

  /**
   * Constructor for LoginViewModel taking Model as an argument
   *
   * @param model Model Manager reference
   */
  public LoginViewModel(MarketplaceModel model)
  {
    this.model = model;

    this.username = new SimpleStringProperty("");
    this.password = new SimpleStringProperty("");
  }

  public StringProperty getUserName()
  {
    return username;
  }

  public StringProperty getUserPassword()
  {
    return password;
  }

  public boolean login() throws RemoteException, NotBoundException
  {
    return model.login(username.get(), password.get());
  }
}
