package com.example.skbnetwork;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class HeadOfficeMenu extends AppCompatActivity {

    TextView AddItemToItemMaster, ListOfItemScreen, PendingForApproval, Quit;
    TextView clientConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_head_office_menu);

        //Set the orientation to Portrait for this screen
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        AddItemToItemMaster = findViewById(R.id.textView1);
        ListOfItemScreen = findViewById(R.id.textView2);
        clientConfiguration = findViewById(R.id.textView3);
        PendingForApproval = findViewById(R.id.textView4);
        Quit = findViewById(R.id.textView6);

        AddItemToItemMaster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HeadOfficeMenu.this, AddItemToItemMasterView.class);
                startActivity(i);
            }
        });

        ListOfItemScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String menuname = "headoffice";
                Intent i = new Intent(HeadOfficeMenu.this, ItemMasterRecyclerView.class);
                i.putExtra("menuname",menuname);
                i.putExtra("clientname","clientname");
                i.putExtra("sitename","sitename");
                i.putExtra("worktype","worktype");
                startActivity(i);
            }
        });

        clientConfiguration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HeadOfficeMenu.this, AddNewClientToMaster.class);
                startActivity(i); ///Demo11072022
            }
        });

        PendingForApproval.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HeadOfficeMenu.this, DemandPendingForHOApproval.class);
                startActivity(i); ///Demo11072022
            }
        });


        Quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // finish();
                finishAffinity();
                System.exit(0);
            }
        });
    }
}