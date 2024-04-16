package org.ad5xj.dao;

import java.util.List;

import org.ad5xj.model.*;

public interface IUser extends Dao<User>
{
    int    userid = -1;
    int    privlvl = 0; // 0=new user 1=low access 2=normal access 9=admin acess
    String login = "";
    String userName = "";
    String userPasswd = "";

    void User();
    void User(int i_id, int i_p, String i_login, String i_name, String i_passwd);

    //       SETTERS AND GETTERS    //
    abstract int     getID();
    abstract int     getPrivLvl();
    abstract String  getLogin();
    abstract String  getName();
    abstract String  getPasswd();

    abstract void setID(int i_id);
    abstract void setPrivLvl(int i_p);
    abstract void setLogin(String i_login);
    abstract void setName(String i_name);
    abstract void setPasswd(String i_passwd);
    //       END SETTERS AND GETTERS    //

    abstract boolean add(User userobj);
    abstract boolean update(User userobj);
    abstract boolean destroy(int i_id);

    abstract String objectToString();
    abstract boolean validate(String i_login, String i_passwd);

    abstract int getLastID();
    abstract void clearData();
    abstract List<User> retrieveAll();
    abstract User retrieveByID(int i_id );
    abstract User retrieveByLogin(String i_login);
    abstract User retrieveByLoginPW(String i_login, String i_passwd);
    abstract void createTestData();
}
