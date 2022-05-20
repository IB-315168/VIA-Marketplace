package com.sep2zg4.viamarket.servermodel;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 *
 * @author Igor Bulinski
 * @version 1.0 - May 2022
 */

public interface ReadWriteAccess extends Remote
{
  /**
   *
   * @return
   * @throws RemoteException if error in Server side
   */
  ReadMap acquireRead() throws RemoteException;

  /**
   *
   * @throws RemoteException if error in Server side
   */
  void releaseRead() throws RemoteException;

  /**
   *
   * @return
   * @throws RemoteException if error in Server side
   */
  WriteMap acquireWrite() throws RemoteException;

  /**
   *
   * @throws RemoteException if error in Server side
   */
  void releaseWrite() throws RemoteException;
}
