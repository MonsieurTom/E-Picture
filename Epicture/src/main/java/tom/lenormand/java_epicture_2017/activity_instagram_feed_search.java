package tom.lenormand.java_epicture_2017;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tom.lenormand.java_epicture_2017.POJO.DataSearch;
import tom.lenormand.java_epicture_2017.POJO.InstagramDataPrivateUser;
import tom.lenormand.java_epicture_2017.POJO.InstagramDataSearch;
import tom.lenormand.java_epicture_2017.adapters.FeedSearchAdapters;
import tom.lenormand.java_epicture_2017.bd.FavDAO;
import tom.lenormand.java_epicture_2017.rest.RestClient;

/**
 * Created by tomle on 06/02/2018.
 */

/**
 * activity allowing the user to search a post by tag or by user name
 */
public class activity_instagram_feed_search extends Activity
{
    private EditText searchBar;
    private ListView listView;

    private FeedSearchAdapters adapter;
    private ArrayList<DataSearch> data = new ArrayList<>();

    private String access_token = "";
    private String owner = "";

    private FavDAO favDAO;

    /**
     * initialize activity_search
     * @param savedInstanceState state of the application
     */
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instagram_search_feed);

        // Get the access_token from the intent extra
        Intent i = this.getIntent();
        access_token = i.getStringExtra("access_token");
        owner = i.getStringExtra("username");

        listView = (ListView) findViewById(R.id.instagram_feed_search_list_view);
        searchBar = (EditText) findViewById(R.id.instagram_feed_search_edittext);

        // Set the listview adapter
        adapter = new FeedSearchAdapters(this, 0, data, owner);
        listView.setAdapter(adapter);

        favDAO = new FavDAO(this);
    }

    /**
     * allow the user to perform a query from the text the user entered
     * @param view the view of the button
     */
    public void onClickButtonSearchList(View view)
    {
        if(searchBar.getText().length() > 0)
        {
            adapter.clearListView();
            adapter.notifyDataSetChanged();

            RadioGroup radioGroup = (RadioGroup) findViewById(R.id.instagram_feed_search_radiogroup);
            RadioButton tagButton = (RadioButton) findViewById(R.id.instagram_feed_search_radioButton1);
            RadioButton userNameButton = (RadioButton) findViewById(R.id.instagram_feed_search_radioButton2);

            int selectedId = radioGroup.getCheckedRadioButtonId();

            if (selectedId == tagButton.getId())
                fetchData(searchBar.getText().toString());
            else if (selectedId == userNameButton.getId())
                fetchDataQuery(searchBar.getText().toString());
            searchBar.setText("");
            searchBar.clearFocus();
        }

        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
    }

    /**
     * fetch data by tag, do http request using retrofit library
     * @param tag the tag to fetch data by
     */
    public void fetchData(String tag)
    {
        Call<InstagramDataSearch> call = RestClient.getRetrofitService().getTagPhotos(tag, access_token);
        call.enqueue(new Callback<InstagramDataSearch>()
        {
            @Override
            public void onResponse(@NonNull Call<InstagramDataSearch> call, @NonNull Response<InstagramDataSearch> response)
            {
                if (response.body() != null)
                {
                    if (response.body().getData().length == 0)
                        Toast.makeText(activity_instagram_feed_search.this, "No post found", Toast.LENGTH_SHORT).show();
                    for(int i = 0; i < response.body().getData().length; i++)
                        data.add(response.body().getData()[i]);
                    adapter.setFavButton(false);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(@NonNull Call<InstagramDataSearch> call, @NonNull Throwable t)
            {
                //Handle failure
                Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    /**
     * fetch data by user's query, do http requests using retrofit library
     * @param query the query the user entered
     */
    public void fetchDataQuery(String query)
    {
        Call<InstagramDataPrivateUser> call911 = RestClient.getRetrofitService().getUserPrivateInfo(query, access_token);
        call911.enqueue(new Callback<InstagramDataPrivateUser>()
        {
            @Override
            public void onResponse(@NonNull Call<InstagramDataPrivateUser> call, @NonNull Response<InstagramDataPrivateUser> response)
            {
                if (response.body() != null)
                {
                    if (response.body().getData().length == 0)
                        Toast.makeText(activity_instagram_feed_search.this, "No relative query found", Toast.LENGTH_SHORT).show();
                    else
                    {
                        for (int i = 0; i < response.body().getData().length; i++)
                        {
                            Call<InstagramDataSearch> call912 = RestClient.getRetrofitService().getUserIdPhotos(response.body().getData()[i].getId(), access_token);
                            call912.enqueue(new Callback<InstagramDataSearch>() {
                                @Override
                                public void onResponse(@NonNull Call<InstagramDataSearch> call913, @NonNull Response<InstagramDataSearch> response2)
                                {
                                    if (response2.body() != null)
                                    {
                                        for (int j = 0; j < response2.body().getData().length; j++)
                                            data.add(response2.body().getData()[j]);
                                        adapter.setFavButton(false);
                                        adapter.notifyDataSetChanged();
                                    }
                                }
                                @Override
                                public void onFailure(@NonNull Call<InstagramDataSearch> call, @NonNull Throwable t)
                                {
                                    Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_LONG).show();
                                }
                            });
                        }
                    }
                }
            }
            @Override
            public void onFailure(@NonNull Call<InstagramDataPrivateUser> call, @NonNull Throwable t)
            {
                Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    /**
     * make the post as favorite
     * @param view view of the button
     */
    public void onClickButtonFavoris(View view)
    {
        final View parent = (View) view.getParent();

        Button bt_fav = (Button) parent.findViewById(R.id.instagram_feed_search_favoris_button);

        if (bt_fav.getTag().equals("favA"))
            bt_fav.setTag("favB");
        else
            bt_fav.setTag("favA");
        adapter.setFavButton(true);
        adapter.notifyDataSetChanged();
    }

    /**
     * post a comment on the post, doesn't work on sandbox mode.
     * @param view view of the button
     */
    public void onClickButtonComment(View view)
    {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        LayoutInflater layoutInflater = this.getLayoutInflater();
        final View cambioView = layoutInflater.inflate(R.layout.comment_dialog, null);
        dialog.setView(cambioView);

        final View parent = (View) view.getParent();

        dialog.setPositiveButton("Send", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                final EditText titleEditText = (EditText) cambioView.findViewById(R.id.comment_dialog_edittext);

                if (titleEditText.getText().equals(""))
                {
                    Toast.makeText(activity_instagram_feed_search.this, "Error Empty comment cannot be sent", Toast.LENGTH_SHORT).show();
                    return;
                }

                final ImageView img = (ImageView) findViewById(R.id.instagram_feed_search_listview_url_picture);
                for (int idx=0; idx<data.size();idx++)
                {
                    ImageView tmp = new ImageView(activity_instagram_feed_search.this);
                    Picasso.with(activity_instagram_feed_search.this)
                            .load(data.get(idx).getImages().getStandard_resolution().getUrl())
                            .into(tmp);

                    Bitmap b1 = ((BitmapDrawable)img.getDrawable()).getBitmap();
                    Bitmap b2 = ((BitmapDrawable)tmp.getDrawable()).getBitmap();

                    if (b1 == b2)
                    {


                        Call<Object> call = RestClient.getRetrofitService().postComment(data.get(idx).getId(), access_token, titleEditText.getText().toString());
                        call.enqueue(new Callback<Object>()
                        {
                            @Override
                            public void onResponse(Call<Object> call, Response<Object> response)
                            {
                                Log.d("response comment", ""+response.raw());
                                Toast.makeText(activity_instagram_feed_search.this, "Comments sent", Toast.LENGTH_SHORT).show();
                            }
                            @Override
                            public void onFailure(Call<Object> call, Throwable t)
                            {
                                Toast.makeText(activity_instagram_feed_search.this, "error", Toast.LENGTH_SHORT).show();
                            }
                        });
                        break;
                    }
                }
            }
        }).setNegativeButton("Cancel", null);
        dialog.show();
    }
}
