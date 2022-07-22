package com.example.skbnetwork;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class DemandPendingForHOApproval extends AppCompatActivity {

    MyAdopterForDemandPendingForHOApproval myAdopterForDemandPendingForHOApproval;
    RecyclerView recyclerView;
    Query query;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demand_pending_for_hoapproval);

        //Set the orientation to Portrait for this screen
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        recyclerView=findViewById(R.id.recyclerView07);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        query = FirebaseDatabase.getInstance().getReference().child("dModelClientSiteWorkTypeItemSubItemQuantityMaster")
                .orderByChild("currentDemand").startAt(1);

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.getValue() != null){
                    Toast.makeText(DemandPendingForHOApproval.this, "Record available for approval", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(DemandPendingForHOApproval.this, "No Record Available for approval", Toast.LENGTH_SHORT).show();
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

        myAdopterForDemandPendingForHOApproval = new MyAdopterForDemandPendingForHOApproval(options);
        recyclerView.setAdapter(myAdopterForDemandPendingForHOApproval);

    }
    @Override
    protected void onStart() {
        super.onStart();
        myAdopterForDemandPendingForHOApproval.startListening();
    }


    @Override
    protected void onStop() {
        super.onStop();
        //myAdopterForDemandPendingForHOApproval.stopListening();
    }

}