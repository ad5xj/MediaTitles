package org.ad5xj.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import org.ad5xj.model.Title;
import org.ad5xj.model.TitleStr;

public class TitleImplDAO implements ITitle
{
    int    titleid  = -1;
    int    authKey  = -1;
    int    mediaKey = -1;
    int    loanKey  = -1;
    String title    = "";
    String bookNote = "";

    private DBUtil db;
    private ResultSet rs;
    private Title   mytitle;

    public void Title() { };

    public void Title(int i_id, int i_a, int i_m, int i_l, String i_title, String i_note)
    {
        this.titleid  = i_id;
        this.authKey  = i_a;
        this.mediaKey = i_m;
        this.loanKey  = i_l;
        this.title    = i_title;
        this.bookNote = i_note;
    }
    
    //       SETTERS AND GETTERS    //
    public int getID()
    {
        try
        {
            return mytitle.getID();
        }
        catch (NullPointerException e)
        {
            return -1;
        }
    }

    public int getAuthKey()
    {
        try
        {
            return mytitle.getAuthKey();
        }
        catch (NullPointerException e)
        {
            return 0;
        }
    }

    public int  getMediaKey()
    {
        try
        {
            return mytitle.getMediaKey();
        }
        catch (NullPointerException e)
        {
            return 0;
        }
    }

    public int  getLoanKey()
    {
        try
        {
            return mytitle.getLoanKey();
        }
        catch (NullPointerException e)
        {
            return 0;
        }
    }

    public String  getTitle()
    {
        try
        {
            return mytitle.getTitle();
        }
        catch (NullPointerException e)
        {
            return "";
        }
    }

    public String  getBookNote()
    {
        try
        {
            return mytitle.getBookNote();
        }
        catch (NullPointerException e)
        {
            return "";
        }
    }
    
    public void setID(int i_id) { mytitle.setID(i_id); }
    public void setAuthKey(int i_a) { mytitle.setAuthKey(i_a); }
    public void setMediaKey(int i_m) { mytitle.setMediaKey(i_m); }
    public void setLoanKey(int i_l) { mytitle.setLoanKey(i_l); }
    public void setTitle(String i_title) { mytitle.setTitle(i_title); }
    public void setBookNote(String i_note) { mytitle.setBookNote(i_note); }
    //       END SETTERS AND GETTERS    //

    // LOCAL CRUD METHODS //
    /**
     * Create and persist a new title instance.
     * @param i_u local instance of title object
     * @return a string representing the view name to display after finishing the execution of this method.
     */
    public boolean add(Title t)
    {
        boolean result = false;
        String DML = "";
        db = new DBUtil();

        DML = "INSERT INTO Title (titleid, authKey, mediaKey, loanKey, title, bookNote) VALUES (" +
              Integer.toString(t.getID()) + "," +
              Integer.toString(t.getAuthKey()) + "," +
              Integer.toString(t.getMediaKey())  + "', '" +
              t.getTitle()   + "', '" +
              t.getBookNote() + "')";
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
     * @brief Update a title instance
     * @param i_u  reference to the title table object
     */
    public boolean update(Title t)
    {
        boolean result = false;
        String DML  = "";
        /* form DML from values in incoming object using MariaDB SQL syntax */
        DML = "UPDATE Tilte (titleid, authKey, mediaKey, loanKey, title, bookNote) VALUES (" +
                Integer.toString(t.getID()) + "," +
                Integer.toString(t.getAuthKey()) + "," +
                Integer.toString(t.getMediaKey())  + "', '" +
                t.getTitle()   + "', '" +
                t.getBookNote() + "')";
        
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

    /**
     * @brief Delete a title instance
     * @param i_id the titleid of the title to delete
     */
    public boolean destroy(int i_id)
    {
        boolean result = false;
        String DML  = "DELETE FROM Title WHERE titleid = " + Integer.toString(i_id);
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
        return "{ id: " + Integer.toString(this.titleid) +
                ", authKey:" + Integer.toString(this.authKey) +
                ", mediaKey: " + Integer.toString(this.mediaKey) +
                ", title: " + this.title +
                ", note: " + this.bookNote +
                "}";
    }

    @Override
    public int getLastID()
    {
        int lstid = -1;
        String DML = "SELECT max('titleid') as max FROM Title ";
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

    /**
     * Update the reference object by setting its property values to match the one
     * existing in the database for the specific instance, identified by the
     * primary key value.
     *
     * @param i_u  the reference to the title instance to be "loaded" from database.
     */
    public void refreshObject(Title t)
    {
        int i_id = t.getID();
        Title foundTitle = retrieveByID(i_id);
        t.setID(foundTitle.getID() );
        t.setAuthKey(foundTitle.getAuthKey());
        t.setMediaKey(foundTitle.getMediaKey() );
        t.setLoanKey(foundTitle.getLoanKey() );
        t.setTitle(foundTitle.getTitle() );
        t.setBookNote(foundTitle.getBookNote() );
    }

    /**
     * @brief Retrieve all Title records from the Title table.
     *
     * @return the list of all Title records
     */
	public List<Title> retrieveAll()
    {
        db = new DBUtil();
        rs = null;
        List <Title> titles = new ArrayList<>();

        String DML = "SELECT titleid, authKey, mediaKey, loanKey, title, bookNote FROM libraryDB.Title ORDER BY title ASC";
        try
        {
            rs = db.execQuery(DML);
        }
        catch ( SQLException e )
        {
        	System.out.println("ERROR TitleImplDAO:retrieveAll 271: query error - "+e.getMessage());
        	return titles;
        }

        try
        {
        	System.out.println("DEBUG TitleImplDAO:retrieveAll 271: creating the list... ");
            while ( rs.next() )
            {
                int i_id = rs.getInt("titleid");
                int i_a  = rs.getInt("authKey");
                int i_m  = rs.getInt("mediaKey");
                int i_l  = rs.getInt("loanKey");
                String i_title = rs.getString("title");
                String i_note = rs.getString("bookNote");
                Title mytitle = new Title(i_id, i_a, i_m, i_l, i_title, i_note);
                titles.add(mytitle);
            }
        }
        catch ( SQLException e)
        {
        	System.out.println("ERROR TitleImplDAO:retrieveAll 286: error adding records to list - "+e.getMessage());
        }
        db = null;
        return titles;
    }

    /**
     * @brief Retrieve all Title records from the Title table.
     *
     * @return the list of all Title records
     */
	public List<TitleStr> listAll()
    {
        db = new DBUtil();
        rs = null;
        List <TitleStr> titless = new ArrayList<>();

        String DML = "SELECT libraryDB.Title.titleid, " +
        	         "  concat(libraryDB.Author.lastName,', ',libraryDB.Author.firstName) as authname, " +
        	         "  libraryDB.MediaType.mediaName as media, " +
        	         "   libraryDB.Title.loanKey, " +
        	         "   libraryDB.Title.title, " +
        	         "   libraryDB.Title.bookNote " + 
        	         "FROM libraryDB.Title " + 
        	         "INNER JOIN libraryDB.Author    ON libraryDB.Author.authid     = libraryDB.Title.authKey " + 
        	         "INNER JOIN libraryDB.MediaType ON libraryDB.MediaType.mediaid = libraryDB.Title.mediaKey " +
        	         "ORDER BY libraryDB.Title.title ASC, authName ASC, media ASC";
        try
        {
            rs = db.execQuery(DML);
        }
        catch ( SQLException e )
        {
        	System.out.println("ERROR TitleImplDAO:listAll 317: query error - "+e.getMessage());
        	return titless;
        }

        try
        {
        	System.out.println("DEBUG TitleImplDAO:listAll 327: creating the list... ");
            while ( rs.next() )
            {
                String i_id = rs.getString("titleid");
                String i_a  = rs.getString("authname");
                String i_m  = rs.getString("media");
                String i_l  = Integer.toString(rs.getInt("loanKey"));
                String i_t  = rs.getString("title");
                String i_n  = rs.getString("bookNote");
                TitleStr mytitle = new TitleStr(i_id, i_a, i_m, i_l, i_t, i_n);
                titless.add(mytitle);
            }
        }
        catch ( SQLException e)
        {
        	System.out.println("ERROR TitleImplDAO:listAll 328: error adding records to list - "+e.getMessage());
        }
        db = null;
        return titless;
    }
	
	/**
     * @brief Retrieve a title record from the titles table.
     *
     * @param i_id the title's titleid
     * @return titleobj the title object with the given id
     */
    public Title retrieveByID(int i_id)
    {
        db = new DBUtil();
        rs = null;

        String DML = "SELECT * FROM Title WHERE titleid = " + Integer.toString(i_id);
        mytitle = new Title();
        try
        {
            rs = db.execQuery(DML);
            mytitle.setID(rs.getInt("titleid"));
            mytitle.setAuthKey(rs.getInt("authKey"));
            mytitle.setMediaKey(rs.getInt("mediaKey"));
            mytitle.setLoanKey(rs.getInt("loanKey"));
            mytitle.setTitle(rs.getString("title"));
            mytitle.setBookNote(rs.getString("bookNote"));
            db.close();
            return mytitle;
        }
        catch ( SQLException e)
        {
            return null;
        }
    }


    /**
     * @brief Retrieve a Title record from the Title table.
     *
     * @param i_title the title 
     * @return LIst<Title> the Title object like the given title
     */
    public List<Title> retrieveByTitle(String i_title)
    {
        db = new DBUtil();
        rs = null;
        List<Title> titles = null;

        String DML = "SELECT titleid, authKey, mediaKey, loanKey, title, bookNote FROM Title WHERE title LIKE '" + i_title + "' ";
        try
        {
            rs = db.execQuery(DML);
            while (rs.next())
            {
	            int i_id = rs.getInt("titleid");
	            if ( !Integer.toString(i_id).equals(null) )
	            {
	                mytitle = new Title();
	                mytitle.setID(rs.getInt("titleid"));
	                mytitle.setAuthKey(rs.getInt("authKey"));
	                mytitle.setMediaKey(rs.getInt("mediaKey"));
	                mytitle.setLoanKey(rs.getInt("loanKey"));
	                mytitle.setTitle(rs.getString("title"));
	                mytitle.setBookNote(rs.getString("bookNote"));
	            }
	            else
	            {
	               System.out.println("TitleImplDAO:retrieveByLoginPW() 383 - Query Failure:"+ db.getLastError());
	            }
            }
            db.close();
        }
        catch ( SQLException e)
        {
            System.out.println("ERROR TitleImplDAO:retrieveByLoginPW() 383 - had an error..."+e.getMessage());
        }
        db = null;
        return titles;
    }
    
    /**
     * @brief Clear all entries from the User table
     */
    public void clearData()
    {
        String DML = "DELETE FROM Title WHERE titleid >= 0 ";
        db = new DBUtil();
        try
        {
            db.execUpdate(DML);
            db.close();
        }
        catch ( SQLException e )
        {
            System.out.println("ERROR TitleImplDAO:clearData() 377 had an error: "+e.getMessage());
        }
    }

    /**
     * @brief Create test data (rows) in the Users table
     */
    public void createTestData()
    {
        db = new DBUtil();
        // clear existing books, so no primary key duplicate conflicts appear
        String DML = "INSERT INTO Title (titleid, authKey, mediaKey, loanKey, title, bookNote) VALUES (" +
                      Integer.toString(1) + "," + Integer.toString(1) + "," + Integer.toString(1) + "," + Integer.toString(1) + 
                      "DUMMY RECORD" + "," + "BOOK NOTE EXAMPLE)";

        try
        {
            db.execUpdate(DML);
            db.close();
        }
        catch ( SQLException e )
        {
            System.out.println("ERROR TitleImplDAO:createTestData() 399 had an error: "+e.getMessage());
        }
        db = null;
        mytitle = null;
    }
}
