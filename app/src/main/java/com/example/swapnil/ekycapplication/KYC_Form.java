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
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class KYC_Form extends AppCompatActivity {
    Spinner customerTypSpinner,residencySpinner,idtypespinner;
    AutoCompleteTextView nationsList;
    Button NextBtn;
    TextView clearTxt;
    ProgressBar progressBar;
    RadioButton yespep,nopep;
    String completeFormData;
    TextView countryList,countryofbirthlist;
    EditText firstname_et,lastname_et,dnum1_et,dnum2_et,dnum3_et,dnum4_et,dnum5_et,dnum6_et,dnum7_et,dnum8_et,eidnumber_et,eidaddress1_et,eidaddress2_et,eidaddress3_et
            ,profession_et,employer_et,expectedvolume_et,expectedva_et,mobilenumber_et,email_et;
    String customertyeString,firstname_string,lastname_string,dnum1_string,dnum2_string,dnum3_string,dnum4_string,dnum5_string,dnum6_string,dnum7_string,dnum8_string,
            nationallityString,ReisdentType,eidnumber_string,eid_addressstring1,eid_addressstring2,eid_addressstring3,pepflag_string,profession_string,
            countryofbirth_string,expectedvolume_string,expectedva_string,mobilenum_string,email_string,employer_String;
    JSONObject jsonObject;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kyc__form);


        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        if(CheckInternet.isNetworkAvailable(connectivityManager)){

            customerTypSpinner = (Spinner) findViewById(R.id.customertype);
            nationsList =  (AutoCompleteTextView)findViewById(R.id.autoCompleteTextView);
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
            yespep=(RadioButton)findViewById(R.id.yespep);
            nopep=(RadioButton)findViewById(R.id.nopep);
            employer_et=(EditText)findViewById(R.id.c_employername);
            countryofbirthlist=(TextView)findViewById(R.id.countryofbirth);
            expectedvolume_et=(EditText)findViewById(R.id.c_expectedannualactivity);
            expectedva_et=(EditText)findViewById(R.id.c_expectedannualactivityval);

            mobilenumber_et=(EditText)findViewById(R.id.mobileno) ;
            email_et=(EditText)findViewById(R.id.c_emailid);
            employer_et=(EditText)findViewById(R.id.c_employername);
            progressBar=(ProgressBar)findViewById(R.id.kycformprogress);
            NextBtn=(Button)findViewById(R.id.submitkyc);

            String[] Nations_list ={"India","Pakistan","Bangladesh","Phillipines","Nepal","Egypt","Others"};
            String[] Country_list ={"United Arab Emirates","India","Pakistan","Bangladesh","Phillipines","Nepal","Egypt","Others"};

            /**
             * Initialisation Of UI
             */

            ///customertype Spinner Inflater
            List<String> spinnerArray =  new ArrayList<String>();
            spinnerArray.add("Individual");
            spinnerArray.add("Corporate");

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                    this, android.R.layout.simple_spinner_item, spinnerArray);

            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            customerTypSpinner.setAdapter(adapter);
            ////////



            ///Idtype Spinner Inflater
            List<String> idtypeArray =  new ArrayList<String>();
            idtypeArray.add("EID-1");
            idtypeArray.add("EID-2");

            ArrayAdapter<String> idtypeadapter = new ArrayAdapter<String>(
                    this, android.R.layout.simple_spinner_item, idtypeArray);

            idtypeadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        idtypespinner.setAdapter(idtypeadapter);
            ////////

            //Nationality autocomplete list inflate
            ArrayAdapter<String> countryadapter = new ArrayAdapter<String>
                    (this,android.R.layout.select_dialog_item,Nations_list);


            nationsList.setThreshold(1);
            nationsList.setAdapter(countryadapter);

            //


            /**
             * Initialisation Of UI ends
             */

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


            clearTxt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dnum1_et.setText("");
                    dnum2_et.setText("");
                    dnum3_et.setText("");
                    dnum4_et.setText("");
                    dnum5_et.setText("");
                    dnum6_et.setText("");
                    dnum7_et.setText("");
                    dnum8_et.setText("");
                    dnum1_et.requestFocus();
                }
            });

            NextBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    validatedata();

                }
            });


        }else{
            Intent gotologinscreen=new Intent(KYC_Form.this,NoInternet.class);
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
            putdataintostring();
        }


    }

    public void putdataintostring(){
        customertyeString=customerTypSpinner.getSelectedItem().toString();
        firstname_string=firstname_et.getText().toString();
        lastname_string=lastname_et.getText().toString();
        dnum1_string=dnum1_et.getText().toString();
        dnum2_string=dnum2_et.getText().toString();
        dnum3_string=dnum3_et.getText().toString();
        dnum4_string=dnum4_et.getText().toString();
        dnum5_string=dnum5_et.getText().toString();
        dnum6_string=dnum6_et.getText().toString();
        dnum7_string=dnum7_et.getText().toString();
        dnum8_string=dnum8_et.getText().toString();
        nationallityString=nationsList.getText().toString();
        mobilenum_string=mobilenumber_et.getText().toString();
        email_string=email_et.getText().toString();
       // ReisdentType=residencySpinner.getSelectedItem().toString();
        eidnumber_string=eidnumber_et.getText().toString();
        eid_addressstring1=eidaddress1_et.getText().toString();
        eid_addressstring2=eidaddress2_et.getText().toString();
        eid_addressstring3=eidaddress3_et.getText().toString();
        countryofbirth_string=countryofbirthlist.getText().toString();
        if(yespep.isChecked()){
            pepflag_string="Yes";
        }else{
            pepflag_string="No";
        }
        profession_string=profession_et.getText().toString();

        expectedvolume_string=expectedvolume_et.getText().toString();
        expectedva_string=expectedva_et.getText().toString();
        employer_String=employer_et.getText().toString();
        jsonObject=new JSONObject();
        try {
            jsonObject.put("customertype",customertyeString);
            jsonObject.put("firstname",firstname_string);
            jsonObject.put("lastname",lastname_string);

            jsonObject.put("dnum1",dnum1_string);
            jsonObject.put("dnum2",dnum2_string);
            jsonObject.put("dnum3",dnum3_string);
            jsonObject.put("dnum4",dnum4_string);
            jsonObject.put("dnum5",dnum5_string);
            jsonObject.put("dnum6",dnum6_string);
            jsonObject.put("dnum7",dnum7_string);
            jsonObject.put("dnum8",dnum8_string);


            jsonObject.put("nationallity",nationallityString);
            jsonObject.put("mobilenumber",mobilenum_string);
            jsonObject.put("email",email_string);
            jsonObject.put("residenttype",ReisdentType);
            jsonObject.put("eidnumber",eidnumber_string);
            jsonObject.put("eidaddress1",eid_addressstring1);
            jsonObject.put("eidaddress2",eid_addressstring2);
            jsonObject.put("eidaddress3",eid_addressstring3);
            jsonObject.put("countryofbirth",countryofbirth_string);
            jsonObject.put("pep",pepflag_string);
            jsonObject.put("profession",profession_string);
            jsonObject.put("expectedvolume",expectedvolume_string);
            jsonObject.put("expectedva",expectedva_string);
            jsonObject.put("employer",employer_String);
            completeFormData=jsonObject.toString();
            //Toast.makeText(KYC_Form.this, completeFormData, Toast.LENGTH_SHORT).show();

            Intent GotoSelectionActivity=new Intent(KYC_Form.this,KYC_form_confirmation.class);
            GotoSelectionActivity.putExtra("formdata",completeFormData);
            startActivity(GotoSelectionActivity);
            finish();

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }


}
