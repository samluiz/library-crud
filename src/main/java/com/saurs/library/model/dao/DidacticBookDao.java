package com.saurs.library.model.dao;

import com.saurs.library.model.entities.Book;
import com.saurs.library.model.entities.DidacticBook;
import javafx.collections.ObservableList;

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
