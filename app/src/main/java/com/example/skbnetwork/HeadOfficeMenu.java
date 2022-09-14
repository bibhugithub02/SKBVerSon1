package com.example.skbnetwork;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class HeadOfficeMenu extends AppCompatActivity {

    TextView AddItemToItemMaster, ListOfItemScreen, PendingForApproval, Quit;
    TextView clientConfiguration, purchaseMenu;
    String action;
    ImageView AddItemToItemMasterI, ListOfItemScreenI, PendingForApprovalI, clientConfigurationI, purchaseMenuI,QuitI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_head_office_menu);
        getSupportActionBar().setTitle(Html.fromHtml("<font color=\"white\">" + getString(R.string.app_name_HO) + "</font>"));


        //Set the orientation to Portrait for this screen
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        AddItemToItemMaster = findViewById(R.id.textView1);
        ListOfItemScreen = findViewById(R.id.textView2);
        clientConfiguration = findViewById(R.id.textView3);
        PendingForApproval = findViewById(R.id.textView4);
        purchaseMenu = findViewById(R.id.textView5);
        Quit = findViewById(R.id.textView6);

        AddItemToItemMasterI = findViewById(R.id.imageView23);
        ListOfItemScreenI = findViewById(R.id.imageView24);
        clientConfigurationI = findViewById(R.id.imageView25);
        PendingForApprovalI = findViewById(R.id.imageView26);
        purchaseMenuI = findViewById(R.id.imageView27);
        QuitI = findViewById(R.id.imageView16);

        AddItemToItemMaster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HeadOfficeMenu.this, AddItemToItemMasterView.class);
                startActivity(i);
            }
        });

        AddItemToItemMasterI.setOnClickListener(new View.OnClickListener() {
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

        ListOfItemScreenI.setOnClickListener(new View.OnClickListener() {
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
                startActivity(i);
            }
        });


        clientConfigurationI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HeadOfficeMenu.this, AddNewClientToMaster.class);
                startActivity(i);
            }
        });


        PendingForApproval.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HeadOfficeMenu.this, DemandPendingForHOApproval.class);
                startActivity(i);
            }
        });

        PendingForApprovalI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HeadOfficeMenu.this, DemandPendingForHOApproval.class);
                startActivity(i);
            }
        });


        purchaseMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HeadOfficeMenu.this, PurchaseMenu.class);
                startActivity(i);

            }
        });

        purchaseMenuI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HeadOfficeMenu.this, PurchaseMenu.class);
                startActivity(i);

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

        QuitI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // finish();
                finishAffinity();
                System.exit(0);
            }
        });
    }
}