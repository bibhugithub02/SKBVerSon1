package com.example.skbnetwork;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SiteOfficeMenu extends AppCompatActivity {

    TextView siteEmployeeMaster, getSiteEmployeeDetails, demand_StokeInHand,
            raiseAdditionalDemand, pendingPurchase, purchaseRequest,billableItems, quit;
    String menuName, clientSiteName;
    String menuOption;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_siteoffice_menu);

        //Set the orientation to Portrait for this screen
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        siteEmployeeMaster = findViewById(R.id.textView73);
        getSiteEmployeeDetails = findViewById(R.id.textView74);
        demand_StokeInHand = findViewById(R.id.textView75);
        raiseAdditionalDemand = findViewById(R.id.textView76);
        pendingPurchase = findViewById(R.id.textView134);
        purchaseRequest = findViewById(R.id.textView135);
        billableItems = findViewById(R.id.textView77);
        quit = findViewById(R.id.textView78);

        menuName = getIntent().getStringExtra("menu").toString();
        clientSiteName = getIntent().getStringExtra("clientsitename").toString();


        demand_StokeInHand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SiteOfficeMenu.this, ClientSiteWorkTypeRecyclerView.class);
                i.putExtra("menu",menuName); // Send what we have received from calling pgm(MAM for main menu & SIM for OTM received options
                i.putExtra("clientsitename",clientSiteName);//need to populate this for particular client site
                startActivity(i);

            }
        });

        // Purchase in Hand for particular Site - Work in Progress

        pendingPurchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuOption = "LPFS"; // List of purchase pending for a site

                Intent i = new Intent(SiteOfficeMenu.this, ClientSiteWorkTypeRecyclerViewToReceiveQuantity.class);
                i.putExtra("menu",menuName); //// Send what we have received from calling pgm(MAM for main menu & SIM for OTM received options
                i.putExtra("menuOption",menuOption);
                i.putExtra("clientsitename",clientSiteName);
                startActivity(i);


            }
        });

        // Purchase request for particular Site - Work in Progress

        purchaseRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuOption = "RPRS"; // Raise purchase request for a site
                Intent i = new Intent(SiteOfficeMenu.this, ClientSiteWorkTypeRecyclerViewToReceiveQuantity.class);
                i.putExtra("menu",menuName); //// Send what we have received from calling pgm(MAM for main menu & SIM for OTM received options
                i.putExtra("menuOption",menuOption);
                i.putExtra("clientsitename",clientSiteName);
                startActivity(i);


            }
        });



        // Add additional demand quantity

        raiseAdditionalDemand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuOption = "AQ";
                Intent i = new Intent(SiteOfficeMenu.this, ClientSiteWorkTypeRecyclerViewToReceiveQuantity.class);
                i.putExtra("menu",menuName); // Send what we have received from calling pgm(MAM for main menu & SIM for OTM received options
                i.putExtra("menuOption",menuOption);
                i.putExtra("clientsitename",clientSiteName);//need to populate this for particular client site
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