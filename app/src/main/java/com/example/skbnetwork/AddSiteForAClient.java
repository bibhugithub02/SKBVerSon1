package com.example.skbnetwork;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AddSiteForAClient extends AppCompatActivity {

    String clientName;
    String siteName;
    TextView clientNameText, addSiteforClient;

    MyAdopterForAddSiteForAClient myAdopterForAddSiteForAClient;
    RecyclerView recyclerView;
    Query query;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_site_for_aclient);

        //Set the orientation to Portrait for this screen
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        clientNameText = findViewById(R.id.textView21);
        addSiteforClient = findViewById(R.id.textView20);

        clientName = getIntent().getStringExtra("clientname").toString();
        clientNameText.setText(clientName);

        recyclerView = findViewById(R.id.recyclerView3);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        query = FirebaseDatabase.getInstance().getReference().child("dModelToAddSiteForAClient")
                .orderByChild("dMTASFClientName").equalTo(clientName);

        FirebaseRecyclerOptions<ModelAddSiteForAClient> options =
                new FirebaseRecyclerOptions.Builder<ModelAddSiteForAClient>()
                        .setQuery(query, ModelAddSiteForAClient.class)
                        .build();

        myAdopterForAddSiteForAClient = new MyAdopterForAddSiteForAClient(options);
        recyclerView.setAdapter(myAdopterForAddSiteForAClient);



        Toast.makeText(this, "Client Name is " + clientName, Toast.LENGTH_SHORT).show();

        addSiteforClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSiteNameFromUser();
            }
        });
    }

    private void getSiteNameFromUser() {
        EditText SiteDescription = new EditText(AddSiteForAClient.this);
        AlertDialog.Builder siteDescriptionDialog = new AlertDialog.Builder(AddSiteForAClient.this);
        siteDescriptionDialog.setTitle("Client Site Dialog"); // Set the title of the Popup
        siteDescriptionDialog.setMessage("Enter Site for Client"); // Set the message to be displayed to the user on the Popup
        siteDescriptionDialog.setView(SiteDescription);

        siteDescriptionDialog.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
               if(SiteDescription.getText().toString().trim().isEmpty()){
                   Toast.makeText(AddSiteForAClient.this, "Site can't be blank", Toast.LENGTH_SHORT).show();
               }else{
                   siteName = SiteDescription.getText().toString().trim();
                   addToSiteToClientMasterDataBase();
               }

            }
        });

        siteDescriptionDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Close the dialog
            }
        });

        siteDescriptionDialog.create().show();
        
    }

    private void addToSiteToClientMasterDataBase() {

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference dbr = firebaseDatabase.getReference("dModelToAddSiteForAClient");

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

        String dMTASFClientName = clientName.toUpperCase();
        String dMTASFSiteName = siteName.toUpperCase();
        String dMTASFWorkType = " ";
        String dMTASFProjectType = " ";
        String dMTASFTimeStamp = timeStamp ;
        String uniquekey = clientName.toUpperCase().toString()+ "_"+siteName.toUpperCase().toString();
        ModelAddSiteForAClient obj = new ModelAddSiteForAClient(dMTASFClientName,dMTASFSiteName,
                dMTASFWorkType, dMTASFProjectType, dMTASFTimeStamp);

        dbr.child(uniquekey).setValue(obj);

        Toast.makeText(AddSiteForAClient.this, "New Client Added Successfully", Toast.LENGTH_SHORT).show();

    }


    // On Start for recyclerview for Sub category
    @Override
    protected void onStart() {
        super.onStart();
        myAdopterForAddSiteForAClient.startListening();
    }

    // On Start for recyclerview for Sub category
    @Override
    protected void onStop() {
        super.onStop();
        // myAdopterForAddSiteForAClient.stopListening();
    }


}