package com.sep2zg4.viamarket.server.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
    String query = "SELECT * FROM category WHERE id = ?";
    PreparedStatement selectStatement = connection.prepareStatement(query);
    selectStatement.setInt(1, idInt);
    ResultSet res = selectStatement.executeQuery();
    res.next();
    return res.getString("name");
  }

  @Override public List<String> getAll() throws SQLException
  {
    ArrayList<String> categories = new ArrayList<>();
    String query = "SELECT * FROM category?";
    PreparedStatement selectStatemenet = connection.prepareStatement(query);
    ResultSet res = selectStatemenet.executeQuery();
    while (res.next())
    {
      categories.add(res.getString("name"));
    }
    return categories;
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
}
