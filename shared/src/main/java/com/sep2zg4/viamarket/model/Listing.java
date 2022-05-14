package com.sep2zg4.viamarket.model;

import java.io.Serializable;
import java.util.Locale;

/**
 * Class representing an item on the marketplace.
 *
 * @author Igor Bulinski
 * @version 1.0 - April 2022
 */
public class Listing implements Serializable
{
  private int id; //Remove, obsolete
  private String title;
  private String description;
  private double price;
  private String city;
  private String condition;
  private User seller;

  /**
   * 7-argument constructor with constraints from {@link com.sep2zg4.viamarket.model.Listing#set(int, String, String, double, String, String, User)} method
   * @param id listings id, required bigger than 0
   * @param title listings title, required not-blank
   * @param description listings description, required not-blank
   * @param price listings price, required bigger than 0.0
   * @param city listings location (i.e. where is the listing available for pick-up)
   * @param condition listed item condition, required to be either "New", "Used" or "Defective"
   * @param seller {@link com.sep2zg4.viamarket.model.User} listings owner
   * @throws IllegalArgumentException if argument conditions are not met
   */
  public Listing(int id, String title, String description, double price, String city,
      String condition, User seller) throws IllegalArgumentException
  {
    set(id, title, description, price, city, condition, seller);
  }

  /**
   * 7-argument method for setting listings properties
   * @param id listings id, required bigger than 0
   * @param title listings title, required not-blank
   * @param description listings description, required not-blank
   * @param price listings price, required bigger than 0.0
   * @param city listings location (i.e. where is the listing available for pick-up)
   * @param condition listed item condition, required to be either "New", "Used" or "Defective"
   * @param seller {@link com.sep2zg4.viamarket.model.User} listings owner
   * @throws IllegalArgumentException if argument conditions are not met
   */
  public void set(int id, String title, String description, double price, String city,
      String condition, User seller) throws IllegalArgumentException {
    if(id < 1) {
      throw new IllegalArgumentException("Id cannot be lower than zero");
    }
    this.id = id;

    if(title == null || title.isBlank()) {
      throw new IllegalArgumentException("Title cannot be empty.");
    }
    this.title = title;

    if(description == null || description.isBlank()) {
      throw new IllegalArgumentException("Description cannot be empty");
    }
    this.description = description;

    if(price < 0.0) {
      throw new IllegalArgumentException("Price cannot be lower than zero");
    }
    this.price = price;

    this.city = city;

    if(condition == null || !(condition.toLowerCase(Locale.ROOT).equals("new") || condition.toLowerCase(Locale.ROOT).equals("used") || condition.toLowerCase(
        Locale.ROOT).equals("defective"))) {
      throw new IllegalArgumentException("Condition must be either New, Used or Defective");
    }
    this.condition = condition;

    if(seller == null) {
      throw new IllegalArgumentException("Seller cannot be null");
    }
    this.seller = seller;
  }

  public int getId()
  {
    return id;
  }

  public String getTitle()
  {
    return title;
  }

  public String getDescription()
  {
    return description;
  }

  public double getPrice()
  {
    return price;
  }

  public String getCity()
  {
    return city;
  }

  public String getCondition()
  {
    return condition;
  }

  public User getSeller()
  {
    return seller;
  }

  @Override public String toString() {
    return title + " - " + price;
  }
}
