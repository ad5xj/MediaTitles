package org.ad5xj.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import org.ad5xj.model.*;

public class MediaImplDAO implements IMediaType 
{
	@SuppressWarnings("unused")
	private int mediaid;
    @SuppressWarnings("unused")
	private String mediaName;

    private DBUtil db;
    private ResultSet rs;
    private MediaType   media;

    public void MediaType() {};

    public void MediaType(int i_id, String i_n)
    {
        this.mediaid   = i_id;
        this.mediaName = i_n;
    }
    
    //       SETTERS AND GETTERS    //
    public int getID()
    {
        try
        {
            return media.getMediaID();
        }
        catch (NullPointerException e)
        {
            return -1;
        }
    }

    public String  getName()
    {
        try
        {
            return media.getMediaName();
        }
        catch (NullPointerException e)
        {
            return "unknown";
        }
    }

    public void setID(int i_id) { media.setMediaID(i_id); }
    public void setName(String i_name) { media.setMediaName(i_name); }
    //       END SETTERS AND GETTERS    //

    // LOCAL CRUD METHODS //
    /**
     * Create and persist a new media instance.
     * @param media local instance of media object
     * @return a string representing the view name to display after finishing the execution of this method.
     */
    public boolean add(MediaType media)
    {
        boolean result = false;
        String DML = "";
        db = new DBUtil();

        DML = "INSERT INTO media (mediaid, mediaName) VALUES (" +
              Integer.toString(media.getMediaID()) + ",'" +
              media.getMediaName() + "')";
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
     * @brief Update a media instance
     * @param media  reference to the media table object
     */
    public boolean update(MediaType media)
    {
        boolean result = false;
        String DML  = "";
        /* form DML from values in incoming object using MariaDB SQL syntax */
        DML = "UPDATE media (mediaid, mediaLogin, mediaName, mediaPasswd) VALUES (" +
        		media.getMediaID()      + ", '" +
        		media.getMediaName()  + "')";
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
     * @brief Delete a media instance
     * @param i_id the mediaid of the media record to delete
     */
    public boolean destroy(int i_id)
    {
        boolean result = false;
        String DML  = "DELETE FROM media WHERE mediaid = " + Integer.toString(i_id);
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
    	String objstr = "{ id: " + Integer.toString(this.getID()) + ", name: " + this.getName() + "}";
        return objstr;
    }
   
    public List<MediaType> retrieveByName(String i_nam)
    {
    	int mid = -1;
    	@SuppressWarnings("unused")
		String i_na = "";
        db = new DBUtil();
        rs = null;
        List <MediaType> medialist = new ArrayList<>();

        String DML = "SELECT mediaid, mediaName FROM libraryDB.media WHERE mediaName LIKE '"
        		+ i_nam
        		+ "' ORDER BY mediaid ASC";
        try
        {
            rs = db.execQuery(DML);
        }
        catch ( SQLException e )
        {
        	System.out.println("ERROR mediaImplDAO:retrieveAll 271: query error - " + e.getMessage());
        	return medialist;
        }

        try
        {
            while ( rs.next() )
            {
                mid = rs.getInt("mediaid");
                i_na = rs.getString("mediaName");
                media = new MediaType();
                media.setMediaID(mid);
                media.setMediaName(i_nam);
                medialist.add(media);
            }
        }
        catch ( SQLException | NullPointerException e)
        {
        	System.out.println("ERROR mediaImplDAO:retrieveAll 281: error adding records - "+e.getMessage());
        }
        db = null;
        return medialist;
    }

    @Override
    public int getLastID()
    {
        int lstid = -1;
        String DML = "SELECT max('mediaid') as max FROM media ";
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
     * @param i_u  the reference to the media instance to be "loaded" from database.
     */
    public void refreshObject(MediaType media)
    {
        int i_id = media.getMediaID();
        MediaType foundmedia = retrieveByID(i_id );
        media.setMediaID(foundmedia.getMediaID() );
        media.setMediaName(foundmedia.getMediaName() );
    }

    /**
     * @brief Retrieve all Title records from the Title table.
     *
     * @return the list of all Title records
     */
	public List<MediaType> retrieveAll()
    {
        db = new DBUtil();
        rs = null;
        List <MediaType> medialist = new ArrayList<>();

        String DML = "SELECT mediaid, mediaName FROM libraryDB.media ORDER BY mediaid ASC";
        try
        {
            rs = db.execQuery(DML);
        }
        catch ( SQLException e )
        {
        	System.out.println("ERROR mediaImplDAO:retrieveAll 271: query error - "+e.getMessage());
        	return medialist;
        }

        try
        {
            while ( rs.next() )
            {
                int mid = rs.getInt("mediaid");
                String i_na = rs.getString("mediaName");
                media = new MediaType();
                media.setMediaID(mid);
                media.setMediaName(i_na);
                medialist.add(media);
            }
        }
        catch ( SQLException e)
        {
        	System.out.println("ERROR mediaImplDAO:retrieveAll 281: error adding records - "+e.getMessage());
        }
        db = null;
        return medialist;
    }

    /**
     * @brief Retrieve a media record from the medias table.
     *
     * @param i_id the media's mediaid
     * @return mediaobj the media object with the given id
     */
    public MediaType retrieveByID(int i_id)
    {
        db = new DBUtil();
        rs = null;

        String DML = "SELECT * FROM medias WHERE mediaid = " + Integer.toString(i_id);
        media = new MediaType();
        try
        {
            rs = db.execQuery(DML);
            media.setMediaID(rs.getInt("mediaid"));
            media.setMediaName(rs.getString("mediaName"));
            db.close();
            return media;
        }
        catch ( SQLException e)
        {
            return null;
        }
    }

    /**
     * @brief Retrieve a media record from the medias table.
     *
     * @param i_login the media's login
     * @return mediaobj the media object with the given login
     */
    public MediaType retrieveByLogin(String i_login)
    {
        db = new DBUtil();
        rs = null;
        media = new MediaType();

        System.out.println("getting media by login: " + i_login);
        String DML = "SELECT * FROM medias WHERE login = '" + i_login + "'";
        try
        {
            rs = db.execQuery(DML);
            int i_id = rs.getInt("mediaid");
            if ( !Integer.toString(i_id).equals(null) )
            {
                media.setMediaID(rs.getInt("mediaid"));
                media.setMediaName(rs.getString("mediaName"));
            }
            else
            {
               System.out.println("Query Failure:"+ db.getLastError());
            }
            db.close();
        }
        catch ( SQLException e)
        {
            System.out.println("retrieveByLogin() had an error..."+e.getMessage());
        }
        db = null;
        return media;
    }

    /**
     * @brief Retrieve a media record from the medias table.
     *
     * @param i_login the media's login
     * @return mediaobj the media object with the given login
     */
    public MediaType retrieveByLoginPW(String i_login, String i_passwd)
    {
        db = new DBUtil();
        rs = null;

        String DML = "SELECT * FROM medias WHERE login = '" + i_login + "' AND mediaPasswd = '" + i_passwd + "'";
        try
        {
            rs = db.execQuery(DML);
            while (rs.next())
            {
	            int i_id = rs.getInt("mediaid");
	            if ( !Integer.toString(i_id).equals(null) )
	            {
	                media = new MediaType();
	                media.setMediaID(rs.getInt("mediaid"));
	                media.setMediaName(rs.getString("mediaName"));
	            }
	            else
	            {
	               System.out.println("mediaImplDAO:retrieveByLoginPW() 383 - Query Failure:"+ db.getLastError());
	            }
            }
            db.close();
        }
        catch ( SQLException e)
        {
            System.out.println("ERROR mediaImplDAO:retrieveByLoginPW() 383 - had an error..."+e.getMessage());
        }
        db = null;
        return media;
    }
    
    /**
     * @brief Clear all entries from the media table
     */
    public void clearData()
    {
        String DML = "DELETE FROM media WHERE mediaid >= 0 ";
        db = new DBUtil();
        try
        {
            db.execUpdate(DML);
            db.close();
        }
        catch ( SQLException e )
        {
            System.out.println("clearData() had an error: "+e.getMessage());
        }
    }

    /**
     * @brief Create test data (rows) in the medias table
     */
    public void createTestData()
    {
        db = new DBUtil();
        // clear existing books, so no primary key duplicate conflicts appear
        String DML = "INSERT INTO media (mediaid, privLvl, mediaName, mediaPasswd) VALUES (" +
                      Integer.toString(1) + "," + Integer.toString(9) + ",'admin','Database Admin,'NhbWun-333')";

        try
        {
            db.execUpdate(DML);
            db.close();
        }
        catch ( SQLException e )
        {
            System.out.println("createTestData() had an error: "+e.getMessage());
        }
        db = null;
    }
}
