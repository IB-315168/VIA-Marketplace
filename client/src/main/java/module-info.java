module client {
  requires javafx.graphics;
  requires javafx.fxml;
  requires javafx.controls;
  requires java.desktop;

  opens com.sep2zg4.viamarket.client.view to javafx.fxml;

  exports com.sep2zg4.viamarket.client.view;
}