package com.example.skbnetwork;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class ListOfItemforClientWiseSiteWiseWorkTypeWise extends AppCompatActivity {



    String menuName, clientName, siteName, workType;
    TextView menuNamev, clientNamev, siteNamev, workTypev;

    MyAdopterForListOfItemforClientWiseSiteWiseWorkTypeWise myAdopterForListOfItemforClientWiseSiteWiseWorkTypeWise;
    RecyclerView recyclerView;
    Query query;
    String searchkey;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_itemfor_client_wise_site_wise_work_type_wise);

        getSupportActionBar().setTitle(Html.fromHtml("<font color=\"white\">"
                + getString(R.string.app_name) + "</font>"));

        //Set the orientation to Portrait for this screen
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // Parameter used when called from Site Menu Option
        //Site menu then Choose the Site then Choose the Item Category and then Item Sub Category to add the Qty
        menuName = getIntent().getStringExtra("menuname").toString();
        clientName = getIntent().getStringExtra("clientname").toString();
        siteName = getIntent().getStringExtra("sitename").toString();
        workType = getIntent().getStringExtra("worktype").toString();

        menuNamev = findViewById(R.id.textView122);
        clientNamev = findViewById(R.id.textView123);
        siteNamev = findViewById(R.id.textView124);
        workTypev = findViewById(R.id.textView125);

        menuNamev.setText("Item List For");
        clientNamev.setText("Client   : "+ clientName);
        siteNamev.setText("Site      : " +siteName);
        workTypev.setText("Work Type : "+workType);
        searchkey = clientName.toString().trim() +"_"+siteName.toString().trim()+"_"+workType.toString().trim();
        recyclerView=findViewById(R.id.recyclerView08);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        query = FirebaseDatabase.getInstance().getReference().child("dModelClientSiteWorkTypeItemSubItemQuantityMaster")
                .orderByChild("dMCSWTISIQMSearchKey1").equalTo(searchkey);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    // Toast.makeText(PurchaseItemStatusAtSite.this, "NO Record to Display", Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(ListOfItemforClientWiseSiteWiseWorkTypeWise.this,
                            "No Record to Display", Toast.LENGTH_SHORT).show();
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

        myAdopterForListOfItemforClientWiseSiteWiseWorkTypeWise = new MyAdopterForListOfItemforClientWiseSiteWiseWorkTypeWise(options, menuName);
        recyclerView.setAdapter(myAdopterForListOfItemforClientWiseSiteWiseWorkTypeWise);


    }

    @Override
    protected void onStart() {
        super.onStart();
        myAdopterForListOfItemforClientWiseSiteWiseWorkTypeWise.startListening();
    }


    @Override
    protected void onStop() {
        super.onStop();
        //myAdopterForListOfItemforClientWiseSiteWiseWorkTypeWise.startListening();
    }

}
