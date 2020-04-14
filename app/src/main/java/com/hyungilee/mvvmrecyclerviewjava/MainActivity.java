package com.hyungilee.mvvmrecyclerviewjava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.hyungilee.mvvmrecyclerviewjava.adapters.NicePlaceRecycleAdapter;
import com.hyungilee.mvvmrecyclerviewjava.models.NicePlace;
import com.hyungilee.mvvmrecyclerviewjava.viewmodels.MainActivityViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity implements NicePlaceRecycleAdapter.OnNicePlaceClickListener {

    private RecyclerView mNicePlacesRecyclerView;
    private NicePlaceRecycleAdapter mNicePlaceRecycleAdapter;
    private MainActivityViewModel mMainActivityViewModel;
    private FloatingActionButton mFab;
    private ProgressBar mProgressbar;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNicePlacesRecyclerView = findViewById(R.id.niceplaces_recycler_view);

        // Floating action button
        mFab = findViewById(R.id.floatingActionButton);

        // ProgressBar
        mProgressbar = findViewById(R.id.progressBar);

        // MainActivityViewModel 초기화 시켜주기.
        mMainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        mMainActivityViewModel.init();
        mMainActivityViewModel.getNicePlaces().observe(this, new Observer<List<NicePlace>>() {
            @Override
            public void onChanged(List<NicePlace> nicePlaces) {
                mNicePlaceRecycleAdapter.notifyDataSetChanged();
            }
        });

        mMainActivityViewModel.getIsUpdating().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean){
                    showProgressBar();
                }else{
                    hideProgressBar();
                    //mNicePlacesRecyclerView.smoothScrollToPosition(mMainActivityViewModel.getNicePlaces().getValue().size()-1);
                }
            }
        });

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMainActivityViewModel.addNewValue(
                        new NicePlace(
                                "https://i.imgur.com/ZcLLrkY.jpg",
                                "Washington"
                        )
                );
            }
        });

        initNicePlacesRecyclerView();

    }

    private void initNicePlacesRecyclerView(){
        mNicePlaceRecycleAdapter = new NicePlaceRecycleAdapter(this, mMainActivityViewModel.getNicePlaces().getValue(),this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mNicePlacesRecyclerView.setLayoutManager(layoutManager);
        mNicePlacesRecyclerView.setAdapter(mNicePlaceRecycleAdapter);
    }

    private void showProgressBar(){
        mProgressbar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar(){
        mProgressbar.setVisibility(View.GONE);
    }

    @Override
    public void onNicePlaceClick(int position) {
        Log.d(TAG, "onNicePlaceClick: clicked");
        Intent intent = new Intent(this, NewActivity.class);
        intent.putExtra("some_object", "something else");
        startActivity(intent);
    }
}
