package com.sep2zg4.viamarket.client.viewmodel;

import com.sep2zg4.viamarket.client.model.MarketplaceModel;
import com.sep2zg4.viamarket.model.Listing;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.rmi.RemoteException;
import java.sql.SQLException;

/** A view model for ListingForm
 *
 * @author Wojtek Rusinski
 * @version 1.0 - May 2022
 */
public class ListingFormViewModel
{
  private MarketplaceModel model;
  private StringProperty title,city,price,condition,phoneNumber, email, socialMediaAndUsername,description;

  /** Constructor for ListingForm view model
   *
   * @param model Model Manager reference
   */
  public ListingFormViewModel(MarketplaceModel model){
    this.model=model;

    this.title= new SimpleStringProperty("");
    this.city= new SimpleStringProperty("");
    this.price= new SimpleStringProperty("");
    this.condition= new SimpleStringProperty("");
    this.phoneNumber= new SimpleStringProperty("");
    this.email= new SimpleStringProperty("");
    this.socialMediaAndUsername= new SimpleStringProperty("");
    this.description=new SimpleStringProperty("");
  }

  public StringProperty getListingTitle()
  {
    return title;
  }

  public StringProperty getListingCity()
  {
    return city;
  }

  public StringProperty getListingPrice()
  {
    return price;
  }

  public StringProperty getListingCondition()
  {
    return condition;
  }

  public StringProperty getListingEmail()
  {
    return email;
  }

  public StringProperty getListingPhoneNumber()
  {
    return phoneNumber;
  }

  public StringProperty getListingSocialMediaAndUsername()
  {
    return socialMediaAndUsername;
  }

  public StringProperty getListingDescription()
  {
    return description;
  }

  /**
   * Method used for creating a Listing
   */
  //set(id, title, description, price, city, condition, seller);
  //there is , no make new id method
  //how do i assign seller without any User object reference?
  public void createListing() throws SQLException, RemoteException
  {

    model.createListing(new Listing(1, getListingTitle().get(),
        getListingDescription().get(), Double.parseDouble(
        getListingPrice().get()), getListingCity().get(),
        getListingCondition().get(), model.getCurrentUser() ));
  }
}