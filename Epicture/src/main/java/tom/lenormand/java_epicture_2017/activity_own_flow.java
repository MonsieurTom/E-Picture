package tom.lenormand.java_epicture_2017;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tom.lenormand.java_epicture_2017.POJO.InstagramDataSearch;
import tom.lenormand.java_epicture_2017.adapters.OwnFlowAdapter;
import tom.lenormand.java_epicture_2017.rest.RestClient;

/**
 * Created by tomle on 06/02/2018.
 */

/**
 * print the user's flow of pictures
 */
public class activity_own_flow extends Activity
{
    private GridView gridView;
    private OwnFlowAdapter ownFlowAdapter;
    private ArrayList<String> data = new ArrayList<>();
    private String access_token = "";

    /**
     * initialize the activity
     * @param savedInstanceState state of the app
     */
    @Override
    protected void onCreate(final Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_own_flow);

        Intent i = this.getIntent();
        access_token = i.getStringExtra("access_token");

        Log.d("acces_token", access_token);

        gridView = (GridView) findViewById(R.id.activity_own_flow_grid_view);
        ownFlowAdapter = new OwnFlowAdapter(this, 0, data);
        gridView.setAdapter(ownFlowAdapter);

        fetchData();
    }

    /**
     * fetch data by doing an http request to the instagram api using retrofit2 library
     */
    public void fetchData()
    {
        Call<InstagramDataSearch> call911 = RestClient.getRetrofitService().getSelfMedia(access_token);
        call911.enqueue(new Callback<InstagramDataSearch>()
        {
            @Override
            public void onResponse(Call<InstagramDataSearch> call, Response<InstagramDataSearch> response)
            {
                Log.d("onResponse", "on response" + response.raw());
                if (response.body() != null)
                {
                    Log.d("onResponse", "body non null, nb found=" + response.body().getData().length);
                    if (response.body().getData().length == 0)
                        Toast.makeText(activity_own_flow.this, "No post found", Toast.LENGTH_LONG).show();
                    for(int i = 0; i < response.body().getData().length; i++)
                    {
                        Log.d("", response.body().getData()[i].getImages().getStandard_resolution().getUrl());
                        data.add(response.body().getData()[i].getImages().getStandard_resolution().getUrl());
                    }
                    ownFlowAdapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onFailure(Call<InstagramDataSearch> call, Throwable t)
            {
                Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
