package onyekachi.me.techcabal;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;


public class HomeActivity extends ActionBarActivity {

    //colors #8C919B #000000 #444444

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private String[] dataSet = {"Lorem Ipsum Dolor Predictable",
                                "Money Makes The World Go Round",
                                "Odd Future In Town For Naija Rock Concert",
                                "Onyekachi Named Time Magazine Person Of The Year",
                                "Lolubee Charity House Holds Ultimate Homeless Party",
                                "Crazy People Around The World Keep Blowing Themselves Up",
                                "Lorem Ipsum Dolor Predictable",
                                "Money Makes The World Go Round",
                                "Odd Future In Town For Naija Rock Concert",
                                "Onyekachi Named Time Magazine Person Of The Year",
                                "Lolubee Charity House Holds Ultimate Homeless Party",
                                "Crazy People Around The World Keep Blowing Themselves Up"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerView);

        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new HomeAdapter(this, dataSet);
        mRecyclerView.setAdapter(mAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
