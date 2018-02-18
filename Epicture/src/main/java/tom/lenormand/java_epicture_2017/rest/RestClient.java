package tom.lenormand.java_epicture_2017.rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import tom.lenormand.java_epicture_2017.constant.Constants;

/**
 * Created by tomle on 05/02/2018.
 */

/**
 * restClient using retrofit library
 */
public class RestClient
{
    public static RetrofitInstagram getRetrofitService() {
        return new Retrofit.Builder()
                .baseUrl(Constants.AUTH_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(RetrofitInstagram.class);
    }
}
