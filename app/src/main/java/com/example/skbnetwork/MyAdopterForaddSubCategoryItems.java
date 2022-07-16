package com.example.skbnetwork;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyAdopterForaddSubCategoryItems extends FirebaseRecyclerAdapter
        <ModelSubCategoryItemMaster, MyAdopterForaddSubCategoryItems.MyViewHolder> {

    String menuOption, clientName, siteName, workType, itemName;
    Context context;
    ModelClientSiteWorkTypeItemSubItemQuantityMaster data;
    String alreadyRecorded;
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *  @param options
     * @param menuOption
     * @param applicationContext
     */
    public MyAdopterForaddSubCategoryItems(@NonNull FirebaseRecyclerOptions
            <ModelSubCategoryItemMaster> options, String menuOption, Context applicationContext) {
        super(options);
        this.menuOption = menuOption;
        this.context = applicationContext;
    }

    public MyAdopterForaddSubCategoryItems(@NonNull FirebaseRecyclerOptions
            <ModelSubCategoryItemMaster> options, String menuOption, Context applicationContext, String clientName, String siteName, String workType, String category) {
        super(options);
        this.menuOption = menuOption;
        this.context = applicationContext;
        this.clientName = clientName;
        this.siteName = siteName;
        this.workType = workType;
        this.itemName = category;
    }

    @Override
    protected void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull ModelSubCategoryItemMaster model) {

        holder.subCategoryDesc.setText(model.getdMSCIMSubCategoryItemName());

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.singlerowforaddsubcategoryitemsdeleteoptionrecycleview, parent, false);

        return new MyViewHolder(view);
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView subCategoryDesc, delete, addSubItemQuantity;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            subCategoryDesc = itemView.findViewById(R.id.textView14);
            delete = itemView.findViewById(R.id.textView15);
            addSubItemQuantity = itemView.findViewById(R.id.textView35);
            
            if(menuOption.equals("headoffice")){
                delete.setVisibility(View.VISIBLE);
            }
            if(menuOption.equals("sitemenu")){
                addSubItemQuantity.setVisibility(View.VISIBLE);
            }
            
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    getConfirmationFromTheUser();

                    Toast.makeText(itemView.getContext(), "Choose:" + subCategoryDesc.getText().toString(), Toast.LENGTH_SHORT).show();

                }
            });
            
            //If coming from site office , then add the quantity

            addSubItemQuantity.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //Check whether the Qty has been added or not , if added then get the record and update that with new
                    //addition else create one record and update the new quantity.

                    getDemandQuantityForTheSubItem();
//                    Intent i = new Intent(subCategoryDesc.getContext(), demandQuantity.class);
//                    i.putExtra("clientname",clientName);
//                    i.putExtra("sitename",siteName);
//                    i.putExtra("worktype",workType);
//                    i.putExtra("category",itemName);
//                    i.putExtra("subcategory",subCategoryDesc.getText().toString());
//                    subCategoryDesc.getContext().startActivity(i);
//                    Toast.makeText(subCategoryDesc.getContext(), "Yet to be coded with ", Toast.LENGTH_SHORT).show();
                }
            });
        }

        private void getDemandQuantityForTheSubItem() {

            EditText quantity = new EditText(subCategoryDesc.getContext());
            quantity.setInputType(InputType.TYPE_CLASS_NUMBER);

//                  |  InputType.TYPE_NUMBER_FLAG_DECIMAL |
//                    InputType.TYPE_NUMBER_FLAG_SIGNED);
            AlertDialog.Builder clientDescriptionDialog = new AlertDialog.Builder(subCategoryDesc.getContext());
            clientDescriptionDialog.setTitle("Quantity Dialog"); // Set the title of the Popup
            clientDescriptionDialog.setMessage("Enter Demand Quantity"); // Set the message to be displayed to the user on the Popup
            clientDescriptionDialog.setView(quantity);

            clientDescriptionDialog.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                  if (Integer.parseInt(quantity.getText().toString()) <= 0)
                  {
                        Toast.makeText(subCategoryDesc.getContext(), "Quantity cannot be Blank/Zero/Negative", Toast.LENGTH_LONG).show();

                    }else{
                        alreadyRecorded = "N";
                        checkExistanceOfRecord();
                        if(alreadyRecorded == "N") {
                            writeToModelClientSiteWorkTypeItemSubItemQuantityMaster();
                        }

                    }
                }

                private void checkExistanceOfRecord() {

                    FirebaseDatabase db = FirebaseDatabase.getInstance();
                    DatabaseReference dbr = db.getReference("dModelClientSiteWorkTypeItemSubItemQuantityMaster");
                    String Search = clientName +"_" +siteName+"_"+workType+"_"+itemName+"_"+subCategoryDesc.getText().toString();
                    Query query= dbr.orderByChild("dMCSWTISIQMSearchKey2").
                            equalTo(Search);

                    query.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot)

                        {
                            if(snapshot.exists()){
                                Toast.makeText(itemView.getContext(), "new Record found", Toast.LENGTH_SHORT).show();
                                alreadyRecorded = "Y";
                            }else{
                                alreadyRecorded = "N";
                            }

                            for(DataSnapshot ds: snapshot.getChildren()){
                                data = ds.getValue(ModelClientSiteWorkTypeItemSubItemQuantityMaster.class);
//                                if (data.getCurrentDemand()>=0){
//                                    alreadyRecorded = "Y";
//                                    Toast.makeText(itemView.getContext(), "Record found", Toast.LENGTH_SHORT).show();
//                                }else{
//                                    alreadyRecorded = "N";
//                                }

                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }

                private void writeToModelClientSiteWorkTypeItemSubItemQuantityMaster() {

                    FirebaseDatabase db = FirebaseDatabase.getInstance();
                    DatabaseReference dbr = db.getReference("dModelClientSiteWorkTypeItemSubItemQuantityMaster");
                    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

                    String dMCSWTISIQMClientName = clientName;
                    String dMCSWTISIQMSiteName = siteName;
                    String dMCSWTISIQMWorkType = workType;
                    String dMCSWTISIQMItemCategory= itemName;
                    String dMCSWTISIQMSubItem = subCategoryDesc.getText().toString();
                    int currentDemand = Integer.parseInt(quantity.getText().toString());
                    String dMCSWTISIQMSearchKey1 = clientName +"_" +siteName+"_"+workType;
                    String dMCSWTISIQMSearchKey2 = clientName +"_" +siteName+"_"+workType+"_"+itemName+"_"+subCategoryDesc.getText().toString();

                    String dMCSWTISIQMDateStamp = timeStamp;
                    int approvedDemand = 0;
                    int totalDemand = 0;
                    int totalApproved = 0;
                    int currentPurchased = 0;
                    int totalPurchased = 0;
                    int currentReceived = 0;
                    int totalReceived = 0;
                    int lastBilled = 0;
                    int totalBilled = 0;
                    int workInProgress = 0;
                    int stokeInHand = 0;

                    String Key = clientName +"_" +siteName+"_"+workType+"_"+itemName+"_"+subCategoryDesc.getText().toString();

                    ModelClientSiteWorkTypeItemSubItemQuantityMaster obj = new ModelClientSiteWorkTypeItemSubItemQuantityMaster
                            (dMCSWTISIQMClientName,dMCSWTISIQMSiteName,dMCSWTISIQMWorkType,dMCSWTISIQMItemCategory,dMCSWTISIQMSubItem,
                                    dMCSWTISIQMSearchKey1, dMCSWTISIQMSearchKey2,dMCSWTISIQMDateStamp,
                                    currentDemand, approvedDemand, totalDemand, totalApproved,currentPurchased,
                                    totalPurchased, currentReceived,totalReceived, lastBilled,totalBilled,workInProgress,stokeInHand) ;

                    dbr.child(dMCSWTISIQMSearchKey2).setValue(obj);

                }
            });

            clientDescriptionDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //Close the dialog
                }
            });

            clientDescriptionDialog.create().show();
        }

        private void getConfirmationFromTheUser() {

            //EditText SiteDescription = new EditText(deleteItem.getContext());
            AlertDialog.Builder siteDescriptionDialog = new AlertDialog.Builder(delete.getContext());
            siteDescriptionDialog.setTitle("Delete Confirmation Dialog"); // Set the title of the Popup
            //siteDescriptionDialog.setMessage("Enter Site for Client"); // Set the message to be displayed to the user on the Popup
            // siteDescriptionDialog.setView(SiteDescription);

            siteDescriptionDialog.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    Toast.makeText(delete.getContext(), "Yes been selected", Toast.LENGTH_SHORT).show();
                    deleteItemFromdModelSubCategoryItemMaster();
                }
            });
            siteDescriptionDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //Close the dialog
                }
            });
            siteDescriptionDialog.create().show();
        }

        private void deleteItemFromdModelSubCategoryItemMaster() {

            //Check the flag and if the flag is N then delete the record
            FirebaseDatabase db = FirebaseDatabase.getInstance();
            DatabaseReference dbr = db.getReference("dModelSubCategoryItemMaster");
            Query query= dbr.orderByChild("dMSCIMSubCategoryItemName").
                    equalTo(subCategoryDesc.getText().toString());

            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for(DataSnapshot ds: snapshot.getChildren()){
                        ds.getRef().removeValue();
                        Toast.makeText(itemView.getContext(), "Record Deleted", Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
        }


    }
