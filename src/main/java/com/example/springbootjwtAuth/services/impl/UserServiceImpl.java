package com.example.springbootjwtAuth.services.impl;

import com.example.springbootjwtAuth.exceptions.ServiceException;
import com.example.springbootjwtAuth.jwt.JwtUserDetails;
import com.example.springbootjwtAuth.models.entities.User;
import com.example.springbootjwtAuth.repositories.UserRepository;
import com.example.springbootjwtAuth.services.UserService;
import com.example.springbootjwtAuth.shared.utils.codes.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) throws ServiceException {
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.orElseThrow(() ->
            ServiceException.builder()
                            .errorCode(ErrorCode.RESOURCE_NOT_FOUND)
                            .message("user not found")
                            .build()
        );
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsernameAndDeletedAtIsNull(username);
        System.out.println(user.getUsername());
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password");
        }
        return JwtUserDetails.build(user);
    }
}
