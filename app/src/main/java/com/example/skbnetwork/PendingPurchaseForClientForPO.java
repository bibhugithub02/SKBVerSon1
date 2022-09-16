package com.example.skbnetwork;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.Html;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PendingPurchaseForClientForPO extends AppCompatActivity {

    MyAdopterForPendingPurchaseForClientForPO myAdopterForPendingPurchaseForClientForPO;
    RecyclerView recyclerView;

   // ArrayList<ModelPurchaseRequest> array = new ArrayList<ModelPurchaseRequest>();
    ArrayList<ModelClientSiteWorkTypeItemSubItemQuantityMaster> array = new ArrayList<ModelClientSiteWorkTypeItemSubItemQuantityMaster>();
    ArrayList<String> array1 = new ArrayList<String>();
    String str;
    int pendingPurchaseQuantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending_purchase_for_client_for_po);

        //Set the orientation to Portrait for this screen
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        getSupportActionBar().setTitle(Html.fromHtml("<font color=\"white\">"
                + "Purchase Pending For" + "</font>"));


        recyclerView=findViewById(R.id.recyclerView10);
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        FirebaseDatabase db1 = FirebaseDatabase.getInstance();
        array.clear();
        array1.clear();
      //  Query query = db1.getReference().child("dModelPurchaseRequest").orderByChild("key");
          Query query = db1.getReference().child("dModelClientSiteWorkTypeItemSubItemQuantityMaster").orderByChild("key");
        query.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.getValue() != null){
                    for(DataSnapshot ds : snapshot.getChildren()){

                        str = (String) ds.child("dMCSWTISIQMSearchKey1").getValue() ;
                        pendingPurchaseQuantity = Integer.parseInt(ds.child("currentPurchased").getValue().toString());
                        if (pendingPurchaseQuantity > 0){
                            if (array1.contains(str)) {
                            }else{
                                array1.add(str);
                                // array.add(ds.getValue(ModelPurchaseRequest.class));}
                                array.add(ds.getValue(ModelClientSiteWorkTypeItemSubItemQuantityMaster.class));}
                            }myAdopterForPendingPurchaseForClientForPO.notifyDataSetChanged();
                        }

                }else{
                    Toast.makeText(PendingPurchaseForClientForPO.this, "Nothing pending for Approval for ", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        myAdopterForPendingPurchaseForClientForPO = new MyAdopterForPendingPurchaseForClientForPO(array);
        recyclerView.setAdapter(myAdopterForPendingPurchaseForClientForPO);
    }
    @Override
    protected void onStart() {
        super.onStart();
      //  myAdopterForPurchasePendingWaitingForPO.startListening();
    }


    @Override
    protected void onStop() {
        super.onStop();
      //  myAdopterForPurchasePendingWaitingForPO.stopListening();
    }

}