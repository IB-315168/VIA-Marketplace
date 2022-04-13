package com.sep2zg4.viamarket.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ListingTest
{
  private Listing test;

  @org.junit.jupiter.api.BeforeEach void setUp()
  {
    System.out.println("--> setUp()");
    test = new Listing("none", "none", 1.00, "none", "New", new User("315168", "Igor Bulinski", false));
  }

  @org.junit.jupiter.api.AfterEach void tearDown()
  {
    System.out.println("<-- tearDown()");
  }

  @Test void setZero() {
    assertThrows(IllegalArgumentException.class, () -> {
      test.set(null,null,0, null, null,null);
    });
    assertThrows(IllegalArgumentException.class, () -> {
      test.set("","",0, "", "",null);
    });
  }

  @Test void setOne() {
    assertThrows(IllegalArgumentException.class, () -> {

    });
  }
}