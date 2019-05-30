package com.ba.mobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class LaunchActivity extends AppCompatActivity {

    Button btnaccept;
    Button btnrefuse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        btnaccept = (Button)findViewById(R.id.accept)       ;
        btnrefuse = (Button)findViewById(R.id. btnrefuse)   ;

        btnrefuse.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v){
                Toast.makeText(getApplicationContext(),"You must accept the T&C for Book Ticket", Toast.LENGTH_LONG).show();
            }
            // Code Change
        });

        btnaccept.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v){
                Log.d("pCloudy ","Terms and Condition Accepted");
                Intent i = new Intent(getApplicationContext(),bookActivity.class);
                startActivity(i);

                /* Dummy code to demonstrate single stepping.*/
                int k = 1 ;
                k = k * 5 ;
                k++;

                // My New code Change
            }
        });
    }

}
