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
 * Created by tomle on 06/02/2018.
 */

/**
 * adapter to handle the search listview activity
 */
public class FeedSearchAdapters extends ArrayAdapter<DataSearch>
{
    private FavDAO favDAO;
    private Context context;
    private ArrayList<DataSearch> data;
    private ArrayList<Favoris> favList;
    private String mOwner;
    private boolean favButton = false;

    public FeedSearchAdapters(Context context, int textViewResourceId, ArrayList<DataSearch> objects, String owner)
    {
        super(context, textViewResourceId, objects);
        this.context = context;
        this.data = objects;
        favDAO = new FavDAO(context);
        mOwner = owner;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View curView = convertView;
        if (curView == null)
        {
            LayoutInflater vi = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            curView = vi.inflate(R.layout.instagram_feed_search_listview, null);
        }

        TextView tv_user_fullname = (TextView) curView.findViewById(R.id.instagram_feed_search_listview_username);
        ImageView iv_photo = (ImageView) curView.findViewById(R.id.instagram_feed_search_listview_url_picture);
        ImageView iv_profile = (ImageView) curView.findViewById(R.id.instagram_feed_search_listview_profile_picture);
        Button iv_favoris = (Button) curView.findViewById(R.id.instagram_feed_search_favoris_button);
        TextView tv_likes = (TextView) curView.findViewById(R.id.instagram_feed_search_likes);

        favList = favDAO.getFav(mOwner);
        boolean isFav = false;

        Log.d("adapter", "tours de adapter");
        for (int i = 0; i < favList.size(); i++)
        {
            Log.d("dao", "tours de boucle");
            if (favList.get(i).getOwner().equals(mOwner))
            {
                if (favList.get(i).getPicture_url().equals(data.get(position).getImages().getStandard_resolution().getUrl()))
                {
                    isFav = true;
                    Log.d("dao", " owner=" + mOwner + " url=" + favList.get(i).getPicture_url());
                    break;
                }
            }
        }

        if (!isFav && !favButton)
            iv_favoris.setTag("favA");
        else if (isFav && !favButton)
            iv_favoris.setTag("favB");

        if (iv_favoris.getTag().equals("favA"))
            iv_favoris.setBackgroundResource(R.drawable.icon_favorisa);
        else if (iv_favoris.getTag().equals("favB"))
            iv_favoris.setBackgroundResource(R.drawable.icon_favorisb);

        if (iv_favoris.getTag().equals("favB") && !isFav) {
            Log.d("dao", "adding" + mOwner + " url=" + data.get(position).getImages().getStandard_resolution().getUrl());
            favDAO.add(new Favoris(mOwner,
                    data.get(position).getUser().getFull_name(),
                    data.get(position).getUser().getProfile_picture(),
                    data.get(position).getImages().getStandard_resolution().getUrl(),
                    data.get(position).getLikes().getCount(),
                    data.get(position).getId()));
        }
        else if (iv_favoris.getTag().equals("favA") && isFav) {
            favDAO.deleteFromPictureUrl(mOwner, data.get(position).getImages().getStandard_resolution().getUrl());
        }

        tv_user_fullname.setText(data.get(position).getUser().getFull_name());
        tv_likes.setText("Likes: " + data.get(position).getLikes().getCount());

        Picasso.with(context)
                .load(data.get(position).getUser().getProfile_picture())
                .resize(100, 100)
                .centerInside()
                .into(iv_profile);
        Picasso.with(context)
                .load(data.get(position).getImages().getStandard_resolution().getUrl())
                .into(iv_photo);

        return curView;
    }

    public void clearListView() {
        data.clear();
    }

    public void setFavButton(boolean state) { favButton = state; }

}
