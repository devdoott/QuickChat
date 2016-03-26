package com.buyhatke_intern.qauth;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Devdoot on 18-03-2016.
 */
public class User {
    private UUID mId;
    private String mName;
    private String mNumber;
    private String[] mLastMessage;

    private Date mDate;
    private  boolean mRead;

    public UUID getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }
    @Override
    public boolean equals(Object u){
        if (u == this) {
            return true;
        }

        /* Check if o is an instance of Complex or not
          "null instanceof [type]" also returns false */
        if (!(u instanceof User)) {
            return false;
        }
       User a=(User)u;
        if(a.getName().equals(mName)&&a.getNumber().equals(mNumber))return true;
        return false;
    }

public User(String na,String nu){
    mName=na;
    mNumber=nu;
}
    public String[] getLastMessage() {
        return mLastMessage;
    }

    public void setLastMessage(String[] lastMessage) {
        mLastMessage = lastMessage;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public boolean isRead() {
        return mRead;
    }

    public void setRead(boolean read) {
        mRead = read;
    }

    public String getNumber() {
        return mNumber;
    }

    public void setNumber(String number) {
        mNumber = number;
    }

    public User()
    {
        mId=UUID.randomUUID();
        mDate=new Date();
    }
}
