package tom.lenormand.java_epicture_2017.rest;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import tom.lenormand.java_epicture_2017.POJO.Comments;
import tom.lenormand.java_epicture_2017.POJO.InstagramDataPrivateUser;
import tom.lenormand.java_epicture_2017.POJO.InstagramDataSearch;
import tom.lenormand.java_epicture_2017.POJO.InstagramDataUser;

/**
 * Created by tomle on 05/02/2018.
 */

/**
 * retrofit requests interface
 */
public interface RetrofitInstagram
{
    @GET("v1/tags/{tag_name}/media/recent")
    Call<InstagramDataSearch> getTagPhotos(@Path("tag_name") String tag_name,
                                           @Query("access_token") String access_token);

    @GET("v1/users/self")
    Call<InstagramDataUser> getUserSelfInfo(@Query("access_token") String access_token);

    @GET("v1/users/search")
    Call<InstagramDataPrivateUser> getUserPrivateInfo(@Query("q") String username,
                                                      @Query("access_token") String access_token);

    @GET("v1/users/{user-id}/media/recent/")
    Call<InstagramDataSearch> getUserIdPhotos(@Path("user-id") String user_id,
                                              @Query("access_token") String access_token);

    @GET("v1/users/self/media/recent/")
    Call<InstagramDataSearch> getSelfMedia(@Query("access_token") String access_token);

    @FormUrlEncoded
    @POST("v1/media/{media_id}/comments")
    Call<Object> postComment(
            @Path("media_id") String mediaId,
            @Field("access_token") String accessToken,
            @Field("text") String text);
}
