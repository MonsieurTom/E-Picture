package tom.lenormand.java_epicture_2017.bd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by tomle on 06/02/2018.
 */

/**
 * databases operations like open and closes
 */
public class DAOBase
{
    protected final static int VERSION = 1;
    protected final static String DATABASE_NAME = "database_epicture.db";

    protected SQLiteDatabase mDb = null;
    protected DatabaseHandler mHandler = null;

    public DAOBase()
    {}

    public DAOBase(Context context)
    {
        this.mHandler = new DatabaseHandler(context, DATABASE_NAME, null, VERSION);
    }

    public SQLiteDatabase open()
    {
        mDb = mHandler.getWritableDatabase();
        return (mDb);
    }

    public void close()
    {
        mDb.close();
    }
}
