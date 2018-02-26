package com.texteditor.model.dao;

import java.util.List;

import com.texteditor.model.domain.User;

public interface UserDAO {
	public boolean insert(User user);
	public User getUserById(long id);
	public User getUserByUsername(String username);
	public List<User> getAllUsers();
	public boolean updateUser(User user);
	public boolean removeUserById(long id);
	public boolean removeUserByUsername(String username);
}
