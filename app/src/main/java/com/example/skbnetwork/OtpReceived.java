package com.example.skbnetwork;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;

public class OtpReceived extends AppCompatActivity {

    EditText n1,n2,n3,n4,n5,n6;
    String phNumber1, otpReceived, otpGenerated;
    TextView msg;
    TextView submit;
    String str;
    String menuName, clientSiteName,userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_received);

        //Set the orientation to Portrait for this screen
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        n1 = findViewById(R.id.textView101);
        n2 = findViewById(R.id.textView102);
        n3 = findViewById(R.id.textView103);
        n4 = findViewById(R.id.textView104);
        n5 = findViewById(R.id.textView105);
        n6 = findViewById(R.id.textView106);
        submit = findViewById(R.id.textView110);
        ProgressBar progressBar = findViewById(R.id.progress_bar_receiveotp);
        msg = findViewById(R.id.textView100);

        phNumber1 = getIntent().getStringExtra("mobile").toString().trim();
        otpReceived = getIntent().getStringExtra("otp").toString().trim();
        msg.setText(String.format("Otp has been sent to : " + "%s",phNumber1));
        n1.requestFocus();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if((!n1.getText().toString().trim().isEmpty()) && (!n2.getText().toString().trim().isEmpty()) &&
                        (!n3.getText().toString().trim().isEmpty()) && (!n4.getText().toString().trim().isEmpty()) &&
                        (!n5.getText().toString().trim().isEmpty()) && (!n6.getText().toString().trim().isEmpty())){

                    otpGenerated = n1.getText().toString().trim() + n2.getText().toString().trim() + n3.getText().toString().trim()+
                            n4.getText().toString().trim()+n5.getText().toString().trim()+n6.getText().toString().trim();

                //    Toast.makeText(OtpReceived.this, "Your OTM is :" + otpReceived, Toast.LENGTH_SHORT).show();

                    if (otpReceived!=null){
                        progressBar.setVisibility(View.VISIBLE);
                        submit.setVisibility(View.INVISIBLE);
                        PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(otpReceived,otpGenerated );

                        FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential).
                                addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if(task.isSuccessful()) {
                                    Query q = FirebaseDatabase.getInstance().getReference().child("dModelEntitlementDb").
                                            orderByChild("phNumber").equalTo(phNumber1);

                                    q.addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            if(snapshot.getValue() != null){
                                                for(DataSnapshot ds : snapshot.getChildren()){
                                                        str = ds.child("menuName").getValue().toString();
                                                        clientSiteName=ds.child("siteNme").getValue().toString();
                                                        clientSiteName = clientSiteName.replace("_","+");
                                                        userName = ds.child("name").getValue().toString();
                                                    //Write to Monitoring DataBase
                                                    writeToMonitoringDataBase();

                                                        //MAM : - Master Menu
                                                    if(str.equals("MAM")){
                                                        progressBar.setVisibility(View.GONE);
                                                        submit.setVisibility(View.VISIBLE);
                                                        Intent i = new Intent(OtpReceived.this, SKBMainMenu.class);
                                                        //Below line will start this activity as a new and when we press back button ,
                                                        // that will never comes back to this activity
                                                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                        startActivity(i);

                                                        //MAA : - Site in Charge Menu
                                                    }else if(str.equals("SIM")){
                                                        progressBar.setVisibility(View.GONE);
                                                        submit.setVisibility(View.VISIBLE);
                                                        menuName = "SIM";
                                                        Intent i = new Intent(OtpReceived.this, SiteOfficeMenu.class);
                                                        i.putExtra("menu",menuName);
                                                        i.putExtra("clientsitename",clientSiteName);
                                                        //Below line will start this activity as a new and when we press back button ,
                                                        // that will never comes back to this activity
                                                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                        startActivity(i);

                                                        //MAA : - Store Menu
                                                    }else if(str.equals("STM")){
                                                        progressBar.setVisibility(View.GONE);
                                                        submit.setVisibility(View.VISIBLE);
                                                        menuName = "STM";
                                                        Intent i = new Intent(OtpReceived.this, StoreMenu.class);
                                                        i.putExtra("menu",menuName);
                                                        i.putExtra("clientsitename",clientSiteName);
                                                        //Below line will start this activity as a new and when we press back button ,
                                                        // that will never comes back to this activity
                                                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                        startActivity(i);

                                                        //MAA : - Head Office Menu
                                                    }else if(str.equals("HOM")){
                                                        progressBar.setVisibility(View.GONE);
                                                        submit.setVisibility(View.VISIBLE);
                                                        Intent i = new Intent(OtpReceived.this, HeadOfficeMenu.class);
                                                        //Below line will start this activity as a new and when we press back button ,
                                                        // that will never comes back to this activity
                                                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                        startActivity(i);
                                                    }else{
                                                        Toast.makeText(OtpReceived.this, "No matching Access found: Quiting " + str, Toast.LENGTH_LONG).show();
                                                        finishAffinity();
                                                        System.exit(0);
                                                    }
                                                    };
                                            }else{
                                                Toast.makeText(OtpReceived.this, "You don't have access to this Application", Toast.LENGTH_SHORT).show();
                                                finishAffinity();
                                                System.exit(0);
                                            }

                                        }

                                        private void writeToMonitoringDataBase() {
                                            //Write to monitoring DB ModelForMonitoring
                                            ModelForMonitoring m = new ModelForMonitoring();
                                            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                                            m.writeToMonioringDB(timeStamp,str ,clientSiteName,"",
                                                    "Login","Action : User "+userName+" / "+ phNumber1 +" Logged in to menu "+ str);
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {

                                        }
                                    });

                                }else{
                                    Toast.makeText(OtpReceived.this, "Not Authorised to access", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                    }else{
                        Toast.makeText(OtpReceived.this, "Check internet Connection", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(OtpReceived.this, "Please enter all numbers appropriately", Toast.LENGTH_SHORT).show();
                }

            }
        });

        numberOtpMove();

    }

    private void numberOtpMove() {

        n1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty()){
                    n2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        n2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty()){
                    n3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        n3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty()){
                    n4.requestFocus();
               }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        n4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty()){
                    n5.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        n5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty()){
                    n6.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }
}