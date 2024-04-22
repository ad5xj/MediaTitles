/*
 * (C)Copyright 2023 ken
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

import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;

import java.util.Objects;

/**
 * @brief Data bean of Title data object
 * @ingroup MARIADB
 * @author ken
 * @details This is the data bean of the Title data object in the Title table.
 */
@Entity @Table(name = "Title") @RequestScoped 
public class Title implements Serializable 
{
    private static final long serialVersionUID = 1L;

    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    @Id @Column(name = "titleid", nullable = false)      private int    titleid;
    @Basic @Column(name = "authKey", nullable = false)   private int    authKey;
    @Basic @Column(name = "mediaKey", nullable = false)  private int    mediaKey;
    @Basic @Column(name = "loanKey")                     private int    loanKey;
    @Basic @Column(name = "title", nullable = false)     private String title;
    @Basic @Column(name = "bookNote")                    private String bookNote;


    public Title( ) { }  // default constructor no parameters for overload

    public Title( int i_id, int i_auth, int i_media, int i_loan, String i_title, String i_note )
    { // with required parameters
        this.titleid  = i_id;
        this.authKey  = i_auth;
        this.mediaKey = i_media;
        this.loanKey  = i_loan;
        this.title    = i_title;
        this.bookNote = i_note;
    }

    //       SETTERS AND GETTERS    //
    public int getID( )          {  return titleid; }
    public int getAuthKey( )     {  return authKey; }
    public int getMediaKey( )    {  return mediaKey; }
    public int getLoanKey( )     {  return mediaKey; }
    public String getTitle( )    {  return title; }
    public String getBookNote( ) {  return bookNote; }

    public void setID(int i_id)            { titleid = i_id; }
    public void setAuthKey(int i_auth)     { authKey = i_auth; }
    public void setMediaKey(int i_media)   { mediaKey = i_media; }
    public void setLoanKey(int i_loan)     { loanKey = i_loan; }
    public void setTitle( String i_title ) { title = i_title; }
    public void setBookNote( String i_note ) { bookNote = i_note; }
    //       END SETTERS AND GETTERS    //

    // CRUD METHODS //
    public static boolean add( Title titleobj )    { return false; }
    public static boolean update( Title titleobj ) { return false; }
    public static boolean destroy( int i_id )      { return false; }
    // END CRUD METHODS //

    public void clearData() { }
    public void createTestData() { }
    public Title retrieveByID(int i_id) { return null; };
    public Title retrieveByTitle(String i_title) { return null; }

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
        Title that = (Title) o;
        return titleid == that.titleid && authKey == that.authKey && mediaKey == that.mediaKey && Objects.equals(
                loanKey, that.loanKey) && Objects.equals(title, that.title) && Objects.equals(bookNote,
                that.bookNote);
    }

    @Override
    public int hashCode( )
    {
        return Objects.hash(titleid, authKey, mediaKey, loanKey, title, bookNote);
    }
}
