package com.sep2zg4.viamarket.server.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginHandler
{

  private Connection connection;

  public LoginHandler(Connection connection)
  {
    this.connection = connection;
  }

  public boolean attemptLogin(int studentNumber, String password)
      throws SQLException
  {
    String query = "SELECT * FROM person WHERE studentNumber = ? AND password = ?";
    PreparedStatement selectStatemenet = connection.prepareStatement(query);
    selectStatemenet.setInt(1, studentNumber);
    selectStatemenet.setString(2, password);
    ResultSet res = selectStatemenet.executeQuery();
    res.next();
    if (!res.isBeforeFirst())
      return false;
    else
      return true;
  }
}
