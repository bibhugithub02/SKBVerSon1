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

public class MyAdopterForAddSiteForAClient extends FirebaseRecyclerAdapter
        <ModelAddSiteForAClient, MyAdopterForAddSiteForAClient.myViewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public MyAdopterForAddSiteForAClient(@NonNull FirebaseRecyclerOptions<ModelAddSiteForAClient> options) {
        super(options);
    }

    String clientName;
    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull ModelAddSiteForAClient model) {

        holder.siteName.setText(model.getdMTASFSiteName());
        clientName = model.getdMTASFClientName();

    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.singlerowforaddsiteforaclient, parent, false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{

        TextView siteName, addWorkType;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            siteName = itemView.findViewById(R.id.textView22);
            addWorkType = itemView.findViewById(R.id.textView23);

            addWorkType.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent i = new Intent(siteName.getContext(), AddWorkTypeForASite.class);
                    i.putExtra("sitename",siteName.getText().toString().trim());
                    i.putExtra("clientname",clientName);
                    siteName.getContext().startActivity(i);

                }
            });

        }
    }
}
