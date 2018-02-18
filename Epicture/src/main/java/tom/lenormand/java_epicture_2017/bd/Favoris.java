package tom.lenormand.java_epicture_2017.bd;

import android.widget.ArrayAdapter;

import java.util.ArrayList;

/**
 * Created by tomle on 06/02/2018.
 */

/**
 * database classes to interface
 */
public class Favoris
{
    private String owner;
    private String userName;
    private String profil_picture;
    private String picture_url;
    private int likes;
    private String id;

    public Favoris()
    {}

    public Favoris(String owner, String username, String profilpicture, String urlPic, int likes, String id)
    {
        this.owner = owner;
        this.userName = username;
        this.profil_picture = profilpicture;
        this.picture_url = urlPic;
        this.likes = likes;
        this.id = id;
    }

    public String getId()
    { return id; }

    public void setId(String id)
    {this.id = id;}

    public int getLikes()
    { return likes; }
    public void setLikes(int likes)
    { this.likes = likes; }

    public String getOwner()
    { return owner; }
    public void setOwner(String owner)
    { this.owner = owner; }

    public String getUserName()
    { return userName; }
    public void setUserName(String userName)
    { this.userName = userName; }

    public String getProfil_picture()
    { return profil_picture; }
    public void setProfil_picture(String profil_picture)
    { this.profil_picture = profil_picture; }

    public String getPicture_url()
    { return picture_url; }
    public void setPicture_url(String picture_url)
    { this.picture_url = picture_url; }
}
