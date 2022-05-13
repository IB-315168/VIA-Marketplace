package com.sep2zg4.viamarket.client;

import com.sep2zg4.viamarket.client.model.MarketplaceModelManager;
import com.sep2zg4.viamarket.client.view.ViewHandler;
import com.sep2zg4.viamarket.client.viewmodel.ViewModelFactory;
import javafx.application.Application;
import javafx.stage.Stage;

import java.rmi.RemoteException;

public class Start extends Application
{
  @Override public void start(Stage primaryStage) throws Exception
  {
    MarketplaceModelManager model = new MarketplaceModelManager();
    ViewModelFactory viewModelFactory = new ViewModelFactory(model);
    ViewHandler viewHandler = new ViewHandler(viewModelFactory);
    viewHandler.start(primaryStage);
  }
  public static void main(String[] args) throws RemoteException
  {
    launch();
  }
}
