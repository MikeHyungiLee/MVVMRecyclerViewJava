package com.hyungilee.mvvmrecyclerviewjava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class NewActivity extends AppCompatActivity {

    private static final String TAG = "NewActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);
        Log.d(TAG, "onCreate: called.");
    }
}
