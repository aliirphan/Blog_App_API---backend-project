package com.codewithirphan.blog.security;

import com.codewithirphan.blog.entities.User;
import com.codewithirphan.blog.exception.ResourceNotFoundException;
import com.codewithirphan.blog.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService  {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user=this.userRepo.findByEmail(username).orElseThrow(()->new ResourceNotFoundException("user"," email: "+username,0));


        return user;
    }
}
