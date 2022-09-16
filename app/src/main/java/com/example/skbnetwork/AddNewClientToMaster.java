package com.example.skbnetwork;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.Html;
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

public class AddNewClientToMaster extends AppCompatActivity {

    TextView addNewClient;
    String clientName;
    MyAdopterForAddNewClientToMaster myAdopterForAddNewClientToMaster;
    RecyclerView recyclerView;
    Query query;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_client_to_master);
        getSupportActionBar().setTitle(Html.fromHtml("<font color=\"white\">"
                + getString(R.string.app_name_ClientConf) + "</font>"));

        //Set the orientation to Portrait for this screen
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        addNewClient = findViewById(R.id.textView16);
        recyclerView = findViewById(R.id.recyclerView2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        query = FirebaseDatabase.getInstance().getReference().child("dModelClientMaster");

        FirebaseRecyclerOptions<ModelClientMaster> options =
                new FirebaseRecyclerOptions.Builder<ModelClientMaster>()
                        .setQuery(query, ModelClientMaster.class)
                        .build();

        myAdopterForAddNewClientToMaster = new MyAdopterForAddNewClientToMaster(options);
        recyclerView.setAdapter(myAdopterForAddNewClientToMaster);


    //What happen when you click on addnewclient button/textfield
        addNewClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText clientDescription = new EditText(AddNewClientToMaster.this);
                AlertDialog.Builder clientDescriptionDialog = new AlertDialog.Builder(AddNewClientToMaster.this);
                clientDescriptionDialog.setTitle("Client Dialog"); // Set the title of the Popup
                clientDescriptionDialog.setMessage("Enter Client Name"); // Set the message to be displayed to the user on the Popup
                clientDescriptionDialog.setView(clientDescription);

                clientDescriptionDialog.setPositiveButton(Html.fromHtml("<font color='#FF7F27'>Add</font>"), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(clientDescription.getText().toString().trim().isEmpty()){
                            Toast.makeText(AddNewClientToMaster.this, "Client Name cannot be blank", Toast.LENGTH_SHORT).show();

                        }else{
                        clientName = clientDescription.getText().toString().trim();
                        addToClientMasterDataBase();
                        }
                    }
                });

                clientDescriptionDialog.setNegativeButton(Html.fromHtml("<font color='#FF7F27'>Cancel</font>"), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Close the dialog
                    }
                });

                clientDescriptionDialog.create().show();


            }
        });

    }

    private void addToClientMasterDataBase() {

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference dbr = firebaseDatabase.getReference("dModelClientMaster");

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String dMCMClientName= clientName.toUpperCase();
        String dMCMCSiteName = "";
        String dMCMCProjectType = "";
        String dMCMCWorkType = "";
        String dMCMCDateStamp = timeStamp ;
        String dMCMCFiller01 ="";
        String dMCMCFiller02 ="";

        ModelClientMaster obj = new ModelClientMaster(dMCMClientName,dMCMCSiteName,
                dMCMCProjectType, dMCMCWorkType, dMCMCDateStamp, dMCMCFiller01,dMCMCFiller02);

        dbr.child(dMCMClientName).setValue(obj);

        //Write to monitoring DB ModelForMonitoring

        ModelForMonitoring m = new ModelForMonitoring();
        m.writeToDB(timeStamp,"AddNewClientToMaster",dMCMClientName,
                "ModelClientMaster","Add Record",
                "");



        Toast.makeText(AddNewClientToMaster.this, "New Client added successfully", Toast.LENGTH_SHORT).show();

    }


    // On Start for recyclerview for Sub category
    @Override
    protected void onStart() {
        super.onStart();
        myAdopterForAddNewClientToMaster.startListening();
    }

    // On Start for recyclerview for Sub category
    @Override
    protected void onStop() {
        super.onStop();
       // myAdopterForAddNewClientToMaster.stopListening();
    }


}