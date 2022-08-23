package com.example.skbnetwork;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class ClientSiteWorkTypeRecyclerViewToReceiveQuantity extends AppCompatActivity {


    MyAdopterForClientSiteWorkTypeRecyclerViewToReceiveQuantity myAdopterForClientSiteWorkTypeRecyclerViewToReceiveQuantity;
    RecyclerView recyclerView;
    Query query;
    String menuName,menuOption, clientSiteName;
    String searchClientSite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_site_work_type_recycler_view_to_receive_quantity);

        //Set the orientation to Portrait for this screen
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        recyclerView=findViewById(R.id.recyclerView5A);
        menuName = getIntent().getStringExtra("menu").toString();
        menuOption = getIntent().getStringExtra("menuOption").toString();
        clientSiteName = getIntent().getStringExtra("clientsitename").toString();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        if(menuName.equals("MAM")){
            query = FirebaseDatabase.getInstance().getReference().child("dModelAddWorkTypeToWorkMaster");
        }else{
            searchClientSite = clientSiteName;
            query = FirebaseDatabase.getInstance().getReference().child("dModelAddWorkTypeToWorkMaster")
                    .orderByChild("dMAWTTWMCClient_Site_WorkType").startAt(searchClientSite).endAt(searchClientSite+"\uf8ff");
        }


        FirebaseRecyclerOptions<ModelAddWorkTypeToWorkMaster> options =
                new FirebaseRecyclerOptions.Builder<ModelAddWorkTypeToWorkMaster>()
                        .setQuery(query, ModelAddWorkTypeToWorkMaster.class)
                        .build();

        myAdopterForClientSiteWorkTypeRecyclerViewToReceiveQuantity =
                new MyAdopterForClientSiteWorkTypeRecyclerViewToReceiveQuantity(options, menuOption);
        recyclerView.setAdapter(myAdopterForClientSiteWorkTypeRecyclerViewToReceiveQuantity);

    }


    @Override
    protected void onStart() {
        super.onStart();
        myAdopterForClientSiteWorkTypeRecyclerViewToReceiveQuantity.startListening();
    }


    @Override
    protected void onStop() {
        super.onStop();
        //myAdopterForClientSiteWorkTypeRecyclerViewToReceiveQuantity.stopListening();
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

        query = FirebaseDatabase.getInstance().getReference().child("dModelAddWorkTypeToWorkMaster")
                .orderByChild("dMAWTTWMCClient_Site_WorkType").startAt(searchText).endAt(searchText+"\uf8ff");

        FirebaseRecyclerOptions<ModelAddWorkTypeToWorkMaster> options =
                new FirebaseRecyclerOptions.Builder<ModelAddWorkTypeToWorkMaster>()
                        .setQuery(query, ModelAddWorkTypeToWorkMaster.class)
                        .build();

        myAdopterForClientSiteWorkTypeRecyclerViewToReceiveQuantity =
                new MyAdopterForClientSiteWorkTypeRecyclerViewToReceiveQuantity(options, menuOption);
        myAdopterForClientSiteWorkTypeRecyclerViewToReceiveQuantity.startListening();
        recyclerView.setAdapter(myAdopterForClientSiteWorkTypeRecyclerViewToReceiveQuantity);

    }
}