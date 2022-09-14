package com.example.skbnetwork;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class PurchaseMenu extends AppCompatActivity {

    TextView purchaseStatus, pendingPurchases, purchaseInHand, tv4, tv5, quit;
    ImageView purchaseStatusI, pendingPurchasesI, purchaseInHandI, tv4I, tv5I, quitI;
    String action;
    String menuName, clientSiteName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_menu);
        getSupportActionBar().setTitle(Html.fromHtml("<font color=\"white\">" + getString(R.string.app_name_Pur) + "</font>"));


        //Set the orientation to Portrait for this screen
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        purchaseStatus = findViewById(R.id.textView115);
        pendingPurchases = findViewById(R.id.textView116);
        purchaseInHand = findViewById(R.id.textView117);
        tv4 = findViewById(R.id.textView118);
        tv5 = findViewById(R.id.textView119);
        quit = findViewById(R.id.textView120);

        purchaseStatusI = findViewById(R.id.imageView18);
        pendingPurchasesI = findViewById(R.id.imageView19);
        purchaseInHandI = findViewById(R.id.imageView20);
        tv4I = findViewById(R.id.imageView21);
        tv5I = findViewById(R.id.imageView22);
        quitI = findViewById(R.id.imageView16);


        purchaseStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                action = "PFS"; // PFS - Purchase for that particular Site
                menuName = "HOM";
                clientSiteName= "";
                Intent i = new Intent(PurchaseMenu.this, ClientSiteWorkTypeRecyclerViewToReceiveQuantity.class);
                i.putExtra("menu",menuName);
                i.putExtra("menuOption",action);
                i.putExtra("clientsitename",clientSiteName);
                startActivity(i);

            }
        });

        purchaseStatusI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                action = "PFS"; // PFS - Purchase for that particular Site
                menuName = "HOM";
                clientSiteName= "";
                Intent i = new Intent(PurchaseMenu.this, ClientSiteWorkTypeRecyclerViewToReceiveQuantity.class);
                i.putExtra("menu",menuName);
                i.putExtra("menuOption",action);
                i.putExtra("clientsitename",clientSiteName);
                startActivity(i);

            }
        });

        pendingPurchases.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                action = "PPFS"; // PFS - Purchase for that particular Site
                menuName = "HOM";
                clientSiteName="";
                Intent i = new Intent(PurchaseMenu.this, ClientSiteWorkTypeRecyclerViewToReceiveQuantity.class);
                i.putExtra("menu",menuName);
                i.putExtra("menuOption",action);
                i.putExtra("clientsitename",clientSiteName);
                startActivity(i);

            }

        });

        pendingPurchasesI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                action = "PPFS"; // PFS - Purchase for that particular Site
                menuName = "HOM";
                clientSiteName="";
                Intent i = new Intent(PurchaseMenu.this, ClientSiteWorkTypeRecyclerViewToReceiveQuantity.class);
                i.putExtra("menu",menuName);
                i.putExtra("menuOption",action);
                i.putExtra("clientsitename",clientSiteName);
                startActivity(i);

            }

        });


        purchaseInHand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                action = "LPFS"; // PFS - Purchase for that particular Site
                menuName="HOM";
                clientSiteName="";
                Intent i = new Intent(PurchaseMenu.this, ClientSiteWorkTypeRecyclerViewToReceiveQuantity.class);
                i.putExtra("menu",menuName);
                i.putExtra("menuOption",action);
                i.putExtra("clientsitename",clientSiteName);
                startActivity(i);

            }

        });

        purchaseInHandI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                action = "LPFS"; // PFS - Purchase for that particular Site
                menuName="HOM";
                clientSiteName="";
                Intent i = new Intent(PurchaseMenu.this, ClientSiteWorkTypeRecyclerViewToReceiveQuantity.class);
                i.putExtra("menu",menuName);
                i.putExtra("menuOption",action);
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
        quitI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity();
                System.exit(0);
            }
        });


    }
}