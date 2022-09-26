package com.api.WNLS.com.api.WNLS.User;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UserModel, UUID> {
    boolean existsByEmail(String email);
}
