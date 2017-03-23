package utils;

import android.drm.DrmManagerClient;
import android.widget.NumberPicker;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import app.smarttrolley.appforyou.smarttrolley.MainActivity;
import app.smarttrolley.appforyou.smarttrolley.OfferActivity;

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


    public void getProductListOfCategory(String category , final MainActivity activity){


        DatabaseReference myref =database.getReference("product");

        Query myref2= myref.orderByChild("productCategory").equalTo(category).limitToLast(3);

        myref2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                ArrayList<ProductDetail> productDetailArrayList = new ArrayList<ProductDetail>();
                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    ProductDetail productDetail  = ds.getValue(ProductDetail.class);
                    productDetail.setProductQuantity(0);
                    productDetailArrayList.add(productDetail);
                }

                activity.getProductListOfCategorylistner(productDetailArrayList);




            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }


    public void insertOffer(OfferDetail offerDetail ){
        DatabaseReference myRef = database.getReference("offer");
        myRef.push().setValue(offerDetail);


    }

    public void getOfferList(final OfferActivity offerActivity){
        DatabaseReference myRef = database.getReference("offer");
        Query myref2 =myRef.orderByKey().limitToLast(10);
        myref2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                ArrayList<OfferDetail> offerDetailArrayList =new ArrayList<OfferDetail>();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){


                    OfferDetail offerDetail = dataSnapshot1.getValue(OfferDetail.class);
                    offerDetailArrayList.add(offerDetail);
                }

                offerActivity.getOfferListListner(offerDetailArrayList);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

}
