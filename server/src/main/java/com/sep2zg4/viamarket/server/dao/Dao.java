package com.sep2zg4.viamarket.server.dao;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;

public interface Dao<Type>
{
  Type getById(String id) throws
      SQLException, RemoteException; //Reason why ID is a string is because user ID's are a string.
  List<Type> getAll() throws SQLException, RemoteException;
  //Create/Type of object to be saved
  void create(Type type) throws SQLException, RemoteException;
  //Update(Type, ID of Item to be Saved)
  void update(Type type) throws SQLException, RemoteException;
  void delete(Type type) throws SQLException, RemoteException;
}
