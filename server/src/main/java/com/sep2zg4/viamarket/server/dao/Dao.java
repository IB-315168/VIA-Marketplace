package com.sep2zg4.viamarket.server.dao;

import java.sql.SQLException;
import java.util.List;

public interface Dao<Type>
{
  Type getById(String id) throws
      SQLException; //Reason why ID is a string is because user ID's are a string.
  List<Type> getAll() throws SQLException;
  //Create/Type of object to be saved
  void create(Type type) throws SQLException;
  //Update(Type, ID of Item to be Saved)
  void update(Type type) throws SQLException;
  void delete(Type type) throws SQLException;
}
