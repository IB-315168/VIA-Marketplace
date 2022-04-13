package com.sep2zg4.viamarket.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest
{
  private User test;

  @BeforeEach void setUp()
  {
    System.out.println("--> setUp()");
  }

  @AfterEach void tearDown()
  {
    System.out.println("<-- tearDown()");
  }

  @Test void setZero() {
    assertThrows(IllegalArgumentException.class, () -> {
      test = new User("", "", false);
    });
  }

  @Test void setOne() {
    System.out.println("--> Id");
    assertThrows(IllegalArgumentException.class, () -> {
      test = new User("315168", "", false);
    });

    System.out.println("--> fullname");
    assertThrows(IllegalArgumentException.class, () -> {
      test = new User("", "Igor Bulinski", false);
    });
  }

  @Test void setExceptions() {
    assertThrows(IllegalArgumentException.class, () -> {
      test = new User(null, null, false);
    });
  }
}