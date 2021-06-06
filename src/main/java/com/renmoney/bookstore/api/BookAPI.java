package com.renmoney.bookstore.api;

import com.renmoney.bookstore.constant.BookStatus;
import com.renmoney.bookstore.dto.BookParams;
import com.renmoney.bookstore.model.Book;
import com.renmoney.bookstore.model.Borrower;
import com.renmoney.bookstore.response.BaseResponse;
import com.renmoney.bookstore.response.ResponseUtil;
import com.renmoney.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/books")
public class BookAPI {

    private BookService bookService;

    @Autowired
    public BookAPI(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public BaseResponse<List<Book>> getAllBooks() {
        return ResponseUtil.success("Request Successful", bookService.getAllBooks());
    }

    @GetMapping("{bookId}")
    public  BaseResponse<Book> getBook(@PathVariable("bookId") Integer bookId) {
        Book book = bookService.getBookById(bookId);
        if(book == null) {
            return ResponseUtil.invalidOrNullInput("Book Not Found");
        }
        return  ResponseUtil.success("Request Successful", book);
    }

    @GetMapping("check")
    public BaseResponse<List<Book>> getBooksByStatus(@RequestParam("status") BookStatus status) {

        List<Book> books = bookService.getBooksByStatus(status);

        if(books == null) {
            return ResponseUtil.invalidOrNullInput("Book Status Not Recognized");
        }

        return ResponseUtil.success("Request Successful", books);
    }

    @GetMapping("search")
    public BaseResponse<List<Book>> searchBooks(@RequestParam("searchTerm") String searchTerm) {
        if(searchTerm == null) {
            return ResponseUtil.invalidOrNullInput("Bad Input Param");
        }
        List<Book> books = bookService.searchBooks(searchTerm);

        return ResponseUtil.success("Request Successful", books);
    }

}
