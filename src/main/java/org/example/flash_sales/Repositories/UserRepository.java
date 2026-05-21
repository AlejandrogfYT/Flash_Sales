package org.example.flash_sales.Repositories;

import org.example.flash_sales.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

interface UserRepository extends JpaRepository<User, Long> {
}
