package com.saurs.library.model.dao;

import com.saurs.library.model.entities.Book;
import com.saurs.library.model.entities.DidacticBook;
import com.saurs.library.model.entities.enums.Subject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import com.saurs.library.factory.DbConnectionFactory;

import java.sql.*;


/**
 * Implementação da interface DAO
 */
public class DidacticBookDaoImpl implements DidacticBookDao {


    private Connection connection;

    // Instanciando uma conexão com o banco de dados no construtor
    public DidacticBookDaoImpl(Connection connection) {
        this.connection = connection;
    }


    // Método que insere um livro didático no banco de dados.
    @Override
    public void insert(DidacticBook book) throws SQLException {
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement("INSERT INTO livros_didaticos "
                    + "(titulo, autor, editora, isbn, "
                    + "assunto) "
                    + "VALUES "
                    + "(?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getAuthor());
            stmt.setString(3, book.getPublisher());
            stmt.setString(4, book.getIsbn());
            stmt.setString(5, book.getSubject().toString());


            // Gerando um id auto incrementado pelo banco de dados
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    book.setId(id);
                }
                DbConnectionFactory.closeResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbConnectionFactory.closeStatement(stmt);
        }
    }

    // Método que modifica um item do banco de dados baseado no id
    @Override
    public void update(DidacticBook book) throws SQLException {
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(
                    "UPDATE livros_literarios " +
                            "SET titulo = IFNULL(NULLIF(?, ''), titulo), " +
                            "autor = IFNULL(NULLIF(?, ''), autor)" +
                            "editora = IFNULL(NULLIF(?, ''), editora)" +
                            "isbn = IFNULL(NULLIF(?, ''), isbn)" +
                            "assunto = IFNULL(NULLIF(?, ''), assunto)" +
                            "WHERE id = ?");
            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getAuthor());
            stmt.setString(3, book.getPublisher());
            stmt.setString(4, book.getIsbn());
            stmt.setString(5, book.getSubject() == null ? null : book.getSubject().toString());
            stmt.setInt(7, book.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbConnectionFactory.closeStatement(stmt);
        }
    }


    // Método que deleta um item do banco de dados com base em seu id
    @Override
    public void deleteById(Integer id) throws SQLException {
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(
                    "DELETE FROM livros_didaticos WHERE id = ?"
            );
            stmt.setInt(1, id);

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbConnectionFactory.closeStatement(stmt);
        }
    }


    // Método que busca todos os livros didáticos do banco de dados
    @Override
    public ObservableList<Book> findAll() throws SQLException {
        ObservableList<Book> listaLivros = FXCollections.observableArrayList();
        PreparedStatement stmt;
        ResultSet rs;
        stmt = connection.prepareStatement("SELECT * FROM livros_didaticos");
        rs = stmt.executeQuery();
        Book livro;
        while(rs.next()) {
            livro = new DidacticBook(rs.getInt("id"),
                    rs.getString("titulo"),
                    rs.getString("autor"),
                    rs.getString("editora"),
                    rs.getString("isbn"),
                    Subject.valueOf(rs.getString("assunto")));
            listaLivros.add(livro);
        }
        DbConnectionFactory.closeStatement(stmt);
        DbConnectionFactory.closeResultSet(rs);
        return listaLivros;
    }
}
