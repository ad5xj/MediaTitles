package org.ad5xj.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import org.ad5xj.model.User;
import org.ad5xj.dao.UserImplDAO;

@Service
public class UserServiceImpl implements UserService
{
	
    @Autowired
    private UserImplDAO userobj;

    @Override
    @Transactional
    public List < User > getUsers() { return userobj.retrieveAll();  }

    @Override
    @Transactional
    public void saveUser(User usr)  { userobj.add(usr); }

    @Override
    @Transactional
    public User getUser(int userId) { return userobj.retrieveByID(userId); }

    @Override
    @Transactional
    public void deleteUser(int userId) { userobj.destroy(userId); }
}