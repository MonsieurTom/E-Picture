package tom.lenormand.java_epicture_2017.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by tomle on 06/02/2018.
 */

/**
 * data container for the instagram api
 */
public class InstagramDataPrivateUser
{
    @SerializedName("data")
    @Expose
    private DataPrivateUser[] data;

    public DataPrivateUser[] getData()
    { return data; }

    public void setData(DataPrivateUser[] data)
    { this.data = data; }
}
