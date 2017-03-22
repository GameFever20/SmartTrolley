package app.smarttrolley.appforyou.smarttrolley;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ShopkeeperActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopkeeper);
    }

    public void shopkeeperAddMoreItemFunction(View view){
        Intent intent = new Intent(this, StoreAddItem.class);
        startActivity(intent);

    }

    public void shopkeeperAddMoreOfferFunction(View view){
        Intent intent = new Intent(this, StoreAddOffer.class);
        startActivity(intent);
    }
}
