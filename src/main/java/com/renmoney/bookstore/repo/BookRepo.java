package com.renmoney.bookstore.repo;

import com.renmoney.bookstore.constant.BookStatus;
import com.renmoney.bookstore.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepo extends JpaRepository<Book, Integer> {

    List<Book> findAll();

    /**
     * Return a list of books for a given status.
     * @param status
     * @return a list of books
     */
    List<Book> findByStatus(BookStatus status);


    // TODDO 1. write my custom query to fetch all book based on the search term.
    @Query("SELECT b FROM Book b WHERE lower(b.title) like :searchTerm or lower(b.author) like :searchTerm or " +
            "lower(b.description) like :searchTerm or lower(b.contributor.name) like :searchTerm")
    List<Book> searchBooks(@Param("searchTerm") String searchTerm);


}
