package com.rajendra.onlinedailygroceries;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ProductListAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<Productseller>productsellerslist;

    public ProductListAdapter(Context context, int layout, ArrayList<Productseller>productsellerslist){
        this.context=context;
        this.layout=layout;
        this.productsellerslist=productsellerslist;
    }
    @Override
    public int getCount() {
        return productsellerslist.size();
    }

    @Override
    public Object getItem(int position) {
        return productsellerslist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class  ViewHolder{
        ImageView imageView;
        TextView txtName,txtPrice,txtdesc;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

         View row=view;
         ViewHolder holder= new ViewHolder();
         if (row==null){
             LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
             row= inflater.inflate(layout,null);

             holder.txtName=row.findViewById(R.id.textView_name);
             holder.txtPrice=row.findViewById(R.id.textView_price);
             holder.imageView=row.findViewById(R.id.imageProduct);
             holder.txtdesc=row.findViewById(R.id.textView_desc);
             row.setTag(holder);
         }
         else {
             holder=(ViewHolder) row.getTag();
         }
         Productseller productseller=productsellerslist.get(position);
         holder.txtName.setText(productseller.getName());
         holder.txtPrice.setText(productseller.getPrice().toString());
         holder.txtdesc.setText(productseller.getDescription());

        byte[] productImage=productseller.getImage();
        Bitmap bitmap= BitmapFactory.decodeByteArray(productImage,0,productImage.length);
        holder.imageView.setImageBitmap(bitmap);
        return row;
    }
}
