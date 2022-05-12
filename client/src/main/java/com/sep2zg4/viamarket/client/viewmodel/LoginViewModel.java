package com.sep2zg4.viamarket.client.viewmodel;

import com.sep2zg4.viamarket.client.model.MarketplaceModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;

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

  /** 0-argument method used for logging in
   *
   * @return result of {@link com.sep2zg4.viamarket.client.model.MarketplaceModel#login(int, String)}
   * @throws RemoteException
   * @throws NotBoundException
   */
  public boolean login() throws RemoteException, NotBoundException, SQLException
  {
    return model.login(Integer.parseInt(username.get()), password.get());
  }
}
