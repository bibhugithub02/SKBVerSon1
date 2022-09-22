package com.example.skbnetwork;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LogInUsingPhoneNumber extends AppCompatActivity {


    TextView buttonLogin ;
    EditText phNumber;
    String ph, str, clientSiteName, menuName, userName;
    String userRetrievePhoneNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_using_phone_number);

        //Set the orientation to Portrait for this screen
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        buttonLogin = findViewById(R.id.textView99);
        phNumber = findViewById(R.id.editTextNumber18);

        TelephonyManager tm = (TelephonyManager)
                getSystemService(ApplicationTestingProgram02.TELEPHONY_SERVICE);

        String[] permission = {
                Manifest.permission.READ_PHONE_NUMBERS
        };

        requestPermissions(permission, 102);

        phNumber.requestFocus();


        ProgressBar progressBar = findViewById(R.id.progress_bar_sendingotp);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!phNumber.getText().toString().trim().isEmpty()) {
                    if ((phNumber.getText().toString().trim()).length() == 10) {
                        ph = "+91"+ phNumber.getText().toString().trim();
                        progressBar.setVisibility(View.VISIBLE);
                        buttonLogin.setVisibility(View.INVISIBLE);
                        getUserPhoneNumber();

                        if ((userRetrievePhoneNumber.toString().trim().isEmpty()) ||
                                (!phNumber.getText().toString().trim().equals(userRetrievePhoneNumber.subSequence(2,12).toString().trim()))){
                            Intent i = new Intent(LogInUsingPhoneNumber.this, LoginScreenOne.class);  // Login screen for OTP
                            startActivity(i);

                        }else{

                            if (phNumber.getText().toString().trim().equals(userRetrievePhoneNumber.subSequence(2,12).toString().trim())){

                                //Take the user to the appropriate  menu option
                                takeUSerToTheRightMenu();
                                Toast.makeText(LogInUsingPhoneNumber.this, "Numbers are matching", Toast.LENGTH_SHORT).show();
                            }else{
                                //May be we can send an msm to get required access
                                Toast.makeText(LogInUsingPhoneNumber.this, "You do not have access to this application", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }else{
                        Toast.makeText(LogInUsingPhoneNumber.this, "Enter a valid Phone Number", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(LogInUsingPhoneNumber.this, "Phone Number cannot be blank", Toast.LENGTH_SHORT).show();
                }

            }

            private void writeToMonitoringDataBase() {
                //Write to monitoring DB ModelForMonitoring
                ModelForMonitoring m = new ModelForMonitoring();
                String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                m.writeToMonioringDB(timeStamp,str ,clientSiteName,"",
                        "Login","Action : User "+userName+"/"+ ph +" Logged in to menu "+ str);
            }

            private void sendSMSCode(String ph, String msg) {
                try {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(ph, null, msg, null, null);
                    Toast.makeText(getApplicationContext(), "Message Sent",
                            Toast.LENGTH_LONG).show();
                } catch (Exception ex) {
                    Toast.makeText(getApplicationContext(),ex.getMessage().toString(),
                            Toast.LENGTH_LONG).show();
                    ex.printStackTrace();
                }

            }

            private void takeUSerToTheRightMenu() {
                String phNumber91 = "+91"+phNumber.getText().toString().trim();
                Query q = FirebaseDatabase.getInstance().getReference().child("dModelEntitlementDb").
                        orderByChild("phNumber").equalTo(phNumber91);

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
                                 //   submit.setVisibility(View.VISIBLE);
                                    Intent i = new Intent(LogInUsingPhoneNumber.this, SKBMainMenu.class);
                                    //Below line will start this activity as a new and when we press back button ,
                                    // that will never comes back to this activity
                                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(i);

                                    //MAA : - Site in Charge Menu
                                }else if(str.equals("SIM")){
                                    progressBar.setVisibility(View.GONE);
                                  //  submit.setVisibility(View.VISIBLE);
                                    menuName = "SIM";
                                    Intent i = new Intent(LogInUsingPhoneNumber.this, SiteOfficeMenu.class);
                                    i.putExtra("menu",menuName);
                                    i.putExtra("clientsitename",clientSiteName);
                                    //Below line will start this activity as a new and when we press back button ,
                                    // that will never comes back to this activity
                                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(i);

                                    //MAA : - Store Menu
                                }else if(str.equals("STM")){
                                    progressBar.setVisibility(View.GONE);
                                  //  submit.setVisibility(View.VISIBLE);
                                    menuName = "STM";
                                    Intent i = new Intent(LogInUsingPhoneNumber.this, StoreMenu.class);
                                    i.putExtra("menu",menuName);
                                    i.putExtra("clientsitename",clientSiteName);
                                    //Below line will start this activity as a new and when we press back button ,
                                    // that will never comes back to this activity
                                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(i);

                                    //MAA : - Head Office Menu
                                }else if(str.equals("HOM")){
                                    progressBar.setVisibility(View.GONE);
                                 //   submit.setVisibility(View.VISIBLE);
                                    Intent i = new Intent(LogInUsingPhoneNumber.this, HeadOfficeMenu.class);
                                    //Below line will start this activity as a new and when we press back button ,
                                    // that will never comes back to this activity
                                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(i);
                                }else{
                                    Toast.makeText(LogInUsingPhoneNumber.this, "No matching Access found: Quiting " + str, Toast.LENGTH_LONG).show();
                                    finishAffinity();
                                    System.exit(0);
                                }
                            };
                        }else{
                            Toast.makeText(LogInUsingPhoneNumber.this, "You don't have access to this Application", Toast.LENGTH_SHORT).show();
                            finishAffinity();
                            System.exit(0);
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }

            private void getUserPhoneNumber() {
                if (ActivityCompat.checkSelfPermission(LogInUsingPhoneNumber.this, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED
                        && ActivityCompat.checkSelfPermission(LogInUsingPhoneNumber.this, Manifest.permission.READ_PHONE_NUMBERS)
                        != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(LogInUsingPhoneNumber.this,
                        Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                userRetrievePhoneNumber = (tm.getLine1Number());
                Toast.makeText(LogInUsingPhoneNumber.this, "Primary Number " + userRetrievePhoneNumber.subSequence(2,12).toString(), Toast.LENGTH_SHORT).show();
                 // userRetrievePhoneNumber = "";

            }
        });


    }
}