package com.example.skbnetwork;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class MyAdopterForPurchaseItemStatusAtSite extends FirebaseRecyclerAdapter
        <ModelClientSiteWorkTypeItemSubItemQuantityMaster, MyAdopterForPurchaseItemStatusAtSite.myViewHolder> {

    String action;

    int AdditionalCurrentDemand;
    int AdditionalReceivedQuantity;

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     * @param menuName
     */
    public MyAdopterForPurchaseItemStatusAtSite(@NonNull FirebaseRecyclerOptions<ModelClientSiteWorkTypeItemSubItemQuantityMaster> options, String menuName) {
        super(options);
        action = menuName;

    }

    @Override
    protected void onBindViewHolder(@NonNull MyAdopterForPurchaseItemStatusAtSite.myViewHolder holder,
                                    int position, @NonNull ModelClientSiteWorkTypeItemSubItemQuantityMaster model) {


        holder.itemDetails.setText(model.getdMCSWTISIQMItemCategory()+model.getdMCSWTISIQMSubItem());
        holder.demandDetails.setText(String.valueOf(model.getTotalDemand()));
        // Get the total quantity received and then add the new receive and display that
        // Total receive should be less than DMD and equals to SIH + WIP + Bill
        // holder.receivedQuantity.setText(String.valueOf(model.getCurrentReceived()));
        holder.purchaseQuantity.setText(String.valueOf(model.getTotalPurchased()));
        //Demand >= Purchase >= Received
        //Purchase = Purchase in Pipe line  + Purchase Pending

        holder.receivedQuantity.setText(String.valueOf(model.getTotalReceived()));
        holder.inProgressPurchaseQty.setText(String.valueOf(model.getCurrentPurchased()));

        holder.pendingPurchaseQuantity.setText(String.valueOf(model.getTotalPurchased()-model.getCurrentPurchased()));
        holder.demandPendingApproval.setText(String.valueOf(model.getCurrentDemand()).toString().trim());
        holder.searchkey2.setText(model.getdMCSWTISIQMSearchKey2());
    }

    @NonNull
    @Override
    public MyAdopterForPurchaseItemStatusAtSite.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerowforpurchaseitemstatusatsitewithinprogressqty,
                parent, false);

        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{

        TextView itemDetails, demandDetails, receivedQuantity, purchaseQuantity, pendingPurchaseQuantity;
        TextView inProgressPurchaseQty;
        TextView demandPendingApproval, addAdditionalDemand, searchkey2;
        TextView totalReceivedQuantity;

           public myViewHolder(@NonNull View itemView) {
            super(itemView);
            itemDetails = itemView.findViewById(R.id.textView62);
            demandDetails = itemView.findViewById(R.id.editTextNumber12);
            purchaseQuantity = itemView.findViewById(R.id.editTextNumber13);
            receivedQuantity = itemView.findViewById(R.id.editTextNumber14);
            pendingPurchaseQuantity = itemView.findViewById(R.id.editTextNumber15);
            demandPendingApproval = itemView.findViewById(R.id.editTextNumber16);
            inProgressPurchaseQty = itemView.findViewById(R.id.editTextNumber17);
            addAdditionalDemand = itemView.findViewById(R.id.textView90);
            searchkey2 = itemView.findViewById(R.id.textView91);
        }
    }

}
