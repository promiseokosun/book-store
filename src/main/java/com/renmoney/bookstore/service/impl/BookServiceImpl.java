package com.renmoney.bookstore.service.impl;

import com.renmoney.bookstore.constant.BookStatus;
import com.renmoney.bookstore.dto.BookParams;
import com.renmoney.bookstore.model.Book;
import com.renmoney.bookstore.model.Borrower;
import com.renmoney.bookstore.model.Contributor;
import com.renmoney.bookstore.repo.BookRepo;
import com.renmoney.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private BookRepo bookRepo;

    @Autowired
    public BookServiceImpl(BookRepo bookRepo) {
        this.bookRepo = bookRepo;
        loadDummyBooks();
    }


    @Override
    public List<Book> getAllBooks() {
        return bookRepo.findAll();
    }

    @Override
    public Book getBookById(Integer bookId) {
        Optional<Book> book = bookRepo.findById(bookId);
        return book.isPresent() ? book.get() : null;
    }

    @Override
    public Book addBook(BookParams params) {
        Book book = getNewBook(params);
        return bookRepo.save(book);
    }

    @Override
    public Book editBook(Integer bookId, BookParams params) {
        Book book = getBookById(bookId);

        if(book == null) {
            return null;
        }

        book.setTitle(params.getTitle());
        book.setDescription(params.getDescription());
        book.setCreatedAt(new Date());
        book.setAuthor(params.getAuthor());
        book.setContributor(params.getContributor());

        return bookRepo.save(book);
    }

    @Override
    public void deleteBook(Integer bookId) {
        bookRepo.deleteById(bookId);
    }

    @Override
    public Book lendBook(Integer bookId, Borrower borrower) {
        List<Book> availableBooks = getBooksByStatus(BookStatus.AVAILABLE);
        Optional<Book> optionalBook = availableBooks.stream()
                .filter(b -> bookId.equals(b.getId()))
                .findFirst();

        if(optionalBook.isPresent()) {
            Book book = optionalBook.get();
            book.setBorrower(borrower);
            book.setStatus(BookStatus.BORROWED);
            return bookRepo.saveAndFlush(book);
        }

        return null;
    }

    @Override
    public List<Book> getBooksByStatus(BookStatus status) {

        if(!(status.equals(BookStatus.AVAILABLE) || status.equals(BookStatus.BORROWED))) {
            return null;
        }

        return bookRepo.findByStatus(status);
    }

     @Override
     public List<Book> searchBooks(String searchTerm) {
         searchTerm = "%" + searchTerm.toLowerCase() + "%";
         return bookRepo.searchBooks(searchTerm);
     }


    @Override
    public Book restoreBook(Integer bookId) {
        Optional<Book> optionalBook = bookRepo.findById(bookId);
        if(optionalBook.isPresent()) {
            Book book = optionalBook.get();
            book.setStatus(BookStatus.AVAILABLE);
            bookRepo.saveAndFlush(book);
            return book;
        }
        return null;
    }



    private void loadDummyBooks() {
        List<Book> books = new ArrayList<>();
        books.add(new Book("Crime and Punishment", "It is a murder story, told from a murder;s point of view, that implicates even the most innocent reader in its enormities. It is a cat-and-mouse game between a tormented young killer and a cheerful...", "Fyodor Dostoyevsky", new Contributor("Adams Chariss", "adams.charis@gmail.com")));
        books.add(new Book("Pride and Prejudice", "The book is narrated in free indirect speech following the main character Elizabeth Bennet as she deals with matters of upbringing, marriage, moral rightness and education in her aristocratic socie...","Jane Austen", new Contributor("Adams Chariss", "adams.charis@gmail.com")));
        books.add(new Book("To the Lighthouse", "A landmark novel of high modernism, the text, centering on the Ramsay family and their visits to the Isle of Skye in Scotland between 1910 and 1920, skillfully manipulates temporality and psycholog...", "Virginia Woolf", new Contributor("Mary Thomas", "mary.thom@gmail.com")));

        bookRepo.saveAll(books);
    }


    private Book getNewBook(BookParams params) {
        return new Book(params.getTitle(), params.getDescription(), params.getAuthor(), params.getContributor());
    }


}
