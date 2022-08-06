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
        action = "";

    }

    @Override
    protected void onBindViewHolder(@NonNull MyAdopterForPurchaseItemStatusAtSite.myViewHolder holder,
                                    int position, @NonNull ModelClientSiteWorkTypeItemSubItemQuantityMaster model) {


        holder.itemDetails.setText(model.getdMCSWTISIQMItemCategory()+model.getdMCSWTISIQMSubItem());
        holder.demandDetails.setText(String.valueOf(model.getTotalDemand()));
        // Get the total quantity received and then add the new receive and display that
        // Total receive should be less than DMD and equals to SIH + WIP + Bill
        // holder.receivedQuantity.setText(String.valueOf(model.getCurrentReceived()));
        holder.receivedQuantity.setText(String.valueOf(model.getTotalReceived()));
        holder.billedQuantity.setText(String.valueOf(model.getTotalBilled()));
        holder.inHandQuantity.setText(String.valueOf(model.getStokeInHand()));
        holder.inProgressQty.setText(String.valueOf(model.getWorkInProgress()));
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

        TextView itemDetails, demandDetails, receivedQuantity, billedQuantity, inHandQuantity;
        TextView inProgressQty;
        TextView demandPendingApproval, addAdditionalDemand, searchkey2;
        TextView totalReceivedQuantity;

           public myViewHolder(@NonNull View itemView) {
            super(itemView);
            itemDetails = itemView.findViewById(R.id.textView62);
            demandDetails = itemView.findViewById(R.id.editTextNumber12);
            receivedQuantity = itemView.findViewById(R.id.editTextNumber13);
            billedQuantity = itemView.findViewById(R.id.editTextNumber14);
            inHandQuantity = itemView.findViewById(R.id.editTextNumber15);
            demandPendingApproval = itemView.findViewById(R.id.editTextNumber16);
            inProgressQty = itemView.findViewById(R.id.editTextNumber17);
            addAdditionalDemand = itemView.findViewById(R.id.textView90);
            searchkey2 = itemView.findViewById(R.id.textView91);
        }
    }

}
