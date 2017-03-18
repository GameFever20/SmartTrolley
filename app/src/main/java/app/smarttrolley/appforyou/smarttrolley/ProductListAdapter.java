package app.smarttrolley.appforyou.smarttrolley;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import utils.ProductDetail;

/**
 * Created by harsh on 19-03-2017.
 */

public class ProductListAdapter extends ArrayAdapter<ProductDetail> {
    private final Context context;
    private final ArrayList<ProductDetail> productDetailArrayList;


    public ProductListAdapter(Context context, ArrayList<ProductDetail> productDetails) {
        super(context, -1, productDetails);
        this.context = context;
        this.productDetailArrayList = productDetails;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.product_list_row_layout, parent, false);

        TextView textView = (TextView) rowView.findViewById(R.id.productListRow_productName_textview);
        textView.setText(productDetailArrayList.get(position).getProductName());
         textView = (TextView) rowView.findViewById(R.id.productListRow_productBrand_textview);
        textView.setText(productDetailArrayList.get(position).getProductBrand());
         textView = (TextView) rowView.findViewById(R.id.productListRow_productcategory_textview);
        textView.setText(productDetailArrayList.get(position).getProductCategory());
         textView = (TextView) rowView.findViewById(R.id.productListRow_productWeight_textview);
        textView.setText(productDetailArrayList.get(position).getProductWeight());
         textView = (TextView) rowView.findViewById(R.id.productListRow_productExpiryDate_textview);
        textView.setText(productDetailArrayList.get(position).getProductExpiryDate());
         textView = (TextView) rowView.findViewById(R.id.productListRow_productID_textview);
        textView.setText(productDetailArrayList.get(position).getProductID());
         textView = (TextView) rowView.findViewById(R.id.productListRow_productPrice_textview);
        textView.setText(productDetailArrayList.get(position).getProductPrice());




        return rowView;
    }
}


