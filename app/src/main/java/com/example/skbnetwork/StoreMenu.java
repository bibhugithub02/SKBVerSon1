package com.example.skbnetwork;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class StoreMenu extends AppCompatActivity {

    TextView tv1,quantityReceived,stokeInHand,dailyIssue,dailyReturn,quit;
    String menuOption;
    String menuName,clientSiteName;


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

        menuName = getIntent().getStringExtra("menu").toString();
        clientSiteName = getIntent().getStringExtra("clientsitename").toString();

        quantityReceived.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuOption = "RQ";  // RQ - Receive Quantity option
                Intent i = new Intent(StoreMenu.this, ClientSiteWorkTypeRecyclerViewToReceiveQuantity.class);
                i.putExtra("menu",menuName);
                i.putExtra("menuOption",menuOption);
                i.putExtra("clientsitename",clientSiteName);
                startActivity(i);
            }
        });

        stokeInHand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuOption = "SIH"; // SIH - Stoke iN hand Quantity Option
              //  menuName = "STM";
                Intent i = new Intent(StoreMenu.this, ClientSiteWorkTypeRecyclerViewToReceiveQuantity.class);
                i.putExtra("menu",menuName);
                i.putExtra("menuOption",menuOption);
                i.putExtra("clientsitename",clientSiteName);
                startActivity(i);

            }
        });

        dailyIssue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuOption = "DI";  // Daily issue Option
            //    menuName = "STM";
                Intent i = new Intent(StoreMenu.this, ClientSiteWorkTypeRecyclerViewToReceiveQuantity.class);
                i.putExtra("menu",menuName);
                i.putExtra("menuOption",menuOption);
                i.putExtra("clientsitename",clientSiteName);
                startActivity(i);

            }
        });

        dailyReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuOption = "DR"; // Daily return Option
            //    menuName = "STM";
                Intent i = new Intent(StoreMenu.this, ClientSiteWorkTypeRecyclerViewToReceiveQuantity.class);
                i.putExtra("menu",menuName);
                i.putExtra("menuOption",menuOption);
                i.putExtra("clientsitename",clientSiteName);
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