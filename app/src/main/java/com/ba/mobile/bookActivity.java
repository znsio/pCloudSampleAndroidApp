package com.ba.mobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class bookActivity extends AppCompatActivity {

    Button ecLoginButton;

    Button nonECLoginButton;

    Button flightButton;
    String test;

    int i = 10;
    // Commented code

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        
        ecLoginButton = (Button)findViewById(R.id.ecLoginButton);
        nonECLoginButton = (Button)findViewById(R.id.nonECLoginButton);
        flightButton = (Button)findViewById(R.id.flightButton);

        flightButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Log.d("PCL","Logined");
                Intent i = new Intent(getApplicationContext(),BookFlightActivity.class);
                startActivity(i);

            }
        });

        nonECLoginButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Log.d("PCL","Logined");
                Intent i = new Intent(getApplicationContext(),SingleBookingActivity.class);
                startActivity(i);
            }
        });


        ecLoginButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Log.d("PCL","Logined");
                Intent i = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(i);
            }
        });


    }
}
