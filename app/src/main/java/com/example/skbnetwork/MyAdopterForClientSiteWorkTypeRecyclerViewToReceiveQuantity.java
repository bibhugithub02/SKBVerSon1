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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MyAdopterForClientSiteWorkTypeRecyclerViewToReceiveQuantity extends FirebaseRecyclerAdapter
        <ModelAddWorkTypeToWorkMaster,MyAdopterForClientSiteWorkTypeRecyclerViewToReceiveQuantity.myViewHolder > {

  String action1;
  Query query1, query2;
  String searchkey;
  String flag;
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

        if (action1.equals("RQ")) {
            holder.addItemCategory.setText("Add Quantity"); // Store Menu -- > Quantity Received
        } else if (action1.equals("AQ")) {
            holder.addItemCategory.setText("Add Quantity"); // Site menu --> Additional qty
        } else if ((action1.equals("SIH")) || (action1.equals("SIHR"))) {
            holder.addItemCategory.setText("Stoke View"); // Stock in hand from different menu
        } else if (action1.equals("DI")) {
            holder.addItemCategory.setText("Issue Qty"); // store Menu
        } else if (action1.equals("DR")) {
            holder.addItemCategory.setText("Return Qty");// store Menu
        } else if (action1.equals("PFS")) {
            holder.addItemCategory.setText("Pur Status"); // Purchase Menu (Purchase for Site)
//        } else if (action1.equals("PPFS")) {
//            holder.addItemCategory.setText("Pen Purchase"); // Purchase Menu (Pending purchase for Site)
        } else if (action1.equals("LPFS")) {
            holder.addItemCategory.setText("List Items"); // Purchase Menu -> List of item purchase for site
            // & also come from Site menu
        } else if (action1.equals("RPRS")) {
            holder.addItemCategory.setText("Pur Request"); // Site Menu- > Raise purchase Request for site
        } else {
            holder.addItemCategory.setText("No Action");
        }


            searchkey = model.getdMAWTTWMClientName() + "_" + model.getdMAWTTWMCSiteName() + "_" + model.getdMAWTTWMCWorkTypeName();
            query2 = FirebaseDatabase.getInstance().getReference().child("dModelClientSiteWorkTypeItemSubItemQuantityMaster")
                    .orderByChild("dMCSWTISIQMSearchKey1").equalTo(searchkey);

            query2.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        // Toast.makeText(DemandItemStatusAtSite.this, "NO Record to Display", Toast.LENGTH_SHORT).show();
                        holder.recordForAction.setText("Next level available for action : Yes");

                    } else {
                        // Toast.makeText(DemandItemStatusAtSite.this, "No Record to Display", Toast.LENGTH_SHORT).show();
                        holder.recordForAction.setText("Next level available for action : No");
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }

            });
        }

 //   }

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
        TextView recordForAction;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            clientName = itemView.findViewById(R.id.textView31);
            siteName = itemView.findViewById(R.id.textView32);
            workType = itemView.findViewById(R.id.textView33);
            addItemCategory = itemView.findViewById(R.id.textView34);
            viewItemAtSite = itemView.findViewById(R.id.textView57);
            recordForAction = itemView.findViewById(R.id.editTextNumber23);

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

                    if ((action1.equals("SIH")) || (action1.equals("DI")) || (action1.equals("DR")) || (action1.equals("SIHR")) ){
                       Intent stokeInHand = new Intent(itemView.getContext(),DemandItemStatusAtSite.class );
                        // Parameter used when called from Store Menu Option
                        //Site menu then Choose the Site then Choose the Item Category and then Item Sub Category to add the Qty
                        stokeInHand.putExtra("menuname",action1);
                        stokeInHand.putExtra("clientname",clientName.getText().toString());
                        stokeInHand.putExtra("sitename",siteName.getText().toString());
                        stokeInHand.putExtra("worktype",workType.getText().toString());
                        clientName.getContext().startActivity(stokeInHand);
                    }

                    // Purchase Status from Purchase menu
                    // Below code is for Purchase Menu, get the purchase status for a site(PFS)


                    if (action1.equals("PFS")) {
                        Toast.makeText(clientName.getContext(), "Purchase menu - Work In Progress ", Toast.LENGTH_SHORT).show();
                        Intent PFS = new Intent(itemView.getContext(),PurchaseItemStatusAtSite.class );
                        // Parameter used when called from Store Menu Option
                        //Site menu then Choose the Site then Choose the Item Category and then Item Sub Category to add the Qty
                        PFS.putExtra("menuname",action1);
                        PFS.putExtra("clientname",clientName.getText().toString());
                        PFS.putExtra("sitename",siteName.getText().toString());
                        PFS.putExtra("worktype",workType.getText().toString());
                        clientName.getContext().startActivity(PFS);

                    }

                    // Pending Purchase from Purchase menu
                    // Below code is for Purchase Menu, get the purchase pending for a site(PPFS)
//
//                    if (action1.equals("PPFS")) {
//                        Toast.makeText(clientName.getContext(), "Purchase menu - Work In Progress ", Toast.LENGTH_SHORT).show();
//                        Intent PPFS = new Intent(itemView.getContext(),PurchaseItemStatusAtSite.class );
//                        // Parameter used when called from Store Menu Option
//                        //Site menu then Choose the Site then Choose the Item Category and then Item Sub Category to add the Qty
//                        PPFS.putExtra("menuname",action1);
//                        PPFS.putExtra("clientname",clientName.getText().toString());
//                        PPFS.putExtra("sitename",siteName.getText().toString());
//                        PPFS.putExtra("worktype",workType.getText().toString());
//                        clientName.getContext().startActivity(PPFS);
//
//                    }

                    // Purchase in Hand from Purchase menu
                    // Below code is for Purchase Menu, get the List(L) of item pending(P) for Purchase for(F) a site(S) (LPFS)
                    //Quantity will show as Zero in-case the demand quantity is pending for approval
                    // Quantity displayed as Total Demand - Total Received

                    if (action1.equals("LPFS")) {
                     //   Toast.makeText(clientName.getContext(), "Purchase menu - Work In Progress ", Toast.LENGTH_SHORT).show();
                        Intent LPFS = new Intent(itemView.getContext(),ListOfItemforClientWiseSiteWiseWorkTypeWise.class);
                        // Parameter used when called from Store Menu Option
                        //Site menu then Choose the Site then Choose the Item Category and then Item Sub Category to add the Qty
                        LPFS.putExtra("menuname",action1);
                        LPFS.putExtra("clientname",clientName.getText().toString());
                        LPFS.putExtra("sitename",siteName.getText().toString());
                        LPFS.putExtra("worktype",workType.getText().toString());
                        clientName.getContext().startActivity(LPFS);

                    }

                    // Purchase request from SIT Menu
                    //Quantity will show as Zero in-case the demand quantity is pending for approval
                    // Quantity displayed as Total Demand - Total Received

                    if (action1.equals("RPRS")) {
                        //   Toast.makeText(clientName.getContext(), "Purchase menu - Work In Progress ", Toast.LENGTH_SHORT).show();
                        Intent LPFS = new Intent(itemView.getContext(),ListOfItemforClientWiseSiteWiseWorkTypeWise.class);
                        // Parameter used when called from Store Menu Option
                        //Site menu then Choose the Site then Choose the Item Category and then Item Sub Category to add the Qty
                        LPFS.putExtra("menuname",action1);
                        LPFS.putExtra("clientname",clientName.getText().toString());
                        LPFS.putExtra("sitename",siteName.getText().toString());
                        LPFS.putExtra("worktype",workType.getText().toString());
                        clientName.getContext().startActivity(LPFS);

                    }

                }
            });


        }
    }
}
