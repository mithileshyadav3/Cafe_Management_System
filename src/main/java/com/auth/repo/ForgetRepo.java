package com.auth.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.auth.model.ForgetPassword;
import com.auth.model.Users;
@Repository
public interface ForgetRepo extends JpaRepository<ForgetPassword, Integer> {
Optional<ForgetPassword>findByUsers(Users users);
}
