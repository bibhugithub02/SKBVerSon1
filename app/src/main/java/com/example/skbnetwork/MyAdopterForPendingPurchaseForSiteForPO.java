package com.example.skbnetwork;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdopterForPendingPurchaseForSiteForPO extends RecyclerView.Adapter <MyAdopterForPendingPurchaseForSiteForPO.myViewHolder>{

    Context context;
    ArrayList<ModelClientSiteWorkTypeItemSubItemQuantityMaster> alist;


    public MyAdopterForPendingPurchaseForSiteForPO(ArrayList<ModelClientSiteWorkTypeItemSubItemQuantityMaster> array) {
        this.context = context;
        this.alist = array;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.singlerowforpendingpurchaseforsiteforpo, parent, false);
        return new myViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {

        ModelClientSiteWorkTypeItemSubItemQuantityMaster model = alist.get(position);

        holder.itemDetails.setText(model.getdMCSWTISIQMItemCategory()+model.getdMCSWTISIQMSubItem());
        holder.demandDetails.setText(String.valueOf(model.getTotalDemand()));
        // Get the total quantity received and then add the new receive and display that
        // Total receive should be less than DMD and equals to SIH + WIP + Bill

        holder.totalPendingPurchaseQuantity.setText(String.valueOf(model.getTotalDemand()-model.getTotalReceived()));
        //Demand >= Purchase >= Received
        //Purchase = Purchase in Pipe line  + Purchase Pending
        holder.receivedQuantity.setText(String.valueOf(model.getTotalReceived()));

        //Current purchase is same as pending purchase
        //holder.inProgressPurchaseQty.setText(String.valueOf(model.getCurrentPurchased()));
        //Stoke in hand
        holder.inProgressPurchaseQty.setText(String.valueOf(model.getStokeInHand()));

        //holder.pendingPurchaseQuantity.setText(String.valueOf(model.getTotalPurchased()-model.getCurrentPurchased()));
        holder.pendingPurchaseQuantity.setText(String.valueOf(model.getCurrentPurchased()));
        holder.demandPendingApproval.setText(String.valueOf(model.getCurrentDemand()).toString().trim());
        holder.searchkey2.setText(model.getdMCSWTISIQMSearchKey2());

    }

    @Override
    public int getItemCount() {
        return alist.size();
    }

    class myViewHolder extends RecyclerView.ViewHolder{

        TextView itemDetails, demandDetails, receivedQuantity, totalPendingPurchaseQuantity, pendingPurchaseQuantity;
        TextView inProgressPurchaseQty;
        TextView demandPendingApproval, addAdditionalDemand, searchkey2;
        TextView totalReceivedQuantity;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            itemDetails = itemView.findViewById(R.id.textView62);
            demandDetails = itemView.findViewById(R.id.editTextNumber12);
            totalPendingPurchaseQuantity = itemView.findViewById(R.id.editTextNumber13);
            receivedQuantity = itemView.findViewById(R.id.editTextNumber14);
            pendingPurchaseQuantity = itemView.findViewById(R.id.editTextNumber15);
            demandPendingApproval = itemView.findViewById(R.id.editTextNumber16);
            inProgressPurchaseQty = itemView.findViewById(R.id.editTextNumber17);
            addAdditionalDemand = itemView.findViewById(R.id.textView90);
            searchkey2 = itemView.findViewById(R.id.textView91);

        }
    }
}
