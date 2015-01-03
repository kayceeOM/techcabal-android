package onyekachi.me.techcabal;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.Random;


public class HomeCursorAdapter extends RecyclerCursorAdapter  {

    Context mContext;
    Cursor mCursor;

    public HomeCursorAdapter(Context context, Cursor cursor)
    {
        super(cursor);
        mContext = context;
        mCursor = cursor;
    }

    public HomeCursorAdapter(Context context)
    {
        super(Article.allAsCursor(context));
        mContext = context;
        mCursor = Article.allAsCursor(context);
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mTitleTextView;
        public TextView mDateTextView;
        public TextView mAuthorTextView;
        public ImageView mImageView;
        public RelativeLayout mRelativeLayout;

        public ViewHolder(View v, Context c) {
            super(v);

            mTitleTextView = (TextView) v.findViewById(R.id.article_title);
            mDateTextView = (TextView) v.findViewById(R.id.article_date);
            mAuthorTextView = (TextView) v.findViewById(R.id.article_author);
            mImageView = (ImageView) v.findViewById(R.id.article_image);
            mRelativeLayout = (RelativeLayout) v.findViewById(R.id.item_container);

            Typeface oswald = Typeface.createFromAsset(c.getAssets(), "fonts/Oswald-Regular.otf");
            Typeface roboto_light = Typeface.createFromAsset(c.getAssets(), "fonts/Roboto-Light.ttf");

            mTitleTextView.setTypeface(oswald);
            mDateTextView.setTypeface(oswald);
            mAuthorTextView.setTypeface(roboto_light);

            final Context context = c;
            mRelativeLayout.setBackgroundResource(R.drawable.clickable_statelist);
           mRelativeLayout.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   Toast.makeText(context, "clicked " + getPosition(), Toast.LENGTH_SHORT).show();
               }
           });
        }
    }

    @Override
    public void onBindViewHolderCursor(RecyclerView.ViewHolder holder, Cursor cursor)
    {
        ((ViewHolder)holder).mTitleTextView.setText(cursor.getString(cursor.getColumnIndex("TITLE")));
        ((ViewHolder)holder).mAuthorTextView.setText("BY " + cursor.getString(cursor.getColumnIndex("AUTHOR")));
        ((ViewHolder)holder).mDateTextView.setText(Article.getDateString(cursor.getLong(cursor.getColumnIndex("DATE"))));
        int[] arr = {R.drawable.four, R.drawable.one, R.drawable.two, R.drawable.three, R.drawable.five, R.drawable.six};
        Picasso.with(mContext).load(arr[new Random().nextInt(6)]).fit().centerCrop().into(((ViewHolder)holder).mImageView);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        ViewHolder vh = new ViewHolder(v, mContext);
        return vh;
    }
}
