package com.renmoney.bookstore.service;

import com.renmoney.bookstore.model.AppUser;

public interface UserService {
    AppUser createUser(AppUser user);

    AppUser findByEmail(String email);
}
