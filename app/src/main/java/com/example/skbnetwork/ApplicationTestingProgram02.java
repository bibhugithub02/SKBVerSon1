package com.example.skbnetwork;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Context;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.credentials.HintRequest;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.List;

public class ApplicationTestingProgram02 extends AppCompatActivity {

    TextView textView129;
    Button button3, button4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application_testing_program02);

        TelephonyManager tm = (TelephonyManager)
                getSystemService(ApplicationTestingProgram02.TELEPHONY_SERVICE);

        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        textView129 = findViewById(R.id.textView129);

        String[] permission = {
                Manifest.permission.READ_PHONE_NUMBERS
        };

        requestPermissions(permission, 102);

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPhoneNumber();
            }

            private void getPhoneNumber() {

                if (ActivityCompat.checkSelfPermission(ApplicationTestingProgram02.this, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED
                        && ActivityCompat.checkSelfPermission(ApplicationTestingProgram02.this, Manifest.permission.READ_PHONE_NUMBERS)
                        != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(ApplicationTestingProgram02.this,
                        Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                textView129.setText(tm.getLine1Number());
                Toast.makeText(ApplicationTestingProgram02.this, "Primary Number is " + textView129.getText().subSequence(2,12).toString(), Toast.LENGTH_SHORT).show();

            }
        });

    }

}