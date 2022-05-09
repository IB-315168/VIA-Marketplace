package com.sep2zg4.viamarket.client.viewmodel;

import com.sep2zg4.viamarket.client.model.MarketplaceModel;
import com.sep2zg4.viamarket.model.Listing;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/** A view model for ListingForm
 *
 * @author Wojtek Rusinski
 * @version 1.0 - May 2022
 */
public class ListingFormViewModel
{
  private MarketplaceModel model;
  private StringProperty title,city,price,condition,phoneNumber, email, socialMediaAndUsername;

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

  public void createListing(){
    //idk what this should do to save, ill ask on meeting
  }
}
