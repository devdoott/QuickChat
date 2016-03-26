package com.buyhatke_intern.qauth;

/**
 * Created by Devdoot on 18-03-2016.
 */
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

public class UserFragment extends Fragment {
    private User mUser;
    private EditText mMessage;
    private Button mDateButton;
    private CheckBox mReadCheckBox;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate( savedInstanceState);
        mUser=new User();
    }
    @Override
    public View onCreateView(LayoutInflater inflater , ViewGroup container , Bundle savedInstanceState){
        View v=inflater.inflate(R.layout.fragment_user,container,false);

        mMessage=(EditText)v.findViewById(R.id.user_name);
        mMessage.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            mUser.setName(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mDateButton=(Button)v.findViewById(R.id.message_date);
        mDateButton.setText(String.format("%ta %<tb %<te\t%<tr",mUser.getDate()));
        mDateButton.setEnabled(false);
        mReadCheckBox=(CheckBox)v.findViewById(R.id.Read);
      mReadCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
          @Override
          public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
              mUser.setRead(isChecked);
          }
      });
        return v;
    }
}
