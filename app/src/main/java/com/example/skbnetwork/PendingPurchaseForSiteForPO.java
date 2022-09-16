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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PendingPurchaseForSiteForPO extends AppCompatActivity {

    String menuName, clientName, siteName, workType;
    TextView menuNamev, clientNamev, siteNamev, workTypev;

    Query query;
    String searchkey;

    MyAdopterForPendingPurchaseForSiteForPO myAdopterForPendingPurchaseForSiteForPO;
    RecyclerView recyclerView;

    // ArrayList<ModelPurchaseRequest> array = new ArrayList<ModelPurchaseRequest>();
    ArrayList<ModelClientSiteWorkTypeItemSubItemQuantityMaster> array = new ArrayList<ModelClientSiteWorkTypeItemSubItemQuantityMaster>();
    ArrayList<String> array1 = new ArrayList<String>();
    String str;
    int pendingPurchaseQuantity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending_purchase_for_site_for_po);

        //Set the orientation to Portrait for this screen
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        getSupportActionBar().setTitle(Html.fromHtml("<font color=\"white\">"
                + "Purchase Pending For" + "</font>"));

        recyclerView=findViewById(R.id.recyclerView11);
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Parameter used when called from Site Menu Option
        //Site menu then Choose the Site then Choose the Item Category and then Item Sub Category to add the Qty
        menuName = getIntent().getStringExtra("menuname").toString();
        clientName = getIntent().getStringExtra("clientname").toString();
        siteName = getIntent().getStringExtra("sitename").toString();
        workType = getIntent().getStringExtra("worktype").toString();


        clientNamev = findViewById(R.id.textView111);

        clientNamev.setText("Client   : "+ clientName);

        if(siteName.isEmpty()){
            searchkey = clientName.toString().trim();
        }else{
            searchkey = clientName.toString().trim() +"_"+siteName.toString().trim()+"_"+workType.toString().trim();
        }

        recyclerView=findViewById(R.id.recyclerView11);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        query = FirebaseDatabase.getInstance().getReference().child("dModelClientSiteWorkTypeItemSubItemQuantityMaster")
                .orderByChild("dMCSWTISIQMSearchKey1").equalTo(searchkey);

        query.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.getValue() != null){
                    for(DataSnapshot ds : snapshot.getChildren()){
                        str = (String) ds.child("dMCSWTISIQMSearchKey1").getValue() ;
                        pendingPurchaseQuantity = Integer.parseInt(ds.child("currentPurchased").getValue().toString());
                        if (pendingPurchaseQuantity > 0){
                            array.add(ds.getValue(ModelClientSiteWorkTypeItemSubItemQuantityMaster.class));
                        }myAdopterForPendingPurchaseForSiteForPO.notifyDataSetChanged();
                    }

                }else{
                    Toast.makeText(PendingPurchaseForSiteForPO.this, "Nothing pending for Approval for ", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        myAdopterForPendingPurchaseForSiteForPO = new MyAdopterForPendingPurchaseForSiteForPO(array);
        recyclerView.setAdapter(myAdopterForPendingPurchaseForSiteForPO);
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