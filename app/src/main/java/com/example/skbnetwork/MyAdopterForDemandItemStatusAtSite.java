package com.example.skbnetwork;

import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class MyAdopterForDemandItemStatusAtSite extends FirebaseRecyclerAdapter
        <ModelClientSiteWorkTypeItemSubItemQuantityMaster, MyAdopterForDemandItemStatusAtSite.myViewHolder> {

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
        holder.demandDetails.setText(String.valueOf(model.getTotalDemand()));
        holder.receivedQuantity.setText(String.valueOf(model.getTotalReceived()));
        holder.billedQuantity.setText(String.valueOf(model.getTotalBilled()));
        holder.inHandQuantity.setText(String.valueOf(model.getStokeInHand()));

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
        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            itemDetails = itemView.findViewById(R.id.textView62);
            demandDetails = itemView.findViewById(R.id.editTextNumber12);
            receivedQuantity = itemView.findViewById(R.id.editTextNumber13);
            billedQuantity = itemView.findViewById(R.id.editTextNumber14);
            inHandQuantity = itemView.findViewById(R.id.editTextNumber15);


        }
    }

}
