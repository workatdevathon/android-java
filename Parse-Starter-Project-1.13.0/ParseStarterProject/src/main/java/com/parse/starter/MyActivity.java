package com.parse.starter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

public class MyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        String s=getIntent().getStringExtra("key");

        ParseQuery<ParseObject> query=ParseQuery.getQuery("hotels");
        query.whereEqualTo("city",s);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {

            }
        });
    }
}
