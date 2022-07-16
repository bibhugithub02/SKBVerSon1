package com.example.skbnetwork;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class DemandItemStatusAtSite extends AppCompatActivity {


    String menuName, clientName, siteName, workType;
    TextView menuNamev, clientNamev, siteNamev, workTypev;

    MyAdopterForDemandItemStatusAtSite myAdopterForDemandItemStatusAtSite;
    RecyclerView recyclerView;
    Query query;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demand_item_status_at_site);


        //Set the orientation to Portrait for this screen
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // Parameter used when called from Site Menu Option
        //Site menu then Choose the Site then Choose the Item Category and then Item Sub Category to add the Qty
        menuName = getIntent().getStringExtra("menuname").toString();
        clientName = getIntent().getStringExtra("clientname").toString();
        siteName = getIntent().getStringExtra("sitename").toString();
        workType = getIntent().getStringExtra("worktype").toString();

        menuNamev = findViewById(R.id.textView61);
        clientNamev = findViewById(R.id.textView58);
        siteNamev = findViewById(R.id.textView59);
        workTypev = findViewById(R.id.textView60);

        menuNamev.setText("Site Menu");
        clientNamev.setText("Client   : "+ clientName);
        siteNamev.setText("Site      : " +siteName);
        workTypev.setText("Work Type : "+workType);

        recyclerView=findViewById(R.id.recyclerView06);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        query = FirebaseDatabase.getInstance().getReference().child("dModelClientSiteWorkTypeItemSubItemQuantityMaster");

        FirebaseRecyclerOptions<ModelClientSiteWorkTypeItemSubItemQuantityMaster> options =
                new FirebaseRecyclerOptions.Builder<ModelClientSiteWorkTypeItemSubItemQuantityMaster>()
                        .setQuery(query, ModelClientSiteWorkTypeItemSubItemQuantityMaster.class)
                        .build();

        myAdopterForDemandItemStatusAtSite = new MyAdopterForDemandItemStatusAtSite(options);
        recyclerView.setAdapter(myAdopterForDemandItemStatusAtSite);


    }

    @Override
    protected void onStart() {
        super.onStart();
        myAdopterForDemandItemStatusAtSite.startListening();
    }


    @Override
    protected void onStop() {
        super.onStop();
        //myAdopterForClientSiteWorkTypeRecyclerView.stopListening();
    }

}