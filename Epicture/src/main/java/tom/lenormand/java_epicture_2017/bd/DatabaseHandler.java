package tom.lenormand.java_epicture_2017.bd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by tomle on 06/02/2018.
 */

/**
 * creation of the database's columns
 */
public class DatabaseHandler extends SQLiteOpenHelper
{
    public static final String FAV_TABLE_NAME = "favoris";
    public static final String FAV_KEY = "kee";
    public static final String FAV_USERNAME = "username";
    public static final String FAV_PROFILE_PICTURE = "profile_picture";
    public static final String FAV_PICTURE_URL = "picture_url";
    public static final String FAV_OWNER = "owner";
    public static final String FAV_LIKES = "likes";
    public static final String FAV_ID = "id";
    public static final String FAV_TABLE_CREATE =
            "CREATE TABLE " + FAV_TABLE_NAME + " (" +
                    FAV_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    FAV_OWNER + " TEXT, " +
                    FAV_USERNAME + " TEXT, " +
                    FAV_PROFILE_PICTURE + " TEXT, " +
                    FAV_LIKES + " INTEGER, " +
                    FAV_ID + " TEXT, " +
                    FAV_PICTURE_URL + " TEXT);";

    public static final String TASK_TABLE_DROP = "DROP TABLE IF EXISTS " + FAV_TABLE_NAME + ";";

    /**
     * constructor, call the parent's constructor
     * @param context context of the activity
     * @param name name of the database (exemple: "database.db"
     * @param factory factory of cursor allowing use to move inside the database
     * @param version version of the database
     */
    public DatabaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version)
    {
        super(context, name, factory, version);
    }

    /**
     * call upon creation of the databaseHandler
     * @param db the database itself
     */
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(FAV_TABLE_CREATE);
    }

    /**
     * call upon upgrade of the databaseHandler
     * @param db the database itself
     * @param oldVersion oldversion
     * @param newVersion newversion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        onCreate(db);
    }
}
