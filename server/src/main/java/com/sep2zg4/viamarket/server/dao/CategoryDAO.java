package com.sep2zg4.viamarket.server.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class CategoryDAO implements Dao<String>
{
  private DAOManager manager = DAOManager.getInstance();
  private Connection connection;

  public CategoryDAO(Connection connection) throws SQLException
  {
    this.connection = connection;
  }

  @Override public String getById(String id) throws SQLException
  {
    int idInt = Integer.parseInt(id);
    String query = "SELECT * FROM listing WHERE id = ?";
    PreparedStatement selectStatemenet = connection.prepareStatement(query);
    selectStatemenet.setInt(1,idInt);
    ResultSet res = selectStatemenet.executeQuery();
    res.next();
  }

  @Override public List<String> getAll() throws SQLException
  {
    return null;
  }

  @Override public void create(String s) throws SQLException
  {

  }

  @Override public void update(String s) throws SQLException
  {

  }

  @Override public void delete(String s) throws SQLException
  {

  }

  public
}
