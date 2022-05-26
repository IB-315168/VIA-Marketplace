package com.sep2zg4.viamarket.client.view;

import com.sep2zg4.viamarket.client.viewmodel.ListingsViewModel;
import com.sep2zg4.viamarket.model.Listing;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Region;

import java.nio.file.FileAlreadyExistsException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.*;

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
  @FXML private ToggleGroup sort;
  @FXML private RadioButton mostRecent;
  @FXML private RadioButton lowToHigh;
  @FXML private RadioButton HighToLow;
  @FXML private TextField searchKey;
  @FXML private MenuItem usersInformation;
  @FXML private MenuItem back;
  @FXML private Menu moderatorPanel;
  private ArrayList<Listing> searchResults;

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

    //Default FALSE - If theres a way to set this on the XML itself it would be PERFECT
    moderatorPanel.setVisible(false);


    this.categoryList.setItems(viewModel.getCategoryList());
    this.listingsList.setItems(viewModel.getListingsList());
    this.loggedAs.textProperty().bindBidirectional(viewModel.getUserType());
    viewModel.setListingsList();
    viewModel.setCategoryList();


    //Moderator Check
    if(viewModel.isModerator()){
      moderatorPanel.setVisible(true);
    }

    mostRecent.setUserData(1);
    lowToHigh.setUserData(2);
    HighToLow.setUserData(3);

    this.sort.selectedToggleProperty().addListener(new ChangeListener<Toggle>()
    {
      @Override public void changed(
          ObservableValue<? extends Toggle> observable, Toggle oldValue,
          Toggle newValue)
      {

        System.out.println(newValue.getUserData().toString());
        System.out.println(viewModel.getListingsList());
        switch (newValue.getUserData().toString()){
          case "1":
            Collections.sort(viewModel.getListingsList(), (o1, o2) -> (int) (o2.getId() - o1.getId()));
            Collections.sort(searchResults, (o1, o2) -> (int) (o2.getId() - o1.getId()));
            break;
          case "2":
            Collections.sort(viewModel.getListingsList(), (o1, o2) -> (int) (o1.getPrice() - o2.getPrice()));
            Collections.sort(searchResults, (o1, o2) -> (int) (o1.getPrice() - o2.getPrice()));
            break;
          case "3":
            Collections.sort(viewModel.getListingsList(), (o1, o2) -> (int) (o2.getPrice() - o1.getPrice()));
            Collections.sort(searchResults, (o1, o2) -> (int) (o2.getPrice() - o1.getPrice()));
            break;
        }


      }
    });

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

    this.categoryList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>()
    {
      @Override public void changed(
          ObservableValue<? extends String> observable, String oldValue,
          String newValue)
      {
        if(newValue == null) {
          viewModel.setListingsList();
        } else
        {
          viewModel.getByCategory(newValue);
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
    viewHandler.openView(ViewHandler.LOGIN);
  }
  /**
   * A function used to open the user information window upon pushing a button on the screen
   */
  @FXML public void userInformation(){
    viewHandler.closeView();
    viewHandler.openView(ViewHandler.USERINFO);
  }
  @FXML public void deleteListing() throws SQLException, RemoteException {
    if(viewModel.isModerator())
    {
      Listing listing = listingsList.getSelectionModel().getSelectedItem();
      viewModel.deleteListing(listing);
    }else{
      //Gotta learn how to make this into an alert.
      System.out.println("User does not have permission to do this action.");
    }
  }
  @FXML public void createCategory(ActionEvent event) throws SQLException, RemoteException {
    if(viewModel.isModerator())
    {
      TextInputDialog textInputDialog = new TextInputDialog();
      textInputDialog.setTitle("Create category");
      textInputDialog.getDialogPane().setContentText("Category name: ");
      Optional<String> result = textInputDialog.showAndWait();
      TextField input = textInputDialog.getEditor();
      viewModel.createCategory(input.getText());
    }else{
      //Gotta learn how to make this into an alert.
      System.out.println("User does not have permission to do this action.");
    }
  }

  @FXML public void deleteCategory() throws SQLException, RemoteException
  {
    if(viewModel.isModerator()){
      String category = categoryList.getSelectionModel().getSelectedItem();
      viewModel.deleteCategory(category);
    }else{
      //Gotta learn how to make this into an alert.
      System.out.println("User does not have permission to do this action.");
    }
  }
  /**
   * A function used to add item to wishlist
   */
  @FXML public void addToWishlist() {
    Listing wishlistListing = listingsList.getSelectionModel().getSelectedItem();
    viewModel.addToWishlist(wishlistListing);
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
    if(viewModel.isModerator()){
      moderatorPanel.setVisible(true);
    } else {
      moderatorPanel.setVisible(false);
    }
  }

  public void search(ActionEvent actionEvent)
  {
    searchResults = new ArrayList<Listing>();
    for (Listing listing : viewModel.getListingsList())
    {
      if(listing.getTitle().toLowerCase().contains(searchKey.getText().toLowerCase())){
        searchResults.add(listing);
      }
    }
    this.listingsList.setItems(FXCollections.observableList(searchResults));
    if(searchKey.getText().equals("")){
      this.listingsList.setItems(viewModel.getListingsList());
    }
  }
}
