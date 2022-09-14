package com.example.skbnetwork;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class DemandPendingForHOApproval extends AppCompatActivity {

    MyAdopterForDemandPendingForHOApproval myAdopterForDemandPendingForHOApproval;
    RecyclerView recyclerView;
    Query query;
    ArrayList<ModelClientSiteWorkTypeItemSubItemQuantityMaster> alist = new ArrayList<ModelClientSiteWorkTypeItemSubItemQuantityMaster>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demand_pending_for_hoapproval);

        getSupportActionBar().setTitle(Html.fromHtml("<font color=\"white\">"
                + getString(R.string.app_name_DemandApprovals) + "</font>"));



        //Set the orientation to Portrait for this screen
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        recyclerView=findViewById(R.id.recyclerView07);
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        query = FirebaseDatabase.getInstance().getReference().child("dModelClientSiteWorkTypeItemSubItemQuantityMaster")
                .orderByChild("currentDemand").startAt(1);


        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                alist.clear();
                if(snapshot.getValue() != null){
                    for(DataSnapshot ds : snapshot.getChildren()){

                        if(ds.child("currentDemand").getValue(Integer.class)>0)
                        {
                            alist.add(ds.getValue(ModelClientSiteWorkTypeItemSubItemQuantityMaster.class));
                        }else{
                            Toast.makeText(DemandPendingForHOApproval.this, "Current Demand Quantity is : " + ds.child("currentDemand").getValue(Integer.class), Toast.LENGTH_SHORT).show();
                        }

                    }myAdopterForDemandPendingForHOApproval.notifyDataSetChanged();
                }else{
                    Toast.makeText(DemandPendingForHOApproval.this, "Nothing pending for Approval for ", Toast.LENGTH_SHORT).show();
                }

            };

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        myAdopterForDemandPendingForHOApproval = new MyAdopterForDemandPendingForHOApproval(alist);
        recyclerView.setAdapter(myAdopterForDemandPendingForHOApproval);

    }
    @Override
    protected void onStart() {
        super.onStart();
       // myAdopterForDemandPendingForHOApproval.startListening();
    }


    @Override
    protected void onStop() {
        super.onStop();
        //myAdopterForDemandPendingForHOApproval.stopListening();
    }

    //Commenting search options as I am not able to get the Name Site name along with qty>0

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.searchoption, menu);
        MenuItem item = menu.findItem(R.id.app_bar_search);

        SearchView searchView = (SearchView) item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                processSearch(query.toUpperCase());
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                processSearch(newText.toUpperCase());
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);

    }

    private void processSearch(String searchText) {
        alist.clear();
        query = FirebaseDatabase.getInstance().getReference().child("dModelClientSiteWorkTypeItemSubItemQuantityMaster")
                .orderByChild("dMCSWTISIQMSearchKey2").startAt(searchText).endAt(searchText+"\uf8ff");

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.getValue() != null){

                    for(DataSnapshot ds : snapshot.getChildren()){

                        if(ds.child("currentDemand").getValue(Integer.class)>0)
                        {
                           alist.add(ds.getValue(ModelClientSiteWorkTypeItemSubItemQuantityMaster.class));
                        }else{
                           // Toast.makeText(DemandPendingForHOApproval.this, "Current Demand Quantity is : " + ds.child("currentDemand").getValue(Integer.class), Toast.LENGTH_SHORT).show();
                        }

                    }
                }else{
                    Toast.makeText(DemandPendingForHOApproval.this, "Nothing pending for Approval for : " + searchText, Toast.LENGTH_SHORT).show();
                }

            };

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        myAdopterForDemandPendingForHOApproval = new MyAdopterForDemandPendingForHOApproval(alist);
        recyclerView.setAdapter(myAdopterForDemandPendingForHOApproval);

    }
}