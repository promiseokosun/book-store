package com.renmoney.bookstore.service;

import com.renmoney.bookstore.model.User;

public interface UserService {
    User createUser(User user);

    User findByEmail(String email);
}
