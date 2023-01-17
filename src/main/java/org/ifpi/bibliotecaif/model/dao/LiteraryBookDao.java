package org.ifpi.bibliotecaif.model.dao;

import javafx.collections.ObservableList;
import org.ifpi.bibliotecaif.model.entities.Book;
import org.ifpi.bibliotecaif.model.entities.LiteraryBook;

import java.sql.SQLException;


/**
 * Interface que define como a DAO deve ser implementada
 */
public interface LiteraryBookDao {

    void insert(LiteraryBook book) throws SQLException;
    void update(LiteraryBook book) throws SQLException;
    void deleteById(Integer id) throws SQLException;
    ObservableList<Book> findAll() throws SQLException;
}
