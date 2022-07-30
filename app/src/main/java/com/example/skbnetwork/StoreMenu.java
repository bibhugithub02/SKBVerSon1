package com.example.skbnetwork;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class StoreMenu extends AppCompatActivity {

    TextView tv1,quantityReceived,stokeInHand,dailyIssue,dailyReturn,quit;
    String action;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_menu);

        //Set the orientation to Portrait for this screen
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        tv1 = findViewById(R.id.textView79);
        quantityReceived = findViewById(R.id.textView80);
        stokeInHand = findViewById(R.id.textView81);
        dailyIssue = findViewById(R.id.textView82);
        dailyReturn = findViewById(R.id.textView83);
        quit = findViewById(R.id.textView84);


        quantityReceived.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                action = "RQ";
                Intent i = new Intent(StoreMenu.this, ClientSiteWorkTypeRecyclerViewToReceiveQuantity.class);
                i.putExtra("menuname",action);
                startActivity(i);
            }
        });

        stokeInHand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                action = "SIH";
                Intent i = new Intent(StoreMenu.this, ClientSiteWorkTypeRecyclerViewToReceiveQuantity.class);
                i.putExtra("menuname",action);
                startActivity(i);

            }
        });

        dailyIssue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                action = "DI";
                Intent i = new Intent(StoreMenu.this, ClientSiteWorkTypeRecyclerViewToReceiveQuantity.class);
                i.putExtra("menuname",action);
                startActivity(i);

            }
        });

        dailyReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                action = "DR";
                Intent i = new Intent(StoreMenu.this, ClientSiteWorkTypeRecyclerViewToReceiveQuantity.class);
                i.putExtra("menuname",action);
                startActivity(i);

            }
        });




        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity();
                System.exit(0);
            }
        });


    }
}