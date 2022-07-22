package com.example.skbnetwork;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class DemandItemStatusAtSite extends AppCompatActivity {


    String menuName, clientName, siteName, workType;
    TextView menuNamev, clientNamev, siteNamev, workTypev;

    MyAdopterForDemandItemStatusAtSite myAdopterForDemandItemStatusAtSite;
    RecyclerView recyclerView;
    Query query;
    String searchkey;




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
        searchkey = clientName.toString().trim() +"_"+siteName.toString().trim()+"_"+workType.toString().trim();
        recyclerView=findViewById(R.id.recyclerView06);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        query = FirebaseDatabase.getInstance().getReference().child("dModelClientSiteWorkTypeItemSubItemQuantityMaster")
                .orderByChild("dMCSWTISIQMSearchKey1").equalTo(searchkey);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                   // Toast.makeText(DemandItemStatusAtSite.this, "NO Record to Display", Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(DemandItemStatusAtSite.this, "NO Record to Display", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

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