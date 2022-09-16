package com.example.skbnetwork;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class ApplicationManitoringLog extends AppCompatActivity {

    Query query;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application_manitoring_log);

        final ModelClientSiteWorkTypeItemSubItemQuantityMaster[] s = {new ModelClientSiteWorkTypeItemSubItemQuantityMaster()};

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference dbr = firebaseDatabase.getReference("dModelAddWorkTypeToWorkMaster");

        query = FirebaseDatabase.getInstance().getReference().child("dModelAddWorkTypeToWorkMaster");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for(DataSnapshot ds : snapshot.getChildren()){
                        String str = ds.child("dMAWTTWMCClient_SiteName").getValue().toString();
                        s[0] =  ds.getValue(ModelClientSiteWorkTypeItemSubItemQuantityMaster.class);
                        Toast.makeText(ApplicationManitoringLog.this, "Full string is :" + s[0], Toast.LENGTH_SHORT).show();
                        Toast.makeText(ApplicationManitoringLog.this, "Full str is :" + str, Toast.LENGTH_SHORT).show();

                        break;
                    }

                }else{
                    Toast.makeText(ApplicationManitoringLog.this, "Nothing to write", Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}