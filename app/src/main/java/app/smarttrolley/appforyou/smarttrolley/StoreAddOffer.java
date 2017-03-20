package app.smarttrolley.appforyou.smarttrolley;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import utils.DatabaseHandler;
import utils.OfferDetail;

public class StoreAddOffer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_add_offer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                EditText editText =(EditText)findViewById(R.id.storeAddoffer_offerHeading_edittext);
                String heading = editText.getText().toString();
                editText =(EditText)findViewById(R.id.storeAddoffer_offersection_edittext);
                String section =editText.getText().toString();

                OfferDetail offerDetail =new OfferDetail();
                if(heading.length()>3){
                    offerDetail.setOfferHEading(heading);
                }else{
                    Toast.makeText(StoreAddOffer.this, "Offer Heading lenght is small", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(section.length()>2){
                    offerDetail.setOfferSection(section);
                }else{
                    Toast.makeText(StoreAddOffer.this, "Offer Section lenght is small", Toast.LENGTH_SHORT).show();
                    return;
                }

                DatabaseHandler db =new DatabaseHandler();
                db.insertOffer(offerDetail);


            }
        });


    }

}
