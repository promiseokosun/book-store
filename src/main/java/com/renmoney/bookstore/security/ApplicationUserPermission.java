package com.renmoney.bookstore.security;

public enum ApplicationUserPermission {
    BOOK_READ("book:read"),
    BOOK_WRITE("book:write"),
    BOOK_LEND("book:lend");


    private final String permission;

    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
