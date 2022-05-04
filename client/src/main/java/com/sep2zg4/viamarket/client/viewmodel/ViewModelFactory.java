package com.sep2zg4.viamarket.client.viewmodel;

import com.sep2zg4.viamarket.client.model.MarketplaceModel;

/**
 * ViewModelFactory for ViaMarketplace
 *
 * @author Wojtek Rusinski
 * @version 1.0 - April 2022
 */
public class ViewModelFactory
{
  private final ListingsViewModel listingsViewModel;
  private final LoginViewModel loginViewModel;
  private final UserInformationViewModel userInformationViewModel;

  /**
   * Constructor for ViewModelFactory, initializing 3 viewmodels
   *
   * @param model Model Manager reference
   */
  public ViewModelFactory(MarketplaceModel model)
  {
    listingsViewModel = new ListingsViewModel(model);
    loginViewModel = new LoginViewModel(model);
    userInformationViewModel = new UserInformationViewModel(model);
  }

  public ListingsViewModel getListingsViewModel()
  {
    return listingsViewModel;
  }

  public LoginViewModel getLoginViewModel()
  {
    return loginViewModel;
  }

  public UserInformationViewModel getUserInformationViewModel()
  {
    return userInformationViewModel;
  }
}
