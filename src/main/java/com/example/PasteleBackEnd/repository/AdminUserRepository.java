package com.example.PasteleBackEnd.repository;

import com.example.PasteleBackEnd.model.AdminUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminUserRepository extends JpaRepository<AdminUser, String> {
}
