package com.renmoney.bookstore.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Borrower {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String borrowerName;

    private String borrowerEmail;

    private Date returnDate;

    private boolean returned;

    private Integer borrowedBookId;

    private String borrowedBookTitle;

    @OneToMany(mappedBy = "borrower")
    private List<Book> books;

    public Borrower() {
    }

    public Borrower(String borrowerName, String borrowerEmail) {
        this.borrowerName = borrowerName;
        this.borrowerEmail = borrowerEmail;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBorrowerName() {
        return borrowerName;
    }

    public void setBorrowerName(String borrowerName) {
        this.borrowerName = borrowerName;
    }

    public String getBorrowerEmail() {
        return borrowerEmail;
    }

    public void setBorrowerEmail(String borrowerEmail) {
        this.borrowerEmail = borrowerEmail;
    }

    @JsonIgnore
    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public boolean isReturned() {
        return returned;
    }

    @JsonIgnore
    public void setReturned(boolean returned) {
        this.returned = returned;
    }

    public Integer getBorrowedBookId() {
        return borrowedBookId;
    }

    @JsonIgnore
    public void setBorrowedBookId(Integer borrowedBookId) {
        this.borrowedBookId = borrowedBookId;
    }

    public String getBorrowedBookTitle() {
        return borrowedBookTitle;
    }

    @JsonIgnore
    public void setBorrowedBookTitle(String borrowedBookTitle) {
        this.borrowedBookTitle = borrowedBookTitle;
    }

    public void addBook(Book book) {
        if(books.isEmpty() || books == null) {
            books = new ArrayList<>();
        }
        books.add(book);
    }

    @Override
    public String toString() {
        return "Borrower{" +
                "id=" + id +
                ", borrowerName='" + borrowerName + '\'' +
                ", borrowerEmail='" + borrowerEmail + '\'' +
                ", returnDate=" + returnDate +
                ", returned=" + returned +
                ", borrowedBookId=" + borrowedBookId +
                ", borrowedBookTitle='" + borrowedBookTitle + '\'' +
                ", books=" + books +
                '}';
    }
}
