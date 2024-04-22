package org.ad5xj.dao;

import java.util.List;

import org.ad5xj.model.*;

public interface IAuthor extends Dao<Author>
{
    int    authID = -1;
	String firstName = "";
	String lastName = "";
	String authNote = "";

	void Author();
	
    void Author( int i_id, String i_fname, String i_lname, String i_note );

    // SETTERS AND GETTERS  //
    abstract int getAuthID();
    abstract String getFirstName();
    abstract String getLastName();
    abstract String getAuthNote();

    abstract void setAuthID(int authID);   
    abstract void setFirstName(String firstName);   
    abstract void setLastName(String lastName);   
    abstract void setAuthNote(String authNote);
    // END SETTERS AND GETTERS  //

    abstract boolean add(Author authobj);
    abstract boolean update(Author authobj);
    abstract boolean destroy(int i_id);

    abstract String objectToString();
    abstract int getLastID();
    abstract void clearData();
    abstract List<Author> retrieveAll();
    abstract Author retrieveByID(int i_id );
    abstract List<Author> retrieveByLastName(String i_lname);
    abstract Author retrieveByWholeName(String i_lname, String i_fname);
    abstract void createTestData();
}
