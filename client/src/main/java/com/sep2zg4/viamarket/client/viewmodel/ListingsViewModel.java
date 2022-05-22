package com.sep2zg4.viamarket.client.viewmodel;

import com.sep2zg4.viamarket.client.model.MarketplaceModel;
import com.sep2zg4.viamarket.model.Listing;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.beans.PropertyChangeListener;
import java.rmi.RemoteException;
import java.sql.SQLException;
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

    model.addPropertyChangeListener(evt -> {
      setListingsList();
      setCategoryList();
    });
  }
  public void setListingsList() {
    listingsList.setAll(model.getAllListings());
  }
  public void getByCategory(String categoryName) { listingsList.setAll(model.getCategoryListing(categoryName));}

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

  public Object getNewCategoryName()
  {
    return categoryName;
  }

  public void trigger()
  {
    model.trigger();
  }

  public  void deleteListing(Listing listing) throws SQLException, RemoteException {
    model.deleteListing(listing);
  }
  public void deleteCategory(String category) throws SQLException, RemoteException {
    model.deleteCategory(category);
  }
  public void createCategory(String categoryName) throws SQLException, RemoteException {
    model.createCategory(categoryName);
  }

  public boolean isModerator(){
    return model.isModerator();
  };

}
