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
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class PurchaseItemStatusAtSite extends AppCompatActivity {



    String menuName, clientName, siteName, workType;
    TextView menuNamev, clientNamev, siteNamev, workTypev;

    MyAdopterForPurchaseItemStatusAtSite myAdopterForPurchaseItemStatusAtSite;
    RecyclerView recyclerView;
    Query query;
    String searchkey;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_item_status_at_site);

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

        menuNamev = findViewById(R.id.textView109);
        clientNamev = findViewById(R.id.textView111);
        siteNamev = findViewById(R.id.textView112);
        workTypev = findViewById(R.id.textView113);

        menuNamev.setText("Purchase Menu");
        clientNamev.setText("Client   : "+ clientName);
        siteNamev.setText("Site      : " +siteName);
        workTypev.setText("Work Type : "+workType);
        searchkey = clientName.toString().trim() +"_"+siteName.toString().trim()+"_"+workType.toString().trim();
        recyclerView=findViewById(R.id.recyclerView07);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        query = FirebaseDatabase.getInstance().getReference().child("dModelClientSiteWorkTypeItemSubItemQuantityMaster")
                .orderByChild("dMCSWTISIQMSearchKey1").equalTo(searchkey);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    // Toast.makeText(PurchaseItemStatusAtSite.this, "NO Record to Display", Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(PurchaseItemStatusAtSite.this, "No Record to Display", Toast.LENGTH_SHORT).show();
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

        myAdopterForPurchaseItemStatusAtSite = new MyAdopterForPurchaseItemStatusAtSite(options, menuName);
        recyclerView.setAdapter(myAdopterForPurchaseItemStatusAtSite);


    }

    @Override
    protected void onStart() {
        super.onStart();
        myAdopterForPurchaseItemStatusAtSite.startListening();
    }


    @Override
    protected void onStop() {
        super.onStop();
        //myAdopterForPurchaseItemStatusAtSite.startListening();
    }

    //We need to decide and code for this search option

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.searchoption, menu);
//        MenuItem item = menu.findItem(R.id.app_bar_search);
//
//        SearchView searchView = (SearchView) item.getActionView();
//
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                processSearch(query.toUpperCase());
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                processSearch(newText.toUpperCase());
//                return false;
//            }
//        });
//
//        return super.onCreateOptionsMenu(menu);
//
//    }
//
//    private void processSearch(String searchText) {
//
//        query = FirebaseDatabase.getInstance().getReference().child("dModelClientSiteWorkTypeItemSubItemQuantityMaster")
//                .orderByChild("dMCSWTISIQMSearchKey1").startAt(searchText).endAt(searchText+"\uf8ff");
//
//        FirebaseRecyclerOptions<ModelClientSiteWorkTypeItemSubItemQuantityMaster> options =
//                new FirebaseRecyclerOptions.Builder<ModelClientSiteWorkTypeItemSubItemQuantityMaster>()
//                        .setQuery(query, ModelClientSiteWorkTypeItemSubItemQuantityMaster.class)
//                        .build();
//
//        myAdopterForPurchaseItemStatusAtSite = new MyAdopterForPurchaseItemStatusAtSite(options, menuName);
//        myAdopterForPurchaseItemStatusAtSite.startListening();
//        recyclerView.setAdapter(myAdopterForPurchaseItemStatusAtSite);
//
//    }
}