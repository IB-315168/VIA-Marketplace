package com.sep2zg4.viamarket.server.dao;

import com.sep2zg4.viamarket.model.Listing;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ListingDAO implements Dao<Listing>
{
  private DAOManager manager = DAOManager.getInstance();
  private List<Listing> listOfListing;
  private Connection connection;

  public ListingDAO(Connection connection) throws SQLException{
    this.connection = connection;
  }

  @Override public Listing getById(String id) throws SQLException
  {
    //I would parse String to int but by doing so could end up in whenever someone types AZ32 it would take id as 32 and change something we dont want to
    int idInt = Integer.parseInt(id);
    String query = "SELECT * FROM listing WHERE id = ?";
    PreparedStatement selectStatemenet = connection.prepareStatement(query);
    selectStatemenet.setInt(1,idInt);
    ResultSet res = selectStatemenet.executeQuery();
    res.next();
    return new Listing(res.getInt("id"),res.getString("title"),res.getString("description"),res.getDouble("price"),res.getString("city"),res.getString("condition"),UserDAO.getById(res.getString("studentNumber")));
  }

  @Override public List<Listing> getAll() throws SQLException
  {
    String query = "SELECT * FROM listing";
    PreparedStatement selectStatement = connection.prepareStatement(query);
    ResultSet res = selectStatement.executeQuery();
    while(res.next()){
      listOfListing.add(new Listing(res.getInt("id"),res.getString("title"),res.getString("description"),res.getDouble("price"),res.getString("city"),res.getString("condition"),UserDAO.getById(res.getString("studentNumber"))));
    }
    return listOfListing;
  }

  @Override public void create(Listing listing) throws SQLException
  {
    String query = "INSERT INTO listing (id,title,description,price,city,condition,studentNumber) VALUES ?,?,?,?,?,?,?";
    PreparedStatement insertStatement = connection.prepareStatement(query);
    insertStatement.setInt(1,listing.getId());
    insertStatement.setString(2,listing.getTitle());
    insertStatement.setString(3,listing.getDescription());
    insertStatement.setDouble(4,listing.getPrice());
    insertStatement.setString(5,listing.getCity());
    insertStatement.setString(6,listing.getCondition());
    insertStatement.setInt(7,listing.getSeller().getId());
    insertStatement.executeUpdate();
  }

  @Override public void update(Listing listing) throws SQLException
  {
    String query = "UPDATE listing SET title=?,description=?,price=?,city=?,condition=?,studentNumber=? WHERE id=? ;";
    PreparedStatement updateStatement = connection.prepareStatement(query);
    updateStatement.setString(1,listing.getTitle());
    updateStatement.setString(2,listing.getDescription());
    updateStatement.setDouble(3,listing.getPrice());
    updateStatement.setString(4,listing.getCity());
    updateStatement.setString(5,listing.getCondition());
    updateStatement.setInt(6,listing.getSeller().getId());
    updateStatement.setInt(7,listing.getId());
    updateStatement.executeUpdate();
  }

  @Override public void delete(Listing listing) throws SQLException
  {
    String query = "DELETE FROM listing WHERE id=?";
    PreparedStatement deleteStatement = connection.prepareStatement(query);
    deleteStatement.setInt(1,listing.getId());
    deleteStatement.executeUpdate();
  }

}
