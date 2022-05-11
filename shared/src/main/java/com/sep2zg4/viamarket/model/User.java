package com.sep2zg4.viamarket.model;

import java.io.Serializable;

/**
 * Class representing user on the marketplace.
 *
 * @author Igor Bulinski
 * @version 1.0 - April 2022
 */
public class User implements Serializable
{
  private String id;
  private String fullName;
  private boolean isModerator;

  /**
   * 3-argument constructor.
   * @param id Id for the user, required not-blank
   * @param fullName Full name of the user, required not-blank
   * @param isModerator parameter stating user privileges <ul><li>false - user has standard access</li><li>true - user has moderator privileges</li></ul>
   * @throws IllegalArgumentException if argument conditions are not met
   */
  public User(String id, String fullName, boolean isModerator) throws IllegalArgumentException
  {
    if(id == null || id.isBlank()) {
      throw new IllegalArgumentException("Email cannot be empty");
    }
    this.id = id;

    if(fullName == null || fullName.isBlank()) {
      throw new IllegalArgumentException("Full name cannot be empty.");
    }
    this.fullName = fullName;

    this.isModerator = isModerator;
  }

  public String getId()
  {
    return id;
  }

  public String getFullName()
  {
    return fullName;
  }

  public boolean isModerator()
  {
    return isModerator;
  }
}
