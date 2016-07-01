package com.example.vitaly.test_gym.activities;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.vitaly.test_gym.R;
import com.example.vitaly.test_gym.adapters.ListOfPagesAdapter;


public class MainActivity extends AppCompatActivity {

    private static final String IS_LOG_SHARED_PREFERENCE_KEY = "is_login";
    private RecyclerView mRvListOfPages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startLoginActivity();
        initViews();
    }

    private void initViews() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mRvListOfPages = (RecyclerView) findViewById(R.id.rvListOfPages);
        mRvListOfPages.setLayoutManager(new LinearLayoutManager(this));
        mRvListOfPages.setAdapter(new ListOfPagesAdapter(this));

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        startLoginActivity();
    }

    private void startLoginActivity(){
        if (!(PreferenceManager.getDefaultSharedPreferences(getBaseContext()).getBoolean(IS_LOG_SHARED_PREFERENCE_KEY,false))){
            startActivity(new Intent(this,LoginActivity.class));
        }
    }
}
