package com.renmoney.bookstore.api;


import com.renmoney.bookstore.constant.BookStatus;
import com.renmoney.bookstore.dto.BookParams;
import com.renmoney.bookstore.model.Book;
import com.renmoney.bookstore.model.Borrower;
import com.renmoney.bookstore.response.BaseResponse;
import com.renmoney.bookstore.response.ResponseUtil;
import com.renmoney.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("management/api/v1/books")
public class BookManagementAPI {

    private BookService bookService;

    @Autowired
    public BookManagementAPI(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public BaseResponse<Book> addBook(@RequestBody BookParams params) {
        if(params == null) {
            return ResponseUtil.invalidOrNullInput("Invalid Input");
        }
        return ResponseUtil.success("Request Successful", bookService.addBook(params));

    }

    @PutMapping("{bookId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public BaseResponse<Book> editBook(@PathVariable("bookId") Integer bookId, @RequestBody BookParams params) {
        Book book = bookService.editBook(bookId, params);
        if(book == null) {
            return ResponseUtil.invalidOrNullInput("Book Not Found");
        }
        return ResponseUtil.success("Request Successful", book);
    }

    @DeleteMapping("{bookId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public BaseResponse<Book> deleteBook(@PathVariable("bookId") Integer bookId) {
        Book book = bookService.getBookById(bookId);
        if(book == null) {
            return ResponseUtil.invalidOrNullInput("Book Not Found");
        }
        bookService.deleteBook(bookId);
        return ResponseUtil.success("Request Successful", null);
    }

    @PutMapping("{bookId}/lend")
    @PreAuthorize("hasAuthority('book:lend')")
    public BaseResponse<Book> lendBook(@PathVariable("bookId") Integer bookId, @RequestBody Borrower borrower) {

        if(borrower == null) {
            return ResponseUtil.invalidOrNullInput("Please Specify a borrower");
        }

        Book book = bookService.lendBook(bookId, borrower);

        if(book == null) {
            return ResponseUtil.invalidOrNullInput("Book Not Found");
        }

        return ResponseUtil.success("Request Successful", book);
    }


}
