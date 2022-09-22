package com.example.skbnetwork;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class MyAdopterForApplicationManitoringLog extends FirebaseRecyclerAdapter
        <ModelForMonitoring, MyAdopterForApplicationManitoringLog.myViewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public MyAdopterForApplicationManitoringLog(@NonNull FirebaseRecyclerOptions<ModelForMonitoring> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull ModelForMonitoring model) {

        holder.dateStamp.setText("Date: " + model.getSystemDate().toString().trim());
        holder.information.setText("Info:" +model.getInformationDetails().toString().trim()+" / " + model.getActionDetails().toString().trim() +
                " / "+model.getDbName().toString().trim()+" / " + model.getMenuOptionName().toString().trim()+" / " + model.getSiteDetails().toString().trim());

    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.singlerowforapplicationmanitoringlog, parent, false);

        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{

        TextView dateStamp, information;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            dateStamp = itemView.findViewById(R.id.textView139);
            information = itemView.findViewById(R.id.textView140);

        }
    }
}
