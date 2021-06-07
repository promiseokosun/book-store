package com.renmoney.bookstore.auth;

import com.renmoney.bookstore.constant.AccountType;
import com.renmoney.bookstore.model.AppUser;
import com.renmoney.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
        AppUser appUser = userService.findByEmail(username);
        if (appUser == null) {
            throw new UsernameNotFoundException(String.format("Username %s not found", username));
        }

        // userType check
        if(appUser.getType().equals(AccountType.ADMIN.name())) {
            return new ApplicationUser(appUser.getEmail(), appUser.getPassword(), ADMIN.getGrantedAuthorities(),
                    true, true, true, true
            );
        }
        else if(appUser.getType().equals(AccountType.ADMIN_ASSISTANT.name())) {
            return new ApplicationUser(appUser.getEmail(), appUser.getPassword(), ADMIN_ASSISTANT.getGrantedAuthorities(),
                    true,true, true, true
            );
        }

        else if(appUser.getType().equals(AccountType.NORMAL_USER.name())) {
            return new ApplicationUser(appUser.getEmail(), appUser.getPassword(), NORMAL.getGrantedAuthorities(),
                    true,true, true, true
            );
        }
        return null;
    }
}
