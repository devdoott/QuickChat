package com.buyhatke_intern.qauth;
import android.content.Context;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import android.provider.*;
import  android.net.*;
import  android.database.*;
/**
 * Created by Devdoot on 18-03-2016.
 */
public class UserLab {
    private static UserLab sUserLab;
    private  ArrayList<User>mUsers;
    public static UserLab get(Context context) {
        if(sUserLab==null)sUserLab=new UserLab(context);
      //  auth.sendCode;
        return sUserLab;
    }

    public ArrayList<User> getUsers() {
        return mUsers;
    }

    private UserLab(Context context){
        Uri contacturi= ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        String[] fields=new String[]{ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER};
        Cursor people = context.getContentResolver().query(contacturi, fields, null, null, null);
       if(mUsers==null)
        mUsers=new ArrayList<>();
        int indexName = people.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
        int indexNumber = people.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);

        people.moveToFirst();int c=0;
        do {if(c==9){
            Teleapi.start();c=0;//To Avoid flood wait Error
        }
            String name   = people.getString(indexName);
            String number = people.getString(indexNumber);
            number=number.replaceAll("\\s+", "");
            String s="+91";
            if(number.charAt(0)=='0')
            number= s.concat(number.substring(1));
            else if(number.charAt(0)!='+')number=s.concat(number);
            User u=new User();
            u.setName(name.trim());
            u.setNumber(number.trim());
            if(!mUsers.contains(u)&&u.getNumber().length()==13){
                try{boolean r=Teleapi.authcheckphone(u.getNumber()).getPhoneRegistered();
                    if(r)
                        mUsers.add(u);}catch(NullPointerException e){}
                }
            c++;

            /*if(number.charAt(0)=='0'){
                number=
            }*/
            //// Do work...
        } while (people.moveToNext());
        if(mUsers.isEmpty())mUsers.add(new User("No Registered Contacts",""));
    }
    public User getUser(UUID id){
        for(User u:mUsers){
            if(u.getId().equals(id))return u;
        }
        return null;
    }

}
