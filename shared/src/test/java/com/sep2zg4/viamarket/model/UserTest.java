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
      test = new User(null, null, false);
    });
  }
}