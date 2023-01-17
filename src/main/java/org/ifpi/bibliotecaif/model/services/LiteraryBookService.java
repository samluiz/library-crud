package org.ifpi.bibliotecaif.model.services;

import javafx.collections.ObservableList;
import org.ifpi.bibliotecaif.model.dao.DaoFactory;
import org.ifpi.bibliotecaif.model.dao.LiteraryBookDao;
import org.ifpi.bibliotecaif.model.entities.Book;
import org.ifpi.bibliotecaif.model.entities.LiteraryBook;

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
