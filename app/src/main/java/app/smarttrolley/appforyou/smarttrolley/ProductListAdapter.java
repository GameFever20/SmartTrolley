package app.smarttrolley.appforyou.smarttrolley;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import utils.ProductDetail;

/**
 * Created by harsh on 19-03-2017.
 */

public class ProductListAdapter extends ArrayAdapter<ProductDetail>  {
    private final Context context;
    private final MainActivity mainActivity;
    private final ArrayList<ProductDetail> productDetailArrayList;


    public ProductListAdapter(Context context, ArrayList<ProductDetail> productDetails ,MainActivity mainActivity) {
        super(context, -1, productDetails);
        this.context = context;
        this.mainActivity =mainActivity;
        this.productDetailArrayList = productDetails;
    }




    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.product_list_row_layout, parent, false);

        TextView textView = (TextView) rowView.findViewById(R.id.productListRow_productName_textview);
        textView.setText(productDetailArrayList.get(position).getProductName());
        textView = (TextView) rowView.findViewById(R.id.productListRow_productBrand_textview);
        textView.setText(productDetailArrayList.get(position).getProductBrand());
        textView = (TextView) rowView.findViewById(R.id.productListRow_productWeight_textview);
        textView.setText(productDetailArrayList.get(position).getProductWeight() + "");
        textView = (TextView) rowView.findViewById(R.id.productListRow_productID_textview);
        textView.setText(productDetailArrayList.get(position).getProductID());

        textView = (TextView) rowView.findViewById(R.id.productListRow_productPrice_textview);
/*

        int price = productDetailArrayList.get(position).getProductPrice() * productDetailArrayList.get(position).getProductQuantity();
*/

        textView.setText(productDetailArrayList.get(position).getProductPrice() + "");

        final TextView quatitytextView = (TextView) rowView.findViewById(R.id.productListRow_productquantity_textView);
        quatitytextView.setText(productDetailArrayList.get(position).getProductQuantity() + "");

        ImageButton minusButton = (ImageButton) rowView.findViewById(R.id.productListRow_productquantityminus_button);
        minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int newquantity = Integer.valueOf(quatitytextView.getText().toString());
                if (newquantity == 1) {
                    productDetailArrayList.remove(position);
                    notifyDataSetChanged();
                } else if (newquantity > 1) {
                    newquantity--;
                    quatitytextView.setText(String.valueOf(newquantity));
                    productDetailArrayList.get(position).setProductQuantity(newquantity);
                    notifyDataSetChanged();
                }

            }
        });


if( productDetailArrayList.get(position).getProductQuantity() <1) {

    quatitytextView.setVisibility(View.GONE);
    minusButton.setVisibility(View.GONE);


}else{



}


        ImageButton addButton = (ImageButton) rowView.findViewById(R.id.productListRow_productquantityadd_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int newquantity = Integer.valueOf(quatitytextView.getText().toString());
                if (newquantity == 10) {
                    Toast.makeText(context, "Item exceed maximum limit", Toast.LENGTH_SHORT).show();
                }else if(newquantity ==0){

                    mainActivity.suggestionListItemClickListner(position);
                    return;
                }
                else  {
                    newquantity++;
                }

                quatitytextView.setText(String.valueOf(newquantity));
                productDetailArrayList.get(position).setProductQuantity(newquantity);
                notifyDataSetChanged();

            }
        });





        return rowView;
    }
}


