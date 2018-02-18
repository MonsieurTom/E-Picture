package tom.lenormand.java_epicture_2017;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.CookieSyncManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tom.lenormand.java_epicture_2017.POJO.DataUser;
import tom.lenormand.java_epicture_2017.POJO.InstagramDataUser;
import tom.lenormand.java_epicture_2017.rest.RestClient;

/**
 * Created by tomle on 05/02/2018.
 */

/**
 * main profil page of the user logged in instagram.
 */
public class activity_instagram_main extends Activity
{
    private ImageView profile_pic = null;
    private TextView user_fullname = null;
    private TextView user_name = null;
    private TextView counts_medias = null;
    private TextView counts_followers = null;
    private TextView counts_following = null;
    private String access_token = "";

    /**
     * initialize the activity
     * @param savedInstanceState state of the app
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instagram_main);

        Intent i = this.getIntent();
        access_token = i.getStringExtra("access_token");

        fetchUserData();
    }

    /**
     * fetch data of the user logged in
     */
    public void fetchUserData()
    {
        Call<InstagramDataUser> call = RestClient.getRetrofitService().getUserSelfInfo(access_token);

        if (call == null)
            Log.d("", "call = null");
        call.enqueue(new Callback<InstagramDataUser>() {
            @Override
            public void onResponse(@NonNull Call<InstagramDataUser> call, Response<InstagramDataUser> response)
            {
                //Log.d("onReponse", "inside CODE=" + response.raw());
                if (response.body() != null)
                {
                    Log.d("onResponse", "username" + response.raw());

                    DataUser data = response.body().getData();

                    profile_pic = (ImageView) findViewById(R.id.instagram_main_profile_picture);
                    Picasso.with(activity_instagram_main.this)
                            .load(data.getProfile_picture())
                            .resize(200, 200)
                            .into(profile_pic);
                    user_fullname = (TextView) findViewById(R.id.instagram_main_full_name);
                    user_fullname.setText(data.getFull_name());
                    user_name = (TextView) findViewById(R.id.instagram_main_user_name);
                    user_name.setText(data.getUsername());
                    counts_medias = (TextView) findViewById(R.id.instagram_main_counts_medias);
                    counts_medias.setText("Post : " + String.valueOf(data.getCounts().getMedia()));
                    counts_followers = (TextView) findViewById(R.id.instagram_main_counts_followers);
                    counts_followers.setText("Followers : " + String.valueOf(data.getCounts().getFollowed_by()));
                    counts_following = (TextView) findViewById(R.id.instagram_main_counts_following);
                    counts_following.setText("Following : " + String.valueOf(data.getCounts().getFollows()));
                }
            }

            @Override
            public void onFailure(@NonNull Call<InstagramDataUser> call, Throwable t) {
                //Handle failure
                Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    /**
     * disconnect the user from hos instagram account
     * @param view view of the button
     */
    public void onClickButtonDisconnect(View view)
    {
        android.webkit.CookieManager.getInstance().removeAllCookies(null);

        Intent intent = new Intent(activity_instagram_main.this, MainActivity.class);
        startActivity(intent);
    }

    /**
     * go to the search activity
     * @param view view of the button
     */
    public void onClickButtonSearch(View view)
    {
        user_fullname = (TextView)  findViewById(R.id.instagram_main_full_name);

        Intent i = new Intent(activity_instagram_main.this, activity_instagram_feed_search.class);
        i.putExtra("access_token", access_token);
        i.putExtra("username", user_fullname.getText());
        startActivity(i);
    }

    /**
     * go to the own flow activity
     * @param view view of the button
     */
    public void onClickButtonOwnFlow(View view)
    {
        Intent i = new Intent(this, activity_own_flow.class);

        i.putExtra("access_token", access_token);
        startActivity(i);
    }

    /**
     * go to the add photo activity
     * @param view view of the button
     */
    public void onClickButtonAddPhotos(View view)
    {
        Intent i = new Intent(this, activity_picture_choser.class);
        i.putExtra("access_token", access_token);
        startActivity(i);
    }

    /**
     * go to the favoris activity
     * @param view view of the button
     */
    public void onClickButtonToFavButton(View view)
    {
        Intent i = new Intent(this, activity_favoris.class);
        user_fullname = (TextView) findViewById(R.id.instagram_main_full_name);

        i.putExtra("username", user_fullname.getText());
        i.putExtra("access_token", access_token);

        startActivity(i);
    }
}
