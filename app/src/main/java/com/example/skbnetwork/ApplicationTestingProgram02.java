package com.example.skbnetwork;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.widget.Toast;

public class ApplicationTestingProgram02 extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application_testing_program02);

        TelephonyManager tm = (TelephonyManager)
                getSystemService(ApplicationTestingProgram02.TELEPHONY_SERVICE);


        // For SIM card, use the getSimSerialNumber()

//        //---get the SIM card ID---
//        String simID = tm.getSimSerialNumber();
//
//        if (simID != null)
//            Toast.makeText(this, "SIM card ID: " + simID,
//                    Toast.LENGTH_LONG).show();


        //---get the phone number---
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_NUMBERS) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
//            // TODO: Consider calling
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//           return;
//       }
//        String telNumber = tm.getLine1Number();
//        if (telNumber != null)
//            Toast.makeText(this, "Phone number: " + telNumber,
//                    Toast.LENGTH_LONG).show();


//       // ---get the IMEI number---
//        String IMEI = tm.getDeviceId();
//        if (IMEI != null)
//            Toast.makeText(this, "IMEI number: " + IMEI,
//                    Toast.LENGTH_LONG).show();
//
    }

}