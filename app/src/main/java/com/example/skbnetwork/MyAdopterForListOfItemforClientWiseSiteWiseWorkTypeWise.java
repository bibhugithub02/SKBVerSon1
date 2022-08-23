package com.example.skbnetwork;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class MyAdopterForListOfItemforClientWiseSiteWiseWorkTypeWise extends FirebaseRecyclerAdapter
        <ModelClientSiteWorkTypeItemSubItemQuantityMaster, MyAdopterForListOfItemforClientWiseSiteWiseWorkTypeWise.myViewHolder>
    {

        String action;

        /**
         * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
         * {@link FirebaseRecyclerOptions} for configuration options.
         *
         * @param options
         * @param menuName
         */
        public MyAdopterForListOfItemforClientWiseSiteWiseWorkTypeWise(@NonNull FirebaseRecyclerOptions<ModelClientSiteWorkTypeItemSubItemQuantityMaster> options, String menuName) {
            super(options);
            action = menuName;
        }

        @Override
        protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull ModelClientSiteWorkTypeItemSubItemQuantityMaster model) {

            holder.itemDetails.setText(model.getdMCSWTISIQMItemCategory()+model.getdMCSWTISIQMSubItem());

        }

        @NonNull
        @Override
        public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerowforlistofitemforclientwisesitewiseworktypewise,
                    parent, false);

            return new myViewHolder(view);
        }

        class myViewHolder extends RecyclerView.ViewHolder{

       TextView itemDetails;

       public myViewHolder(@NonNull View itemView) {
           super(itemView);

           itemDetails = itemView.findViewById(R.id.textView126);
       }
   }

}
