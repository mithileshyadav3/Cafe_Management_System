package com.auth.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.auth.model.Users;

public interface UserRepo extends JpaRepository<Users, Integer> {
  Users findByUsername( String username);
  List<Users>findByRole(String role);
  Optional<Users> findByEmail(String username);
}
