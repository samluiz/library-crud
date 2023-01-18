package com.saurs.library.model.dao;

import javafx.collections.ObservableList;
import com.saurs.library.model.entities.Book;
import com.saurs.library.model.entities.LiteraryBook;

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
