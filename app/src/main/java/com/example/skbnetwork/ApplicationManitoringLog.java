package com.example.skbnetwork;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.Query.Direction;
import com.google.firebase.firestore.core.OrderBy;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ApplicationManitoringLog extends AppCompatActivity {

    MyAdopterForApplicationManitoringLog myAdopterForApplicationManitoringLog;
    Query query;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application_manitoring_log);

        //Set the orientation to Portrait for this screen
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        String dateStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());


        recyclerView=findViewById(R.id.monitoringRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        query = FirebaseDatabase.getInstance().getReference().child("dModelForMonitoring")
                .orderByChild("systemDate");

        FirebaseRecyclerOptions<ModelForMonitoring> options =
                new FirebaseRecyclerOptions.Builder<ModelForMonitoring>()
                        .setQuery(query, ModelForMonitoring.class)
                        .build();

        myAdopterForApplicationManitoringLog = new MyAdopterForApplicationManitoringLog(options);
        recyclerView.setAdapter(myAdopterForApplicationManitoringLog);

    }

    @Override
    protected void onStart() {
        super.onStart();
        myAdopterForApplicationManitoringLog.startListening();
    }


    @Override
    protected void onStop() {
        super.onStop();
        myAdopterForApplicationManitoringLog.stopListening();
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


        query = FirebaseDatabase.getInstance().getReference().child("dModelForMonitoring")
                .orderByChild("systemDate").startAt(searchText).endAt(searchText+"\uf8ff");;

        FirebaseRecyclerOptions<ModelForMonitoring> options =
                new FirebaseRecyclerOptions.Builder<ModelForMonitoring>()
                        .setQuery(query, ModelForMonitoring.class)
                        .build();

        myAdopterForApplicationManitoringLog = new MyAdopterForApplicationManitoringLog(options);

        myAdopterForApplicationManitoringLog.startListening();
        recyclerView.setAdapter(myAdopterForApplicationManitoringLog);

    }

}