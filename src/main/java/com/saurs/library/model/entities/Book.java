package com.saurs.library.model.entities;

/**
 * Classe abstrata que extende as classes filhas com atributos comuns entre elas
 */
public abstract class Book {

    private Integer id;
    private String title;
    private String author;
    private String publisher;
    private String isbn;

    public Book(Integer id, String title, String author, String publisher) {

    }

    public Book(Integer id, String title, String author, String publisher, String isbn) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.isbn = isbn;
    }

    public Book(String title, String author, String publisher) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Override
    public String toString() {
        return "ID: " + id + " - TITULO: " + title;
    }
}
