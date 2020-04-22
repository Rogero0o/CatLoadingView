package com.roger.catloadingview;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.roger.catloadinglibrary.CatLoadingView;

public class MainActivity extends AppCompatActivity {

    CatLoadingView mView;


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mView = new CatLoadingView();
        mView.setBackgroundColor(Color.parseColor("#000000"));
        findViewById(R.id.button).setOnClickListener(
                new View.OnClickListener() {
                    @Override public void onClick(View v) {
                        showDialog();
                    }
                });
    }

    public void showDialog() {
        mView.show(getSupportFragmentManager(), "");
    }

}
