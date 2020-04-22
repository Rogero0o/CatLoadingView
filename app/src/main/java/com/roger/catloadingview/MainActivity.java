package com.roger.catloadingview;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.roger.catloadinglibrary.CatLoadingView;

public class MainActivity extends AppCompatActivity {

    CatLoadingView mView;


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mView = new CatLoadingView();
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
