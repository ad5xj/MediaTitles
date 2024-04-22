package org.ad5xj.dao;

import java.util.List;

import org.ad5xj.model.*;

public interface IMediaType extends Dao<MediaType>
{
    int    mediaid = -1;
    String medianame = "";

    void MediaType();
    void MediaType(int i_id, String i_name);

    //       SETTERS AND GETTERS    //
    abstract int     getID();
    abstract String  getName();

    abstract void setID(int i_id);
    abstract void setName(String i_name);
    //       END SETTERS AND GETTERS    //

    abstract boolean add(MediaType mediaobj);
    abstract boolean update(MediaType mediaobj);
    abstract boolean destroy(int i_id);

    abstract String objectToString();

    abstract int getLastID();
    abstract void clearData();
    abstract List<MediaType> retrieveAll();
    abstract List<MediaType> retrieveByName(String i_name);
    abstract MediaType retrieveByID(int i_id );
    abstract void createTestData();
}
