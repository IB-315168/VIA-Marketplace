package com.sep2zg4.viamarket.server;

import com.sep2zg4.viamarket.model.Listing;
import com.sep2zg4.viamarket.server.dao.DAOManager;
import com.sep2zg4.viamarket.server.dao.LoginHandler;
import com.sep2zg4.viamarket.servermodel.RemoteMarketplace;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Start
{
  public static void main(String[] args)
      throws RemoteException, AlreadyBoundException, SQLException,
      InterruptedException
  {
    /*
    Registry registry = LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
    RemoteMarketplace comm = new RemoteMarketplaceImplementation();
    registry.bind("comm", comm);
    System.out.println("Server running on " + Registry.REGISTRY_PORT);
    test(comm);
    if(comm.login(315236,"derciofernandes")) {
      System.out.println("success");
    } else {
      System.out.println("fail");
    }*/
    Connection connection = DriverManager.getConnection(
        "jdbc:postgresql://abul.db.elephantsql.com/unnmkiby", "unnmkiby",
        "9rQAlABdHOKbbTS46V662goUMd2IjnKZ");
    LoginHandler loginHandler = new LoginHandler(connection);

    System.out.println(loginHandler.attemptLogin(111111,"admin1234"));


  }

  private static void test(RemoteMarketplace comm)
      throws SQLException, RemoteException
  {
    System.out.println(comm.getAllListing().get("Slaves").get(0).getTitle());
  }
}
