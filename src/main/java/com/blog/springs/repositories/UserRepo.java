package com.blog.springs.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.springs.entities.User;

public interface UserRepo extends JpaRepository<User, Integer> {
}
