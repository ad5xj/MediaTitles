package org.ad5xj.model;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.enterprise.context.RequestScoped;

import java.io.Serializable;

import java.util.Objects;

/**
 * @brief Data bean of MediaType data object
 * @ingroup MARIADB
 * @author ken
 * @details This is the data bean of the MediaType data object in the Users table.
 */
@Entity @Table(name="MediaType") @RequestScoped
public class MediaType implements Serializable 
{
	private static final long serialVersionUID = 1L;

	@GeneratedValue @Id @Column(name = "mediaID", nullable = false) private int mediaID;
    @Basic @Column(name = "mediaName", nullable = false)            private String mediaName;

	public MediaType() { super(); }   

	public int getMediaID()      { return this.mediaID; }
	public String getMediaName() { return this.mediaName; }

	public void setMediaID(int mediaID) { this.mediaID = mediaID; }   
	public void setMediaName(String mediaName) { this.mediaName = mediaName; }
   
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
        MediaType that = (MediaType) o;
        return mediaID == that.mediaID && Objects.equals(mediaName, that.mediaName);
    }

    @Override
    public int hashCode( )
    {
        return Objects.hash(mediaID, mediaName);
    }
}
