package com.wordpress.umangandroidblog.flashsearch;

import android.support.v4.app.Fragment;

public class SearchActivity extends SingleFragmentActivity {
    @Override
    protected Fragment getFragment() {
        return new SearchFragment();
    }
}
