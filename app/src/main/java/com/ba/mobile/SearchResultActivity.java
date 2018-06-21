package com.ba.mobile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SearchResultActivity extends AppCompatActivity {

   TextView txtTravel,txtfrom,txtto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        Bundle extras = getIntent().getExtras();

        String exampleString = extras.getString("identifier");

        txtTravel = (TextView)findViewById(R.id.txttravel);
        txtfrom = (TextView)findViewById(R.id.txtfrom);
        txtto = (TextView)findViewById(R.id.txtto);

        txtTravel.setText(extras.getString("Travel"));
        txtfrom.setText(extras.getString("From"));
        txtto.setText(extras.getString("To"));

    }
}
