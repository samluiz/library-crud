package org.ifpi.bibliotecaif.model.dao;

import javafx.collections.ObservableList;
import org.ifpi.bibliotecaif.model.entities.Book;
import org.ifpi.bibliotecaif.model.entities.DidacticBook;

import java.sql.SQLException;


/**
 * Interface que define como a DAO deve ser implementada
 */
public interface DidacticBookDao {

    void insert(DidacticBook book) throws SQLException;
    void update(DidacticBook book) throws SQLException;
    void deleteById(Integer id) throws SQLException;
    ObservableList<Book> findAll() throws SQLException;
}
