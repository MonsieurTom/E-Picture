package tom.lenormand.java_epicture_2017.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by tomle on 06/02/2018.
 */

/**
 * data container for the instagram api
 */
public class InstagramDataSearch
{
    @SerializedName("data")
    @Expose
    private DataSearch[] data;

    public DataSearch[] getData()
    {return data;}

    public void setData(DataSearch[] data)
    {this.data = data;}
}
