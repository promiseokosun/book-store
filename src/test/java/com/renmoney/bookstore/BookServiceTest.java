package com.renmoney.bookstore;

import com.renmoney.bookstore.constant.BookStatus;
import com.renmoney.bookstore.dto.BookParams;
import com.renmoney.bookstore.model.Book;
import com.renmoney.bookstore.model.Borrower;
import com.renmoney.bookstore.model.Contributor;
import com.renmoney.bookstore.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class BookServiceTest {


    private final BookService bookService;
    private Book book;
    private  List<Book> books;
    private Integer bookId;
    private String bookStatus;
    private String searchTerm;

    @Autowired
    public BookServiceTest(BookService bookService) {
        this.bookService = bookService;
    }

    @Test
    public void test_getAllBooks() {
        givenThatUserIsLoggedIn();
        whenGetAllBooksInInvoked();
        thenReturnAlistOfBooks();
    }

    private void givenThatUserIsLoggedIn() {

    }

    private void whenGetAllBooksInInvoked() {
        books = bookService.getAllBooks();
    }

    private void thenReturnAlistOfBooks() {
        assertTrue(books.size() > 0);
    }

    @Test
    public void test_addBook() {
        givenThatUserIsLoggedIn();
        whenAddBookIsInvoked();
        thenBookShouldBeCreated();
    }

    private void whenAddBookIsInvoked() {
        book = bookService.addBook(new BookParams("Test", "Test Description", "Promise Okosun",
                new Contributor("Ade", "ade@gmail.com")));
        assertNotNull(book);
    }

    private void thenBookShouldBeCreated() {
        assertNotNull(book);
    }

    @Test
    public void test_editBook() {
        givenThatUserIsLoggedIn();
        whenEditBookIsInvoked();
        thenBookShouldBeUpdated();
    }

    private void whenEditBookIsInvoked() {
        book = bookService.editBook(1, new BookParams("Test Edit", "Test Description", "Promise Okosun",
                new Contributor("Ade", "ade@gmail.com")));
    }

    private void thenBookShouldBeUpdated() {
        assertNotNull(book);
    }

    @Test
    public void test_deleteBook() {
        givenThatUserIsLoggedIn();
        whenDeleteBookIsInvoked();
        thenTheBookShouldBeRemoved();
    }

    private void whenDeleteBookIsInvoked() {
        bookId = 1;
        bookService.deleteBook(bookId);
    }

    private void thenTheBookShouldBeRemoved() {
        Book book = bookService.getBookById(bookId);
        assertNull(book);
    }

    @Test
    public void test_lendBook() {
        givenThatUserIsLoggedIn();
        whenLendBookIsInvoked();
        thenBookStatusShouldBeSetToBORROWED();
    }

    private void whenLendBookIsInvoked() {
        bookId = 3;
        book = bookService.lendBook(bookId, new Borrower("Kingsley James", "kings@yahoo.com"));
        bookStatus = book.getStatus().name();
    }

    private void thenBookStatusShouldBeSetToBORROWED() {
        assertTrue(bookStatus.equals(BookStatus.BORROWED.name()));
    }

    @Test
    public void test_getBooksByStatus() {
        givenThatUserIsLoggedIn();
        whenGetBooksByStatusIsInvoked();
        thenReturnAListOfBooks();
    }

    private void whenGetBooksByStatusIsInvoked() {
        books = bookService.getBooksByStatus(BookStatus.AVAILABLE);
    }

    private void thenReturnAListOfBooks() {
        assertTrue(books.size() > 0);
    }

    @Test
    public void test_searchBooksWithValidData() {
        givenThatUserIsLoggedIn();
        whenSearchBooksIsInvokedParsingValidBookTitleOrDescriptionOrContributorName();
        thenReturnAListOfBooks();
    }

    private void whenSearchBooksIsInvokedParsingValidBookTitleOrDescriptionOrContributorName() {
        searchTerm = "Punishment";
        books = bookService.searchBooks(searchTerm);
    }


    @Test
    public void test_searchBooksWithInValidData() {
        givenThatUserIsLoggedIn();
        whenSearchBooksIsInvokedWithInvalidData();
        thenReturnEmptyList();
    }

    private void whenSearchBooksIsInvokedWithInvalidData() {
        searchTerm = "yehdhdhdhdhdhhddsk";
        books = bookService.searchBooks(searchTerm);
    }

    private void thenReturnEmptyList() {
        assertTrue(books.size() == 0);
    }


}
