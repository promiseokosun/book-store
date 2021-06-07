package com.renmoney.bookstore.security.auth;

import com.renmoney.bookstore.constant.AccountType;
import com.renmoney.bookstore.model.User;
import com.renmoney.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static com.renmoney.bookstore.security.ApplicationUserRole.*;

@Service
public class ApplicationUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public ApplicationUserDetailsService( UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("Username %s not found", username));
        }

        // userType check
        if(user.getType().equals(AccountType.ADMIN.name())) {
            return new ApplicationUser(user.getEmail(), user.getPassword(), ADMIN.getGrantedAuthorities(),
                    true, true, true, true
            );
        }
        else if(user.getType().equals(AccountType.ADMIN_ASSISTANT.name())) {
            return new ApplicationUser(user.getEmail(), user.getPassword(), ADMIN_ASSISTANT.getGrantedAuthorities(),
                    true,true, true, true
            );
        }

        else if(user.getType().equals(AccountType.NORMAL_USER.name())) {
            return new ApplicationUser(user.getEmail(), user.getPassword(), NORMAL.getGrantedAuthorities(),
                    true,true, true, true
            );
        }
        return null;
    }
}
