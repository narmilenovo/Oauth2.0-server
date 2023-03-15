package com.example.authorizationserver.service;

import com.example.authorizationserver.entity.User;
import com.example.authorizationserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        user.orElseThrow(()->new UsernameNotFoundException("UserName or password wrong"));

        UserDetails userDetails = user.get();
        new AccountStatusUserDetailsChecker().check(userDetails);
        return userDetails;

    }
}
