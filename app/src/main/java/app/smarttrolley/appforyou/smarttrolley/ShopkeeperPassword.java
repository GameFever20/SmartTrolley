package app.smarttrolley.appforyou.smarttrolley;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ShopkeeperPassword extends AppCompatActivity {

    EditText pw_enter_shopkeeper_edittext;
    Button pw_enter_shopkeeper_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopkeeper_password);

        pw_enter_shopkeeper_edittext=(EditText)findViewById(R.id.pw_enter_shopkeeper_edittext);
        pw_enter_shopkeeper_button=(Button)findViewById(R.id.pw_enter_shopkeeper_button);
        pw_enter_shopkeeper_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String pw= pw_enter_shopkeeper_edittext.getText().toString();
                String savepw="123456";
                if(pw.equalsIgnoreCase(savepw)){
                    Toast.makeText(getApplicationContext(), "Logeed in", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), ShopkeeperActivity.class);
                    startActivity(intent);

                }
                else{
                    Toast.makeText(getApplicationContext(), "Enter Correct Password", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}
