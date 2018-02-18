package tom.lenormand.java_epicture_2017.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by tomle on 05/02/2018.
 */

/**
 * data container for the instagram api
 */
public class InstagramDataUser
{
    @SerializedName("data")
    @Expose
    private DataUser data;

    public DataUser getData() {
        return data;
    }

    public void setData(DataUser data) {
        this.data = data;
    }
}
