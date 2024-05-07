package org.ad5xj.Service;

import org.ad5xj.Model.Login;
import org.ad5xj.Model.User;
import org.ad5xj.DAO.UserImplDAO;

import org.ad5xj.Repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService 
{
    private Integer userID;
    private String login;
    private String userName;
    private String userPasswd;
    private Integer privLvl;

    @Autowired
	private UserRepository urep;
    
    private User user;

	// SETTERS AND GETTERS
    public void setUserID(Integer i_id)     { userID = i_id; }
    public void setLogin(String i_login)    { login = i_login; }
    public void setUserName(String i_name)  { userName = i_name; }
    public void setUserPasswd(String i_pw)  { userPasswd = i_pw; }
    public void setPrivLvl(int i_pl)        { privLvl = i_pl; }
    
    public Integer getUserID() { return userID; }
    public String  getLogin()  { return login; }
    public String  getName()   { return userName; }
    public String  getPasswd() { return userPasswd; }
    public int     getPrivLvl() { return privLvl; }
    //                      //
    
	@Override
	public User getUserByID(Integer i_id) 
	{
		User user = new User();
		UserImplDAO userobj = new UserImplDAO();
		
		user = userobj.retrieveByID(i_id);
		if ( user != null)
		{
			userobj = null;
			return user;
		}
		userobj = null;
		return null;
	}

	@Override
	public User getUserByLogin(String i_login) 
	{ 
		User user = new User();
		UserImplDAO userobj = new UserImplDAO();
		
		user = userobj.retrieveByLogin(i_login);
		if ( user != null)
		{
			userobj = null;
			return user;
		}
		userobj = null;
		return null;
	}


	public void register(User i_u)
    {
    	User user = i_u;
    	UserImplDAO userobj = new UserImplDAO();
    	
    	userobj.save(user);
    }

    public User validateUser(Login i_login) 
    {
    	Login login = i_login;
    	User user = null;
    	UserImplDAO userobj = new UserImplDAO();

    	String loginstr = login.getLogin();
		user = userobj.retrieveByLogin(loginstr);
        userobj = null;
        return user;
    }

	public boolean validate(String login, String pwd)
    {
    	boolean OK = false;
    	User user = new User();
    	UserImplDAO userobj = new UserImplDAO();
    	if ( user != null )
    	{
    	    user = userobj.retrieveByLoginPW(login, pwd);
    	    OK = true;
    	}
	    userobj = null;
    	return OK;
    }
    

    // LOCAL CRUD METHODS //
    /**
     * Create and persist a new User instance.
     * @param i_u local instance of User object
     * @return a string representing the view name to display after finishing the execution of this method.
     */
    @Override
    public void save(User i_u)
    {
    	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String pwd = encoder.encode(this.userPasswd);
    	user.setPasswd(pwd);
        this.urep.save(i_u);
    }

    @Override
    public void deleteUser(Integer i_id)
    {
    	this.urep.deleteById(i_id);
    }
    // END CRUD METHODS //
}