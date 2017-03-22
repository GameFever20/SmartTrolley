package app.smarttrolley.appforyou.smarttrolley;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import utils.OfferDetail;
import utils.ProductDetail;

/**
 * Created by harsh on 21-03-2017.
 */

public class OfferListAdapter extends ArrayAdapter<OfferDetail> {


    private final Context context;

    private final ArrayList<OfferDetail> offerDetailsArrayList;

    public OfferListAdapter(Context context, ArrayList<OfferDetail> offerDetailArrayList) {
        super(context,0, offerDetailArrayList);
        this.context = context;
        this.offerDetailsArrayList =offerDetailArrayList;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.offer_list_adapter_row, parent, false);






        return rowView;
    }
}
