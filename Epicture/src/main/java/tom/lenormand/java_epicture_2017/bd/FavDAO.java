package tom.lenormand.java_epicture_2017.bd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;

import java.util.ArrayList;

import tom.lenormand.java_epicture_2017.POJO.DataPrivateUser;

/**
 * Created by tomle on 06/02/2018.
 */

/**
 * implementation of the Favoris classes into the database
 */
public class FavDAO extends DAOBase
{
    public FavDAO(Context context)
    { super(context); }

    public void add(Favoris favoris)
    {
        open();
        ContentValues values = new ContentValues();
        values.put(DatabaseHandler.FAV_OWNER, favoris.getOwner());
        values.put(DatabaseHandler.FAV_PICTURE_URL, favoris.getPicture_url());
        values.put(DatabaseHandler.FAV_USERNAME, favoris.getUserName());
        values.put(DatabaseHandler.FAV_PROFILE_PICTURE, favoris.getProfil_picture());
        values.put(DatabaseHandler.FAV_LIKES, favoris.getLikes());
        values.put(DatabaseHandler.FAV_ID, favoris.getId());
        mDb.insert(DatabaseHandler.FAV_TABLE_NAME, null, values);
        close();
    }

    public void deleteFromUserName(String username)
    {
        open();
        mDb.delete(DatabaseHandler.FAV_TABLE_NAME, DatabaseHandler.FAV_USERNAME + " = ?",
                new String[]{username});
        close();
    }

    public void deleteFromPictureUrl(String owner, String picture_url)
    {
        open();
        mDb.delete(DatabaseHandler.FAV_TABLE_NAME, DatabaseHandler.FAV_PICTURE_URL + " = ? AND " + DatabaseHandler.FAV_OWNER + " = ?",
                new String[]{picture_url, owner});
        close();
    }

    public ArrayList<Favoris> getFav(String owner)
    {
        ArrayList<Favoris>      favList = new ArrayList<>();
        int                     idxUsername;
        int                     idxowner;
        int                     idxProfilePic;
        int                     idxPicUrl;
        int                     idxLikes;
        int                     idxId;

        open();
        Cursor cursor = mDb.rawQuery("SELECT * FROM " + DatabaseHandler.FAV_TABLE_NAME, null);
        while (cursor.moveToNext())
        {
            idxowner = cursor.getColumnIndex(DatabaseHandler.FAV_OWNER);
            idxPicUrl = cursor.getColumnIndex(DatabaseHandler.FAV_PICTURE_URL);
            idxProfilePic = cursor.getColumnIndex(DatabaseHandler.FAV_PROFILE_PICTURE);
            idxUsername = cursor.getColumnIndex(DatabaseHandler.FAV_USERNAME);
            idxLikes = cursor.getColumnIndex(DatabaseHandler.FAV_LIKES);
            idxId = cursor.getColumnIndex(DatabaseHandler.FAV_ID);

            if (cursor.getString(idxowner).equals(owner))
            {
                Favoris                 fav = new Favoris();
                fav.setOwner(owner);
                fav.setPicture_url(cursor.getString(idxPicUrl));
                fav.setProfil_picture(cursor.getString(idxProfilePic));
                fav.setUserName(cursor.getString(idxUsername));
                fav.setLikes(cursor.getInt(idxLikes));
                fav.setId(cursor.getString(idxId));
                favList.add(fav);
            }
        }
        cursor.close();
        close();
        return favList;
    }
}
