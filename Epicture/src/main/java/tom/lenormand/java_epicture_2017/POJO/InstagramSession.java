package tom.lenormand.java_epicture_2017.POJO;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import tom.lenormand.java_epicture_2017.constant.Constants;

/**
 * Created by tomle on 05/02/2018.
 */

/**
 * Manage all information of a session (access_token and username).
 */
public class InstagramSession
{
    private SharedPreferences sharedPref;
    private Editor editor;

    InstagramSession(Context context)
    {
        sharedPref = context.getSharedPreferences(Constants.SHARED_NAME, Context.MODE_PRIVATE);
        editor = sharedPref.edit();
    }

    /**
     * Store Token's data and username's data
     * @param accessToken access token
     * @param id clients id
     * @param username username
     * @param name name
     */
    public void storeAccessToken(String accessToken, String id, String username, String name)
    {
        editor.putString(Constants.INSTAGRAM_API_ID, id);
        editor.putString(Constants.INSTAGRAM_API_NAME, name);
        editor.putString(Constants.INSTAGRAM_API_ACCESS_TOKEN, accessToken);
        editor.putString(Constants.INSTAGRAM_API_USERNAME, username);
        editor.commit();
    }

    /**
     * Store access_token data
     * @param accessToken the accessToken
     */
    public void storeAccessToken(String accessToken)
    {
        editor.putString(Constants.INSTAGRAM_API_ACCESS_TOKEN, accessToken);
        editor.commit();
    }

    /**
     * Reset all data from the Editor save
     */
    public void resetAccessToken()
    {
        editor.putString(Constants.INSTAGRAM_API_ID, null);
        editor.putString(Constants.INSTAGRAM_API_NAME, null);
        editor.putString(Constants.INSTAGRAM_API_ACCESS_TOKEN, null);
        editor.putString(Constants.INSTAGRAM_API_USERNAME, null);
        editor.commit();
    }

    /**
     * Get the user name
     * @return User name
     */
    public String getUsername()
    {
        return sharedPref.getString(Constants.INSTAGRAM_API_USERNAME, null);
    }

    /**
     * Get the id
     * @return the id
     */
    public String getId()
    {
        return sharedPref.getString(Constants.INSTAGRAM_API_ID, null);
    }

    /**
     * Get the name
     * @return the name
     */
    public String getName()
    {
        return sharedPref.getString(Constants.INSTAGRAM_API_NAME, null);
    }

    /**
     * Get access token
     * @return the access_token
     */
    public String getAccessToken()
    {
        return sharedPref.getString(Constants.INSTAGRAM_API_ACCESS_TOKEN, null);
    }
}
