package com.saurs.library.model.services;

import com.saurs.library.model.dao.DidacticBookDao;
import com.saurs.library.model.entities.Book;
import com.saurs.library.model.entities.DidacticBook;
import javafx.collections.ObservableList;
import com.saurs.library.model.dao.DaoFactory;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Serviço da DAO
 */
public class DidacticBookService
{
    private DidacticBookDao dao;

    {
        try {
            dao = DaoFactory.createDidacticBookDao();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ObservableList<Book> findAll() throws SQLException {
        return dao.findAll();
    }

    public void insertOrUpdate(DidacticBook book) throws SQLException {
        if (book.getId() == null) {
            dao.insert(book);
        } else {
            dao.update(book);
        }
    }
    
    public void remove(DidacticBook book) throws SQLException {
        dao.deleteById(book.getId());
    }
}
