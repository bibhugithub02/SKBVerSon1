package com.example.skbnetwork;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class MyAdopterForClientSiteWorkTypeRecyclerView extends FirebaseRecyclerAdapter
        <ModelAddWorkTypeToWorkMaster, MyAdopterForClientSiteWorkTypeRecyclerView.myViewHolder> {

    String OptionName;
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     * @param optionName
     */
    public MyAdopterForClientSiteWorkTypeRecyclerView(@NonNull FirebaseRecyclerOptions<ModelAddWorkTypeToWorkMaster> options, String optionName) {
        super(options);
        this.OptionName = optionName;
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull ModelAddWorkTypeToWorkMaster model) {

        holder.clientName.setText(model.getdMAWTTWMClientName());
        holder.siteName.setText(model.getdMAWTTWMCSiteName());
        holder.workType.setText(model.getdMAWTTWMCWorkTypeName());

        if (OptionName.equals("STDD")){
            holder.addItemCategory.setVisibility(View.VISIBLE);
        }else{
            holder.viewItemAtSite.setVisibility(View.VISIBLE);
        }

    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.singlerowforclientsiteworktyperecyclerview, parent, false);

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
                    String menuname = "sitemenu";
                    Intent i = new Intent(itemView.getContext(),ItemMasterRecyclerView.class );
                    // Parameter used when called from Site Menu Option
                    //Site menu then Choose the Site then Choose the Item Category and then Item Sub Category to add the Qty
                    i.putExtra("menuname",menuname);
                    i.putExtra("clientname",clientName.getText().toString());
                   // String T1 = clientName.getText().toString();
                    i.putExtra("sitename",siteName.getText().toString());
                   // String T2 = siteName.getText().toString();
                    i.putExtra("worktype",workType.getText().toString());
                   // String T3 =workType.getText().toString();
                    clientName.getContext().startActivity(i);

                }
            });

            viewItemAtSite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Code to add the Category for Site

                    String menuname = "AQ";
                    Intent i = new Intent(itemView.getContext(),DemandItemStatusAtSite.class );
                    // Parameter used when called from Site Menu Option
                    //Site menu then Choose the Site then Choose the Item Category and then Item Sub Category to add the Qty
                    i.putExtra("menuname",menuname);
                    i.putExtra("clientname",clientName.getText().toString());
                    i.putExtra("sitename",siteName.getText().toString());
                    i.putExtra("worktype",workType.getText().toString());
                    clientName.getContext().startActivity(i);


                }
            });

        }
    }
}
