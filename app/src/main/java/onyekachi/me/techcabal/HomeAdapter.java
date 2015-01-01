package onyekachi.me.techcabal;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder>{
    private String[] mDataset;
    private Context mContext;

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView mTitleTextView;
        public TextView mDateTextView;
        public TextView mAuthorTextView;

        public ViewHolder(View v, Context c){
            super(v);
            mTitleTextView =  (TextView)v.findViewById(R.id.article_title);
            mDateTextView = (TextView)v.findViewById(R.id.article_date);
            mAuthorTextView = (TextView)v.findViewById(R.id.article_author);

            Typeface oswald = Typeface.createFromAsset(c.getAssets(), "fonts/Oswald-Regular.otf");
            Typeface roboto_light = Typeface.createFromAsset(c.getAssets(), "fonts/Roboto-Light.ttf");

            mTitleTextView.setTypeface(oswald);
            mDateTextView.setTypeface(oswald);
            mAuthorTextView.setTypeface(roboto_light);
        }
    }

    public HomeAdapter(Context myContext, String[] myDataset) {
        mDataset = myDataset;
        mContext = myContext;
    }

    @Override
    public HomeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        ViewHolder vh = new ViewHolder(v, mContext);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTitleTextView.setText(mDataset[position]);
    }

    @Override
    public int getItemCount() {
        return mDataset.length;
    }
}
