package app.smarttrolley.appforyou.smarttrolley;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class EbillPaymentActivity extends AppCompatActivity {
    ListView ebill_listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ebill_payment);
        ebill_listview=(ListView)findViewById(R.id.ebill_listview);
    }

    public void proceedToPaymentFuction(View view){

        ProgressDialog progressDialog= new ProgressDialog(getApplicationContext());
        progressDialog.setMessage("Paying");
        progressDialog.setCancelable(false);
        progressDialog.setInverseBackgroundForced(false);
        progressDialog.show();

    }
}
