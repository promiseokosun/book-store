package com.renmoney.bookstore.service.impl;

import com.renmoney.bookstore.auth.ApplicationUser;
import com.renmoney.bookstore.model.AppUser;
import com.renmoney.bookstore.repo.UserRepo;
import com.renmoney.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.renmoney.bookstore.security.ApplicationUserRole.ADMIN;
import static com.renmoney.bookstore.security.ApplicationUserRole.ADMIN_ASSISTANT;

@Service
public class UserServiceImpl implements UserService {

    private UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        createSuperAdminAccount();
    }

    @Override
    public AppUser createUser(AppUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    @Override
    public AppUser findByEmail(String email) {
        return userRepo.findByEmail(email);
    }


    private void createSuperAdminAccount() {
        createUser(new AppUser("super", "pass", "ADMIN"));
    }

}
