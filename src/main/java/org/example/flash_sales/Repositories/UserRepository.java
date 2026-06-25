package org.example.flash_sales.Repositories;

import org.example.flash_sales.Models.User;
import org.jspecify.annotations.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    User findUserByEmail(String email);
    Page<User> findAll(@NonNull Pageable pageable);

    boolean existsUserById(String id);
}
