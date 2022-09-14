package com.example.skbnetwork;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class ItemMasterRecyclerView extends AppCompatActivity {

    RecyclerView recyclerView;
    MyAdopterForitemmasterrecyclerviewsinglerow myAdopterForitemmasterrecyclerviewsinglerow;
    String searchText;
    Query query;

    String menuName, clientName, siteName, workType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_master_recycler_view);
        getSupportActionBar().setTitle(Html.fromHtml("<font color=\"white\">" + getString(R.string.app_name_Item_List) + "</font>"));


        //Set the orientation to Portrait for this screen
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // Parameter used when called from Site Menu Option
        //Site menu then Choose the Site then Choose the Item Category and then Item Sub Category to add the Qty
        menuName = getIntent().getStringExtra("menuname").toString();
        clientName = getIntent().getStringExtra("clientname").toString();
        siteName = getIntent().getStringExtra("sitename").toString();
        workType = getIntent().getStringExtra("worktype").toString();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        query = FirebaseDatabase.getInstance().getReference().child("dModelItemMaster");

        FirebaseRecyclerOptions<ModelItemMaster> options =
                new FirebaseRecyclerOptions.Builder<ModelItemMaster>()
                        .setQuery(query, ModelItemMaster.class)
                        .build();

        myAdopterForitemmasterrecyclerviewsinglerow = new MyAdopterForitemmasterrecyclerviewsinglerow
  //              (options, menuName);
                  (options, menuName, clientName, siteName, workType);
        recyclerView.setAdapter(myAdopterForitemmasterrecyclerviewsinglerow);


    }

    @Override
    protected void onStart() {
        super.onStart();
        myAdopterForitemmasterrecyclerviewsinglerow.startListening();
    }


    @Override
    protected void onStop() {
        super.onStop();
        //myAdopterForitemmasterrecyclerviewsinglerow.stopListening();
    }

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

        query = FirebaseDatabase.getInstance().getReference().child("dModelItemMaster").orderByChild("dMIMItemName").
                startAt(searchText).endAt(searchText+"\uf8ff");
                //startAt(`%${query}%`)
                //startAt("%${searchText}%").endAt(searchText+"\uf8ff");
                //startAt("[a-zA-Z0-9]*" +searchText).endAt(searchText+"\uf8ff");


        FirebaseRecyclerOptions<ModelItemMaster> options =
                new FirebaseRecyclerOptions.Builder<ModelItemMaster>()
                        .setQuery(query, ModelItemMaster.class)
                        .build();

        myAdopterForitemmasterrecyclerviewsinglerow = new MyAdopterForitemmasterrecyclerviewsinglerow
              //  (options, menuName);
                (options, menuName, clientName, siteName, workType);
        myAdopterForitemmasterrecyclerviewsinglerow.startListening();
        recyclerView.setAdapter(myAdopterForitemmasterrecyclerviewsinglerow);

    }


}