/*
 * (C)Copyright 2024 AD5XJ ken
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.ad5xj.model;

import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.enterprise.context.RequestScoped;

import java.io.Serializable;

import java.util.Objects;

/**
 * @brief Data bean of User data object
 * @ingroup MARIADB
 * @author ken
 * @details This is the data bean of the User data object in the Users table.
 */
@Entity @Table(name = "Users") @RequestScoped 
public class User implements Serializable
{
    private static final long serialVersionUID = 1L;

    
    @GeneratedValue @Id @Column(name = "userid", nullable = false)   private int userID;
    @Basic @Column(name = "login", nullable = false)                 private String login;
    @Basic @Column(name = "userName", nullable = false)              private String userName;
    @Basic @Column(name = "userPasswd", nullable = false)            private String userPasswd;
    @Basic @Column(name = "privLvl", nullable = false)               private Integer privLvl;
    
    public User( )
    {
    }  // default constructor no parameters

    public User( int i_id, int i_p, String i_login, String i_name, String i_passwd )
    { // with required parameters
        this.userID     = i_id;
        this.privLvl    = i_p;
        this.login      = i_login;
        this.userName   = i_name;
        this.userPasswd = i_passwd;
    }

    //       SETTERS AND GETTERS    //
    public int     getID()       { return userID; }
    public int     getPrivLvl()  { return privLvl; }
    public String  getLogin()    { return login; }
    public String  getName()     { return userName; }
    public String  getPasswd()   { return userPasswd; }

    public void setID(int i_id)            { userID = i_id; }
    public void setPrivLvl(int i_p)        { privLvl = i_p; }
    public void setLogin(String i_login)   { login = i_login; }
    public void setName(String i_name)     { userName = i_name; }
    public void setPasswd(String i_passwd) { userPasswd = i_passwd; }
    //       END SETTERS AND GETTERS    //

    public static boolean validate(String i_login, String i_passwd) { return false; }

    @Override
    public boolean equals( Object o )
    {
        if ( this == o )
        {
            return true;
        }
        if ( o == null || getClass() != o.getClass() )
        {
            return false;
        }
        User that = (User) o;
        return userID == that.userID && Objects.equals(privLvl, that.privLvl) && Objects.equals(login, that.login) && Objects.equals(userName,
                that.userName) && Objects.equals(userPasswd, that.userPasswd) && Objects.equals(privLvl,
                that.privLvl);
    }

    @Override
    public int hashCode( )
    {
        return Objects.hash(userID, privLvl, login, userName, userPasswd );
    }
}
