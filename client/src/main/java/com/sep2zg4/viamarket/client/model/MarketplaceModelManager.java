package com.sep2zg4.viamarket.client.model;

import com.sep2zg4.viamarket.client.model.comm.ClientMarketplaceCommunicator;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.sql.SQLException;

/**
 * Implementation of the {@link MarketplaceModel}
 *
 * @author Igor Bulinski
 * @version 1.0 - April 2022
 */
public class MarketplaceModelManager implements MarketplaceModel
{
  private ClientMarketplaceCommunicator client;

  /**
   * Constructor creating a {@link ClientMarketplaceCommunicator} object and establishing connection
   * @throws RemoteException
   */
  public MarketplaceModelManager() throws RemoteException
  {
    client = new ClientMarketplaceCommunicator("localhost", Registry.REGISTRY_PORT);
  }

  /**
   * 2-argument method for passing credentials to the {@link ClientMarketplaceCommunicator#login(int, String)} method and handling the result
   *
   * @param username username of the user
   * @param password matching password
   * @return result of {@link com.sep2zg4.viamarket.client.model.comm.ClientMarketplaceCommunicator#login(int, String)}
   * @throws RemoteException
   */
  public boolean login(int username, String password)
      throws RemoteException, NotBoundException, SQLException
  {
    return client.login(username, password);
  }
}
