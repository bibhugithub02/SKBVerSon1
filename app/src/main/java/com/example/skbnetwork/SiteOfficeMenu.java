package com.example.skbnetwork;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SiteOfficeMenu extends AppCompatActivity {

    TextView siteEmployeeMaster, getSiteEmployeeDetails, demand_StokeInHand, raiseAdditionalDemand, billableItems, quit;


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
        billableItems = findViewById(R.id.textView77);
        quit = findViewById(R.id.textView78);


        demand_StokeInHand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SiteOfficeMenu.this, ClientSiteWorkTypeRecyclerView.class);
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