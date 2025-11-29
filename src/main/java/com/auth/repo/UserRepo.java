package com.auth.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.auth.model.Users;

public interface UserRepo extends JpaRepository<Users, Integer> {
  Users findByUsername( String username);
}
