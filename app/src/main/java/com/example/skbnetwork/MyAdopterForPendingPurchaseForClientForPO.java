package com.example.skbnetwork;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdopterForPendingPurchaseForClientForPO extends RecyclerView.Adapter <MyAdopterForPendingPurchaseForClientForPO.myViewHolder> {
    Context context;
    ArrayList<ModelClientSiteWorkTypeItemSubItemQuantityMaster> alist;

    public MyAdopterForPendingPurchaseForClientForPO(ArrayList<ModelClientSiteWorkTypeItemSubItemQuantityMaster> array) {

        this.context = context;
        this.alist = array;

    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.singlerowforpendingpurchaseforclientforpo, parent, false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {

        ModelClientSiteWorkTypeItemSubItemQuantityMaster model = alist.get(position);
        holder.clientSiteWorkType.setText((model.getdMCSWTISIQMSearchKey1()));

    }

    @Override
    public int getItemCount() {
        return alist.size();
    }


    class myViewHolder extends RecyclerView.ViewHolder{

        TextView clientSiteWorkType, pendingPurchaseDetails, preview;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            clientSiteWorkType = itemView.findViewById(R.id.textView78);
            pendingPurchaseDetails = itemView.findViewById(R.id.textView85);
            preview = itemView.findViewById(R.id.textView86);

            pendingPurchaseDetails.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String action1 = "PPFS";
                    String clientName = clientSiteWorkType.getText().toString().trim();
                    String siteName = "";
                    String workType = "";

                  //  Intent PPFS = new Intent(itemView.getContext(),PurchaseItemStatusAtSite.class );
                    Intent PPFS = new Intent(itemView.getContext(),PendingPurchaseForSiteForPO.class );
                    // Parameter used when called from Store Menu Option
                    //Site menu then Choose the Site then Choose the Item Category and then Item Sub Category to add the Qty
                    PPFS.putExtra("menuname",action1);
                    PPFS.putExtra("clientname",clientName);
                    PPFS.putExtra("sitename",siteName);
                    PPFS.putExtra("worktype",workType);
                    clientSiteWorkType.getContext().startActivity(PPFS);
                }
            });

            preview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(clientSiteWorkType.getContext(), "Preview Button working", Toast.LENGTH_SHORT).show();
                }
            });
            
            
        }
    }

}


