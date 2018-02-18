package tom.lenormand.java_epicture_2017;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tom.lenormand.java_epicture_2017.POJO.DataSearch;
import tom.lenormand.java_epicture_2017.adapters.FavorisAdapter;
import tom.lenormand.java_epicture_2017.adapters.FeedSearchAdapters;
import tom.lenormand.java_epicture_2017.bd.FavDAO;
import tom.lenormand.java_epicture_2017.bd.Favoris;
import tom.lenormand.java_epicture_2017.rest.RestClient;

/**
 * Created by tomle on 07/02/2018.
 */


/**
 * Activity that show post selected has favorite
 */
public class activity_favoris extends Activity
{
    private ListView listView;
    private String owner = "";
    private String access_token = "";
    private FavorisAdapter adapter;

    private FavDAO favDAO;
    private ArrayList<Favoris> favorises = new ArrayList<>();

    /**
     * Instanciate activity_favoris
     * @param savedInstanceState state of the application
     */
    @Override
    protected void onCreate(final Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoris);

        // Get the access_token from the intent extra
        Intent i = this.getIntent();
        owner = i.getStringExtra("username");
        access_token = i.getStringExtra("access_token");


        favDAO = new FavDAO(this);

        listView = (ListView) findViewById(R.id.activity_favoris_listview);

        adapter = new FavorisAdapter(this, 1, favorises, owner);
        listView.setAdapter(adapter);

        fetchData();
    }

    /**
     * fetch data into internal database
     */
    private void fetchData()
    {
        ArrayList<Favoris> tmp = favDAO.getFav(owner);

        Log.d("fetchdata - size", ""+tmp.size());
        adapter.clearListView();
        for (int i = 0; i < tmp.size();i++)
            favorises.add(tmp.get(i));
        adapter.setFavButton(false);
        adapter.notifyDataSetChanged();
    }

    /**
     * unselect post as favoris
     * @param view view of the button (allow to get the parent).
     */
    public void onClickFavButtonFav(View view)
    {
        final View parent = (View) view.getParent();

        ImageView myPhoto = (ImageView) findViewById(R.id.instagram_favoris_favoris_url_picture);
        ArrayList<Favoris> tmpArray = favDAO.getFav(owner);

        for (int i=0;i<tmpArray.size();i++)
        {
            ImageView tmp = new ImageView(this);
            Picasso.with(this)
                    .load(tmpArray.get(i).getPicture_url())
                    .into(tmp);

            Bitmap b1 = ((BitmapDrawable)myPhoto.getDrawable()).getBitmap();
            Bitmap b2 = ((BitmapDrawable)tmp.getDrawable()).getBitmap();

            if (b1 == b2)
            {
                favDAO.deleteFromPictureUrl(owner, tmpArray.get(i).getPicture_url());
            }
        }
        adapter.setFavButton(true);
        adapter.notifyDataSetChanged();
        fetchData();
    }

    /**
     * post a comment on the post, doesn't work on sandbox mode.
     * @param view view of the button
     */
    public void onClickButtonCommentFavoris(View view)
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
                    Toast.makeText(activity_favoris.this, "Error Empty comment cannot be sent", Toast.LENGTH_SHORT).show();
                    return;
                }

                ArrayList<Favoris> data = favDAO.getFav(owner);

                final ImageView img = (ImageView) findViewById(R.id.instagram_favoris_favoris_url_picture);
                for (int idx=0; idx<data.size();idx++)
                {
                    ImageView tmp = new ImageView(activity_favoris.this);
                    Picasso.with(activity_favoris.this)
                            .load(data.get(idx).getPicture_url())
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
                                Toast.makeText(activity_favoris.this, "Comments sent", Toast.LENGTH_SHORT).show();
                            }
                            @Override
                            public void onFailure(Call<Object> call, Throwable t)
                            {
                                Toast.makeText(activity_favoris.this, "error", Toast.LENGTH_SHORT).show();
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
