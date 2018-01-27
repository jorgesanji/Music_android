package com.jorgesanmartin.sample.ui.utils;

/**
 * Created by jorgesanmartin on 11/3/17.
 */

public interface IODataSource<T> {

    int getCount();

    T getItemAtPosition(int position);
}