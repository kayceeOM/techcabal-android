package onyekachi.me.techcabal;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

/**
 * Created by Onyekachy on 02/01/2015.
 */
 abstract class DBModel implements BaseColumns {

    public static final String TYPE_INTEGER_PRIMARY_KEY = "INTEGER PRIMARY KEY AUTOINCREMENT";
    public static final String TYPE_INTEGER = "INTEGER";
    public static final String TYPE_TEXT = "TEXT";
    public static final String TYPE_UNIQUE_TEXT = "TEXT NOT NULL UNIQUE";

//    public abstract String[] getColumns();
//    public abstract String[] getTypes();
//    public abstract String getTableName();

    private static final String DATABASE_NAME = "TECHCABAL";
    protected static SQLiteDatabase database;


    public static void open(Context context) throws SQLException
    {
        DatabaseOpenHelper databaseOpenHelper = new DatabaseOpenHelper(context, DATABASE_NAME, 1);

        if(database == null)
        {
            database = databaseOpenHelper.getWritableDatabase();
        }
    }

    public static void close()
    {
        if (database != null)
        {
            database.close();
        }
    }

}
