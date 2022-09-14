package com.example.skbnetwork;

import android.content.DialogInterface;
import android.text.Html;
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

import java.text.SimpleDateFormat;
import java.util.Date;
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

            holder.itemDetails.setText(model.getdMCSWTISIQMItemCategory()+"_"+model.getdMCSWTISIQMSubItem());
            holder.itemQuantity.setText(String.valueOf(model.getTotalDemand()-model.getTotalReceived()));
            holder.rQuantity = model.getTotalDemand()-model.getTotalReceived();
            holder.pendingPurchaseQuantity.setText(String.valueOf(model.getCurrentPurchased()));
            holder.searchkey2.setText(model.getdMCSWTISIQMSearchKey2());
            holder.searchkey1.setText(model.getdMCSWTISIQMSearchKey1());
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
       TextView searchkey2, searchkey1;

       public myViewHolder(@NonNull View itemView) {
           super(itemView);

           itemDetails = itemView.findViewById(R.id.textView126);
           itemQuantity = itemView.findViewById(R.id.editTextNumber22);
           request = itemView.findViewById(R.id.textView136);
           searchkey2 = itemView.findViewById(R.id.textView138);
           searchkey1 = itemView.findViewById(R.id.textView67);
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

                   itemDescriptionDialog.setPositiveButton(Html.fromHtml("<font color='#FF7F27'>Add</font>")
                           , new DialogInterface.OnClickListener() {
                       @Override
                       public void onClick(DialogInterface dialog, int which) {
                           if(Integer.parseInt(purChaseRequestQuantity.getText().toString()) <= 0){
                               Toast.makeText(itemView.getContext(), "Request Quantity cannot be 0", Toast.LENGTH_SHORT).show();
                           }else if ((Integer.parseInt(purChaseRequestQuantity.getText().toString())
                           +Integer.parseInt(pendingPurchaseQuantity.getText().toString())) > rQuantity){
                               Toast.makeText(itemView.getContext(), "Request Quantity cannot more than standard Quantity", Toast.LENGTH_SHORT).show();

                           }else {
                               // subItem = itemDescription.getText().toString().trim();
                               updateToDataBase();
                               addToPurchaseDB();//Add purchase request details to purchase master db
                               Toast.makeText(itemView.getContext(), "Working // Add to the DB", Toast.LENGTH_SHORT).show();
                           }

                       }



                               private void updateToDataBase() {
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

                               private void addToPurchaseDB() {

                                   FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                                   DatabaseReference dbr = firebaseDatabase.getReference("dModelPurchaseRequest");

                                   String mpmdemandDate = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

                                   String mpmclientSiteWorkType = searchkey1.getText().toString();
                                   String mpmitemDescription = searchkey2.getText().toString();
                                   int mpmpurchaseDemandQuantity= Integer.parseInt(purChaseRequestQuantity.getText().toString())
                                           +Integer.parseInt(pendingPurchaseQuantity.getText().toString());
                                   String mpmsiteFiller01 =searchkey1.getText().toString()+ mpmpurchaseDemandQuantity;
                                   String mpmsiteFiller02="";
                                   int mpmsiteFiller03 = 0 ;
                                   //String sKey = itemDetails.getText().toString().trim();

                                   ModelPurchaseRequest obj = new ModelPurchaseRequest(mpmdemandDate, mpmclientSiteWorkType, mpmitemDescription,
                                   mpmpurchaseDemandQuantity, mpmsiteFiller01, mpmsiteFiller02, mpmsiteFiller03);

                                  // dbr.child(mpmclientSiteWorkType).child(sKey).setValue(obj);
                                    dbr.child(mpmitemDescription).setValue(obj);

                                   Toast.makeText(itemDetails.getContext(), "New Client added successfully", Toast.LENGTH_SHORT).show();


                               }



                   });

                   itemDescriptionDialog.setNegativeButton(Html.fromHtml("<font color='#FF7F27'>Cancel</font>"), new DialogInterface.OnClickListener() {
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
