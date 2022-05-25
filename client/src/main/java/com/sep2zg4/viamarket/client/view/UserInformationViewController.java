package com.sep2zg4.viamarket.client.view;

import com.sep2zg4.viamarket.client.viewmodel.UserInformationViewModel;
import com.sep2zg4.viamarket.model.Listing;
import com.sep2zg4.viamarket.model.User;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Region;

import java.rmi.RemoteException;
import java.sql.SQLException;

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
  @FXML private ListView<Listing> userWishList;
  @FXML private MenuItem create;
  @FXML private MenuItem edit;
  @FXML private MenuItem remove;
  @FXML private Label title;
  @FXML private Label price;
  @FXML private Label city;
  @FXML private Label condition;
  @FXML private Label contacts;
  @FXML private TextArea description;



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
    this.userName.setText(viewModel.getFullName());
    this.userListings.setItems(viewModel.getUserListings());
    this.userWishList.setItems(viewModel.getUserWishlist());
    this.userListings.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Listing>()
    {
      @Override public void changed(
          ObservableValue<? extends Listing> observable, Listing oldValue,
          Listing newValue)
      {
        viewModel.setCurrentSelectedUserListing(newValue);
      }
    });
    this.userWishList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Listing>()
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
          contacts.setText(newValue.getSeller().getFullName() + "\n" + newValue.getSeller().getEmail()
                  + "\n" + newValue.getSeller().getPhoneNumber());
          description.setText(newValue.getDescription());
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
    viewHandler.openView(ViewHandler.LISTINGS);
  }
  /**
   * A function used to open the listing form window upon pushing a button on the screen
   */
  @FXML public void create(){
    viewModel.setCurrentSelectedUserListing(null);
    viewHandler.closeView();
    viewHandler.openView(ViewHandler.LISTINGFORM);
  }
  /**
   * A function used to open the listing form window upon pushing a button on the screen
   */
  @FXML public void edit(){
    viewHandler.closeView();
    viewHandler.openView(ViewHandler.LISTINGFORM);
  }
  /**
   * A function remove listing upon selection on the screen
   */
  @FXML public void remove() throws SQLException, RemoteException {
    Listing listing = userListings.getSelectionModel().getSelectedItem();
    viewModel.deleteListing(listing);
  }
  @FXML public void deleteWishlistItem(){
    Listing wishlistListing = userWishList.getSelectionModel().getSelectedItem();
    try
    {
      viewModel.deleteWishlistItem(wishlistListing);
    }
    catch (SQLException e)
    {
      throw new RuntimeException(e);
    }
    catch (RemoteException e)
    {
      throw new RuntimeException(e);
    }
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
