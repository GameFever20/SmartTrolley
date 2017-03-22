package app.smarttrolley.appforyou.smarttrolley;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import utils.ProductDetail;

import static app.smarttrolley.appforyou.smarttrolley.MainActivity.productDetailArrayList;

public class EbillPaymentActivity extends AppCompatActivity {
    ListView ebill_listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ebill_payment);
        ebill_listview = (ListView) findViewById(R.id.ebill_listview);
        EbillListAdapter ebillListAdapter = new EbillListAdapter(this, 0, MainActivity.productDetailArrayList);

        ebill_listview.setAdapter(ebillListAdapter);
        showTotalAmount();
        showTotalweight();


    }

    private void showTotalAmount() {
        int billingAmount=0;
        for (ProductDetail productDetail : MainActivity.productDetailArrayList) {
             billingAmount = billingAmount + (productDetail.getProductPrice() * productDetail.getProductQuantity());
        }

        TextView textView = (TextView)findViewById( R.id.ebill_total_amount);
        textView.setText("Total amount = "+billingAmount +"Rs");


    }

    private void showTotalweight() {
        int billingweight=0;
        for (ProductDetail productDetail : MainActivity.productDetailArrayList) {
            billingweight = billingweight + (productDetail.getProductWeight() * productDetail.getProductQuantity());
        }


        TextView textView = (TextView)findViewById( R.id.ebill_total_weight);
        if(billingweight/1000  >0) {
            textView.setText("Total weight = " + (billingweight/1000f) +"Kg");
        }else if(billingweight/1000==0){
            textView.setText("Total weight = " + billingweight +"g");
        }

    }

    public void proceedToPaymentFuction(View view) {

        Intent intent =new Intent(this ,ThankYouActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
