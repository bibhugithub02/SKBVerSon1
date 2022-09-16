package com.example.skbnetwork;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.SearchView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class ClientSiteWorkTypeRecyclerView extends AppCompatActivity {

    MyAdopterForClientSiteWorkTypeRecyclerView myAdopterForClientSiteWorkTypeRecyclerView;
    RecyclerView recyclerView;
    Query query;
    String menuName,clientSiteName;
    String searchClientSite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_site_work_type_recycler_view);

        getSupportActionBar().setTitle(Html.fromHtml("<font color=\"white\">"
                + getString(R.string.app_name) + "</font>"));

        menuName = getIntent().getStringExtra("menu").toString();
        clientSiteName = getIntent().getStringExtra("clientsitename").toString();

        //Set the orientation to Portrait for this screen
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        recyclerView=findViewById(R.id.recyclerView5);
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

        myAdopterForClientSiteWorkTypeRecyclerView = new MyAdopterForClientSiteWorkTypeRecyclerView(options);
        recyclerView.setAdapter(myAdopterForClientSiteWorkTypeRecyclerView);

    }


    @Override
    protected void onStart() {
        super.onStart();
        myAdopterForClientSiteWorkTypeRecyclerView.startListening();
    }


    @Override
    protected void onStop() {
        super.onStop();
        //myAdopterForClientSiteWorkTypeRecyclerView.stopListening();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.searchoption, menu);
        MenuItem item = menu.findItem(R.id.app_bar_search);

        SearchView searchView = (SearchView) item.getActionView();
        searchView.setQueryHint("Type Your Search here");

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

        myAdopterForClientSiteWorkTypeRecyclerView = new MyAdopterForClientSiteWorkTypeRecyclerView(options);
        myAdopterForClientSiteWorkTypeRecyclerView.startListening();
        recyclerView.setAdapter(myAdopterForClientSiteWorkTypeRecyclerView);

    }
}