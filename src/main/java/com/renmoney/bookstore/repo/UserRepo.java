package com.renmoney.bookstore.repo;

import com.renmoney.bookstore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> {

    User findByEmail(String email);
}
