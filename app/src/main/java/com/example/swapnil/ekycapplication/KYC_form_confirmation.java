package com.example.swapnil.ekycapplication;

import android.content.Context;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;


import android.net.ConnectivityManager;
import android.os.Bundle;

import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.security.Signature;
import java.util.ArrayList;
import java.util.List;

public class KYC_form_confirmation extends AppCompatActivity {
    Intent formContentIntent;
    String formDataString;
    Spinner customerTypSpinner,residencySpinner,idtypespinner;
    AutoCompleteTextView nationsList;
    Button NextBtn;
    TextView clearTxt;
    RadioButton yespep,nopep;
    ProgressBar progressBar;
    String completeFormData;
    TextView countryList,countryofbirthlist;
    EditText firstname_et,lastname_et,dnum1_et,dnum2_et,dnum3_et,dnum4_et,dnum5_et,dnum6_et,dnum7_et,dnum8_et,eidnumber_et,eidaddress1_et,eidaddress2_et,eidaddress3_et
            ,profession_et,employer_et,expectedvolume_et,expectedva_et,mobilenumber_et,email_et;
    String customertyeString,firstname_string,lastname_string,dnum1_string,dnum2_string,dnum3_string,dnum4_string,dnum5_string,dnum6_string,dnum7_string,dnum8_string,
            nationallityString,ReisdentType,eidnumber_string,eid_addressstring1,eid_addressstring2,eid_addressstring3,pepflag_string,profession_string,
            countryofbirth_string,expectedvolume_string,expectedva_string,mobilenum_string,email_string,employer_String;
    JSONObject jsonObject;
    FirebaseDatabase database;
    DatabaseReference myRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kyc_form_confirmation);
        formContentIntent=getIntent();
        formDataString=formContentIntent.getExtras().getString("formdata");

        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        if(CheckInternet.isNetworkAvailable(connectivityManager)){
            customerTypSpinner = (Spinner) findViewById(R.id.customertype);
            // residencySpinner = (Spinner) findViewById(R.id.residencystatus);
            //idtypespinner = (Spinner) findViewById(R.id.idtype);
            nationsList =  (AutoCompleteTextView)findViewById(R.id.nationallity);
            progressBar=(ProgressBar)findViewById(R.id.progressBar);
            countryList =  (TextView)findViewById(R.id.country);
            clearTxt=(TextView)findViewById(R.id.cleartxt);
            firstname_et=(EditText)findViewById(R.id.c_firstname);
            lastname_et=(EditText)findViewById(R.id.c_lastname);
            dnum1_et=(EditText)findViewById(R.id.num1);
            dnum2_et=(EditText)findViewById(R.id.num2);
            dnum3_et=(EditText)findViewById(R.id.num3);
            dnum4_et=(EditText)findViewById(R.id.num4);
            dnum5_et=(EditText)findViewById(R.id.num5);
            dnum6_et=(EditText)findViewById(R.id.num6);
            dnum7_et=(EditText)findViewById(R.id.num7);
            dnum8_et=(EditText)findViewById(R.id.num8);
            profession_et=(EditText)findViewById(R.id.c_profession);
            eidnumber_et=(EditText)findViewById(R.id.eidnumber);
            eidaddress1_et=(EditText)findViewById(R.id.c_addressone);
            eidaddress2_et=(EditText)findViewById(R.id.c_addresstwo);
            eidaddress3_et=(EditText)findViewById(R.id.c_addressthree);
            yespep=(RadioButton)findViewById(R.id.pepyes);
            nopep=(RadioButton)findViewById(R.id.pepno);
            employer_et=(EditText)findViewById(R.id.c_employername);
            countryofbirthlist=(TextView)findViewById(R.id.countryofbirth);
            expectedvolume_et=(EditText)findViewById(R.id.c_expectedannualactivity);
            expectedva_et=(EditText)findViewById(R.id.c_expectedannualactivityval);

            mobilenumber_et=(EditText)findViewById(R.id.mobileno) ;
            email_et=(EditText)findViewById(R.id.c_emailid);
            employer_et=(EditText)findViewById(R.id.c_employername);

            NextBtn=(Button)findViewById(R.id.gotsuccess);

        /*
        This must be changed

         */
            database = FirebaseDatabase.getInstance();
            try {
                jsonObject=new JSONObject(formDataString);
                eidnumber_string=jsonObject.getString("eidnumber");
                myRef = database.getReference("todaysdata").child(eidnumber_string);
                setdata();
            } catch (JSONException e) {
                e.printStackTrace();
            }




            NextBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    progressBar.setVisibility(View.VISIBLE);
                    validatedata();

                }
            });

            ///customertype Spinner Inflater
            List<String> spinnerArray =  new ArrayList<String>();
            spinnerArray.add("Individual");
            spinnerArray.add("Corporate");

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                    this, android.R.layout.simple_spinner_item, spinnerArray);

            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            customerTypSpinner.setAdapter(adapter);
            String[] Country_list ={"United Arab Emirates","India","Pakistan","Bangladesh","Phillipines","Nepal","Egypt","Others"};
            ArrayAdapter<String> countryadapter = new ArrayAdapter<String>
                    (this,android.R.layout.select_dialog_item,Country_list);


            nationsList.setThreshold(1);
            nationsList.setAdapter(countryadapter);
            ////////

            dnum1_et.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {
                    dnum2_et.requestFocus();
                }
            });

            dnum2_et.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {
                    dnum3_et.requestFocus();
                }
            });

            dnum3_et.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {
                    dnum4_et.requestFocus();
                }
            });

            dnum4_et.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {
                    dnum5_et.requestFocus();
                }
            });

            dnum5_et.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {
                    dnum6_et.requestFocus();
                }
            });

            dnum6_et.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {
                    dnum7_et.requestFocus();
                }
            });

            dnum7_et.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {
                    dnum8_et.requestFocus();
                }
            });

            dnum8_et.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });
            Toast.makeText(this, formDataString, Toast.LENGTH_SHORT).show();
        }else{
            Intent gotologinscreen=new Intent(KYC_form_confirmation.this,NoInternet.class);
            startActivity(gotologinscreen);
        }



    }

    public void validatedata(){
        if(firstname_et.getText().toString().trim().equals("")){
            progressBar.setVisibility(View.INVISIBLE);
            firstname_et.setError("Field cant be left blank");
            Toast.makeText(this, "Some field cant be left blank", Toast.LENGTH_SHORT).show();
        }else if(lastname_et.getText().toString().trim().equals("")){
            progressBar.setVisibility(View.INVISIBLE);
            lastname_et.setError("Field cant be left blank");
            Toast.makeText(this, "Some field cant be left blank", Toast.LENGTH_SHORT).show();
        }else if(nationsList.getText().toString().trim().equals("")){
            progressBar.setVisibility(View.INVISIBLE);
            nationsList.setError("Field cant be left blank");
            Toast.makeText(this, "Some field cant be left blank", Toast.LENGTH_SHORT).show();
        }else if(mobilenumber_et.getText().toString().trim().equals("")){
            progressBar.setVisibility(View.INVISIBLE);
            mobilenumber_et.setError("Field cant be left blank");
            Toast.makeText(this, "Some field cant be left blank", Toast.LENGTH_SHORT).show();
        }else if(eidnumber_et.getText().toString().trim().equals("")){
            progressBar.setVisibility(View.INVISIBLE);
            eidnumber_et.setError("Field cant be left blank");
            Toast.makeText(this, "Some field cant be left blank", Toast.LENGTH_SHORT).show();
        }else if(eidaddress1_et.getText().toString().trim().equals("")){
            progressBar.setVisibility(View.INVISIBLE);
            eidaddress1_et.setError("Field cant be left blank");
            Toast.makeText(this, "Some field cant be left blank", Toast.LENGTH_SHORT).show();
        }else if(profession_et.getText().toString().trim().equals("")){
            progressBar.setVisibility(View.INVISIBLE);
            profession_et.setError("Field cant be left blank");
            Toast.makeText(this, "Some field cant be left blank", Toast.LENGTH_SHORT).show();
        }else if(employer_et.getText().toString().trim().equals("")){
            progressBar.setVisibility(View.INVISIBLE);
            employer_et.setError("Field cant be left blank");
            Toast.makeText(this, "Some field cant be left blank", Toast.LENGTH_SHORT).show();
        }else if(expectedvolume_et.getText().toString().trim().equals("")){
            progressBar.setVisibility(View.INVISIBLE);
            expectedvolume_et.setError("Field cant be left blank");
            Toast.makeText(this, "Some field cant be left blank", Toast.LENGTH_SHORT).show();
        }else if(expectedva_et.getText().toString().trim().equals("")){
            progressBar.setVisibility(View.INVISIBLE);
            expectedva_et.setError("Field cant be left blank");
            Toast.makeText(this, "Some field cant be left blank", Toast.LENGTH_SHORT).show();
        }else if(!yespep.isChecked() && !nopep.isChecked()){
            progressBar.setVisibility(View.INVISIBLE);
            Toast.makeText(this, "Please select at least one option for PEP", Toast.LENGTH_SHORT).show();
        }else{
            myRef.setValue(formDataString);
            Intent gotosignature=new Intent(KYC_form_confirmation.this, Getsignature.class);
            gotosignature.putExtra("eidnumber",eidnumber_string);
            startActivity(gotosignature);
        }


    }

    public void setdata(){

        try {

            firstname_et.setText(jsonObject.getString("firstname"));
            lastname_et.setText(jsonObject.getString("lastname"));


            profession_et.setText(jsonObject.getString("profession"));

            dnum1_et.setText(jsonObject.getString("dnum1"));
            dnum2_et.setText(jsonObject.getString("dnum2"));
            dnum3_et.setText(jsonObject.getString("dnum3"));
            dnum4_et.setText(jsonObject.getString("dnum4"));
            dnum5_et.setText(jsonObject.getString("dnum5"));
            dnum6_et.setText(jsonObject.getString("dnum6"));
            dnum7_et.setText(jsonObject.getString("dnum7"));
            dnum8_et.setText(jsonObject.getString("dnum8"));
            nationsList.setText(jsonObject.getString("nationallity"));
            mobilenumber_et.setText(jsonObject.getString("mobilenumber"));
            email_et.setText(jsonObject.getString("email"));
            eidnumber_et.setText(jsonObject.getString("eidnumber"));
            eidaddress1_et.setText(jsonObject.getString("eidaddress1"));
            eidaddress2_et.setText(jsonObject.getString("eidaddress2"));
            eidaddress3_et.setText(jsonObject.getString("eidaddress3"));
            employer_et.setText(jsonObject.getString("employer"));
            pepflag_string=jsonObject.getString("pep");
            if(pepflag_string.equals("Yes")){
                yespep.setChecked(true);
            }else{
                nopep.setChecked(true);
            }
            expectedvolume_et.setText(jsonObject.getString("expectedvolume"));
            expectedva_et.setText(jsonObject.getString("expectedva"));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}



