package com.sep2zg4.viamarket.client.viewmodel;

import com.sep2zg4.viamarket.client.model.MarketplaceModel;
import com.sep2zg4.viamarket.model.Listing;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

/**
 * A viewmodel for Listings view
 *
 * @author Wojtek Rusinski
 * @version 1.0 - April 2022
 */
public class ListingsViewModel
{
  private MarketplaceModel model;
  private ObservableList<Listing> listingsList;
  private ObservableList<String> categoryList;
  private StringProperty userType;
  private Object categoryName;

  /**
   * Constructor for ListingsViewModel taking Model as an argument
   *
   * @param model Model Manager reference
   */
  public ListingsViewModel(MarketplaceModel model)
  {
    this.model = model;
    this.userType = new SimpleStringProperty("");
    this.listingsList = FXCollections.observableList(model.getAllListings());
    this.categoryList = FXCollections.observableList(model.getAllCategories());
  }

  public void setListingsList() {
    listingsList.setAll(model.getAllListings());
  }

  public void setCategoryList() {
    categoryList.setAll(model.getAllCategories());
  }
  public ObservableList<String> getCategoryList()
  {
    return categoryList;
  }

  public ObservableList<Listing> getListingsList()
  {
    return listingsList;
  }

  public StringProperty getUserType()
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
