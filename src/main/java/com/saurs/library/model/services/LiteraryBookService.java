package com.saurs.library.model.services;

import com.saurs.library.model.dao.LiteraryBookDao;
import com.saurs.library.model.entities.Book;
import com.saurs.library.model.entities.LiteraryBook;
import javafx.collections.ObservableList;
import com.saurs.library.model.dao.DaoFactory;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Serviço da DAO
 */
public class LiteraryBookService
{
    private LiteraryBookDao dao;

    {
        try {
            dao = DaoFactory.createLiteraryBookDao();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ObservableList<Book> findAll() throws SQLException {
        return dao.findAll();
    }

    public void insertOrUpdate(LiteraryBook book) throws SQLException {
        if (book.getId() == null) {
            dao.insert(book);
        } else {
            dao.update(book);
        }
    }

    public void remove(LiteraryBook book) throws SQLException {
        dao.deleteById(book.getId());
    }
}
