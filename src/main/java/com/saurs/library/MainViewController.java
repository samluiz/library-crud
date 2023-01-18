package com.saurs.library;

import com.saurs.library.utils.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainViewController implements Initializable {

    @FXML
    private MenuItem menuItemAdd;

    @FXML
    private MenuItem menuItemEdit;

    @FXML
    private MenuItem menuItemList;

    @FXML
    private MenuItem menuItemAbout;
    @FXML
    public void onMenuItemAdd() {
        loadView("AddBook.fxml");
    }

    @FXML
    public void onMenuItemEdit() {
        loadView("EditOrDeleteBook.fxml");
    }

    @FXML
    public void onMenuItemList() {
        loadView("ListLiteraryBook.fxml");
    }

    @FXML
    public void onMenuItemAbout() {
        loadView("About.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    private synchronized void loadView(String absName) {
        try {
            URL fxmlLocation = getClass().getResource(absName);
            FXMLLoader loader = new FXMLLoader(fxmlLocation);
            AnchorPane newPane = loader.load();
            Scene mainScene = Main.getMainScene();
            AnchorPane mainPane = (AnchorPane) ((ScrollPane) mainScene.getRoot()).getContent();
            Node mainMenu = mainPane.getChildren().get(0);
            mainPane.getChildren().clear();
            mainPane.getChildren().add(mainMenu);
            mainPane.getChildren().addAll(newPane.getChildren());
        } catch (IOException e) {
            Alerts.showAlert("IO Exception", "Erro ao carregar view", String.valueOf(e.getCause()), Alert.AlertType.ERROR);
        }
    }
}
