package com.example.springbootjwtAuth.services;

import com.example.springbootjwtAuth.exceptions.ServiceException;
import com.example.springbootjwtAuth.models.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<User> findAll();
    User findById(Long id) throws ServiceException;
}
