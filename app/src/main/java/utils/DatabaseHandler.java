package utils;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import app.smarttrolley.appforyou.smarttrolley.MainActivity;

/**
 * Created by harsh on 19-03-2017.
 */

public class DatabaseHandler {
    FirebaseDatabase database;
    public DatabaseHandler() {
         database = FirebaseDatabase.getInstance();

    }

    public void getProductByID(String productID , final MainActivity activity){
        DatabaseReference myRef = database.getReference("product/"+productID);
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot!=null) {
                    ProductDetail productDetail = dataSnapshot.getValue(ProductDetail.class);
                    activity.getProductByIDListner(productDetail);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }

    public void insertProduct(ProductDetail productDetail ){

        DatabaseReference myRef = database.getReference("product/"+productDetail.getProductID());


        myRef.setValue(productDetail);

    }

    public void getProductList(){

    }

    public void getproductSuggestion(){

    }

}
