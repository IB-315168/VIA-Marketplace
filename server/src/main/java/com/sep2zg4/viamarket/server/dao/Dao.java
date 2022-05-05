package com.sep2zg4.viamarket.server.dao;

import java.sql.SQLException;
import java.util.List;

public interface Dao<Type>
{
  Type getById(String id) throws SQLException; //Reason why ID is a string is because user ID's are a string.
  List<Type> getAll() throws SQLException;
  void save(Type type) throws SQLException;
  void update(Type type, String id) throws SQLException;
  void delete(Type type);
}
