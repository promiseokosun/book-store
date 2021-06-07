package com.renmoney.bookstore.repo;

import com.renmoney.bookstore.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<AppUser, Integer> {

    AppUser findByEmail(String email);
}
