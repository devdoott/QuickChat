package com.buyhatke_intern.qauth;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.telegram.api.auth.TLSentCode;
import org.telegram.api.engine.*;
import org.telegram.api.TLAbsUpdates;

import org.telegram.api.requests.*;

import  java.lang.*;
import  android.content.*;
import android.support.multidex.*;
import android.widget.*;
import android.view.inputmethod.*;
import android.net.*;

public class MainActivity extends AppCompatActivity {
   //private TextView mTextView=new TextView();
   // (TextView)findViewById(R.id.authres);
    private TextView mTextView;
private EditText mEditText;
    private String mnumber;
    private Button mButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
mEditText=(EditText)findViewById(R.id.editText);
        mButton=(Button)findViewById(R.id.button);
mTextView=(TextView)findViewById(R.id.textView);
     //   mTextView.setText("Hello");
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mnumber = mEditText.getText().toString().trim();
              //  mTextView.setText(mnumber);
                View view = MainActivity.this.getCurrentFocus();
                if (view != null) {
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
                if(!isNetworkConnected()){
                    Toast.makeText(MainActivity.this,
                            R.string.nc_toast,
                            Toast.LENGTH_SHORT).show();
                }
                else
                {new teleclass().execute();}
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
            }
            else if(i.equals(0)){
                Toast.makeText(MainActivity.this,
                        R.string.correct_toast,
                        Toast.LENGTH_SHORT).show();
            }
            else if(i.equals(1)){
                Toast.makeText(MainActivity.this,
                        R.string.incorrect_toast,
                        Toast.LENGTH_SHORT).show();
            }
            else if(i.equals(2)){
                Toast.makeText(MainActivity.this,
                        R.string.error_toast,
                        Toast.LENGTH_SHORT).show();
            }

        }

    }
    public Integer tele(){
      //public static Integer i;
        TelegramApi api = new TelegramApi(new MyApiStorage(), new AppInfo(23564,"","","","en"), new ApiCallback()
        {
           // @Override
            public void onApiDies(TelegramApi api) {
                // When auth key or user authorization dies
            }

            @Override
            public void onAuthCancelled(TelegramApi telegramApi) {

            }

            @Override
            public void onUpdatesInvalidated(TelegramApi api) {
                // When api engine expects that update sequence might be broken
            }

            @Override
            public void onUpdate(TLAbsUpdates tlAbsUpdates) {

            }
        });

        TLRequestAuthSendCode authSendCode=new TLRequestAuthSendCode(mnumber,0,23564,"72403e0500ec6bf9c2685f80ee670a4d","en");
      // try( TLSentCode sentCode=api.doRpcCall(authSendCode)){}catch(IOException e){}


// Syncronized call
// All request objects are in org.telegram.api.requests package
      // try( TLConfig config = api.doRpcCall(new TLRequestHelpGetConfig())){

// Standart async call
      class rpp implements   RpcCallback<TLSentCode>{ private Integer i;

            @Override
            public void onResult(TLSentCode tlSentCode) {
                boolean reg=tlSentCode.getPhoneRegistered();

                if(reg==true){
                    //             mTextView.setText("True");
                    i= new Integer(0);

                }
                else
                {
                    //           mTextView.setText("false");
                    i= new Integer(1);
                }
            }

            public void onError(int errorCode, String message)
            {
                //     mTextView.setText(message);
                i=new Integer(2);

            }
      public Integer geti(){
          return i;
      }}
        rpp n=new rpp();
        api.doRpcCall(authSendCode, n);
        return n.geti();


    }
    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }
    //catch (IOException e){}
}

