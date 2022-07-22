package com.example.skbnetwork;

import android.content.DialogInterface;
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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class MyAdopterForDemandItemStatusAtSite extends FirebaseRecyclerAdapter
        <ModelClientSiteWorkTypeItemSubItemQuantityMaster, MyAdopterForDemandItemStatusAtSite.myViewHolder> {


    int AdditionalCurrentDemand;
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public MyAdopterForDemandItemStatusAtSite(@NonNull FirebaseRecyclerOptions
            <ModelClientSiteWorkTypeItemSubItemQuantityMaster> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position,
                                    @NonNull ModelClientSiteWorkTypeItemSubItemQuantityMaster model) {

        holder.itemDetails.setText(model.getdMCSWTISIQMItemCategory()+model.getdMCSWTISIQMSubItem());
        holder.demandDetails.setText(String.valueOf(model.getTotalDemand()));
        holder.receivedQuantity.setText(String.valueOf(model.getTotalReceived()));
        holder.billedQuantity.setText(String.valueOf(model.getTotalBilled()));
        holder.inHandQuantity.setText(String.valueOf(model.getStokeInHand()));
        holder.demandPendingApproval.setText(String.valueOf(model.getCurrentDemand()).toString().trim());
        holder.searchkey2.setText(model.getdMCSWTISIQMSearchKey2());
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.singlerowfordemanditemstatusatsite, parent, false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{
        TextView itemDetails, demandDetails, receivedQuantity, billedQuantity, inHandQuantity;
        TextView demandPendingApproval, addAdditionalDemand, searchkey2;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            itemDetails = itemView.findViewById(R.id.textView62);
            demandDetails = itemView.findViewById(R.id.editTextNumber12);
            receivedQuantity = itemView.findViewById(R.id.editTextNumber13);
            billedQuantity = itemView.findViewById(R.id.editTextNumber14);
            inHandQuantity = itemView.findViewById(R.id.editTextNumber15);
            demandPendingApproval = itemView.findViewById(R.id.editTextNumber16);
            addAdditionalDemand = itemView.findViewById(R.id.textView90);
            searchkey2 = itemView.findViewById(R.id.textView91);

            addAdditionalDemand.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AdditionalCurrentDemand = 0;
                    EditText quantity = new EditText(itemView.getContext());
                    quantity.setInputType(InputType.TYPE_CLASS_NUMBER);
                    AlertDialog.Builder itemDescriptionDialog = new AlertDialog.Builder(itemView.getContext());
                    itemDescriptionDialog.setTitle("Sub Category Item Dialog"); // Set the title of the Popup
                    itemDescriptionDialog.setMessage("Enter your Additional Demand"); // Set the message to be displayed to the user on the Popup
                    itemDescriptionDialog.setView(quantity);

                    itemDescriptionDialog.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (Integer.parseInt(quantity.getText().toString()) <= 0)
                            {
                                Toast.makeText(itemView.getContext(), "Quantity cannot be Blank/Zero/Negative", Toast.LENGTH_LONG).show();

                            }else{
                                AdditionalCurrentDemand = Integer.parseInt(quantity.getText().toString());
                                addToDataBase();
                            }

                        }

                        private void addToDataBase() {

                            FirebaseDatabase db = FirebaseDatabase.getInstance();
                            DatabaseReference dbr = db.getReference("dModelClientSiteWorkTypeItemSubItemQuantityMaster");

                            // dbr.child(searchString).child("dMCSWTISIQMSearchKey3").setValue("100");

                            HashMap hashMap = new HashMap();

                            hashMap.put("currentDemand", Integer.parseInt(demandPendingApproval.getText().toString()) + AdditionalCurrentDemand);
                            hashMap.put("dMCSWTISIQMSearchKey3", "It is working for now");

                            dbr.child(searchkey2.getText().toString()).updateChildren(hashMap).
                                    addOnSuccessListener(new OnSuccessListener() {
                                @Override
                                public void onSuccess(Object o) {
                                    Toast.makeText(itemDetails.getContext(), "Update works", Toast.LENGTH_SHORT).show();
                                }
                            });

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
    }

}
