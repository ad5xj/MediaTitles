package org.ad5xj.service;

import java.util.List;

import org.ad5xj.model.User;

public interface UserService 
{
	public List < User > getUsers();
    public void saveUser(User user);
    public User getUser(int usrId);
    public void deleteUser(int usrId);
}