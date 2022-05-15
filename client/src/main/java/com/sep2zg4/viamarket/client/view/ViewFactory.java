package com.sep2zg4.viamarket.client.view;

import com.sep2zg4.viamarket.client.viewmodel.ViewModelFactory;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Region;

import java.io.IOException;

/**
 * A factory pattern class used for opening different views in the system
 *
 * @author Rojus Paukste
 * @version 1.0 - April 2022
 */
public class ViewFactory
{
  private ViewHandler viewHandler;
  private ViewModelFactory viewModelFactory;
  private LoginViewController loginViewController;
  private ListingsViewController listingsViewController;
  private UserInformationViewController userInformationViewController;
  private ListingFormViewController listingFormViewController;

  /**
   * Constructor function for ViewFactory
   *
   * @param viewHandler
   * @param viewModelFactory
   */
  public ViewFactory(ViewHandler viewHandler, ViewModelFactory viewModelFactory)
  {
    this.viewHandler = viewHandler;
    this.viewModelFactory = viewModelFactory;
    this.loginViewController = null;
    this.listingsViewController = null;
    this.userInformationViewController = null;
    this.listingFormViewController = null;
  }

  /**
   * Function used for booting up LogInView and LogInViewController and returning the root of the controller
   *
   * @return
   */
  public Region loadLogInView()
  {
    if (loginViewController == null)
    {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource("LogInView.fxml"));
      try
      {
        Region root = loader.load();
        loginViewController = loader.getController();
        loginViewController.init(viewHandler,
            viewModelFactory.getLoginViewModel(), root);
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
    }
    return loginViewController.getRoot();
  }

  /**
   * Function used for booting up ListingsView and ListingsViewController and returning the root of the controller
   *
   * @return
   */
  public Region loadListingsView()
  {
    if (listingsViewController == null)
    {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource("ListingsView.fxml"));
      try
      {
        Region root = loader.load();
        listingsViewController = loader.getController();
        listingsViewController.init(viewHandler,
            viewModelFactory.getListingsViewModel(), root);
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
    }
    listingsViewController.reset();
    return listingsViewController.getRoot();
  }

  /**
   * Function used for booting up UserInformationView and UserInformationViewController and returning the root of the controller
   *
   * @return
   */
  public Region loadUserInformationView()
  {
    if (userInformationViewController == null)
    {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource("UserInformationView.fxml"));
      try
      {
        Region root = loader.load();
        userInformationViewController = loader.getController();
        userInformationViewController.init(viewHandler,
            viewModelFactory.getUserInformationViewModel(), root);
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
    }
    return userInformationViewController.getRoot();
  }
  /**
   * Function used for booting up ListingFormView and ListingFormViewController and returning the root of the controller
   *
   * @return
   */
  public Region loadListingFormView()
  {
    if (listingFormViewController == null)
    {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource("ListingFormView.fxml"));
      try
      {
        Region root = loader.load();
        listingFormViewController = loader.getController();
        listingFormViewController.init(viewHandler,
            viewModelFactory.getListingFormViewModel(), root);
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
    }
    return listingFormViewController.getRoot();
  }
}
