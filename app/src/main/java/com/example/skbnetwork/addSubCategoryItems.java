package com.example.skbnetwork;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class addSubCategoryItems extends AppCompatActivity {

    TextView itemCategory, itemSubCategoryAdd ;
    EditText itemSubCategoryDesc;
    ImageView imageView;
    ListView listview;
    String imageUrl, category, menuOption, clientName, siteName, workType;

    String subItem;

    MyAdopterForaddSubCategoryItems myAdopterForaddSubCategoryItems;
    RecyclerView recyclerView;
    Query query;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sub_category_items);

        //Set the orientation to Portrait for this screen
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        imageUrl = getIntent().getStringExtra("url").toString();
        category = getIntent().getStringExtra("category").toString();
        menuOption = getIntent().getStringExtra("menuoption").toString();
        clientName = getIntent().getStringExtra("clientname");
        siteName = getIntent().getStringExtra("sitename");
        workType = getIntent().getStringExtra("worktype");

        itemCategory = findViewById(R.id.textView12);
        itemSubCategoryAdd = findViewById(R.id.textView13);
        imageView = findViewById(R.id.imageView3);
        //itemSubCategoryDesc = findViewById(R.id.editTextTextPersonName);

        recyclerView = findViewById(R.id.recyclerView1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        itemCategory.setText(category);
        Glide.with(addSubCategoryItems.this).load(imageUrl).into(imageView);

        query = FirebaseDatabase.getInstance().getReference().child("dModelSubCategoryItemMaster")
                .orderByChild("dMSCIMItemName").equalTo(category);

        FirebaseRecyclerOptions<ModelSubCategoryItemMaster> options =
                new FirebaseRecyclerOptions.Builder<ModelSubCategoryItemMaster>()
                        .setQuery(query, ModelSubCategoryItemMaster.class)
                        .build();

        if(menuOption.equals("headoffice")){
            myAdopterForaddSubCategoryItems = new MyAdopterForaddSubCategoryItems(options, menuOption, getApplicationContext());
            recyclerView.setAdapter(myAdopterForaddSubCategoryItems);
        }else{

            myAdopterForaddSubCategoryItems = new MyAdopterForaddSubCategoryItems
                    (options, menuOption, getApplicationContext(), clientName, siteName, workType, category);
            recyclerView.setAdapter(myAdopterForaddSubCategoryItems);
        }



// Add sub category items to sub category master file
        itemSubCategoryAdd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                EditText itemDescription = new EditText(addSubCategoryItems.this);
                AlertDialog.Builder itemDescriptionDialog = new AlertDialog.Builder(addSubCategoryItems.this);
                itemDescriptionDialog.setTitle("Sub Category Item Dialog"); // Set the title of the Popup
                itemDescriptionDialog.setMessage("Enter Sub Category Item Description"); // Set the message to be displayed to the user on the Popup
                itemDescriptionDialog.setView(itemDescription);

                itemDescriptionDialog.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        subItem = itemDescription.getText().toString().trim();
                        addToDataBase();
                    }
                });

                itemDescriptionDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Close the dialog
                    }
                });

                itemDescriptionDialog.create().show();

            }


        });

    }

    // Add Sub Category to the database
    private void addToDataBase() {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference dbr = firebaseDatabase.getReference("dModelSubCategoryItemMaster");

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String dMSCIMItemName = category.toString().trim().toUpperCase();
        String dMSCIMItemUrl = imageUrl;
        String dMSCIMSubCategoryItemName = subItem.toString().toUpperCase();
        String dMSCIMDateStamp= timeStamp;
        String dMSCIMEnterBy = "SKB";
        String dMSCIMProductBy = "SKB";
        String dMSCIMDelete = "Y";

        ModelSubCategoryItemMaster obj = new ModelSubCategoryItemMaster(dMSCIMItemName,dMSCIMItemUrl,
                dMSCIMSubCategoryItemName, dMSCIMDateStamp, dMSCIMEnterBy, dMSCIMProductBy,dMSCIMDelete);

        dbr.child(timeStamp).setValue(obj);


        Toast.makeText(addSubCategoryItems.this, "Sub Category Item been added successfully", Toast.LENGTH_SHORT).show();

    }


    // On Start for recyclerview for Sub category
    @Override
    protected void onStart() {
        super.onStart();
        myAdopterForaddSubCategoryItems.startListening();
    }

    // On Start for recyclerview for Sub category
    @Override
    protected void onStop() {
        super.onStop();
        //myAdopterForaddSubCategoryItems.stopListening();
    }
}