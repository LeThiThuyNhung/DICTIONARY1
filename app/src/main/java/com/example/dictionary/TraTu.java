package com.example.dictionary;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class TraTu extends AppCompatActivity {

    TextView anhviet, trainghia, nghiacuaban, txttt;
    ViewPager viewPager;
    TTAdapter adapter;
    ImageView search;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tra_tu);
        viewPager = (ViewPager)findViewById(R.id.fragment_container);
        txttt = (TextView)findViewById(R.id.txtTT) ;
        search = findViewById(R.id.search_tt);
        adapter = new TTAdapter(getSupportFragmentManager());

        viewPager.setAdapter(adapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tbdemo);
        tabLayout.setupWithViewPager(viewPager);
        Intent intent = getIntent();
        String tu = intent.getStringExtra("Word");
        txttt.setText(tu);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TraTu.this, Search.class);
                startActivity(intent);
            }
        });
    }

}
