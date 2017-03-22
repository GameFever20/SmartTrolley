package app.smarttrolley.appforyou.smarttrolley;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import utils.DatabaseHandler;
import utils.OfferDetail;

public class OfferActivity extends AppCompatActivity {

    ArrayList<OfferDetail> offerDetailArrayList = new ArrayList<>();
    OfferListAdapter offerListAdapter;
    ListView offerListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DatabaseHandler db =new DatabaseHandler();
        db.getOfferList(this);

    }

    public void getOfferListListner(ArrayList<OfferDetail> offerDetailArrayList) {
        this.offerDetailArrayList =offerDetailArrayList;
        Toast.makeText(this, "Name of offerlist"+offerDetailArrayList.get(4).toString(), Toast.LENGTH_SHORT).show();

        offerListView =(ListView) findViewById(R.id.offerActivity_offer_listView);
        offerListAdapter =new OfferListAdapter(this ,this.offerDetailArrayList);
        offerListView.setAdapter(offerListAdapter);
    }

}
