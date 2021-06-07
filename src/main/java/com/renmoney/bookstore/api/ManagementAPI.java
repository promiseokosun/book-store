package com.renmoney.bookstore.api;


import com.renmoney.bookstore.dto.BookParams;
import com.renmoney.bookstore.model.User;
import com.renmoney.bookstore.model.Book;
import com.renmoney.bookstore.model.Borrower;
import com.renmoney.bookstore.response.BaseResponse;
import com.renmoney.bookstore.response.ResponseUtil;
import com.renmoney.bookstore.service.BookService;
import com.renmoney.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static com.renmoney.bookstore.constant.AccountType.*;


@RestController
@RequestMapping("management/api/v1")
public class ManagementAPI {

    private BookService bookService;

    private UserService userService;

    @Autowired
    public ManagementAPI(BookService bookService, UserService userService) {
        this.bookService = bookService;
        this.userService = userService;
    }

    @PostMapping("books")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public BaseResponse<Book> addBook(@RequestBody BookParams params) {
        if(params == null) {
            return ResponseUtil.invalidOrNullInput("Invalid Input");
        }
        return ResponseUtil.success("Request Successful", bookService.addBook(params));

    }

    @PutMapping("books/{bookId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public BaseResponse<Book> editBook(@PathVariable("bookId") Integer bookId, @RequestBody BookParams params) {
        Book book = bookService.editBook(bookId, params);
        if(book == null) {
            return ResponseUtil.invalidOrNullInput("Book Not Found");
        }
        return ResponseUtil.success("Request Successful", book);
    }

    @DeleteMapping("books/{bookId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public BaseResponse<Book> deleteBook(@PathVariable("bookId") Integer bookId) {
        Book book = bookService.getBookById(bookId);
        if(book == null) {
            return ResponseUtil.invalidOrNullInput("Book Not Found");
        }
        bookService.deleteBook(bookId);
        return ResponseUtil.success("Request Successful", null);
    }

    @PutMapping("books/{bookId}/lend")
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

    @PostMapping("create-user")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public BaseResponse<User> createUser(@RequestBody User user) {
        if(user == null || user.getType() == null) {
            return ResponseUtil.invalidOrNullInput("Invalid Input");
        }

        if( !(user.getType().equals(NORMAL_USER.name())
                || user.getType().equals(ADMIN_ASSISTANT.name())
                || user.getType().equals(ADMIN.name())
        )) {
            return ResponseUtil.invalidOrNullInput("Invalid Account Type - NORMAL_USER, ADMIN_ASSISTANT, ADMIN");
        }
        return ResponseUtil.success("Request Successful", userService.createUser(user));

    }

    @PutMapping("books/{bookId}/restore")
    @PreAuthorize("hasAuthority('book:lend')")
    public BaseResponse<Book> restoreBook(@PathVariable("bookId") Integer bookId) {

        if(bookId.equals(null)) {
            return ResponseUtil.invalidOrNullInput("Please Specify bookId");
        }

        Book book = bookService.restoreBook(bookId);

        if(book == null) {
            return ResponseUtil.invalidOrNullInput("Book Not Found");
        }

        return ResponseUtil.success("Request Successful", book);
    }

}
