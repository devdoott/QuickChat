package com.buyhatke_intern.qauth;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.*;
import android.util.Log;
/**
 * Created by Devdoot on 18-03-2016.
 */
public class UserListFragment extends Fragment {
    private RecyclerView mUserRecyclerView;
    private UserAdapter mAdapter;
    private  ArrayList<User> us ;
    private ArrayList<String>name;

    private ArrayList<String>number;
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState){
       // View view =inflater.inflate(R.layout.contact_auth,container ,false);

       View  view =inflater.inflate(R.layout.fragment_user_list,container ,false);
        mUserRecyclerView=(RecyclerView)view.findViewById(R.id.user_recycler_view);
        mUserRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();
        return view;
    }




    private class UserHolder extends RecyclerView.ViewHolder{
        public TextView mTitleTextView;
        public UserHolder(View itemView){
            super(itemView);
            mTitleTextView=(TextView)itemView;
            mTitleTextView.setBackgroundColor(Color.parseColor("#3CB371"));
            mTitleTextView.setTextColor(Color.parseColor("#000000"));
            //mTitleTextView.setlayou
        }


    }
    private class UserAdapter extends RecyclerView.Adapter<UserHolder> {
        private List<User> mUsers;
        public UserAdapter(List<User> crimes) {
            mUsers = crimes;
        }
        @Override
        public UserHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater
                    .inflate(android.R.layout.simple_list_item_1, parent, false);
            return new UserHolder(view);
        }
        @Override
        public void onBindViewHolder(UserHolder holder, int position) {
            User u = mUsers.get(position);
            holder.mTitleTextView.setText(u.getName()+"\n"+u.getNumber());
        }
        @Override
        public int getItemCount() {
            return mUsers.size();
        }
    }
    private void updateUI() {

        UserLab userLab = UserLab.get(getActivity());
        us= userLab.getUsers();

        mAdapter = new UserAdapter(us);
        mUserRecyclerView.setAdapter(mAdapter);
    }

}
