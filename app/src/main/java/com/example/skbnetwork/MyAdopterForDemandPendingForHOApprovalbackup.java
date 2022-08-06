package com.example.skbnetwork;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class MyAdopterForDemandPendingForHOApprovalbackup extends
        FirebaseRecyclerAdapter<ModelClientSiteWorkTypeItemSubItemQuantityMaster,
                MyAdopterForDemandPendingForHOApprovalbackup.myViewHolder> {

    //int totalQty;
    String searchString;
    //ActivityUpdateDataBinding binding;

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public MyAdopterForDemandPendingForHOApprovalbackup(@NonNull FirebaseRecyclerOptions<ModelClientSiteWorkTypeItemSubItemQuantityMaster> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position,
                                    @NonNull ModelClientSiteWorkTypeItemSubItemQuantityMaster model) {

        holder.siteName.setText(model.getdMCSWTISIQMSearchKey1());
        holder.itemName.setText(model.getdMCSWTISIQMSubItem()+"_" + model.getdMCSWTISIQMItemCategory());
        holder.qty.setText("Quantity Pending for Approval :  "+String.valueOf(model.getCurrentDemand()));
        //holder.qty.setText("90");
        //holder.qty.setText(Integer.parseInt(model.getCurrentDemand()));
        holder.totalqty = model.getTotalDemand()+model.getCurrentDemand();
        //holder.sea = model.getdMCSWTISIQMSearchKey3().toString();
        holder.sea = model.getdMCSWTISIQMSearchKey1() +"_" + model.getdMCSWTISIQMItemCategory() +"_" + model.getdMCSWTISIQMSubItem();

    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.singlerowfordemandpendingforhoapproval, parent, false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{

        TextView siteName, itemName, qty, approval, approvalForSite;
        String sea;
        int totalqty;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            siteName = itemView.findViewById(R.id.textView68);
            itemName = itemView.findViewById(R.id.textView69);
            qty = itemView.findViewById(R.id.textView70);
            approval = itemView.findViewById(R.id.textView71);
            approvalForSite = itemView.findViewById(R.id.textView72);

            approval.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    updateMasterApprove();
                }
            });

            approvalForSite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FirebaseDatabase db = FirebaseDatabase.getInstance();
                    DatabaseReference dbr = db.getReference("dModelClientSiteWorkTypeItemSubItemQuantityMaster");

                    String sea1 = siteName.getText().toString();
                    Query query = FirebaseDatabase.getInstance().getReference().
                             child("dModelClientSiteWorkTypeItemSubItemQuantityMaster")
                            .orderByChild("dMCSWTISIQMSearchKey1").equalTo(sea1);


                     query.addListenerForSingleValueEvent(new ValueEventListener() {
                         @Override
                         public void onDataChange(@NonNull DataSnapshot snapshot) {
                             if (snapshot.getValue() != null){

                                 for(DataSnapshot ds : snapshot.getChildren()){

                                     if((ds.child("dMCSWTISIQMSearchKey1").getValue(String.class).equals(sea1) &&
                                             (ds.child("currentDemand").getValue(Integer.class)>0)))
                                     {
                                         int totalQtyApproved = ds.child("totalDemand").getValue(Integer.class)+
                                                 ds.child("currentDemand").getValue(Integer.class);
                                         String sea2 = ds.child("dMCSWTISIQMSearchKey2").getValue(String.class);

                                         HashMap hashMap = new HashMap();
                                         hashMap.put("totalDemand", totalQtyApproved);
                                         hashMap.put("totalApproved", totalQtyApproved);

                                         hashMap.put("currentDemand", 0);
                                         hashMap.put("dMCSWTISIQMSearchKey3", "It is working for now");

                                         dbr.child(sea2).updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener() {
                                             @Override
                                             public void onSuccess(Object o) {
                                                 //  Toast.makeText(approval.getContext(), "Update works", Toast.LENGTH_SHORT).show();
                                             }
                                         });
                                     }

                                 }

                             }else{
                                 Toast.makeText(siteName.getContext(), "No record to Process", Toast.LENGTH_SHORT).show();
                             }
                         }

                         @Override
                         public void onCancelled(@NonNull DatabaseError error) {

                         }
                     });

                }
            });
        }


        private void updateMasterApprove() {
            FirebaseDatabase db = FirebaseDatabase.getInstance();
            DatabaseReference dbr = db.getReference("dModelClientSiteWorkTypeItemSubItemQuantityMaster");

            // dbr.child(searchString).child("dMCSWTISIQMSearchKey3").setValue("100");

            HashMap hashMap = new HashMap();
            hashMap.put("totalDemand", totalqty);
            hashMap.put("totalApproved", totalqty);

            hashMap.put("currentDemand", 0);
            hashMap.put("dMCSWTISIQMSearchKey3", "It is working for now");

            dbr.child(sea).updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener() {
                @Override
                public void onSuccess(Object o) {
                    Toast.makeText(approval.getContext(), "Update works", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }


}
