package org.ifpi.bibliotecaif.model.dao;

import org.ifpi.bibliotecaif.factory.DbConnectionFactory;

import java.io.IOException;
import java.sql.SQLException;


/**
 * Classe que injeta as dependências e instancia o banco de dados
 */
public class DaoFactory {

    public static LiteraryBookDao createLiteraryBookDao() throws SQLException, IOException {
        return new LiteraryBookDaoImpl(DbConnectionFactory.connect());
    }

    public static DidacticBookDao createDidacticBookDao() throws SQLException, IOException {
        return new DidacticBookDaoImpl(DbConnectionFactory.connect());
    }
}
