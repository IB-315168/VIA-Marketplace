package com.sep2zg4.viamarket.client.view;

import com.sep2zg4.viamarket.client.viewmodel.ViewModelFactory;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * An MVVM pattern class used for managing different stages of the application
 *
 * @author Rojus Paukste
 * @version 1.0 - April 2022
 */

public class ViewHandler
{
  public static final String LOGIN = "login";
  public static final String LISTINGS = "listings";
  public static final String USERINFO = "userInfo";
  private Scene currentScene;
  private Stage primaryStage;
  private final ViewFactory viewFactory;

  /**
   * Constructor function for the ViewHandler
   *
   * @param viewModelFactory
   */
  public ViewHandler(ViewModelFactory viewModelFactory)
  {
    this.viewFactory = new ViewFactory(this, viewModelFactory);
    this.currentScene = new Scene(new Region());
  }

  /**
   * A function that initializes the primary stage and opens up the first view for the user which is log in
   *
   * @param primaryStage
   */
  public void start(Stage primaryStage)
  {
    this.primaryStage = primaryStage;
    openView(LOGIN);
    primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
      @Override
      public void handle(WindowEvent t) {
        Platform.exit();
        System.exit(0);
      }
    });
  }

  /**
   * Function used for opening a different view depending on the keyword sent to the function
   *
   * @param view
   */
  public void openView(String view)
  {
    Region root = switch (view)
        {
          case LOGIN -> viewFactory.loadLogInView();
          case LISTINGS -> viewFactory.loadListingsView();
          case USERINFO -> viewFactory.loadUserInformationView();
          default -> throw new IllegalArgumentException("Unknown bruh.view");
        };
    currentScene.setRoot(root);
    if (root.getUserData() == null)
      primaryStage.setTitle("");
    else
      primaryStage.setTitle(root.getUserData().toString());
    primaryStage.setScene(currentScene);
    primaryStage.sizeToScene();
    primaryStage.show();
  }

  public void closeView()
  {
    primaryStage.close();
  }
}
