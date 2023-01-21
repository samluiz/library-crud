package com.saurs.library;

import com.saurs.library.model.entities.Book;
import com.saurs.library.model.entities.LiteraryBook;
import com.saurs.library.model.entities.enums.Genre;
import com.saurs.library.model.entities.enums.ParentalRating;
import com.saurs.library.utils.Alerts;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import com.saurs.library.model.services.LiteraryBookService;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ListLiteraryBooksController implements Initializable {

    @FXML
    private TableView<Book> tableViewBook;

    @FXML
    private TableColumn<LiteraryBook, String> tableColumnId;
    @FXML
    private TableColumn<LiteraryBook, String> tableColumnTitle;
    @FXML
    private TableColumn<LiteraryBook, String> tableColumnAuthor;
    @FXML
    private TableColumn<LiteraryBook, String> tableColumnPublisher;
    @FXML
    private TableColumn<LiteraryBook, String> tableColumnIsbn;
    @FXML
    private TableColumn<LiteraryBook, Genre> tableColumnGenre;
    @FXML
    private TableColumn<LiteraryBook, ParentalRating> tableColumnParentalRating;

    @FXML
    private Button changeCategoryButton;

    @FXML
    public void onChangeCategoryButtonAction() {
        loadView("ListDidacticsBook.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            initializeNodes();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void initializeNodes() throws SQLException {

        LiteraryBookService service = new LiteraryBookService();
        ObservableList<Book> list = service.findAll();

        tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableColumnTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        tableColumnAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
        tableColumnPublisher.setCellValueFactory(new PropertyValueFactory<>("publisher"));
        tableColumnIsbn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        tableColumnGenre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        tableColumnParentalRating.setCellValueFactory(new PropertyValueFactory<>("parentalRating"));
        Stage stage = (Stage) Main.getMainScene().getWindow();
        tableViewBook.prefHeightProperty().bind(stage.heightProperty());
        tableViewBook.setItems(list);
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
            Alerts.showAlert("IO Exception", "Erro ao carregar view", e.getMessage(), Alert.AlertType.ERROR);
        }
    }
}
