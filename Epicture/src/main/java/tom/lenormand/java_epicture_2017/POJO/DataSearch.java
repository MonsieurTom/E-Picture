package tom.lenormand.java_epicture_2017.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by tomle on 06/02/2018.
 */

/**
 * data container for the instagram api
 */
public class DataSearch
{
    @SerializedName("images")
    @Expose
    private Images images;
    @SerializedName("user")
    @Expose
    private User user;
    @SerializedName("likes")
    @Expose
    private Likes likes;
    @SerializedName("id")
    @Expose
    private String id;

    public String getId()
    { return this.id; }
    public void setId(String id)
    { this.id = id; }

    public Likes getLikes()
    { return likes; }
    public void setLikes(Likes likes)
    { this.likes = likes; }

    public Images getImages()
    {return images;}

    public User getUser()
    {return user;}

    public class User
    {
        @SerializedName("profile_picture")
        @Expose
        private String profile_picture;
        @SerializedName("username")
        @Expose
        private String full_name;

        public String getProfile_picture()
        {return profile_picture;}

        public String getFull_name()
        {return full_name;}
    }

    public class Images
    {
        @SerializedName("standard_resolution")
        @Expose
        private Standard_resolution standard_resolution;

        public Standard_resolution getStandard_resolution()
        {return standard_resolution;}

        public class Standard_resolution
        {
            @SerializedName("url")
            @Expose
            private String url;

            public String getUrl()
            {return url;}
        }
    }

    public class Likes
    {
        private int count;

        public int getCount()
        { return count; }
        public void setCount(int count)
        { this.count = count; }
    }
}
