package tom.lenormand.java_epicture_2017.POJO;

import com.google.gson.annotations.SerializedName;

/**
 * Created by tomle on 06/02/2018.
 */

/**
 * data container for the instagram api
 */
public class Counts
{
    @SerializedName("media")
    public int media;
    @SerializedName("follows")
    public int follows;
    @SerializedName("followed_by")
    public int followed_by;

    public int getMedia()
    { return media; }
    public void setMedia(int media)
    {this.media=media;}
    public int getFollows()
    { return follows; }
    public void setFollows(int follows)
    {this.follows=follows;}
    public int getFollowed_by()
    { return followed_by; }
    public void setFollowed_by(int followed_by)
    { this.followed_by = followed_by;}
}
