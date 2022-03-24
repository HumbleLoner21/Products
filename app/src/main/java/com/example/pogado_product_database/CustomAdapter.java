package com.example.pogado_product_database;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList product_id, product_name, product_desc, product_price, product_quantity;


    CustomAdapter(Activity activity, Context context, ArrayList product_id, ArrayList product_name, ArrayList product_desc, ArrayList product_price, ArrayList product_quantity) {
        this.activity = activity;
        this.context = context;
        this.product_id = product_id;
        this.product_name = product_name;
        this.product_desc = product_desc;
        this.product_price = product_price;
        this.product_quantity = product_quantity;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.activity_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {

        holder.product_id.setText(String.valueOf(product_id.get(position)));
        holder.product_name.setText(String.valueOf(product_name.get(position)));
        holder.product_desc.setText(String.valueOf(product_desc.get(position)));
        holder.product_price.setText(String.valueOf(product_price.get(position)));
        holder.product_quantity.setText(String.valueOf(product_quantity.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Update_Activity.class);
                intent.putExtra("id", String.valueOf(product_id.get(position)));
                intent.putExtra("name", String.valueOf(product_name.get(position)));
                intent.putExtra("desc", String.valueOf(product_desc.get(position)));
                intent.putExtra("price", String.valueOf(product_price.get(position)));
                intent.putExtra("quantity", String.valueOf(product_quantity.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return product_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView product_id, product_name, product_desc, product_price, product_quantity;
        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            product_id = itemView.findViewById(R.id.product_id);
            product_name = itemView.findViewById(R.id.product_name);
            product_desc = itemView.findViewById(R.id.product_desc);
            product_price = itemView.findViewById(R.id.product_price);
            product_quantity = itemView.findViewById(R.id.product_quantity);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
