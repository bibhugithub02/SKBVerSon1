package com.example.skbnetwork;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class ApplicationTestingProgram extends AppCompatActivity {

    String str="";
    String phNumber1;
    String menuName;
    String clientSiteName;

   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application_testing_program);

        phNumber1 = "+919989184701";
        menuName = "MAM";
        Query q = FirebaseDatabase.getInstance().getReference().child("dModelEntitlementDb").
                orderByChild("phNumber").equalTo(phNumber1);

        q.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.getValue() != null){
                    for(DataSnapshot ds : snapshot.getChildren()){
                      //  if(phNumber1.equals("+919989184701")) {
                            str = ds.child("menuName").getValue().toString();
                            clientSiteName = ds.child("siteNme").getValue().toString();
                            clientSiteName = clientSiteName.replace("_","+");
                        if(str.equals("SIM")){
                            menuName = "SIM";
                            Intent i = new Intent(ApplicationTestingProgram.this, SiteOfficeMenu.class);
                            i.putExtra("menu",menuName);
                            i.putExtra("clientsitename",clientSiteName);
                            //Below line will start this activity as a new and when we press back button ,
                            // that will never comes back to this activity
                            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(i);
                        }else{
                            Toast.makeText(ApplicationTestingProgram.this, "No matching menu found: " + str, Toast.LENGTH_LONG).show();
                        }
                      //      break;
                       // }
                    };
                }else{
                    Toast.makeText(ApplicationTestingProgram.this, "Nothing pending for Approval for ", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        }

    }


