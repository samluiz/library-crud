package org.ifpi.bibliotecaif;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.ifpi.bibliotecaif.model.entities.Book;
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

public class EditOrDeleteBookController implements Initializable {


    private List<Book> books = new ArrayList<>();
    private ObservableList<Book> obsBooks;
    private List<Subject> subjects = new ArrayList<>();
    private ObservableList<Subject> obsSubjects;
    private List<Genre> genres = new ArrayList<>();
    private ObservableList<Genre> obsGenres;
    private List<ParentalRating> parentalRatings = new ArrayList<>();
    private ObservableList<ParentalRating> obsParentalRatings;

    @FXML
    private Button updateButton;

    @FXML
    private Button deleteButton;

    @FXML
    private ToggleGroup type;

    @FXML
    private RadioButton cbDidactic;

    @FXML
    private RadioButton cbLiterary;

    @FXML
    void onRadioDidacticSelect() throws SQLException {
        cbbSubject.setDisable(false);
        cbbGenre.setDisable(true);
        cbbParentalRating.setDisable(true);
        loadDidacticBooks();
    }

    @FXML
    void onRadioLiterarySelect() throws SQLException {
        cbbGenre.setDisable(false);
        cbbSubject.setDisable(true);
        cbbParentalRating.setDisable(false);
        loadLiteraryBooks();
    }

    @FXML
    private ComboBox<ParentalRating> cbbParentalRating;

    @FXML
    private ComboBox<Genre> cbbGenre;

    @FXML
    private ComboBox<Subject> cbbSubject;

    @FXML
    private ComboBox<Book> cbbBooks;

    @FXML
    private TextField txtAuthor;

    @FXML
    private TextField txtPublisher;

    @FXML
    private TextField txtIsbn;

    @FXML
    private TextField txtTitle;

    @FXML
    void onAtualizarButtonAction(ActionEvent event) throws SQLException {
        Integer id = cbbBooks.getSelectionModel().getSelectedItem().getId();
        String title = txtTitle.getText();
        String author = txtAuthor.getText();
        String publisher = txtPublisher.getText();
        String isbn = txtIsbn.getText();
        if (cbLiterary.isSelected()) {
            LiteraryBookService service = new LiteraryBookService();
            Genre genre = cbbGenre.getSelectionModel().getSelectedItem();
            ParentalRating pr = cbbParentalRating.getSelectionModel().getSelectedItem();
            LiteraryBook book = new LiteraryBook(id, title, author, publisher, isbn, genre, pr);
            try {
                service.insertOrUpdate(book);
            } catch (SQLException e) {
                throw new SQLException(e);
            }
        } else {
            DidacticBookService service = new DidacticBookService();
            Subject subject = cbbSubject.getSelectionModel().getSelectedItem();
            DidacticBook book = new DidacticBook(id, title, author, publisher, isbn, subject);
            try {
                service.insertOrUpdate(book);
            } catch (SQLException e) {
                throw new SQLException(e);
            }
        }
        reset();
        }

    @FXML
    void onDeleteButtonAction(ActionEvent event) throws SQLException {
        if (cbLiterary.isSelected()) {
            LiteraryBook book = (LiteraryBook) cbbBooks.getSelectionModel().getSelectedItem();
            LiteraryBookService service = new LiteraryBookService();
            service.remove(book);
        } else {
            DidacticBook book = (DidacticBook) cbbBooks.getSelectionModel().getSelectedItem();
            DidacticBookService service = new DidacticBookService();
            service.remove(book);
        }
        txtTitle.setText("");
        txtAuthor.setText("");
        txtPublisher.setText("");
        txtIsbn.setText("");
        cbbSubject.setDisable(true);
        cbLiterary.setSelected(true);
        cbbParentalRating.setValue(ParentalRating.FORALL);
        loadLiteraryBooks();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            loadLiteraryBooks();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        loadParentalRatings();
        loadGenres();
        loadSubjects();
        cbbSubject.setDisable(true);
        cbLiterary.setSelected(true);
        cbbParentalRating.setValue(ParentalRating.FORALL);
    }
    public void loadGenres() {
        for(Genre g : Genre.values()) {
            genres.add(g);
        }
        obsGenres = FXCollections.observableArrayList(genres);
        cbbGenre.setItems(obsGenres);
    }

    public void loadSubjects() {
        for(Subject s : Subject.values()) {
            subjects.add(s);
        }
        obsSubjects = FXCollections.observableArrayList(subjects);
        cbbSubject.setItems(obsSubjects);
    }

    public void loadParentalRatings() {
        for(ParentalRating pr : ParentalRating.values()) {
            parentalRatings.add(pr);
        }
        obsParentalRatings = FXCollections.observableArrayList(parentalRatings);
        cbbParentalRating.setItems(obsParentalRatings);
    }

    public void loadLiteraryBooks() throws SQLException {
        LiteraryBookService service = new LiteraryBookService();
        ObservableList<Book> list = service.findAll();

        if (books != null && !books.isEmpty()) {
            books.clear();
        }
        if (obsBooks != null) {
            obsBooks.clear();
        }

        for(Book b : list) {
            books.add(b);
        }
        obsBooks = FXCollections.observableArrayList(books);
        cbbBooks.setItems(obsBooks);
    }

    public void loadDidacticBooks() throws SQLException {
        DidacticBookService service = new DidacticBookService();
        ObservableList<Book> list = service.findAll();

        if (books != null && !books.isEmpty()) {
            books.clear();
        }
        if (obsBooks != null) {
            obsBooks.clear();
        }
        for(Book b : list) {
            books.add(b);
        }
        obsBooks = FXCollections.observableArrayList(books);
        cbbBooks.setItems(obsBooks);
    }

    public void reset() throws SQLException {
        txtTitle.setText("");
        txtAuthor.setText("");
        txtPublisher.setText("");
        txtIsbn.setText("");
        cbbSubject.setDisable(true);
        cbLiterary.setSelected(true);
        cbbParentalRating.setValue(ParentalRating.FORALL);
        loadLiteraryBooks();
    }
}
