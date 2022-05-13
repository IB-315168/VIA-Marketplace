package com.sep2zg4.viamarket.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ListingTest
{
  private Listing test;

  @org.junit.jupiter.api.BeforeEach void setUp()
  {
    System.out.println("--> setUp()");
    //test = new Listing(1, "none", "none", 1.00, "none", "New", new User("315168", "Igor Bulinski", false));
  }

  @org.junit.jupiter.api.AfterEach void tearDown()
  {
    System.out.println("<-- tearDown()");
  }

  @Test void setZero() {
    assertThrows(IllegalArgumentException.class, () -> {
      test.set(1, "","",0, "", "",null);
    });
  }

  @Test void setOne() {
    System.out.println("--> title");
    assertThrows(IllegalArgumentException.class, () -> {
      test.set(1, "Example title","",0, "", "",null);
    });

    System.out.println("--> desc");
    assertThrows(IllegalArgumentException.class, () -> {
      test.set(1,"","Example description",0, "", "",null);
    });

    System.out.println("--> condition");
    assertThrows(IllegalArgumentException.class, () -> {
      test.set(1,"","",0, "", "Example condition",null);
    });

    System.out.println("--> seller");
    assertThrows(IllegalArgumentException.class, () -> {
      test.set(1,"","",0, "", "",new User("315168", "Igor Bulinski", false));
    });
  }

  @Test void setBoundary() {
    System.out.println("--> id");
    assertThrows(IllegalArgumentException.class, () -> {
      test.set(0,"Example title","Example description",-1, "", "New",new User("315168", "Igor Bulinski", false));
    });

    System.out.println("--> price");
    assertThrows(IllegalArgumentException.class, () -> {
      test.set(1,"Example title","Example description",-1, "", "New",new User("315168", "Igor Bulinski", false));
    });

    System.out.println("--> condition");
    assertThrows(IllegalArgumentException.class, () -> {
      test.set(1,"Example title","Example description",0, "", "ner",new User("315168", "Igor Bulinski", false));
    });
  }

  @Test void setException() {
    assertThrows(IllegalArgumentException.class, () -> {
      test.set(0, null,null,0, null, null,null);
    });
  }
}