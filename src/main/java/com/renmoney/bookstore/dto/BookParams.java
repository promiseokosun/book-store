package com.renmoney.bookstore.dto;


import com.renmoney.bookstore.model.Contributor;

public class BookParams {

    private String title;

    private String description;

    private String Author;

    private Contributor contributor;

    public BookParams() {
    }

    public BookParams(String title, String description, String author, Contributor contributor) {
        this.title = title;
        this.description = description;
        Author = author;
        this.contributor = contributor;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public Contributor getContributor() {
        return contributor;
    }

    public void setContributor(Contributor contributor) {
        this.contributor = contributor;
    }

    @Override
    public String toString() {
        return "BookParam{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", Author='" + Author + '\'' +
                ", contributor=" + contributor +
                '}';
    }
}
