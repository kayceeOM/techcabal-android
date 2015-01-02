package onyekachi.me.techcabal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Onyekachy on 31/12/2014.
 */
public class Article extends DBModel {

    String author, body, created_at, date, image_url, title, url;

    public Article(){ }

    public static String[] getColumns() {
        return new String[]{_ID, "AUTHOR", "BODY", "DATE", "IMAGE_URL", "TITLE", "URL"};
    }

    public static String[] getTypes() {
        return new String[] {TYPE_INTEGER_PRIMARY_KEY, TYPE_TEXT, TYPE_TEXT, TYPE_INTEGER, TYPE_TEXT, TYPE_TEXT, TYPE_UNIQUE_TEXT};
    }

    public static String getTableName() {
        return "ARTICLES";
    }



    //getters & setters
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getDateInMillis(String date){
        SimpleDateFormat df = new SimpleDateFormat("EEE, dd MMM yyyy kk:mm:ss Z", Locale.ENGLISH);
        Date pDate;
        Calendar time = Calendar.getInstance();
        long timeInMillis;
        try {
            pDate = df.parse(date);
            time.setTime(pDate);
            timeInMillis = time.getTimeInMillis();
        } catch (ParseException e) {
            time.setTime(new Date());
            timeInMillis = time.getTimeInMillis();
        }

        return timeInMillis;
    }


    public void save(Context c)
    {
        ContentValues cv = new ContentValues();
        cv.put("AUTHOR", author);
        cv.put("BODY", body);
        cv.put("DATE", getDateInMillis(date));
        cv.put("IMAGE_URL", image_url);
        cv.put("TITLE", title);
        cv.put("URL", url);

        open(c);
        try {
            database.insert(getTableName(), null, cv);
        }catch(Exception e){
           // e.printStackTrace();
        }
    }

    public static void saveAll(Context c, ArrayList<Article> articles)
    {
        open(c);
        for(int x = 0; x < articles.size(); x++ ){
            articles.get(x).save(c);
        }
    }

    //should be called on another thread
    public static ArrayList<Article> all(Context context)
    {
        ArrayList<Article> articles = new ArrayList<Article>();
        open(context);
        Cursor c =  database.query(true, getTableName(), getColumns(), null, null, null, null, "DATE DESC", null);

        c.moveToFirst();
        while(!c.isAfterLast()){
            Article a = new Article();
            a.setAuthor(c.getString(c.getColumnIndex("AUTHOR")));
            a.setBody(c.getString(c.getColumnIndex("BODY")));
            a.setDate(c.getString(c.getColumnIndex("DATE")));
            a.setImage_url(c.getString(c.getColumnIndex("IMAGE_URL")));
            a.setTitle(c.getString(c.getColumnIndex("TITLE")));
            a.setUrl(c.getString(c.getColumnIndex("URL")));

            articles.add(a);
        }

        return articles;
    }


    public static Cursor allAsCursor(Context context)
    {
        open(context);
        return  database.query(true, getTableName(), getColumns(), null, null, null, null, "DATE DESC", null);
    }

    public static String getDateString(long thenDateInMillis){

        Date thenDate = new Date(thenDateInMillis);

        SimpleDateFormat df = new SimpleDateFormat("MMMM dd, yyy", Locale.ENGLISH);
        String date = df.format(thenDate);

        return date;
    }

}
