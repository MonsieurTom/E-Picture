package tom.lenormand.java_epicture_2017.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import tom.lenormand.java_epicture_2017.R;

/**
 * Created by tomle on 06/02/2018.
 */

/**
 * adapter to handle the ownflow gridview
 */
public class OwnFlowAdapter extends ArrayAdapter<String>
{
    private Context context;
    private ArrayList<String> data;

    public OwnFlowAdapter(Context context, int textViewResourceId, ArrayList<String> objects)
    {
        super(context, textViewResourceId, objects);
        this.context = context;
        this.data = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View curView = convertView;
        if (curView == null)
        {
            LayoutInflater vi = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            curView = vi.inflate(R.layout.instagram_own_flow_gridview, null);
        }

        ImageView iv_photo = (ImageView) curView.findViewById(R.id.instagram_own_flow_image);

        Log.d("ownAdapter", "url=" + data.get(position));

        Picasso.with(context)
                .load(data.get(position))
                .into(iv_photo);
        return curView;
    }
}
