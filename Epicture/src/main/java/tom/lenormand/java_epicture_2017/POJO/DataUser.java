package tom.lenormand.java_epicture_2017.POJO;

import android.provider.ContactsContract;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by tomle on 05/02/2018.
 */

/**
 * data container for the instagram api
 */
public class DataUser
{
    @SerializedName("profile_picture")
    @Expose
    public String profile_picture;
    @SerializedName("username")
    @Expose
    public String username;
    @SerializedName("full_name")
    @Expose
    public String full_name;
    @SerializedName("website")
    @Expose
    public String website;
    @SerializedName("bio")
    @Expose
    public String bio;
    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("is_bisuness")
    @Expose
    public boolean is_business;

    public boolean getIs_business()
    { return this.is_business;}
    public void setIs_business(boolean is_business)
    { this.is_business = is_business; }

    public String getId()
    {return this.id;}
    public void setId(String id)
    {this.id = id;}

    public String getProfile_picture() {
        return profile_picture;
    }
    public void setProfile_picture(String profile_picture)
    {this.profile_picture = profile_picture;}

    public String getFull_name() {
        return full_name;
    }
    public void setFull_name(String full_name)
    { this.full_name = full_name;}

    public String getUsername()
    { return username; }
    public void setUsername()
    { this.username = username; }

    public String getWebsite()
    { return website; }
    public void setWebsite(String website)
    { this.website = website;}

    public String getBio()
    { return bio; }
    public void setBio(String bio)
    { this.bio = bio;}

    @SerializedName("counts")
    @Expose
    public Counts counts;

    public Counts getCounts()
    { return this.counts; }
    public void setCounts(Counts counts)
    {this.counts = counts;}

}
