package org.ifpi.bibliotecaif.model.entities;

import org.ifpi.bibliotecaif.model.entities.enums.ParentalRating;
import org.ifpi.bibliotecaif.model.entities.enums.Genre;

/**
 * Classe do tipo Livro Literário
 */
public class LiteraryBook extends Book {
    private Genre genre;
    private ParentalRating parentalRating;

    public LiteraryBook(Integer id, String titulo, String autor, String editora, String isbn, Genre genre, ParentalRating parentalRating) {
        super(id, titulo, autor, editora, isbn);
        this.genre = genre;
        this.parentalRating = parentalRating;
    }

    public Genre getGenero() {
        return genre;
    }

    public void setGenero(Genre genre) {
        this.genre = genre;
    }

    public ParentalRating getClassificacaoIndicativa() {
        return parentalRating;
    }

    public void setClassificacaoIndicativa(ParentalRating parentalRating) {
        this.parentalRating = parentalRating;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
