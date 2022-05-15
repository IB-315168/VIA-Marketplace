package com.sep2zg4.viamarket.server.dao;

import com.sep2zg4.viamarket.model.Listing;

import java.beans.PropertyChangeListener;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.beans.PropertyChangeSupport;

public class ListingDAO implements Dao<Listing>
{
  private DAOManager manager = DAOManager.getInstance();
  private Connection connection;
  private UserDAO userDAO;

  public ListingDAO(Connection connection) throws SQLException, RemoteException
  {
    this.connection = connection;
    this.userDAO = (UserDAO) manager.getDao(DAOManager.Table.User);
  }

  @Override public Listing getById(String id)
      throws SQLException, RemoteException
  {
    //I would parse String to int but by doing so could end up in whenever someone types AZ32 it would take id as 32 and change something we dont want to
    int idInt = Integer.parseInt(id);
    String query = "SELECT * FROM listing WHERE id = ?";
    PreparedStatement selectStatement = connection.prepareStatement(query);
    selectStatement.setInt(1, idInt);
    ResultSet res = selectStatement.executeQuery();
    res.next();
    String categoryQuery = "SELECT name FROM category WHERE idCategory = ?";
    selectStatement = connection.prepareStatement(categoryQuery);
    selectStatement.setInt(1, res.getInt("idCategory"));
    ResultSet categorySet = selectStatement.executeQuery();
    categorySet.next();
    return new Listing(res.getInt("id"), categorySet.getString(1), res.getString("title"),
        res.getString("description"), res.getDouble("price"),
        res.getString("city"), res.getString("condition"),
        userDAO.getById(res.getString("studentNumber")));
  }

  @Override public List<Listing> getAll() throws SQLException, RemoteException
  {
    List<Listing> listOfListing = new ArrayList<>();
    String query = "SELECT * FROM listing";
    PreparedStatement selectStatement = connection.prepareStatement(query);
    ResultSet res = selectStatement.executeQuery();
    while (res.next())
    {
      listOfListing.add(new Listing(res.getInt("id"), null , res.getString("title"),
          res.getString("description"), res.getDouble("price"),
          res.getString("city"), res.getString("condition"),
          userDAO.getById(res.getString("studentNumber"))));
    }
    return listOfListing;
  }

  @Override public void create(Listing listing) throws RemoteException, SQLException
  {
    String query = "INSERT INTO listing (title,description,price,city,condition,studentNumber,idCategory) VALUES (?,?,?,?,?,?,?)";
    PreparedStatement insertStatement = connection.prepareStatement(query);
    insertStatement.setString(1, listing.getTitle());
    insertStatement.setString(2, listing.getDescription());
    insertStatement.setDouble(3, listing.getPrice());
    insertStatement.setString(4, listing.getCity());
    insertStatement.setString(5, listing.getCondition());
    insertStatement.setInt(6, listing.getSeller().getId());
    String categoryQuery = "SELECT idCategory FROM category WHERE name = ?";
    PreparedStatement selectStatement = connection.prepareStatement(categoryQuery);
    selectStatement.setString(1, listing.getCategoryName());
    ResultSet categorySet = selectStatement.executeQuery();
    categorySet.next();
    insertStatement.setInt(7, categorySet.getInt(1));
    //insertStatement.setInt(8,?);
    try {
      insertStatement.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    }
    System.out.println("DAO done");
  }

  @Override public void update(Listing listing) throws SQLException
  {
    String query = "UPDATE listing SET title=?,description=?,price=?,city=?,condition=?,studentNumber=?,idCategory=? WHERE id=? ;";
    PreparedStatement updateStatement = connection.prepareStatement(query);
    updateStatement.setString(1, listing.getTitle());
    updateStatement.setString(2, listing.getDescription());
    updateStatement.setDouble(3, listing.getPrice());
    updateStatement.setString(4, listing.getCity());
    updateStatement.setString(5, listing.getCondition());
    updateStatement.setInt(6, listing.getSeller().getId());
    updateStatement.setInt(7, listing.getId());
    updateStatement.executeUpdate();
  }

  @Override public void delete(Listing listing)
      throws SQLException, RemoteException
  {
    String query = "DELETE FROM listing WHERE id=?";
    PreparedStatement deleteStatement = connection.prepareStatement(query);
    deleteStatement.setInt(1, listing.getId());
    deleteStatement.executeUpdate();
  }

}
