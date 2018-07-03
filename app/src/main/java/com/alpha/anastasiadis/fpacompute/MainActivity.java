package com.alpha.anastasiadis.fpacompute;

import android.app.Activity;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends Activity implements AdapterView.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText inputValue;
        final EditText inputFPA; final EditText receipt;
        final TextView res,resLbl;



        Button resultBtn;

        inputValue = (EditText) findViewById(R.id.inputVal);
        inputFPA = (EditText) findViewById(R.id.inputFP);
        receipt=(EditText)findViewById(R.id.receipt);

        res = (TextView) findViewById(R.id.result);
        resLbl = (TextView) findViewById(R.id.resultLbl);
        resLbl.setVisibility(View.GONE);
        res.setTextColor(Color.RED);

        resultBtn = (Button) findViewById(R.id.result_button);


        //get the spinner from the xml.
        //  Spinner dropdown = findViewById(R.id.spinner1);
//create a list of items for the spinner.
        String[] items = new String[]{"24", "18", "12"};
//create an adapter to describe how the items are displayed, adapters are used in several places in android.
//There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
//set the spinners adapter to the previously created one.
        //  dropdown.setAdapter(adapter);
        //  dropdown.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);

        resultBtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                String percentage="0.0%",language;
                Double arxiki_timi=0.0,fpa_synt,axia_apodeixis=0.0,result,temp,temp1,temp3;
                language=Locale.getDefault().getLanguage();

                if (inputValue.getText() != null && !TextUtils.isEmpty(inputValue.getText()) && !inputValue.getText().equals("null"))
                    arxiki_timi=Double.parseDouble(inputValue.getText().toString());
                fpa_synt=Double.parseDouble(inputFPA.getText().toString());
                if (receipt.getText() != null && !TextUtils.isEmpty(receipt.getText()) && !receipt.getText().equals("null"))
                    axia_apodeixis=Double.parseDouble(receipt.getText().toString());

                resLbl.setVisibility(View.VISIBLE);

                //calculate % of reduction
                temp3=arxiki_timi-axia_apodeixis;
                temp3=(temp3*100)/arxiki_timi;
                percentage=percentage;
                if (arxiki_timi != 0  ){

                    percentage=String.format("%.1f", temp3)+" %";}
                //end calculate
                                    if (language.equals("en"))
                                        percentage="After the reduction:"+percentage;
                                    else
                                        percentage="Μετά τη μείωση: "+percentage;
                resLbl.setText(percentage);


                fpa_synt=fpa_synt/100;
                temp=arxiki_timi-axia_apodeixis;
                temp1=1+fpa_synt;
                result=temp/temp1+axia_apodeixis;
                String str=" = "+String.format("%.1f", result);
                res.setText(str);
            }
        });
    }
    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {

        switch (position) {
            case 0:
                Toast.makeText(this.getBaseContext(),"option 18",
                        Toast.LENGTH_SHORT).show();
                break;
            case 1:
                // Whatever you want to happen when the second item gets selected
                break;
            case 2:
                // Whatever you want to happen when the thrid item gets selected
                break;

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}