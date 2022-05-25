package com.sep2zg4.viamarket.client.view;

import com.sep2zg4.viamarket.client.viewmodel.LoginViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;

/**
 * Controller class for LogInView.fxml
 *
 * @author Rojus Paukste
 * @version 1.0 - April 2022
 */
public class LoginViewController
{
  @FXML private TextField userNameTextField;
  @FXML private PasswordField userPasswordTextField;

  private ViewHandler viewHandler;
  private LoginViewModel viewModel;
  private Region root;

  /**
   * A constructor/function of ListingsViewController containing actions to be made by the system upon initialization
   *
   * @param viewHandler
   * @param viewModel
   * @param root
   */
  public void init(ViewHandler viewHandler, LoginViewModel viewModel,
      Region root)
  {
    this.viewHandler = viewHandler;
    this.viewModel = viewModel;
    this.root = root;

    userNameTextField.textProperty().bindBidirectional(viewModel.getUserName());
    userPasswordTextField.textProperty()
        .bindBidirectional(viewModel.getUserPassword());
  }

  /**
   * A function used to retrieve username and password and then open the main window which is ListingsView
   */
  @FXML public void logIn()
  {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    try {
      if(viewModel.login()) {
        viewHandler.openView(ViewHandler.LISTINGS);
      } else {
        alert.setContentText("Incorrect username and/or password");
        alert.show();
      }
      reset();
    } catch (Exception e) {
      e.printStackTrace();
      alert.setContentText(e.getMessage());
      alert.show();
    }

//    userNameTextField.getText();
//    userPasswordTextField.getText();
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
  public void reset()
  {
    userNameTextField.setText("");
    userPasswordTextField.setText("");
  }
}
