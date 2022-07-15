package com.example.skbnetwork;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class demandQuantity extends AppCompatActivity {

    String clientName, siteName, workType, itemName, subItemName;
    TextView sclientName, ssiteName, sworkType, sitemName, ssubItemName;
    final String program = "Demand";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demand_quantity);

        //Set the orientation to Portrait for this screen
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        clientName = getIntent().getStringExtra("clientname");
        siteName = getIntent().getStringExtra("sitename");
        workType = getIntent().getStringExtra("worktype");
        itemName = getIntent().getStringExtra("category");
        subItemName = getIntent().getStringExtra("subcategory");

        sclientName = findViewById(R.id.textView37);
        ssiteName = findViewById(R.id.textView39);
        sworkType = findViewById(R.id.textView41);
        sitemName= findViewById(R.id.textView43);
        ssubItemName = findViewById(R.id.textView45);

        sclientName.setText( clientName);
        ssiteName.setText( siteName);
        sworkType.setText( workType);
        sitemName.setText( itemName);
        ssubItemName.setText(subItemName);

        if (program =="Demand"){

        }

        Toast.makeText(this, "Code to add Quantity here", Toast.LENGTH_SHORT).show();
    }
}