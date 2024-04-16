package org.ad5xj.dao;

import java.util.List;

import org.ad5xj.model.*;

public interface ITitle extends Dao<Title>
{
    int    titleid  = -1;
    int    authKey  = -1;
    int    mediaKey = -1;
    int    loanKey  = -1;
    String title    = "";
    String bookNote = "";

    void Title();
    void Title(int i_id, int i_a, int i_m, int i_l, String i_title, String i_note);

    //       SETTERS AND GETTERS    //
    abstract int    getID();
    abstract int    getAuthKey();
    abstract int    getMediaKey();
    abstract int    getLoanKey();
    abstract String getTitle();
    abstract String getBookNote();

    abstract void setID(int i_id);
    abstract void setAuthKey(int i_a);
    abstract void setMediaKey(int i_m);
    abstract void setLoanKey(int i_l);
    abstract void setTitle(String i_title);
    abstract void setBookNote(String i_note);
    //       END SETTERS AND GETTERS    //

    abstract boolean add(Title titleobj);
    abstract boolean update(Title titleobj);
    abstract boolean destroy(int i_id);

    abstract String objectToString();

    abstract int getLastID();
    abstract void clearData();
    abstract List<Title> retrieveAll();
    abstract List<Title> retrieveByTitle(String i_title);
    abstract Title retrieveByID(int i_id );
    abstract void createTestData();
}
