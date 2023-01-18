package com.saurs.library.model.entities;

import com.saurs.library.model.entities.enums.ParentalRating;
import com.saurs.library.model.entities.enums.Genre;

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

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public ParentalRating getParentalRating() {
        return parentalRating;
    }

    public void setParentalRating(ParentalRating parentalRating) {
        this.parentalRating = parentalRating;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
