package org.ad5xj.Service;

import org.ad5xj.Model.User;

public interface UserService 
{
	void    register(User i_u);
	boolean validate(String login, String pwd);

    void save(User userobj);
    void deleteUser(Integer i_id);
    User getUserByID(Integer i_id);
    User getUserByLogin(String i_login);
}