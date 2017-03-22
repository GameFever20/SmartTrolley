package app.smarttrolley.appforyou.smarttrolley;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.vision.text.Text;
import com.google.firebase.database.DataSnapshot;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import utils.DatabaseHandler;
import utils.ProductDetail;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private IntentIntegrator qrScan;
    private ListView mListView;
    private ProductListAdapter mProductListAdapter;
    private ArrayList<ProductDetail> productDetailArrayList = new ArrayList<>();


    private ListView mSuggestionListView;
    private ProductListAdapter mSuggestionProductListAdapter;
    private ArrayList<ProductDetail> productSuggestionArrayList = new ArrayList<>();

    TextView total_amount_textview;


    int billingAmount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_add_item);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                qrScan.initiateScan();
                Toast.makeText(MainActivity.this, "scanning", Toast.LENGTH_SHORT).show();

            }
        });


        FloatingActionButton fab_done_btn = (FloatingActionButton) findViewById(R.id.fab_done_item);
        fab_done_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), EbillPaymentActivity.class);
                startActivity(intent);

            }
        });

        total_amount_textview=(TextView)findViewById(R.id.main_total_amount_textview);
        total_amount_textview.setText(""+billingAmount);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        qrScan = new IntentIntegrator(MainActivity.this);


        Button btn = (Button) findViewById(R.id.mainqrbutton);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                qrScan.initiateScan();

            }
        });



        mListView = (ListView) findViewById(R.id.mainActivity_product_listView);
        mProductListAdapter = new ProductListAdapter(this, productDetailArrayList, this);
        mListView.setAdapter(mProductListAdapter);


        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                calculateBillAmount();

            }
        });

        View bottomSheet = findViewById(R.id.design_bottom_sheet);
        final BottomSheetBehavior behavior = BottomSheetBehavior.from(bottomSheet);


        mSuggestionListView = (ListView) findViewById(R.id.main_bottomSheet_suggestion_listView);
        mSuggestionProductListAdapter = new ProductListAdapter(this, productSuggestionArrayList, this);
        mSuggestionListView.setAdapter(mSuggestionProductListAdapter);
        mSuggestionListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (!checkProductIDInList(productSuggestionArrayList.get(i).getProductID(), productDetailArrayList)) {
                    productDetailArrayList.add(productSuggestionArrayList.get(i));
                    productSuggestionArrayList.remove(i);
                    mProductListAdapter.notifyDataSetChanged();
                    mSuggestionProductListAdapter.notifyDataSetChanged();
                }
            }
        });


    }


    //Getting the scan results
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            //if qrcode has nothing in it
            if (result.getContents() == null) {
                Toast.makeText(this, "Result Not Found", Toast.LENGTH_LONG).show();
            } else {
                //if qr contains data
                try {

                    String productID = result.getContents();
                    getproductDetail(productID);

                } catch (Exception e) {
                    e.printStackTrace();
                    //if control comes here
                    //that means the encoded format not matches
                    //in this case you can display whatever data is available on the qrcode
                    //to a toast
                    Toast.makeText(this, result.getContents(), Toast.LENGTH_LONG).show();

                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void getproductDetail(String productID) {
        if (!checkProductIDInList(productID, productDetailArrayList)) {
            DatabaseHandler databaseHandler = new DatabaseHandler();
            databaseHandler.getProductByID(productID, this);
        } else {
            Toast.makeText(this, "product Already added", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean checkProductIDInList(String productID, ArrayList<ProductDetail> productDetaillist) {

        for (ProductDetail productDetail : productDetaillist) {

            if (productDetail.getProductID().equalsIgnoreCase(productID)) {
                return true;
            }
        }

        return false;

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_Offers) {
            onStoreuserOfferClick();

        } else if (id == R.id.nav_shopkeeper_menu) {
            onShopkeeperActivityCall();

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void onStoreofferClick() {
        Intent intent = new Intent(this, StoreAddOffer.class);
        startActivity(intent);
    }

    private void onStoreuserOfferClick() {
        Intent intent = new Intent(this, OfferActivity.class);
        startActivity(intent);
    }

    private void onStoreClick() {

        Intent intent = new Intent(this, StoreAddItem.class);
        startActivity(intent);

    }


    public void getProductByIDListner(ProductDetail productDetail) {
        if (productDetail != null) {
            Toast.makeText(this, "product is " + productDetail.toString(), Toast.LENGTH_SHORT).show();
            /*show in ui
            * add price in bill amount
            * give product  suggestion */

            productDetail.setProductQuantity(1);
            productDetailArrayList.add(productDetail);
            mProductListAdapter.notifyDataSetChanged();
            calculateBillAmount();

            DatabaseHandler db = new DatabaseHandler();
            db.getProductListOfCategory(productDetail.getProductCategory(), this);
            total_amount_textview.setText(""+billingAmount);

        }
    }

    public void calculateBillAmount() {
        billingAmount = 0;
        for (ProductDetail productDetail : productDetailArrayList) {
            billingAmount = billingAmount + (productDetail.getProductPrice() * productDetail.getProductQuantity());
        }
        total_amount_textview.setText(""+billingAmount);


    }

    public void onShopkeeperActivityCall(){
        Intent intent = new Intent(this, ShopkeeperActivity.class);
        startActivity(intent);

    }

    public void getProductListOfCategorylistner(ArrayList<ProductDetail> productDetailArrayList) {

        for (ProductDetail pd : productDetailArrayList) {
            if (!checkProductIDInList(pd.getProductID(), productSuggestionArrayList)) {
                productSuggestionArrayList.add(0, pd);
            }
        }

        mSuggestionProductListAdapter.notifyDataSetChanged();

    }

    public void suggestionListItemClickListner(int position) {

        if (!checkProductIDInList(productSuggestionArrayList.get(position).getProductID(), productDetailArrayList)) {
            productSuggestionArrayList.get(position).setProductQuantity(1);
            productDetailArrayList.add(productSuggestionArrayList.get(position));
            productSuggestionArrayList.remove(position);
            mProductListAdapter.notifyDataSetChanged();
            mSuggestionProductListAdapter.notifyDataSetChanged();
        } else {
            Toast.makeText(this, "Already in cart", Toast.LENGTH_SHORT).show();
        }

    }
}
