package com.sep2zg4.viamarket.server.dao;

import com.sep2zg4.viamarket.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login
{

  Connection connection;

  public Login(Connection connection){
    this.connection = connection;
  }

  public boolean doLogin(int studentNumber, String password) throws SQLException
  {
    String query = "SELECT * FROM person WHERE studentNumber = ? AND password = ?";
    PreparedStatement selectStatemenet = connection.prepareStatement(query);
    selectStatemenet.setInt(1, studentNumber);
    selectStatemenet.setString(2, password);
    ResultSet res = selectStatemenet.executeQuery();
    res.next();
    if(!res.isBeforeFirst())
      return false;
    else
      return true;
  }
}
