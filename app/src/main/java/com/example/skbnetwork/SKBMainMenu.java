package com.example.skbnetwork;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SKBMainMenu extends AppCompatActivity {

    TextView CongigurationOption, HeadOfficeMenu, SiteOfficeMenu, StoreMenu, StokeInHand, Quit;
    String menuName, clientSiteName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skbmain_menu);

        CongigurationOption = findViewById(R.id.textView1);
        HeadOfficeMenu = findViewById(R.id.textView2);
        SiteOfficeMenu = findViewById(R.id.textView3);
        StoreMenu = findViewById(R.id.textView4);
        StokeInHand = findViewById(R.id.textView5);
        Quit = findViewById(R.id.textView6);

        //Set the orientation to Portrait for this screen
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        CongigurationOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              Intent i = new Intent(SKBMainMenu.this, ConfigurationMenu.class);  // Login screen for OTP
              startActivity(i); //NotDeliveredOn1stAug2022

            }
        });

        HeadOfficeMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(SKBMainMenu.this, com.example.skbnetwork.HeadOfficeMenu.class);
                startActivity(i);

            }
        });

        SiteOfficeMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuName="MAM";
                clientSiteName="";
                Intent i = new Intent(SKBMainMenu.this, com.example.skbnetwork.SiteOfficeMenu.class);
                i.putExtra("menu",menuName);
                i.putExtra("clientsitename",clientSiteName);
                startActivity(i);
            }
        });

        StoreMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuName="MAM";
                clientSiteName="";
                Intent i = new Intent(SKBMainMenu.this, StoreMenu.class);
                i.putExtra("menu",menuName);
                i.putExtra("clientsitename",clientSiteName);
                startActivity(i); //otDeliveredOn1stAug2022
            }
        });

        StokeInHand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String action = "SIH";
                menuName = "MAM";
                clientSiteName="";
                Intent i = new Intent(SKBMainMenu.this, ClientSiteWorkTypeRecyclerViewToReceiveQuantity.class);
                i.putExtra("menu",menuName);
                i.putExtra("menuOption",action);
                i.putExtra("clientsitename",clientSiteName);
                startActivity(i);

            }
        });


        Quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity();
                System.exit(0);
            }
        });

    }
}