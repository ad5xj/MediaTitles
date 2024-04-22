package org.ad5xj.model;

public class UserStr
{
	public String userid_s;
	public String login_s;
	public String name_s;
	public String passwd_s;
	public String privLvl_s;

	public UserStr() { }
	
	public UserStr( String i_id, String i_p, String i_login, String i_name, String i_passwd )
	{ // with required parameters
	    this.userid_s  = i_id;
	    this.privLvl_s = i_p;
	    this.login_s   = i_login;
	    this.name_s    = i_name;
	    this.passwd_s  = i_passwd;
	}
}