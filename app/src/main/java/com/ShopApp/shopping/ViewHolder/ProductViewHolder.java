package com.ShopApp.shopping.ViewHolder;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.ShopApp.shopping.Interface.ItemClickListner;
import com.ShopApp.shopping.R;

public class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView txtProductName, txtProductDescription, txtProductPrice;
    public ImageView imageView;
    public ItemClickListner listner;

///////////////////////////
    public ImageView imageView2;
    public TextView textView2;
    public TextView textView2_description;
    public TextView textView2_price;

    public View v;


    public ProductViewHolder(View itemView)
    {
        super(itemView);

        ///////////////////////
        imageView2 = itemView.findViewById(R.id.image_single_view);
        textView2 = itemView.findViewById(R.id.textView_single_view);
        textView2_description = itemView.findViewById(R.id.textView_single_view2);
        textView2_price = itemView.findViewById(R.id.textView_single_view3);

        v = itemView;

        //////////////////////
        imageView = (ImageView) itemView.findViewById(R.id.product_image);
        txtProductName = (TextView) itemView.findViewById(R.id.product_name);
        txtProductDescription = (TextView) itemView.findViewById(R.id.product_description);
        txtProductPrice = (TextView) itemView.findViewById(R.id.product_price);
    }

    public void setItemClickListner(ItemClickListner listner)
    {
        this.listner = listner;
    }

    @Override
    public void onClick(View view)
    {
        listner.onClick(view, getAdapterPosition(), false);
    }
}

