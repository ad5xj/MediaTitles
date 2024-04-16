package org.ad5xj.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;

import org.ad5xj.model.*;

public class AuthorImplDAO implements IAuthor 
{
    int    authID;
	String firstName;
	String lastName;
	String authNote;

    private DBUtil db;
    private ResultSet rs;
    private Author author;
    private AuthorImplDAO authobj;

    private List<Author> authors;

	public void Author()	{ }   
	
    public void Author( int i_id, String i_fname, String i_lname, String i_note )
    {
        this.authID = i_id;
        this.firstName = i_fname;
        this.lastName = i_lname;
        this.authNote = i_note;
    }

    // SETTERS AND GETTERS  //
    public int getAuthID() 
    {
        try
        {
        	authobj = new AuthorImplDAO();
            return authobj.getAuthID();
        }
        catch (NullPointerException e)
        {
            return -1;
        }
    }

    public String getFirstName() {	return authobj.getFirstName(); }
    public String getLastName()  { 	return authobj.getLastName();  }
    public String getAuthNote()  { return authobj.getAuthNote();  }

    public void setAuthID(int i_authID) { authID = i_authID; }   
    public void setFirstName(String i_fName) { this.firstName = i_fName; }   
    public void setLastName(String i_lName)   { this.lastName = i_lName;   }   
    public void setAuthNote(String i_note)   { this.authNote = i_note;   }
    // END SETTERS AND GETTERS  //

    public boolean add(Author i_ath)
    {
        boolean result = false;
        String DML = "";
        db = new DBUtil();

        DML = "INSERT INTO Author (authid, firstName, lastName, authNote) VALUES (" +
              Integer.toString(i_ath.getAuthID()) + ",'" +
              i_ath.getFirstName()   + "', '" +
              i_ath.getLastName() + "', '" +
              i_ath.getAuthNote() + "')";
        try
        {
            result = db.execUpdate(DML);
            db.close();
        }
        catch (Exception e)
        {
            result = false;
        }
        db = null;
        return result;
    }

    /**
     * @brief Update an Author instance
     * @param i_ath  reference to the Author table object
     */
    public boolean update(Author i_ath)
    {
        boolean result = false;
        String DML  = "";
        /* form DML from values in incoming object using MariaDB SQL syntax */
        DML = "UPDATE Author (authID, firstName, lastName, authNote) VALUES (" +
                i_ath.getAuthID()      + ", '" +
                i_ath.getFirstName()   + "', '" +
                i_ath.getLastName()    + "', '" +
                i_ath.getAuthNote()  + "')";
        try
        {
            db.execQuery(DML);
            result = true;
        }
        catch (Exception e)
        {
            result =  false;
        }
        return result;
    }

    public boolean destroy(int i_id)
    {
        boolean result = false;
        String DML  = "DELETE FROM Author WHERE authid = " + Integer.toString(i_id);
        try
        {
            db.execQuery(DML);
            db.close();
            result = true;
        }
        catch (Exception e)
        {
            result =  false;
        }
        db = null;
        return result;
        }
    // END CRUD METHODS //

    public String objectToString()
    {
    	// make the object a string 
        return "{ id: " + 
    			Integer.toString(this.authID) +
                ", firstname: " + this.firstName +
                ", lastname: "  + this.lastName +
                ", note: "      + this.authNote +
                "}";
    }

    public int getLastID()
    {
        int lstid = -1;
        String DML = "SELECT max('authid') as max FROM Author ";

        db = new DBUtil();
        rs = null;

        try
        {
            rs = db.execQuery(DML);
            lstid = rs.getInt("max");
        }
        catch (SQLException | NullPointerException e)
        {
            return - 1;
        }
        return lstid;
    }

    public void clearData()
    {
        //	
    }

    @SuppressWarnings("null")
	public List<Author> retrieveAll()
    {
        db = new DBUtil();
        rs = null;
        List<Author> authors = null;

        authors.clear();
        String DML = "SELECT authid, firstName, lastName, authNote FROM Author ORDER BY lastName ASC, firstName ASC";
        try
        {
            rs = db.execQuery(DML);
        }
        catch ( SQLException e )
        {
        	System.out.println("ERROR AuthorImplDAO retrieveAll(): query error - "+e.getMessage());
        	return authors;
        }

        try
        {
            while ( rs.next() )
            {
                int i_id = rs.getInt("userid");
                String i_fname  = rs.getString("firstName");
                String i_lname = rs.getString("lastName");
                String i_note = rs.getString("authNote");
                Author auth = new Author(i_id, i_fname, i_lname, i_note);
                authors.add(auth);
            }
            db.close();
        }
        catch ( SQLException | NullPointerException e)
        {
            // do nothing
        }
        db = null;
        return authors;
    }

    public Author retrieveByID(int i_id )
    {
        db = new DBUtil();
        rs = null;

        String DML = "SELECT * FROM Author WHERE authid = " + Integer.toString(i_id);
        author = new Author();
        try
        {
            rs = db.execQuery(DML);
            author.setAuthID(rs.getInt("authid"));
            author.setFirstName(rs.getString("fisrtName"));
            author.setLastName(rs.getString("lastName"));
            author.setAuthNote(rs.getString("authNote"));
            db.close();
            return author;
        }
        catch ( SQLException e)
        {
            return null;
        }
    }

    public List<Author> retrieveByLastName(String i_lname)
    {
    	List<Author> list;
        db = new DBUtil();
        rs = null;
        AuthorImplDAO authobj = new AuthorImplDAO();
        author = new Author();
        
        authors.clear();
        
        System.out.println("getting Author by last name: " + i_lname);
        list = authobj.retrieveByLastName(i_lname);

        try
        {
            for ( int x=0; x < list.size(); ++x )
            {
                int i_id = list.get(x).getAuthID();
                String i_fname  = list.get(x).getFirstName();
                String i_lstname  = list.get(x).getLastName();
                String i_note   = list.get(x).getAuthNote();
                Author auth     = new Author(i_id, i_fname, i_lstname, i_note);
                authors.add(auth);
            }
            db.close();
        }
        catch ( SQLException | NullPointerException e)
        {
            // do nothing
        }
        db = null;
        return authors;
    }

    public Author retrieveByWholeName(String i_lname, String i_fname)
    {
        db = new DBUtil();
        rs = null;

        String DML = "SELECT * FROM Author WHERE firstName = " + i_fname + "' AND lastName = '" + i_lname + "'";
        author = new Author();
        try
        {
            rs = db.execQuery(DML);
            author.setAuthID(rs.getInt("authid"));
            author.setFirstName(rs.getString("fisrtName"));
            author.setLastName(rs.getString("lastName"));
            author.setAuthNote(rs.getString("authNote"));
            db.close();
            return author;
        }
        catch ( SQLException e)
        {
            return null;
        }
    }

    public void createTestData()
    {
    	
    }
}
