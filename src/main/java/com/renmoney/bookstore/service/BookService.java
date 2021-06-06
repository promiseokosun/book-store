package com.renmoney.bookstore.service;

import com.renmoney.bookstore.constant.BookStatus;
import com.renmoney.bookstore.dto.BookParams;
import com.renmoney.bookstore.model.Book;
import com.renmoney.bookstore.model.Borrower;

import java.util.List;

public interface BookService {


    List<Book> getAllBooks();

    Book getBookById(Integer bookId);

    Book addBook(BookParams params);

    Book editBook(Integer bookId, BookParams params);

    void deleteBook(Integer bookId);

    Book lendBook(Integer bookId, Borrower borrower);

    List<Book> getBooksByStatus(BookStatus status);

    List<Book> searchBooks(String searchTerm);

}
