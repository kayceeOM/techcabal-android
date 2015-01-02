package onyekachi.me.techcabal;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Onyekachy on 02/01/2015.
 */
public class DatabaseOpenHelper extends SQLiteOpenHelper
{
    public DatabaseOpenHelper(Context context, String name, int version)
    {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(createTable(Article.getTableName(), Article.getColumns(), Article.getTypes()));
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private String createTable(String tableName, String[] columns, String[] types)
    {
        if (tableName == null || columns == null || types == null || types.length != columns.length || types.length == 0)
        {
            throw new IllegalArgumentException("Invalid parameters for creating table "+ tableName);
        } else {
            StringBuilder stringBuilder = new StringBuilder("CREATE TABLE ");

            stringBuilder.append(tableName);
            stringBuilder.append(" (");
            for (int n = 0, i = columns.length; n < i; n++) {
                if (n > 0) {
                    stringBuilder.append(", ");
                }
                stringBuilder.append(columns[n]).append(' ').append(types[n]);
            }
            return stringBuilder.append(");").toString();
        }
    }
}
