package tom.lenormand.java_epicture_2017.POJO;

/**
 * Created by tomle on 07/02/2018.
 */

/**
 * data container for the instagram api
 */
public class Comments
{
    private String access_token;
    private String text;

    public Comments(String access_token, String text)
    {
        this.access_token = access_token;
        this.text = text;
    }

    public String getAccess_token()
    {
        return (access_token);
    }
    public void setAccess_token(String access_token)
    {
        this.access_token = access_token;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
