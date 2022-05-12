package com.sep2zg4.viamarket.client.view;

import com.sep2zg4.viamarket.client.viewmodel.ListingFormViewModel;
import com.sep2zg4.viamarket.client.viewmodel.ListingsViewModel;
import com.sep2zg4.viamarket.model.Listing;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Region;

import javafx.event.ActionEvent;

public class ListingFormViewController
{
  @FXML private TextField title;
  @FXML private TextField city;
  @FXML private TextField price;
  @FXML private Label condition;
  @FXML private TextField socialMedia;
  @FXML private TextField username;
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


    this.title.textProperty().bindBidirectional(viewModel.getListingTitle());
    this.city.textProperty().bindBidirectional(viewModel.getListingCity());
    this.price.textProperty().bindBidirectional(viewModel.getListingPrice());
    this.condition.textProperty().bindBidirectional(viewModel.getListingCondition());
    this.socialMedia.textProperty().bindBidirectional(viewModel.getListingSocialMedia());
    this.username.textProperty().bindBidirectional(viewModel.getListingUsername());
  }

  /**
   * A function used for setting the condition label to a specific value according to the selected radio button, to be later used in the viewModel
   * @param event
   */
  @FXML public void getCondition(ActionEvent event)
  {
    if(conditionNew.isSelected())
      condition.setText(conditionNew.getText());
    else if(conditionUsed.isSelected())
      condition.setText(conditionUsed.getText());
    else if(conditionDefective.isSelected())
      condition.setText(conditionDefective.getText());
  }

  /**
   * A function used to save a listing and open the previous (UserInformation) window upon pushing a button on the screen
   */
  @FXML public void save()
  {
    viewModel.createListing();
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
}
