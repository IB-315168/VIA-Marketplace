package com.sep2zg4.viamarket.server;

import com.sep2zg4.viamarket.model.Listing;
import com.sep2zg4.viamarket.servermodel.RemoteMarketplace;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.SQLException;

public class Start
{
  public static void main(String[] args)
      throws RemoteException, AlreadyBoundException, SQLException,
      InterruptedException
  {
    Registry registry = LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
    RemoteMarketplace comm = new RemoteMarketplaceImplementation();
    registry.bind("comm", comm);
    registry.bind("lock", comm.getLock());
    System.out.println("Server running on " + Registry.REGISTRY_PORT);
//    Thread.sleep(5000);
//    System.out.println("Should update");
//    comm.exampleMethod();
//    test(comm);
//    if(comm.login(315236,"derciofernandes")) {
//      System.out.println("success");
//    } else {
//      System.out.println("fail");
//    }
  }

//  private static void test(RemoteMarketplace comm)
//      throws SQLException, RemoteException
//  {
//    System.out.println(comm.getAllListing());
//  }
}
