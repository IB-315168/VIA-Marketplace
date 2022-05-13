package com.sep2zg4.viamarket.servermodel;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ReadWriteAccess extends Remote
{
  ReadMap acquireRead() throws RemoteException;
  void releaseRead() throws RemoteException;
  WriteMap acquireWrite() throws RemoteException;
  void releaseWrite() throws RemoteException;
}
