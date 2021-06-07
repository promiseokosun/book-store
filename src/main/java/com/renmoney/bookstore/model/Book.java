package com.renmoney.bookstore.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.renmoney.bookstore.constant.BookStatus;

import javax.persistence.*;
import java.util.Date;


@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String title;

    private String description;

    private String author;

    @Enumerated(EnumType.STRING)
    private BookStatus status;

    private Date createdAt;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "contributor_id")
    private Contributor contributor;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "borrower_id")
    private Borrower borrower;

//    @OneToMany(mappedBy = "book")
//    @JsonIgnore
//    private Set<BookCategory> bookCategories = new HashSet<>();

    public Book() {
        setStatus(BookStatus.AVAILABLE);
        setCreatedAt(new Date());
    }

    public Book(String title, String description, String author, Contributor contributor) {
        this.title = title;
        this.description = description;
        this.author = author;
        this.contributor = contributor;
        setStatus(BookStatus.AVAILABLE);
        setCreatedAt(new Date());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public BookStatus getStatus() {
        return status;
    }

    public void setStatus(BookStatus status) {
        this.status = status;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Contributor getContributor() {
        return contributor;
    }

    public void setContributor(Contributor contributor) {
        this.contributor = contributor;
    }

    public Borrower getBorrower() {
        return borrower;
    }

    public void setBorrower(Borrower borrower) {
        this.borrower = borrower;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", author='" + author + '\'' +
                ", status=" + status +
                ", createdAt=" + createdAt +
                ", contributor=" + contributor +
                '}';
    }
}
