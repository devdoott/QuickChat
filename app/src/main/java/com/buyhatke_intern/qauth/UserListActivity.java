package com.buyhatke_intern.qauth;

import android.support.v4.app.Fragment;
import android.widget.*;

/**
 * Created by Devdoot on 18-03-2016.
 */
public class UserListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment(){

        return new UserListFragment();
    }
}
