package com.sep2zg4.viamarket.client.view;

import com.sep2zg4.viamarket.client.viewmodel.ListingFormViewModel;
import com.sep2zg4.viamarket.client.viewmodel.ListingsViewModel;
import com.sep2zg4.viamarket.model.Listing;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Region;

import javafx.event.ActionEvent;

import java.rmi.RemoteException;
import java.sql.SQLException;

public class ListingFormViewController
{
  @FXML private TextField title;
  @FXML private TextField city;
  @FXML private TextField price;
  @FXML private ToggleGroup condition;
  @FXML private ChoiceBox<String> category;
  @FXML private TextArea description;
  @FXML private Button saveButton;
  @FXML private RadioButton conditionDefective;
  @FXML private RadioButton conditionUsed;
  @FXML private RadioButton conditionNew;

  private ViewHandler viewHandler;
  private ListingFormViewModel viewModel;
  private Region root;

  /**
   * A constructor/function of ListingFormViewController containing actions to be made by the system upon initialization
   *
   * @param viewHandler
   * @param viewModel
   * @param root
   */
  public void init(ViewHandler viewHandler, ListingFormViewModel viewModel,
      Region root)
  {
    this.viewHandler = viewHandler;
    this.viewModel = viewModel;
    this.root = root;

    this.conditionNew.setUserData("New");
    this.conditionUsed.setUserData("Used");
    this.conditionDefective.setUserData("Defective");

    this.category.setItems(viewModel.getAllCategories());
    this.title.textProperty().bindBidirectional(viewModel.getListingTitle());
    this.city.textProperty().bindBidirectional(viewModel.getListingCity());
    this.price.textProperty().bindBidirectional(viewModel.getListingPrice());
    this.description.textProperty().bindBidirectional(
        viewModel.getListingDescription());
    this.condition.selectedToggleProperty().addListener(new ChangeListener<Toggle>()
    {
      @Override public void changed(
          ObservableValue<? extends Toggle> observable, Toggle oldValue,
          Toggle newValue)
      {
        viewModel.getListingCondition().setValue(newValue.getUserData().toString());
      }
    });
    /*this.socialMedia.textProperty().bindBidirectional(viewModel.getListingSocialMedia());
    this.username.textProperty().bindBidirectional(viewModel.getListingUsername());*/
  }

  /**
   * A function used to save a listing and open the previous (UserInformation) window upon pushing a button on the screen
   */
  @FXML public void save() throws SQLException, RemoteException
  {
    viewModel.createListing(category.getSelectionModel().getSelectedItem());
    viewHandler.closeView();
    viewHandler.openView(ViewHandler.USERINFO);
  }
  /**
   * A function used to open the previous window(ListingsView) upon pushing a button on the screen
   */
  @FXML public void goBack()
  {
    viewHandler.closeView();
    viewHandler.openView(ViewHandler.LISTINGS);
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
}
