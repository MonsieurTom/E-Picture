package tom.lenormand.java_epicture_2017.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by tomle on 06/02/2018.
 */

/**
 * data container for the instagram api
 */
public class DataPrivateUser
{
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("first_name")
    @Expose
    private String first_name;
    @SerializedName("profile_picture")
    @Expose
    private String profile_picture;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("last_name")
    @Expose
    private String last_name;

    public String getUsername()
    { return this.username; }
    public void setUsername(String username)
    { this.username = username; }

    public String getFirst_name()
    { return first_name; }
    public void setFirst_name(String first_name)
    { this.first_name = first_name; }

    public String getProfile_picture()
    { return this.profile_picture; }
    public void setProfile_picture(String profile_picture)
    { this.profile_picture = profile_picture; }

    public String getId()
    { return id; }
    public void setId(String id)
    { this.id = id; }

    public String getLast_name()
    {return last_name;}

    public void setLast_name(String last_name)
    {this.last_name = last_name;}
}
