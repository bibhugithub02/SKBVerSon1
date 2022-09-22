package com.example.skbnetwork;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class EntitlementDb extends AppCompatActivity {

    Spinner sp1, sp2, sp3;
    Button submit;
    EditText eName, ePhNumber;
    String cName, sName, mName;
    String ErrorFlag, str;
    String menuName;

    ArrayList<String> array1 = new ArrayList<String>();
    ArrayList<String> array2 = new ArrayList<String>();
    ArrayList<String> array3 = new ArrayList<String>();

    String ts1, ts2, ts3, ts4;
    int databaseflag = 0;
    int firstReadFlag = 0;
    String s,s1,s2,s3,s4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entitlement_db);

        //Set the orientation to Portrait for this screen
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        getSupportActionBar().setTitle(Html.fromHtml("<font color=\"white\">"
                + getString(R.string.app_name) + "</font>"));


        sp1 = findViewById(R.id.spinner);
        sp2 = findViewById(R.id.spinner1);
        sp3 = findViewById(R.id.spinner2);
        eName = findViewById(R.id.editTextTextPersonName);
        ePhNumber = findViewById(R.id.editTextNumber20);
        submit = findViewById(R.id.button2);

        FirebaseDatabase db1 = FirebaseDatabase.getInstance();
        DatabaseReference dbr1 = db1.getReference().child("dModelClientMaster");
        //array1.clear();
        array1.clear();
        array1.add("Client Name Required");
        Query query = db1.getReference().child("dModelClientMaster").orderByChild("key");
        query.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.getValue() != null){
                    for(DataSnapshot ds : snapshot.getChildren()){

                        str = (String) ds.child("dMCMClientName").getValue() ;

                        array1.add(str);
                    };
                }else{
                    Toast.makeText(EntitlementDb.this, "Nothing pending for Approval for ", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        buildArray2();

//        array1.add("Client Name Required");
//        array1.add("Proficiency : Batsman");
//        array1.add("Proficiency : Bowler");
//        array1.add("Proficiency : All-rounder");
//        array1.add("Proficiency : Wicket Keeper");
//        array1.add("Proficiency : Wicket Keeper- Batsman");

        //  ArrayList<String> array2 = new ArrayList<String>();
//            array2.add("Site Name Required");
//            array2.add("Batting Style : Right Hand Batsman");
//            array2.add("Batting Style : Left Hand Batsman");

        //    ArrayList<String> array3 = new ArrayList<String>();
        array3.add("Enter Role Type:");
        array3.add("Access to Head Office Menu");
        array3.add("Access to Site Office Menu");
        array3.add("Access to Store Menu");
        array3.add("Access to Master Menu");
        array3.add("No Access");


        Adapter adapter1 = new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, array1);
        sp1.setAdapter((SpinnerAdapter) adapter1);

        Adapter adapter2 = new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, array2);
        sp2.setAdapter((SpinnerAdapter) adapter2);

        Adapter adapter3 = new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, array3);
        sp3.setAdapter((SpinnerAdapter) adapter3);


        sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                if (firstReadFlag ==0) {
                    ts1 = parent.getItemAtPosition(position).toString();
                }
                databaseflag = 0;
                cName = parent.getItemAtPosition(position).toString();
                //sName = "";
                buildArray2();
//                    Toast.makeText(studentProfileView.this, "You selected : " + s1 , Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

                Toast.makeText(EntitlementDb.this, "Please select your option" , Toast.LENGTH_SHORT).show();
            }
        });

        sp2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                ts2 = parent.getItemAtPosition(position).toString();
                databaseflag = 0;
                sName = parent.getItemAtPosition(position).toString();

//                    Toast.makeText(studentProfileView.this, "You selected : " + s2 , Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(EntitlementDb.this, "Please select your option" , Toast.LENGTH_SHORT).show();
            }
        });
        sp3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                ts3 = parent.getItemAtPosition(position).toString();
                databaseflag = 0;
                mName = parent.getItemAtPosition(position).toString();
//                    Toast.makeText(studentProfileView.this, "You selected : " + s3 , Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(EntitlementDb.this, "Please select your option" , Toast.LENGTH_SHORT).show();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                verifyTheScreen();
                validateSpinner();
                if (ErrorFlag == "N") {
                    updateModelEntitlementDb();
                    eName.setText("");
                    ePhNumber.setText("");
                }

            }
        });


    }

    private void validateSpinner() {

            s1 = sp1.getSelectedItem().toString();
            s = array1.get(0);
            if (s1.equalsIgnoreCase(s.toString()) && (databaseflag != 1)) {
                databaseflag = 1;
                Toast.makeText(EntitlementDb.this, "Revisit " + s + " dropdown again:  ", Toast.LENGTH_SHORT).show();
            }

            s2 = sp2.getSelectedItem().toString();
            s = array2.get(0);
            if (s2.equalsIgnoreCase(s.toString()) && (databaseflag != 1)) {
                databaseflag = 1;
                Toast.makeText(EntitlementDb.this, "Revisit " + s + " dropdown again:  ", Toast.LENGTH_SHORT).show();
            }
            s3 = sp3.getSelectedItem().toString();
            s = array3.get(0);
            if (s3.equalsIgnoreCase(s.toString()) && (databaseflag != 1)) {
                databaseflag = 1;
                Toast.makeText(EntitlementDb.this, "Revisit " + s + " dropdown again:  ", Toast.LENGTH_SHORT).show();
            }

    }
    private void buildArray2() {

        FirebaseDatabase db2 = FirebaseDatabase.getInstance();
        DatabaseReference dbr2 = db2.getReference().child("dModelToAddSiteForAClient");
        //array1.clear();
        array2.clear();
        array2.add("Site Name Required");
        Query query2 = db2.getReference().child("dModelToAddSiteForAClient").orderByChild("key");
        query2.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.getValue() != null){
                    for(DataSnapshot ds : snapshot.getChildren()){
                        if (ds.child("dMTASFClientName").getValue().equals(cName)){

                            str = (String) ds.child("dMTASFClientName").getValue() +"_" +
                                    (String) ds.child("dMTASFSiteName").getValue() ;
                            array2.add(str);
                        };

                    };
                }else{
                    Toast.makeText(EntitlementDb.this, "Nothing pending for Approval for ", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


    private void verifyTheScreen() {

        ErrorFlag = "N";

        if (TextUtils.isEmpty(eName.getText().toString())){

            eName.setError("Name can never be Blank");
            ErrorFlag = "Y";
            eName.requestFocus();

        }else{
           // ErrorFlag = "N";

        };

        if (TextUtils.isEmpty(ePhNumber.getText().toString()) &&  ErrorFlag == "N") {

            ePhNumber.setError("Enter a valid Phone Number");
            ErrorFlag = "Y";
            ePhNumber.requestFocus();

        }else{
           // ErrorFlag = "N";

        };

        if (ePhNumber.getText().toString().length() < 10 &&  ErrorFlag == "N") {

            ePhNumber.setError("Enter a valid 10 digit Phone Number");
            ErrorFlag = "Y";
            ePhNumber.requestFocus();

        }else{
            // ErrorFlag = "N";

        };


    }

    private void updateModelEntitlementDb() {

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference dbr = db.getReference("dModelEntitlementDb");

        String name = eName.getText().toString().trim();
        String phNumber = "+91"+ePhNumber.getText().toString().trim();
        String clientName = cName.toString().trim();
        String siteNme = sName.toString().trim();
       // String menuName = mName.toString().trim();
        if (s3.equals("Access to Head Office Menu")){
            menuName = "HOM";
        }else if (s3.equals("Access to Site Office Menu")){
            menuName = "SIM";
        } else if (s3.equals("Access to Store Menu")){
            menuName = "STM";
        }else if (s3.equals("Access to Master Menu")){
            menuName = "MAM";
        }else{
            menuName = " ";
        }

        String dateStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String Filler01 = phNumber + clientName + siteNme;
        String Filler02="";
        String Filler03="";
        String Filler04="";

        ModelEntitlementDb obj = new ModelEntitlementDb(name,phNumber,clientName,siteNme,menuName, dateStamp, Filler01, Filler02, Filler03, Filler04);
        dbr.child(phNumber).setValue(obj);
       // dbr.child(phNumber + clientName + siteNme).setValue(obj);

        //Write to monitoring DB ModelForMonitoring

        ModelForMonitoring m = new ModelForMonitoring();
        m.writeToMonioringDB(dateStamp,"Action : Entry to Entitlement DB",name,
                "DataBase: ModelEntitlementDb","Add Record",
                Filler01);

    }


}
