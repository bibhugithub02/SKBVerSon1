package com.example.skbnetwork;


import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyAdopterForitemmasterrecyclerviewsinglerow extends FirebaseRecyclerAdapter
        <ModelItemMaster, MyAdopterForitemmasterrecyclerviewsinglerow.myViewHolder>

{

    String temp01;
    String menuOption;
    String clientName;
    String siteName;
    String workType;
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     * @param menuName
     */
    public MyAdopterForitemmasterrecyclerviewsinglerow(@NonNull FirebaseRecyclerOptions<ModelItemMaster>
                                                               options, String menuName) {
        super(options);
        // Parameter used when called from Site Menu Option
        //Site menu then Choose the Site then Choose the Item Category and then Item Sub Category to add the Qty
        this.menuOption = menuName;
    }

    public MyAdopterForitemmasterrecyclerviewsinglerow(@NonNull FirebaseRecyclerOptions<ModelItemMaster> options,
                                                       String menuName, String clientName, String siteName, String workType) {
        super(options);
        this.menuOption = menuName;
        this.clientName = clientName;
        this.siteName = siteName;
        this.workType = workType;

    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull ModelItemMaster model) {

        if (menuOption.equals("sitemenu")){
            holder.itemCategory.setText("IC:" + model.getdMIMItemName());
            holder.imageUrl.setText(model.getdMIMItemUrl());
            Glide.with(holder.imageView.getContext()).load(model.getdMIMItemUrl()).into(holder.imageView);
            temp01 = "IC:" + model.getdMIMItemName();
        }else{
            holder.itemCategory.setText("IC:" + model.getdMIMItemName());
            holder.imageUrl.setText(model.getdMIMItemUrl());
            Glide.with(holder.imageView.getContext()).load(model.getdMIMItemUrl()).into(holder.imageView);
            temp01 = "IC:" + model.getdMIMItemName();
        }

    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.singlerowforitemmasterrecyclerview, parent, false);

        return new myViewHolder(view);

    }

    class myViewHolder extends RecyclerView.ViewHolder{

        CircleImageView imageView;
        TextView itemCategory, addItems, viewItems, imageUrl;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

        itemCategory = itemView.findViewById(R.id.textView9);
        addItems = itemView.findViewById(R.id.textView10);
        viewItems = itemView.findViewById(R.id.textView11);
        imageUrl = itemView.findViewById(R.id.textView12);
        imageView = itemView.findViewById(R.id.imageView2);

        if (menuOption.equals("headoffice")){

            addItems.setVisibility(View.VISIBLE);

        }else if (menuOption.equals("sitemenu")){

            viewItems.setVisibility(View.VISIBLE);

        }


        viewItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    Intent i = new Intent(addItems.getContext(), addSubCategoryItems.class);
                    i.putExtra("url",imageUrl.getText().toString().trim());
                    i.putExtra("category",itemCategory.getText().toString().trim());
                    i.putExtra("menuoption",menuOption);
                    i.putExtra("clientname",clientName);
                    i.putExtra("sitename",siteName);
                    i.putExtra("worktype",workType);

                    addItems.getContext().startActivity(i);
            }
        });

        addItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(addItems.getContext(), "Add button working " +
                        itemCategory.getText().toString(), Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(addItems.getContext(), addSubCategoryItems.class);
                    i.putExtra("url",imageUrl.getText().toString().trim());
                    i.putExtra("category",itemCategory.getText().toString().trim());
                    i.putExtra("menuoption",menuOption);
                    i.putExtra("clientname",clientName);
                    i.putExtra("sitename",siteName);
                    i.putExtra("worktype",workType);
                    addItems.getContext().startActivity(i);

            }
        });

        }
    }
}
