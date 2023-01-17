package org.ifpi.bibliotecaif.model.entities;

import org.ifpi.bibliotecaif.model.entities.enums.Subject;


/**
 * Classe do tipo Livro Didático
 */
public class DidacticBook extends Book {

    private Subject subject;

    public DidacticBook(Integer id, String titulo, String autor, String editora, String isbn, Subject subject) {
        super(id, titulo, autor, editora, isbn);
        this.subject = subject;
    }

    public DidacticBook(String titulo, String autor, String editora, Subject subject) {
        super(titulo, autor, editora);
        this.subject = subject;
    }

    public Subject getAssunto() {
        return subject;
    }

    public void setAssunto(Subject subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
