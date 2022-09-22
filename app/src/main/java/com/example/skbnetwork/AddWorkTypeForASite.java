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

public class AddWorkTypeForASite extends AppCompatActivity {

    String clientName, siteName, workType;
    TextView addWorkType, textView25, textView27;

    MyAdopterForWorkTypeForASiteForAClient myAdopterForWorkTypeForASiteForAClient;
    RecyclerView recyclerView;
    Query query;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_work_type_for_asite);
        getSupportActionBar().setTitle(Html.fromHtml("<font color=\"white\">"
                + getString(R.string.app_name_ClientConf) + "</font>"));

        //Set the orientation to Portrait for this screen
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        clientName = getIntent().getStringExtra("clientname").toString();
        siteName = getIntent().getStringExtra("sitename").toString();


        textView25 = findViewById(R.id.textView25);
        textView27 = findViewById(R.id.textView27);
        addWorkType = findViewById(R.id.textView28);


        textView25.setText(clientName);
        textView27.setText(siteName);

        recyclerView = findViewById(R.id.recyclerView4);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        String querytext = clientName.toUpperCase()+"+"+siteName.toUpperCase();
        query = FirebaseDatabase.getInstance().getReference().child("dModelAddWorkTypeToWorkMaster")
                .orderByChild("dMAWTTWMCClient_SiteName").equalTo(querytext);

        FirebaseRecyclerOptions<ModelAddWorkTypeToWorkMaster> options =
                new FirebaseRecyclerOptions.Builder<ModelAddWorkTypeToWorkMaster>()
                        .setQuery(query, ModelAddWorkTypeToWorkMaster.class)
                        .build();

        myAdopterForWorkTypeForASiteForAClient = new MyAdopterForWorkTypeForASiteForAClient(options);
        recyclerView.setAdapter(myAdopterForWorkTypeForASiteForAClient);


        Toast.makeText(this, "Received:" + clientName + " " + siteName, Toast.LENGTH_SHORT).show();

        addWorkType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Add work type
                addNewWorkType();

            }
        });

    }

    private void addNewWorkType() {

        EditText workTypeDescription = new EditText(AddWorkTypeForASite.this);
        AlertDialog.Builder workTypeDescriptionDialog = new AlertDialog.Builder(AddWorkTypeForASite.this);
        workTypeDescriptionDialog.setTitle("Work Type Dialog"); // Set the title of the Popup
        workTypeDescriptionDialog.setMessage("Enter work type for the Site "); // Set the message to be displayed to the user on the Popup
        workTypeDescriptionDialog.setView(workTypeDescription);

        workTypeDescriptionDialog.setPositiveButton(Html.fromHtml("<font color='#FF7F27'>Add</font>"), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(workTypeDescription.getText().toString().trim().isEmpty()){
                    Toast.makeText(AddWorkTypeForASite.this, "Work Type Can't be Blank", Toast.LENGTH_SHORT).show();
                }else{
                    workType = workTypeDescription.getText().toString().trim();
                    addWorkTypeToWorkMasterDataBase();
                }

            }
        });

        workTypeDescriptionDialog.setNegativeButton(Html.fromHtml("<font color='#FF7F27'>Cancel</font>"), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Close the dialog
            }
        });

        workTypeDescriptionDialog.create().show();

    }

    private void addWorkTypeToWorkMasterDataBase() {

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference dbr = firebaseDatabase.getReference("dModelAddWorkTypeToWorkMaster");

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

        String dMAWTTWMClientName = clientName.toUpperCase();
        String dMAWTTWMCSiteName = siteName.toUpperCase();
        String dMAWTTWMCWorkTypeName = workType.toUpperCase();
        String dMAWTTWMCDateStamp = timeStamp;
        String dMAWTTWMCClient_SiteName =clientName.toUpperCase()+"+"+siteName.toUpperCase() ;
        String dMAWTTWMCClient_Site_WorkType =clientName.toUpperCase()+ "+" +siteName.toUpperCase()
                                                + "+" +workType.toUpperCase();

        ModelAddWorkTypeToWorkMaster obj = new ModelAddWorkTypeToWorkMaster(dMAWTTWMClientName,dMAWTTWMCSiteName,
                dMAWTTWMCWorkTypeName, dMAWTTWMCDateStamp, dMAWTTWMCClient_SiteName, dMAWTTWMCClient_Site_WorkType);

        dbr.child(dMAWTTWMCClient_Site_WorkType).setValue(obj);

        //Write to monitoring DB ModelForMonitoring

        ModelForMonitoring m = new ModelForMonitoring();
        m.writeToMonioringDB(timeStamp,"Action : AddWorkTypeForASite",dMAWTTWMCClient_SiteName,
                "DataBase : ModelAddWorkTypeToWorkMaster","Add Record",
                dMAWTTWMCClient_Site_WorkType);


        Toast.makeText(AddWorkTypeForASite.this, "New Client Added Successfully", Toast.LENGTH_SHORT).show();


    }

    // On Start for recyclerview for work Type
    @Override
    protected void onStart() {
        super.onStart();
        myAdopterForWorkTypeForASiteForAClient.startListening();
    }

    // On Start for recyclerview for work Type
    @Override
    protected void onStop() {
        super.onStop();
        myAdopterForWorkTypeForASiteForAClient.stopListening();
    }



}