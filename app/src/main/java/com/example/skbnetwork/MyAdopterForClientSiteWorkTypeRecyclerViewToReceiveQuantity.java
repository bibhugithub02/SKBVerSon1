package com.example.skbnetwork;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class MyAdopterForClientSiteWorkTypeRecyclerViewToReceiveQuantity extends FirebaseRecyclerAdapter
        <ModelAddWorkTypeToWorkMaster,MyAdopterForClientSiteWorkTypeRecyclerViewToReceiveQuantity.myViewHolder > {

  String action1;
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public MyAdopterForClientSiteWorkTypeRecyclerViewToReceiveQuantity(@NonNull FirebaseRecyclerOptions<ModelAddWorkTypeToWorkMaster> options) {
        super(options);
    }

    public MyAdopterForClientSiteWorkTypeRecyclerViewToReceiveQuantity
            (FirebaseRecyclerOptions<ModelAddWorkTypeToWorkMaster> options, String menuOption) {
        super(options);

        action1 = menuOption;

    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull ModelAddWorkTypeToWorkMaster model) {
        holder.clientName.setText(model.getdMAWTTWMClientName());
        holder.siteName.setText(model.getdMAWTTWMCSiteName());
        holder.workType.setText(model.getdMAWTTWMCWorkTypeName());

        //RQ - Request quantity , the initial Demand raised coming from Site menu
        //AQ - Additional Quantity demand, coming from Site menu
        //SIN - Stoke in Hand - Coming from Store Menu

        if (action1.equals("RQ")){
            holder.addItemCategory.setText("Add Receive Quantity");
            }else if (action1.equals("AQ")){
                holder.addItemCategory.setText("Add Demand Quantity");
            }else if (action1.equals("SIH")){
                holder.addItemCategory.setText("Stoke View");
            }else if (action1.equals("DI")){
                holder.addItemCategory.setText("Issue Qty");
            }else if (action1.equals("DR")){
                holder.addItemCategory.setText("Return Qty");
            }else if (action1.equals("PFS")){
                holder.addItemCategory.setText("Pur Status");
            }else if (action1.equals("PPFS")){
                holder.addItemCategory.setText("Pen Purchase");
            }else if (action1.equals("LPFS")){
                holder.addItemCategory.setText("List Items");
        }else
            {
                holder.addItemCategory.setText("No Action");
            }


    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.singlerowforclientsiteworktyperecyclerviewtoreceivequantity, parent, false);
            return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder
    {

        TextView clientName,siteName, workType, addItemCategory;
        TextView viewItemAtSite;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            clientName = itemView.findViewById(R.id.textView31);
            siteName = itemView.findViewById(R.id.textView32);
            workType = itemView.findViewById(R.id.textView33);
            addItemCategory = itemView.findViewById(R.id.textView34);
            viewItemAtSite = itemView.findViewById(R.id.textView57);

            addItemCategory.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Code to add the Category for Site
                   if (action1.equals("AQ") || (action1.equals("RQ") )){

                    Intent i = new Intent(itemView.getContext(),DemandItemStatusAtSite.class );
                    // Parameter used when called from Site Menu Option
                    //Site menu then Choose the Site then Choose the Item Category and then Item Sub Category to add the Qty
                    i.putExtra("menuname",action1);
                    i.putExtra("clientname",clientName.getText().toString());
                    i.putExtra("sitename",siteName.getText().toString());
                    i.putExtra("worktype",workType.getText().toString());
                    clientName.getContext().startActivity(i);
                   }

                    if ((action1.equals("SIH")) || (action1.equals("DI")) || (action1.equals("DR"))){
                       Intent stokeInHand = new Intent(itemView.getContext(),DemandItemStatusAtSite.class );
                        // Parameter used when called from Store Menu Option
                        //Site menu then Choose the Site then Choose the Item Category and then Item Sub Category to add the Qty
                        stokeInHand.putExtra("menuname",action1);
                        stokeInHand.putExtra("clientname",clientName.getText().toString());
                        stokeInHand.putExtra("sitename",siteName.getText().toString());
                        stokeInHand.putExtra("worktype",workType.getText().toString());
                        clientName.getContext().startActivity(stokeInHand);
                    }

                    // Below code is for Purchase Menu, get the purchase status for a site(PFS)


                    if (action1.equals("PFS")) {
                        Toast.makeText(clientName.getContext(), "Purchase menu - Work In Progress ", Toast.LENGTH_SHORT).show();
                        Intent stokeInHand = new Intent(itemView.getContext(),PurchaseItemStatusAtSite.class );
                        // Parameter used when called from Store Menu Option
                        //Site menu then Choose the Site then Choose the Item Category and then Item Sub Category to add the Qty
                        stokeInHand.putExtra("menuname",action1);
                        stokeInHand.putExtra("clientname",clientName.getText().toString());
                        stokeInHand.putExtra("sitename",siteName.getText().toString());
                        stokeInHand.putExtra("worktype",workType.getText().toString());
                     //   clientName.getContext().startActivity(stokeInHand);

                    }

                    // Below code is for Purchase Menu, get the purchase pending for a site(PPFS)

                    if (action1.equals("PPFS")) {
                        Toast.makeText(clientName.getContext(), "Purchase menu - Work In Progress ", Toast.LENGTH_SHORT).show();
                        Intent stokeInHand = new Intent(itemView.getContext(),PurchaseItemStatusAtSite.class );
                        // Parameter used when called from Store Menu Option
                        //Site menu then Choose the Site then Choose the Item Category and then Item Sub Category to add the Qty
                        stokeInHand.putExtra("menuname",action1);
                        stokeInHand.putExtra("clientname",clientName.getText().toString());
                        stokeInHand.putExtra("sitename",siteName.getText().toString());
                        stokeInHand.putExtra("worktype",workType.getText().toString());
                      //  clientName.getContext().startActivity(stokeInHand);

                    }

                    if (action1.equals("LPFS")) {
                        Toast.makeText(clientName.getContext(), "Purchase menu - Work In Progress ", Toast.LENGTH_SHORT).show();
                        Intent stokeInHand = new Intent(itemView.getContext(),ListOfItemforClientWiseSiteWiseWorkTypeWise.class );
                        // Parameter used when called from Store Menu Option
                        //Site menu then Choose the Site then Choose the Item Category and then Item Sub Category to add the Qty
                        stokeInHand.putExtra("menuname",action1);
                        stokeInHand.putExtra("clientname",clientName.getText().toString());
                        stokeInHand.putExtra("sitename",siteName.getText().toString());
                        stokeInHand.putExtra("worktype",workType.getText().toString());
                        clientName.getContext().startActivity(stokeInHand);

                    }

                }
            });


        }
    }
}
