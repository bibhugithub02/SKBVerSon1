package com.example.skbnetwork;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class MyAdopterForWorkTypeForASiteForAClient extends FirebaseRecyclerAdapter
        <ModelAddWorkTypeToWorkMaster, MyAdopterForWorkTypeForASiteForAClient.myViewHolder>{


    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public MyAdopterForWorkTypeForASiteForAClient(@NonNull FirebaseRecyclerOptions<ModelAddWorkTypeToWorkMaster> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull ModelAddWorkTypeToWorkMaster model) {
        holder.textView29.setText(model.getdMAWTTWMCWorkTypeName());
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.singlerowforaddworktypeforasiteforaclient, parent, false);
        return new myViewHolder(view);

    }

    class myViewHolder extends RecyclerView.ViewHolder{
        TextView textView29, textView30;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            textView29 = itemView.findViewById(R.id.textView29);
        }
    }

}
