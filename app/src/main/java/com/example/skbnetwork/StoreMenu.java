package com.example.skbnetwork;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class StoreMenu extends AppCompatActivity {

    TextView tv1,tv2,tv3,tv4,tv5,quit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_menu);

        //Set the orientation to Portrait for this screen
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        tv1 = findViewById(R.id.textView79);
        tv2 = findViewById(R.id.textView80);
        tv3 = findViewById(R.id.textView81);
        tv4 = findViewById(R.id.textView82);
        tv5 = findViewById(R.id.textView83);
        quit = findViewById(R.id.textView84);

        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity();
                System.exit(0);
            }
        });


    }
}