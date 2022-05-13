package com.sep2zg4.viamarket.server.listingaccess;

import com.sep2zg4.viamarket.server.RemoteMarketplaceImplementation;

public interface ReadWriteAccess
{
  ReadMap acquireRead();
  void releaseRead();
  WriteMap acquireWrite();
  void releaseWrite();
}