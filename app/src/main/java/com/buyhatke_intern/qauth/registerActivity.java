package com.buyhatke_intern.qauth;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

//import org.telegram.api.auth.TLSentCode;
import org.telegram.api.engine.*;
import org.telegram.api.TLAbsUpdates;
import org.telegram.api.auth.*;
import org.telegram.api.requests.*;

import java.io.IOException;
import  java.lang.*;
import  android.content.*;
import android.support.multidex.*;
import android.widget.*;
import android.view.inputmethod.*;
import android.net.*;
import org.telegram.api.*;

public class registerActivity extends AppCompatActivity {
    //private TextView mTextView=new TextView();
    // (TextView)findViewById(R.id.authres);
   // private TextView mTextView;
    private EditText mEditText2;
    private EditText mEditText3;
    private EditText mEditText4;
    private String mfname;
    private String mlname;
    private String mcode;
    private Button mButton;
    private String mnumber;
    private  String phonehash;
    //private  TelegramApi api;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_register);
        mEditText2=(EditText)findViewById(R.id.editText2);
        mEditText3=(EditText)findViewById(R.id.editText3);
        mEditText4=(EditText)findViewById(R.id.editText4);
        mButton=(Button)findViewById(R.id.button2);
        mButton.setBackgroundColor(Color.parseColor("#18d6f0"));
        mButton.setEnabled(true);
       // mTextView=(TextView)findViewById(R.id.textView);
        //   mTextView.setText("Hello");
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mfname = mEditText2.getText().toString().trim();
                mlname = mEditText3.getText().toString().trim();
                mcode = mEditText4.getText().toString().trim();
                phonehash=getIntent().getStringExtra("phonehash");
                mnumber=getIntent().getStringExtra("mnumber");
                //  mTextView.setText(mnumber);
              //  mButton.setEnabled(false);
                View view = registerActivity.this.getCurrentFocus();
                if (view != null) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
                if (!isNetworkConnected()) {
                    Toast.makeText(registerActivity.this,
                            R.string.nc_toast,
                            Toast.LENGTH_SHORT).show();
                } else {
                    mButton.setEnabled(false);
                    mButton.setBackgroundColor(Color.parseColor("#696969"));
                    try {

                        new teleclass().execute();
                    } catch (NullPointerException e) {
                        Toast.makeText(registerActivity.this,
                                R.string.nc_toast,
                                Toast.LENGTH_SHORT).show();
                    }
                }
                mEditText2.setCursorVisible(false);
                mEditText2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mEditText2.setCursorVisible(true);
                    }
                });
                mEditText3.setCursorVisible(false);
                mEditText3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mEditText3.setCursorVisible(true);
                    }
                });
                mEditText4.setCursorVisible(false);
                mEditText4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mEditText4.setCursorVisible(true);
                    }
                });


            }
        });



    }
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.


        return super.onOptionsItemSelected(item);
    }
    private  class teleclass extends AsyncTask<Void,Void,Integer>{

        @Override
        protected Integer doInBackground(Void... params) {
            return  tele();

        }

        @Override
        protected void onPostExecute(Integer i) {
            //super.onPostExecute(aVoid);
            if(i==null){
                Toast.makeText(registerActivity.this,
                        R.string.null_toast,
                        Toast.LENGTH_LONG).show();
                //Intent contact=new Intent(registerActivity.this,MainActivity.class);
                //contact.putExtra("mnumber", mnumber);

               // startActivity(contact);
            }
            else if(i.equals(1)){
                setContentView(R.layout.contact_auth);
                /*Toast.makeText(registerActivity.this,
                        R.string.correct_toast,
                        Toast.LENGTH_SHORT).show();*/
                Intent contact=new Intent(registerActivity.this,UserListActivity.class);
                contact.putExtra("mnumber", mnumber);

                startActivity(contact);


            }
            else if(i.equals(0)){
                Toast.makeText(registerActivity.this,
                        R.string.incorrect_toast,
                        Toast.LENGTH_SHORT).show();
              //  Intent contact=new Intent(registerActivity.this,MainActivity.class);
                //contact.putExtra("mnumber", mnumber);

               // startActivity(contact);
                //Intent reg = new Intent(MainActivity.this, registerActivity.class);
                //startActivity(reg);
            }
            else if(i.equals(2)){
                Toast.makeText(registerActivity.this,
                        R.string.error_toast,
                        Toast.LENGTH_SHORT).show();
                //Intent contact=new Intent(registerActivity.this,MainActivity.class);
                //contact.putExtra("mnumber", mnumber);

                //startActivity(contact);
            }

        }

    }
    public Integer tele(){
        Integer i;
        //public static Integer i;

        //TLRequestAuthCheckPhone authSendCode=new TLRequestAuthCheckPhone(mnumber);
        // String phoneNumber = "1234567890";
        //


// Call service synchronously
        try {
            //TLConfig config = api.doRpcCallNonAuth(new TLRequestHelpGetConfig());
           // state.updateSettings(config);
           // TLSentCode sentCode = api.doRpcCallNonAuth(new TLRequestAuthSendCode(mnumber, 0, 23564, "72403e0500ec6bf9c2685f80ee670a4d", "en"));
            // registered = sentCode.getPhoneRegistered();

           // TLConfig config = api.doRpcCallNonAuth(new TLRequestHelpGetConfig());
            //state.updateSettings(config);
            //state.updateSettings(null);
              TLAuthorization signUp = Teleapi.signup(mnumber,mfname,mlname,phonehash,mcode);

            TLCheckedPhone checkedPhone=Teleapi.authcheckphone(mnumber);
           // boolean invited = checkedPhone.getPhoneInvited();
            boolean registered = checkedPhone.getPhoneRegistered();

            if(registered==true){

                i=new Integer(1);
            }
            else {i=new Integer(0);
                //   TLSentCode sentCode = api.doRpcCallNonAuth(new TLRequestAuthSendCode(mnumber, 0, 23564, "72403e0500ec6bf9c2685f80ee670a4d", "en"));
                // registered = sentCode.getPhoneRegistered();
            }
            return i;
        }catch (NullPointerException e){
            //System.out.println(e+"////////////////////////////////////////////////////////////////////////////////////////////////////");
            return null;}



    }
    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }
    //catch (IOException e){}
}

