package onyekachi.me.techcabal;

import android.graphics.Typeface;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;


public class HomeActivity extends ActionBarActivity {

    //app colors #8C919B #000000 #444444
    private RecyclerView mRecyclerView;
    private HomeCursorAdapter mCAdapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private LinearLayoutManager mLayoutManager;


    //empty state views
    private RelativeLayout mEmptyContainer;
    private ProgressView mProgressView;
    private TextView mRetryTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        mCAdapter = new HomeCursorAdapter(this);

        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        mRecyclerView.setAdapter(mCAdapter);
        mRecyclerView.setHasFixedSize(false);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                mSwipeRefreshLayout.setEnabled(mLayoutManager.findFirstCompletelyVisibleItemPosition() == 0);
            }
        });

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
            }
        });

        initializeEmptyViews();
        refresh();
    }

    public void initializeEmptyViews(){
        mProgressView = (ProgressView) findViewById(R.id.pvRefreshing);
        mEmptyContainer = (RelativeLayout)findViewById(R.id.empty);
        mRetryTextView = (TextView) findViewById(R.id.tvRetry);

        mRetryTextView.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/Oswald-Regular.otf"));
        mRetryTextView.setText("RETRY");
        mRetryTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refresh();
            }
        });
        setEmptyViews();
    }

    public void setEmptyViews(){
        if (mCAdapter.getItemCount() > 0){
            mRecyclerView.setVisibility(View.VISIBLE);

            mProgressView.setVisibility(View.GONE);
            mRetryTextView.setVisibility(View.GONE);
            mEmptyContainer.setVisibility(View.GONE);
        }else{
            if(mSwipeRefreshLayout.isRefreshing()){
                mEmptyContainer.setVisibility(View.VISIBLE);
                mProgressView.setVisibility(View.VISIBLE);
                mRetryTextView.setVisibility(View.GONE);
            }else{
                mEmptyContainer.setVisibility(View.VISIBLE);
                mProgressView.setVisibility(View.GONE);
                mRetryTextView.setVisibility(View.VISIBLE);
            }
        }
    }

    public void refresh(){
        mSwipeRefreshLayout.setRefreshing(true);
        RestAdapter adapter = new RestAdapter.Builder().setEndpoint("http://techcabalapp.appspot.com").build();
        TechcabalService api = adapter.create(TechcabalService.class);
        api.response(new Callback<Response>() {
            @Override
            public void success(Response response, retrofit.client.Response response2) {
                Article.saveAll(HomeActivity.this, response.getArticles());
                mSwipeRefreshLayout.setRefreshing(false);
                mCAdapter.changeCursor(Article.allAsCursor(HomeActivity.this));
                mCAdapter.notifyDataSetChanged();
                setEmptyViews();
            }

            @Override
            public void failure(RetrofitError retrofitError) {
                mSwipeRefreshLayout.setRefreshing(false);
                mCAdapter.notifyDataSetChanged();
                setEmptyViews();
            }
        });
    }
}
