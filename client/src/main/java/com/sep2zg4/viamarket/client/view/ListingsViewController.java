package com.sep2zg4.viamarket.client.view;

import com.sep2zg4.viamarket.client.viewmodel.ListingsViewModel;
import com.sep2zg4.viamarket.model.Listing;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Region;

/**
 * Controller class for ListingsView.fxml
 *
 * @author Rojus Paukste
 * @version 1.0 - April 2022
 */

public class ListingsViewController
{
  @FXML private ListView<String> categoryList;
  @FXML private ListView<Listing> listingsList;
  @FXML private Label title;
  @FXML private Label price;
  @FXML private Label city;
  @FXML private Label condition;
  @FXML private Label contacts;
  @FXML private TextArea description;
  @FXML private Label loggedAs;
  @FXML private MenuItem usersInformation;
  @FXML private MenuItem back;

  private ViewHandler viewHandler;
  private ListingsViewModel viewModel;
  private Region root;

  /**
   * A constructor/function of ListingsViewController containing actions to be made by the system upon initialization
   *
   * @param viewHandler
   * @param viewModel
   * @param root
   */
  public void init(ViewHandler viewHandler, ListingsViewModel viewModel,
      Region root)
  {
    this.viewHandler = viewHandler;
    this.viewModel = viewModel;
    this.root = root;

    this.categoryList.setItems(viewModel.getCategoryList());
    this.listingsList.setItems(viewModel.getListingsList());
    this.loggedAs.textProperty().bindBidirectional(viewModel.getUserType());
    viewModel.setListingsList();
    viewModel.setCategoryList();

//    Only for debug/testing purposes - open USERINFO View
    this.listingsList.setOnKeyPressed(new EventHandler<KeyEvent>()
    {
      @Override public void handle(KeyEvent event)
      {
        if (event.getCode() == KeyCode.A)
        {
          viewModel.trigger();
        }
      }
    });
    this.listingsList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Listing>()
    {
      @Override public void changed(
          ObservableValue<? extends Listing> observable, Listing oldValue,
          Listing newValue)
      {
        if(newValue != null)
        {
          title.setText(newValue.getTitle());
          price.setText(String.valueOf(newValue.getPrice()));
          city.setText(newValue.getCity());
          condition.setText(newValue.getCondition());
        }
      }
    });
  }

  /**
   * A function used to open the previous (Log-in) window upon pushing a button on the screen
   */
  @FXML public void goBack()
  {
    viewHandler.closeView();
    viewHandler.openView(ViewHandler.LOGIN);
  }
  /**
   * A function used to open the user information window upon pushing a button on the screen
   */
  @FXML public void userInformation(){
    viewHandler.closeView();
    viewHandler.openView(ViewHandler.USERINFO);
  }

  /**
   * A function returning the root
   *
   * @return
   */
  public Region getRoot()
  {
    return root;
  }

  public void reset() {
  }
}
