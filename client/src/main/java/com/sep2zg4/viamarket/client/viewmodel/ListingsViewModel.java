package com.sep2zg4.viamarket.client.viewmodel;

import com.sep2zg4.viamarket.client.model.MarketplaceModel;
import javafx.beans.property.Property;

/**
 * A viewmodel for Listings view
 *
 * @author Wojtek Rusinski
 * @version 1.0 - April 2022
 */
public class ListingsViewModel
{
  private MarketplaceModel model;
  private Property categoryList, listingsList;
  private Property<String> userType;
  private Object categoryName;

  /**
   * Constructor for ListingsViewModel taking Model as an argument
   *
   * @param model Model Manager reference
   */
  public ListingsViewModel(MarketplaceModel model)
  {
    this.model = model;
  }

  public void setModel(MarketplaceModel model)
  {
    this.model = model;
  }

  public void setCategoryName(Object categoryName)
  {
    this.categoryName = categoryName;
  }

  public void setListingsList(Property listingsList)
  {
    this.listingsList = listingsList;
  }

  public void setUserType(Property<String> userType)
  {
    this.userType = userType;
  }

  public Property getCategoryList()
  {
    return categoryList;
  }

  public Property getListingsList()
  {
    return listingsList;
  }

  public Property<String> getUserType()
  {
    return userType;
  }

  public void getListingProperties()
  {
  }

  public Object getNewCategoryName()
  {
    return categoryName;
  }
}
