package com.blog.springs.services;

import com.blog.springs.entities.User;
import com.blog.springs.payloads.UserDto;

import java.util.List;

//In UserDTO file only those fields are taken that are directly taken from user
public interface UserService {

	UserDto createUser(UserDto user);
	UserDto updateUser(UserDto user, Integer UserId);
	UserDto getUserById(Integer userId);
	List<UserDto> getAllUsers();
	void deleteUser(Integer userId);
}
