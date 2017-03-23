package app.smarttrolley.appforyou.smarttrolley;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import utils.ProductDetail;

/**
 * Created by harsh on 23-03-2017.
 */

public class EbillListAdapter extends ArrayAdapter<ProductDetail> {

    ArrayList<ProductDetail> productDetailArrayList;
    Context context;

    public EbillListAdapter(Context context, int resource, ArrayList<ProductDetail> productDetailArrayList) {
        super(context, resource ,productDetailArrayList);
        this.context = context;
        this.productDetailArrayList = productDetailArrayList;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.ebillpayment_list_row_layout, parent, false);

        TextView nameTextView = (TextView)rowView.findViewById(R.id.ebill_row_name_textview);
        nameTextView.setText(productDetailArrayList.get(position).getProductName());

        nameTextView = (TextView)rowView.findViewById(R.id.ebill_row_price_textview);
        nameTextView.setText(productDetailArrayList.get(position).getProductPrice()+"");

        nameTextView = (TextView)rowView.findViewById(R.id.ebill_row_weight_textview);
        nameTextView.setText(productDetailArrayList.get(position).getProductWeight()+"");

        nameTextView=(TextView)rowView.findViewById(R.id.ebill_row_quantity_textview);
        nameTextView.setText(productDetailArrayList.get(position).getProductQuantity()+"");





        return rowView;
    }
}
