package com.example.springbootjwtAuth.repositories;

import com.example.springbootjwtAuth.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsernameAndDeletedAtIsNull(String username);
}
