package org.ad5xj.model;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.enterprise.context.RequestScoped;

import java.io.Serializable;

import java.util.Objects;

/**
 * @brief Entity implementation class for Entity: Author
 *
 */
@Entity @Table(name="Author") @RequestScoped
public class Author implements Serializable 
{
    private static final long serialVersionUID = 1L;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id    @Column(name = "authID", nullable = false)    private int authID;
	@Basic @Column(name = "firstName", nullable = false) private String firstName;
	@Basic @Column(name = "lastName", nullable = false)	 private String lastName;
	@Basic @Column(name = "authNote")                    private String authNote;

	public Author()	{  } // default without parameters   
	
    public Author( int i_id, String i_fname, String i_lname, String i_note )
    { // with required parameters
        this.authID     = i_id;
        this.firstName  = i_fname;
        this.lastName   = i_lname;
        this.authNote   = i_note;
    }

    public int getAuthID() { return this.authID; }
	public String getFirstName() { return this.firstName; }
	public String getLastName()  { return this.lastName;  }
	public String getAuthNote()  { return this.authNote;  }

	public void setAuthID(int authID) { this.authID = authID; }   
	public void setFirstName(String firstName) { this.firstName = firstName; }   
	public void setLastName(String lastName)   { this.lastName = lastName;   }   
	public void setAuthNote(String authNote)   { this.authNote = authNote;   }

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
        Author that = (Author) o;
        return authID == that.authID && Objects.equals(firstName, that.firstName) && Objects.equals(
                lastName, that.lastName) && Objects.equals(authNote, that.authNote);
    }

    @Override
    public int hashCode( )
    {
        return Objects.hash(authID, firstName, lastName, authNote);
    }
}
