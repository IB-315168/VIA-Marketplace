package com.sep2zg4.viamarket.server.dao;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WishlistDAO implements Dao<Integer>
{

  private Connection connection;
  private int currentStudentNumber;

  public WishlistDAO(Connection connection) throws SQLException
  {
    this.connection = connection;
  }

  public void setCurrentStudentNumber(int currentStudentNumber)
  {
    this.currentStudentNumber = currentStudentNumber;
  }

  @Override public Integer getById(int id) throws SQLException, RemoteException
  {
    return null;
  }

  @Override public List<Integer> getAll() throws SQLException, RemoteException
  {
    ArrayList<Integer> wishlist = new ArrayList<>();
    String query = "SELECT * FROM wishlist WHERE studentNumber=?";
    PreparedStatement selectStatemenet = connection.prepareStatement(query);
    selectStatemenet.setInt(1,currentStudentNumber);
    ResultSet res = selectStatemenet.executeQuery();
    while (res.next())
    {
      wishlist.add(res.getInt("idListing"));
    }
    return wishlist;
  }

  @Override public void create(Integer idListing)
      throws SQLException, RemoteException
  {
    String query = "INSERT INTO wishlist (studentNumber,idListing) VALUES (?,?)";
    PreparedStatement insertStatement = connection.prepareStatement(query);
    insertStatement.setInt(1,currentStudentNumber);
    insertStatement.setInt(2,idListing);
    insertStatement.executeUpdate();
  }

  @Override public void update(Integer idListing)
      throws SQLException, RemoteException
  {

  }

  @Override public void delete(Integer idListing)
      throws SQLException, RemoteException
  {
    String query = "DELETE FROM wishlist WHERE studentNumber=? AND idListing=?";
    PreparedStatement deleteStatement = connection.prepareStatement(query);
    deleteStatement.setInt(1,currentStudentNumber);
    deleteStatement.setInt(2,idListing);
    deleteStatement.executeUpdate();
  }
}
