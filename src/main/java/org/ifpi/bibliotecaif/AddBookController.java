package org.ifpi.bibliotecaif;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.ifpi.bibliotecaif.model.entities.DidacticBook;
import org.ifpi.bibliotecaif.model.entities.LiteraryBook;
import org.ifpi.bibliotecaif.model.entities.enums.Subject;
import org.ifpi.bibliotecaif.model.entities.enums.ParentalRating;
import org.ifpi.bibliotecaif.model.entities.enums.Genre;
import org.ifpi.bibliotecaif.model.services.DidacticBookService;
import org.ifpi.bibliotecaif.model.services.LiteraryBookService;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AddBookController implements Initializable {


    private List<Subject> subjects = new ArrayList<>();
    private ObservableList<Subject> obsSubjects;
    private List<Genre> genres = new ArrayList<>();
    private ObservableList<Genre> obsGenres;
    private List<ParentalRating> parentalRatings = new ArrayList<>();
    private ObservableList<ParentalRating> obsParentalRatings;
    @FXML
    private TextField txtAuthor;

    @FXML
    private TextField txtPublisher;

    @FXML
    private TextField txtISBN;

    @FXML
    private TextField txtTitle;

    @FXML
    private ToggleGroup type;

    @FXML
    private RadioButton rbDidactic;

    @FXML
    private RadioButton rbLiterary;

    @FXML
    void onRadioDidacticSelect() {
        cbbSubject.setDisable(false);
        cbbGenre.setDisable(true);
        cbbParentalRating.setDisable(true);
    }

    @FXML
    void onRadioLiterarySelect() {
        cbbGenre.setDisable(false);
        cbbSubject.setDisable(true);
        cbbParentalRating.setDisable(false);
    }

    @FXML
    private ComboBox<Subject> cbbSubject;

    @FXML
    private ComboBox<Genre> cbbGenre;

    @FXML
    private ComboBox<ParentalRating> cbbParentalRating;

    @FXML
    private Button addButton;

    @FXML
    void onAddButtonAction(ActionEvent event) throws SQLException {
        String title = txtTitle.getText();
        String author = txtAuthor.getText();
        String publisher = txtPublisher.getText();
        String isbn = txtISBN.getText();
        if (rbLiterary.isSelected()) {
            LiteraryBookService service = new LiteraryBookService();
            Genre genre = cbbGenre.getSelectionModel().getSelectedItem();
            ParentalRating pr = cbbParentalRating.getSelectionModel().getSelectedItem();
            LiteraryBook book = new LiteraryBook(null, title, author, publisher, isbn, genre, pr);
            try {
                service.insertOrUpdate(book);
            } catch (SQLException e) {
                throw new SQLException(e);
            }
        } else {
            DidacticBookService service = new DidacticBookService();
            Subject subject = cbbSubject.getSelectionModel().getSelectedItem();
            DidacticBook book = new DidacticBook(null, title, author, publisher, isbn, subject);
            try {
                service.insertOrUpdate(book);
            } catch (SQLException e) {
                throw new SQLException(e);
            }
        }
        txtTitle.setText("");
        txtAuthor.setText("");
        txtPublisher.setText("");
        txtISBN.setText("");
        cbbParentalRating.setValue(ParentalRating.FORALL);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadSubjects();
        loadGenres();
        loadParentalRatings();
        cbbSubject.setDisable(true);
        rbLiterary.setSelected(true);
        cbbParentalRating.setValue(ParentalRating.FORALL);
    }

    public void loadSubjects() {
        for(Subject s : Subject.values()) {
            subjects.add(s);
        }
        obsSubjects = FXCollections.observableArrayList(subjects);
        cbbSubject.setItems(obsSubjects);
    }

    public void loadGenres() {
        for(Genre g : Genre.values()) {
            genres.add(g);
        }
        obsGenres = FXCollections.observableArrayList(genres);
        cbbGenre.setItems(obsGenres);
    }

    public void loadParentalRatings() {
        for(ParentalRating pr : ParentalRating.values()) {
            parentalRatings.add(pr);
        }
        obsParentalRatings = FXCollections.observableArrayList(parentalRatings);
        cbbParentalRating.setItems(obsParentalRatings);
    }

}
