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

/**
 * @file DBUtil.java
 * @defgroup MARIADB
 */
package org.ad5xj.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @ingroup MARIADB
 * @brief Provides database level JDBC functions
 * @details
 * Provides database level JDBC functions as a transfer object
 * for MariaDB database access as a transfer object for MariaDB SQL
 * database access. Use of other database types require a unique
 * transfer object for each (e.g. MySQL, Postgres, etc.).
 *
 * @author ken
 * @copyright
 * &copy;Copyright 2023 ken AD5XJ@qso.com see the copyright notice in
 * the help files.
 * \license
 * This product is released under the GNU Public license found in the
 * help documentation.
 */
public class DBUtil
{
    private boolean connected; // connect flag true=connected false=disconnected

    private int    lstErr;

    private String lastError;
    private String CONN_STR;

    private PreparedStatement stmt;
    private Connection conn;
    /*
    private String DBUSER;
    private String DBPWD;

    private static final String DBDRIVER = "jdbc:mariadb";
    private static final String DBHOST = "localhost"; // set in .ini file
    private static final String DBPORT = "3306";
    private static final String DBPATH = "/";
    private static final String DBNAME = "libraryDB";

	private static final String THISMODULE = "DBUtil";
    private static final String DBSECTION  = "DATABASE";
    */

    public DBUtil()
    {
        initLocalVars();
        setDBPath();
    }

    //Connect to DB
    public void connect() throws SQLException
    {
        if ( conn != null ) // test for connection previously open
        {
        	System.out.println("DEBUG DBUtil:connect - connection is open so close it");
            conn.close();
            connected = false;
        }

        try
        {
    	    Class.forName("org.mariadb.jdbc.Driver");
        }
        catch ( ClassNotFoundException e )
        {
        	System.out.println("ERROR DBUtil : connect() - driver failed " + e.getMessage());
        	return;
        }

    	Properties connConfig = new Properties();
        connConfig.setProperty("user", "root");
        connConfig.setProperty("password", "NhbWun-333");
        try
        {
            conn = (Connection) DriverManager.getConnection("jdbc:mariadb://localhost:3306/libraryDB",connConfig);
            connected = true;
        }
        catch ( SQLException e )
        {
            String msg;
            msg  = "ERROR DBUtil:connect - Connection Failed! err=" + e.getMessage() + "\nConn:" + CONN_STR;
            System.out.println(msg);
            connected = false;
            throw e;
        }
    }

    //Close Connection
    public void close() throws SQLException
    {
        try
        {
            if ( (conn != null) && !(conn.isClosed()) )
            {
                conn.close();
                connected = false;
            }
        }
        catch ( SQLException e)
        {
            connected = false;
            throw e;
        }
    }

    //DB Execute Query Operation
    public ResultSet execQuery(String dmlstr) throws SQLException
    {
        ResultSet rs = null;
        String DML = dmlstr;

        if ( dmlstr.trim().equals("") ) { return null; }

        if ( !connected )
        {   //Connect to DB (Establish JDBC Connection)
            try
            {
                connect();
            }
            catch ( SQLException e )
            {
                String msg = "execQuery: Unable to connect at execQuery operation : " + e.getMessage() + "\nCONN:" + CONN_STR;
                System.out.println(msg);
                throw e;
            }
        }

        try
        {
            //Create statement
            stmt = conn.prepareStatement(DML);
            rs = stmt.executeQuery();
            return rs;
        }
        catch ( SQLException | NullPointerException e )
        {
            String msg;
            msg  = "ERROR DBUtil.execQuery(): Problem occurred preparing statement: " + e.getMessage() + "\nSQL=" + dmlstr;
            System.out.println(msg);
            throw e;
        }
    }

    public void execSQL(String i_dml) throws SQLException
    {
        String DML = i_dml;

        //Connect to DB (Establish JDBC Connection)
        try
        {
            connect();
        }
        catch ( SQLException e )
        {
            String msg = "ERROR DBUtil.execSQL(): Unable to connect at execSQL operation : " + e.getMessage();
            System.out.println(msg);
            throw e;
        }

        try
        {
            //Create Statement
            stmt = conn.prepareStatement(DML);
            //Run executeUpdate operation with given sql statement
            stmt.execute();
        }
        catch (SQLException e)
        {
            String msg = "ERROR DBUtil.execSQL(): Problem occurred at execSQL operation : " + e.getMessage() + "\nSQL:" + i_dml;
            System.out.println(msg);
            throw e;
        }
        finally
        {
            if (stmt != null)
            {
                try
                {
                    stmt.close();
                }
                catch ( SQLException se )
                {
                    String msg = "ERROR DBUtil.execSQL(): Problem occurred at execSQL statement close : " + se.getMessage()+"\nSQL: " + i_dml;
                    System.out.println(msg);
                }
            }
            //Close connection
            try
            {
                close();
            }
            catch ( SQLException ce )
            {
                String msg = "ERROR DBUtil.execSQL(): Problem occurred on close : " + ce.getMessage() + "\nSQL: " + i_dml;
                System.out.println(msg);
            }
        }
    }

    //DB Execute Update (For Update/Insert/Delete) Operation
    public boolean execUpdate(String i_dml)
    {
        boolean result = false;
        String DML = i_dml;
        //Connect to DB (Establish JDBC Connection)
        try
        {
            connect();
            result = true;
        }
        catch ( SQLException e )
        {
            String msg = "ERROR DBUtil.execUpdate(): Problem occurred on connecting to DB : " + e.getMessage();
            System.out.println(msg);
            result = false;
            return result;
        }

        try
        {
            //Create Statement
            stmt = conn.prepareStatement(DML);
            //Run executeUpdate operation with given sql statement
            result = stmt.execute();
            close();
        }
        catch (SQLException e)
        {
            String msg = "ERROR DBUtil.execUpdate(): Problem occurred at executeUpdate operation : " + e.getMessage() + "\nSQL: " + i_dml;
            System.out.println(msg);
            result = false;
        }
        return result;
    }

    //DB Execute Update (For Update/Insert/Delete) Operation in a loop or batch
    public boolean execUpdateBatch(String i_dml)
    {
        boolean result = false;
        String DML = i_dml;
        //Connect to DB (Establish JDBC Connection)
        try
        {
            if ( !connected ) { connect(); }
        }
        catch ( SQLException e )
        {
            String msg = "ERROR DBUtil.execUpdate(): Problem occurred on connecting to DB : "+ e.getMessage();
            System.out.println(msg);
            result = false;
        }

        try
        {
            //Create Statement
            stmt = conn.prepareStatement(DML);
            //Run executeUpdate operation with given sql statement
            stmt.executeUpdate();
            close();
            result = true;
        }
        catch (SQLException e)
        {
            String msg = "ERROR DBUtil.execUpdate(): Problem occurred at executeUpdate operation : " + e.getMessage() + "\nSQL: " + i_dml;
            System.out.println(msg);
            result = false;
        }
        return result;
    }

    public String getLastError()
    {
        return lastError;
    }

    public int getLastErrorCode()
    {
        return lstErr;
    }

    private void setDBPath()
    {
        // Host name not used since SQLite 3 assumes localhost
        // if remote database is ever used then the path should include
        // the mounted path to it.
        String os = System.getProperty("os.name").toLowerCase();
        if ( os.contains("lin") )
        {
        	/*
            if ( !DBDRIVER.equals("") ) CONN_STR  = DBDRIVER + ":";
            if ( !DBHOST.equals("") ) { CONN_STR += "//"; CONN_STR += DBHOST; }
            if ( !DBPORT.equals("") ) { CONN_STR += ":" + DBPORT + "/"; }
            if ( !DBPATH.equals("") )
            {
                CONN_STR += DBPATH;
            }
            else
            {
                CONN_STR += "localhost";
            }
            CONN_STR += "/";
            CONN_STR += DBNAME;
            */
            CONN_STR = "jdbc:mariadb://localhost:3306/libraryDB?user='root';password='NhbWun-333'";
        }
        else if ( os.contains("win") )
        {
            /*
            CONN_STR  = DBDRIVER;
            CONN_STR += "\\";
            CONN_STR += DBPATH;
            CONN_STR += "\\";
            CONN_STR += DBNAME;
            */
            CONN_STR = "jdbc:mariadb:\\localhost:3306\\libraryDB?user='root'&password='NhbWun-333'";
        }
    }

    private void initLocalVars()
    {
        connected = false;

        CONN_STR = "";
    }
}