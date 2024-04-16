package org.ad5xj.model;

public class TitleStr
{
	String titleid_s = "";
    String author_s  = "";
    String media_s   = "";
    String loan_s    = "";
    String title_s   = "";
    String note_s    = "";

    public TitleStr() { }
    
    public TitleStr(String i_id, String i_a, String i_m, String i_l, String i_t, String i_n)
    {
        this.titleid_s = i_id;
        this.author_s  = i_a;
        this.media_s   = i_m;
        this.loan_s    = i_l;
        this.title_s   = i_t;
        this.note_s    = i_n;
    };
    
};