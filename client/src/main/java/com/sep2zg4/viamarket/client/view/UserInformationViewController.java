package com.sep2zg4.viamarket.client.view;

import com.sep2zg4.viamarket.client.viewmodel.UserInformationViewModel;
import com.sep2zg4.viamarket.model.Listing;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;

/**
 * Controller class for UserInformationView.fxml
 *
 * @author Rojus Paukste
 * @version 1.0 - April 2022
 */
public class UserInformationViewController
{
  @FXML private Label userName;
  @FXML private ListView<Listing> userListings;

  private ViewHandler viewHandler;
  private UserInformationViewModel viewModel;
  private Region root;

  /**
   * A constructor/function of ListingsViewController containing actions to be made by the system upon initialization
   *
   * @param viewHandler
   * @param viewModel
   * @param root
   */
  public void init(ViewHandler viewHandler, UserInformationViewModel viewModel,
      Region root)
  {
    this.viewHandler = viewHandler;
    this.viewModel = viewModel;
    this.root = root;
    this.userListings.setItems(viewModel.getUserListings());
  }

  /**
   * A function used to open the previous (Log-in) window upon pushing a button on the screen
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
