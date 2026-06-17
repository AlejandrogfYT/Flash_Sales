package org.example.flash_sales.Repositories;

import org.example.flash_sales.Models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByEmail(String email);
    Page<User> getAll(Pageable pageable);
}
