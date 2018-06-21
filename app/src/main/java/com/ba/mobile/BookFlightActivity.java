package com.ba.mobile;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import org.w3c.dom.Text;

public class BookFlightActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

   Button searchFlights;

   int mYear, mMonth, mDay, mHour, mMinute;
   String ddates = new String();
   RadioButton radioButtonretun;
   RadioButton radioButtononeway;
   TextView txtdepart, txtreturn;

   int txtflag = 0;

    String[] fromair={"--Select-- ","Aalborg,Denmark","Bangalore, India (BLR)","Pune, India (PNQ)", "Hydrabad, India(HYD)", "Dubai, UAE (DXB)","New Delhi, India(DEL)","Mumbai, India(BOM)","LONDON, GB (LHR)","CHICAGO IL, US (ORD)", "LOS ANGELES CA, US (LAX)", "PARIS, FR (CDG)","TOKYO, JP (HND)", "HONG KONG, HK (HKG)","HONG KONG, HK (HKG)" };

    String[] toair={"--Select-- ","Aalborg,Denmark","Bangalore, India (BLR)","Pune, India (PNQ)", "Hydrabad, India(HYD)", "Dubai, UAE (DXB)","New Delhi, India(DEL)", "LONDON, GB (LHR)","CHICAGO IL, US (ORD)", "LOS ANGELES CA, US (LAX)", "PARIS, FR (CDG)","TOKYO, JP (HND)", "HONG KONG, HK (HKG)","HONG KONG, HK (HKG)" };

    String[] noofadults={"0","1","2","3","4","5","6","7","8","9","10"};
    String[] noochild={"0","1","2","3","4","5","6"};
    String[] nooinfants={"0","1","2","3"};
    String[] flightclass={"Economy","Premium Economy","Business","First"};
    String[] tickettype={"Lowest","Flexible Conditions"};

    Calendar myCalendar = Calendar.getInstance();
    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_flight);

        final Spinner spinfrom = (Spinner) findViewById(R.id.spinnerfrom);
        final Spinner spinto = (Spinner) findViewById(R.id.spinnerto);
        final Spinner spinflight = (Spinner) findViewById(R.id.spinnerflight);
        Spinner spinnerticket = (Spinner)findViewById(R.id.spinnerticket);

        Spinner spinnernoa = (Spinner)findViewById(R.id.no_adults);
        Spinner spinnernoc = (Spinner)findViewById(R.id.no_children);
        Spinner spinnernoi = (Spinner)findViewById(R.id.no_infants);

        radioButtononeway = (RadioButton)findViewById(R.id.singleTrip);
        radioButtonretun = (RadioButton)findViewById(R.id.returnTrip);

        txtdepart = (TextView) findViewById(R.id.txtdepart);
        txtreturn = (TextView) findViewById(R.id.txtreturn);



        txtdepart.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                txtflag = 1;
                new DatePickerDialog(BookFlightActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();

            }
        });


        txtreturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtflag = 0;
                new DatePickerDialog(BookFlightActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        radioButtononeway.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioButtonretun.setChecked(false);
                txtreturn.setVisibility(View.INVISIBLE);
            }
        });

        radioButtonretun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtreturn.setVisibility(View.VISIBLE);
                radioButtononeway.setSelected(false);
                radioButtononeway.setChecked(false);
            }
        });


        spinnernoa.setOnItemSelectedListener(this);
        spinnernoc.setOnItemSelectedListener(this);
        spinnernoi.setOnItemSelectedListener(this);

        spinfrom.setOnItemSelectedListener(this);
        spinto.setOnItemSelectedListener(this);
        spinflight.setOnItemSelectedListener(this);
        spinnerticket.setOnItemSelectedListener(this);

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aaspinfrom = new ArrayAdapter(this,android.R.layout.simple_spinner_item,fromair);
        aaspinfrom.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter aaspinto = new ArrayAdapter(this,android.R.layout.simple_spinner_item,toair);
        aaspinfrom.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        ArrayAdapter aaflight = new ArrayAdapter(this,android.R.layout.simple_spinner_item,flightclass);
        aaflight.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter aaticket = new ArrayAdapter(this,android.R.layout.simple_spinner_item,tickettype);
        aaticket.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter aanoa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,noofadults);
        aanoa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter aanoc = new ArrayAdapter(this,android.R.layout.simple_spinner_item,noochild);
        aanoc.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter aanoi = new ArrayAdapter(this,android.R.layout.simple_spinner_item,nooinfants);
        aanoi.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //Setting the ArrayAdapter data on the Spinner

        spinfrom.setAdapter(aaspinfrom);
        spinto.setAdapter(aaspinfrom);
        spinflight.setAdapter(aaflight);
        spinnerticket.setAdapter(aaticket);
        spinnernoa.setAdapter(aanoa);
        spinnernoc.setAdapter(aanoc);
        spinnernoi.setAdapter(aanoi);

        searchFlights = (Button)findViewById(R.id.searchFlights);
        searchFlights.setOnClickListener(new View.OnClickListener() {

    @Override
    public void onClick(View v) {

        String text = spinflight.getSelectedItem().toString();

        Intent i = new Intent(getApplicationContext(),SearchResultActivity.class)
                .putExtra("Travel", txtdepart.getText().toString())
                .putExtra("From",spinfrom.getSelectedItem().toString())
                .putExtra("To",spinto.getSelectedItem().toString());
        startActivity(i);
    }
    });
    }

    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position,long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
    }

    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        if (txtflag == 1){
            txtdepart.setText(sdf.format(myCalendar.getTime()));
        }else{
            txtreturn.setText(sdf.format(myCalendar.getTime()));
        }
    }
}
