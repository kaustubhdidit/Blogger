package com.blog.springs.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.blog.springs.entities.*;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.springs.exceptions.*;
import com.blog.springs.entities.User;
import com.blog.springs.payloads.UserDto;
import com.blog.springs.repositories.UserRepo;
import com.blog.springs.services.UserService;


@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private ModelMapper modelMapper;
	@Override
	public UserDto createUser(UserDto userDto) {
		// TODO Auto-generated method stub
		User user=this.dtoToUser(userDto);
		User savedUser=this.userRepo.save(user);
		return this.userToDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		
		User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","id",userId));
		 	
			user.setName(userDto.getName());
		    user.setEmail(userDto.getEmail());
		    user.setAbout(userDto.getAbout());
		    user.setPassword(userDto.getPassword());

		    // Save the updated user entity
		    User updatedUser = this.userRepo.save(user);

		    // Convert the updated user entity to UserDto and return
		    UserDto userDto1=this.userToDto(updatedUser);
		    return userDto1;
	}

	@Override
	public UserDto getUserById(Integer userId) {
		// TODO Auto-generated method stub
		User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","Id",userId));
		UserDto userDto=this.userToDto(user);
		return userDto;
	}

	@Override
	public List<UserDto> getAllUsers() {
		// TODO Auto-generated method stub
		List<User> users= this.userRepo.findAll();
		
		List<UserDto> userDtos= users.stream().map(user->userToDto(user)).collect(Collectors.toList());
		return userDtos;
	}

	@Override
	public void deleteUser(Integer userId) {
		// TODO Auto-generated method stub
		
		User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","id",userId));
		this.userRepo.delete(user);

	}
	
	public User dtoToUser(UserDto userDto) {

		User user=this.modelMapper.map(userDto, User.class); // Using ModelMapper and Autowire

//		User user=new User();
//		user.setId(userDto.getId());
//		user.setName(userDto.getName());
//		user.setEmail(userDto.getEmail());
//		user.setAbout(userDto.getAbout());
//		user.setPassword(userDto.getPassword());
		
		return user;
	}
	
	public UserDto userToDto(User user) {
		UserDto userDto=this.modelMapper.map(user, UserDto.class);

//		UserDto userDto=new UserDto();
//		userDto.setId(user.getId());
//		userDto.setName(user.getName());
//		userDto.setEmail(user.getEmail());
//		userDto.setAbout(user.getAbout());
//		userDto.setPassword(user.getPassword());
		return userDto;
		
	}


}
