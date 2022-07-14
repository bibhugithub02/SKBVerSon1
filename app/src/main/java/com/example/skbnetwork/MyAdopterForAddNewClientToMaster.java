package com.example.skbnetwork;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class MyAdopterForAddNewClientToMaster extends FirebaseRecyclerAdapter
        <ModelClientMaster, MyAdopterForAddNewClientToMaster.myViewHolder>{

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public MyAdopterForAddNewClientToMaster(@NonNull FirebaseRecyclerOptions<ModelClientMaster> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull ModelClientMaster model) {

        holder.textView17.setText(model.getdMCMClientName());

    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.singlerowforaddnewclienttomaster, parent, false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{

    TextView textView17, addSite;


    public myViewHolder(@NonNull View itemView) {
        super(itemView);

        textView17 = itemView.findViewById(R.id.textView17);
        addSite = itemView.findViewById(R.id.textView18);

        addSite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Take this to next screen to add Site for specified Client

                Intent i = new Intent(textView17.getContext(), AddSiteForAClient.class);
                i.putExtra("clientname",textView17.getText().toString().trim());
                textView17.getContext().startActivity(i);


            }
        });

    }
}

}
