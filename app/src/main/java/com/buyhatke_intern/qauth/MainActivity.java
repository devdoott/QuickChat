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

public class MainActivity extends AppCompatActivity {
   //private TextView mTextView=new TextView();
   // (TextView)findViewById(R.id.authres);
    private TextView mTextView;
private EditText mEditText;
    private String mnumber;
    private Button mButton;
    private Intent reg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        reg= new Intent(MainActivity.this, registerActivity.class);
mEditText=(EditText)findViewById(R.id.editText);
        mButton=(Button)findViewById(R.id.button);
        mButton.setBackgroundColor(Color.parseColor("#18d6f0"));
        mButton.setEnabled(true);
mTextView=(TextView)findViewById(R.id.textView);
     //   mTextView.setText("Hello");
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            //    reg.putExtra("mnumber",mnumber);
                mButton.setBackgroundColor(Color.parseColor("#696969"));

                mnumber = mEditText.getText().toString().trim();
                mButton.setEnabled(false);

                //  mTextView.setText(mnumber);
                View view = MainActivity.this.getCurrentFocus();
                if (view != null) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
                if (!isNetworkConnected()) {
                    Toast.makeText(MainActivity.this,
                            R.string.nc_toast,
                            Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        new teleclass().execute();
                    } catch (NullPointerException e) {
                        Toast.makeText(MainActivity.this,
                                R.string.nc_toast,
                                Toast.LENGTH_SHORT).show();
                    }
                }
                mEditText.setCursorVisible(false);
                mEditText.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mEditText.setCursorVisible(true);
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
                Toast.makeText(MainActivity.this,
                        R.string.null_toast,
                        Toast.LENGTH_LONG).show();
                mButton.setBackgroundColor(Color.parseColor("#18d6f0"));
                mButton.setEnabled(true);
            }
            else if(i.equals(1)){
                setContentView(R.layout.contact_auth);
                /*Toast.makeText(MainActivity.this,
                        R.string.correct_toast,
                        Toast.LENGTH_SHORT).show();*/
                Intent contact=new Intent(MainActivity.this,UserListActivity.class);
                contact.putExtra("mnumber", mnumber);

                startActivity(contact);

            }
            else if(i.equals(0)){
                /*Toast.makeText(MainActivity.this,
                        R.string.incorrect_toast,
                        Toast.LENGTH_SHORT).show();*/
                reg.putExtra("mnumber",mnumber);

                startActivity(reg);
            }
            else if(i.equals(2)){
                Toast.makeText(MainActivity.this,
                        R.string.error_toast,
                        Toast.LENGTH_SHORT).show();
                mButton.setBackgroundColor(Color.parseColor("#18d6f0"));
                mButton.setEnabled(true);
            }

        }

    }

    public Integer tele(){
        Integer i;
      //public static Integer i;
        Teleapi.start();


        //TLRequestAuthCheckPhone authSendCode=new TLRequestAuthCheckPhone(mnumber);
       // String phoneNumber = "1234567890";
      //

// Call service synchronously
try {

    //reg.putExtra("dc",state.getPrimaryDc());
    TLCheckedPhone checkedPhone = Teleapi.authcheckphone(mnumber);
    boolean invited = checkedPhone.getPhoneInvited();
    boolean registered = checkedPhone.getPhoneRegistered();
  //  api.close();

    if(registered==true){

i=new Integer(1);
    }
    else {i=new Integer(0);


       TLSentCode sentCode = Teleapi.authsendcode(mnumber);
       // registered = sentCode.getPhoneRegistered();
        reg.putExtra("phonehash",sentCode.getPhoneCodeHash());
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

