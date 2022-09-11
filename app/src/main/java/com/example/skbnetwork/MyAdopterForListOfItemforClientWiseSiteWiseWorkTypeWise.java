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

public class MyAdopterForListOfItemforClientWiseSiteWiseWorkTypeWise extends FirebaseRecyclerAdapter
        <ModelClientSiteWorkTypeItemSubItemQuantityMaster, MyAdopterForListOfItemforClientWiseSiteWiseWorkTypeWise.myViewHolder>
    {

        String action;

        /**
         * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
         * {@link FirebaseRecyclerOptions} for configuration options.
         *
         * @param options
         * @param menuName
         */
        public MyAdopterForListOfItemforClientWiseSiteWiseWorkTypeWise(@NonNull FirebaseRecyclerOptions<ModelClientSiteWorkTypeItemSubItemQuantityMaster> options, String menuName) {
            super(options);
            action = menuName;

        }

        @Override
        protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull ModelClientSiteWorkTypeItemSubItemQuantityMaster model) {

            holder.itemDetails.setText(model.getdMCSWTISIQMItemCategory()+"/"+model.getdMCSWTISIQMSubItem());
            holder.itemQuantity.setText(String.valueOf(model.getTotalDemand()-model.getTotalReceived()));
            holder.rQuantity = model.getTotalDemand()-model.getTotalReceived();
            holder.pendingPurchaseQuantity.setText(String.valueOf(model.getCurrentPurchased()));
            holder.searchkey2.setText(model.getdMCSWTISIQMSearchKey2());
        }

        @NonNull
        @Override
        public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerowforlistofitemforclientwisesitewiseworktypewise,
                    parent, false);

            return new myViewHolder(view);
        }

        class myViewHolder extends RecyclerView.ViewHolder{

       TextView itemDetails;
       TextView itemQuantity, pendingPurchaseQuantity;
       TextView request;
       int rQuantity;
       TextView searchkey2;

       public myViewHolder(@NonNull View itemView) {
           super(itemView);

           itemDetails = itemView.findViewById(R.id.textView126);
           itemQuantity = itemView.findViewById(R.id.editTextNumber22);
           request = itemView.findViewById(R.id.textView136);
           searchkey2 = itemView.findViewById(R.id.textView138);
           pendingPurchaseQuantity = itemView.findViewById(R.id.editTextNumber21);

           if (action.equals("RPRS"))
           {
               request.setVisibility(View.VISIBLE);
           }

           request.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {

                   EditText purChaseRequestQuantity = new EditText(itemView.getContext());
                   purChaseRequestQuantity.setInputType(InputType.TYPE_CLASS_NUMBER);

                   AlertDialog.Builder itemDescriptionDialog = new AlertDialog.Builder(itemView.getContext());
                   itemDescriptionDialog.setTitle("Purchase Request Dialog"); // Set the title of the Popup
                   itemDescriptionDialog.setMessage("Enter required Quantity"); // Set the message to be displayed to the user on the Popup
                   itemDescriptionDialog.setView(purChaseRequestQuantity);

                   itemDescriptionDialog.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                       @Override
                       public void onClick(DialogInterface dialog, int which) {
                           if(Integer.parseInt(purChaseRequestQuantity.getText().toString()) <= 0){
                               Toast.makeText(itemView.getContext(), "Request Quantity cannot be 0", Toast.LENGTH_SHORT).show();
                           }else if (Integer.parseInt(purChaseRequestQuantity.getText().toString()) > rQuantity){
                               Toast.makeText(itemView.getContext(), "Request Quantity cannot more than standard Quantity", Toast.LENGTH_SHORT).show();

                           }else {
                               // subItem = itemDescription.getText().toString().trim();
                               addToDataBase();
                               Toast.makeText(itemView.getContext(), "Working // Add to the DB", Toast.LENGTH_SHORT).show();
                           }

                       }

                       private void addToDataBase() {
                           FirebaseDatabase db = FirebaseDatabase.getInstance();
                           DatabaseReference dbr = db.getReference("dModelClientSiteWorkTypeItemSubItemQuantityMaster");

                           HashMap hashMap = new HashMap();

                           hashMap.put("currentPurchased", Integer.parseInt(purChaseRequestQuantity.getText().toString())
                                                           +Integer.parseInt(pendingPurchaseQuantity.getText().toString()));

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
