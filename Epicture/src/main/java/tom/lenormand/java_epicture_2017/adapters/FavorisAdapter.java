package tom.lenormand.java_epicture_2017.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import tom.lenormand.java_epicture_2017.POJO.DataSearch;
import tom.lenormand.java_epicture_2017.R;
import tom.lenormand.java_epicture_2017.bd.FavDAO;
import tom.lenormand.java_epicture_2017.bd.Favoris;

/**
 * Created by tomle on 07/02/2018.
 */

/**
 * adapter to handle the favoris listview
 */
public class FavorisAdapter extends ArrayAdapter<Favoris>
{
    private Context context;
    private ArrayList<Favoris> favList;
    private String mOwner;
    private FavDAO favDAO;
    private boolean favButton = false;

    public FavorisAdapter(Context context, int textViewResourceId, ArrayList<Favoris> objects, String owner)
    {
        super(context, textViewResourceId, objects);
        this.context = context;
        this.favList = objects;
        mOwner = owner;
        favDAO = new FavDAO(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View curView = convertView;
        if (curView == null)
        {
            LayoutInflater vi = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            curView = vi.inflate(R.layout.instagram_favoris_listview, null);
        }
        TextView tv_user_fullname = (TextView) curView.findViewById(R.id.instagram_favoris_favoris_username);
        ImageView iv_photo = (ImageView) curView.findViewById(R.id.instagram_favoris_favoris_url_picture);
        ImageView iv_profile = (ImageView) curView.findViewById(R.id.instagram_favoris_favoris_profile_picture);
        Button iv_favoris = (Button) curView.findViewById(R.id.instagram_favoris_favoris_button);
        TextView tv_likes = (TextView) curView.findViewById(R.id.instagram_favoris_favoris_likes);

        tv_user_fullname.setText(favList.get(position).getUserName());
        tv_likes.setText("Likes: " + favList.get(position).getLikes());
        iv_favoris.setBackgroundResource(R.drawable.icon_favorisb);


        Picasso.with(context)
                .load(favList.get(position).getProfil_picture())
                .resize(100, 100)
                .centerInside()
                .into(iv_profile);
        Picasso.with(context)
                .load(favList.get(position).getPicture_url())
                .into(iv_photo);
        return curView;
    }

    public void clearListView() {
        favList.clear();
    }

    public void setFavButton(boolean state) {
        favButton = state;
    }
}
