package org.ifpi.bibliotecaif;

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
import org.ifpi.bibliotecaif.model.entities.Book;
import org.ifpi.bibliotecaif.model.entities.DidacticBook;
import org.ifpi.bibliotecaif.model.entities.enums.Subject;
import org.ifpi.bibliotecaif.model.services.DidacticBookService;
import org.ifpi.bibliotecaif.utils.Alerts;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ListDidacticBooksController implements Initializable {

    @FXML
    private TableView<Book> tableViewBook;

    @FXML
    private TableColumn<DidacticBook, String> tableColumnId;
    @FXML
    private TableColumn<DidacticBook, String> tableColumnTitle;
    @FXML
    private TableColumn<DidacticBook, String> tableColumnAuthor;
    @FXML
    private TableColumn<DidacticBook, String> tableColumnPublisher;
    @FXML
    private TableColumn<DidacticBook, String> tableColumnIsbn;
    @FXML
    private TableColumn<DidacticBook, Subject> tableColumnSubject;

    @FXML
    private Button mudarTipoButton;

    @FXML
    public void onMudarTipoButton() {
        loadView("ListLiteraryBook.fxml");
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

        DidacticBookService service = new DidacticBookService();

        ObservableList<Book> list = service.findAll();

        tableColumnId.setCellValueFactory(new PropertyValueFactory<>("Id"));
        tableColumnTitle.setCellValueFactory(new PropertyValueFactory<>("Title"));
        tableColumnAuthor.setCellValueFactory(new PropertyValueFactory<>("Author"));
        tableColumnPublisher.setCellValueFactory(new PropertyValueFactory<>("Publisher"));
        tableColumnIsbn.setCellValueFactory(new PropertyValueFactory<>("Isbn"));
        tableColumnSubject.setCellValueFactory(new PropertyValueFactory<>("Subject"));

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
