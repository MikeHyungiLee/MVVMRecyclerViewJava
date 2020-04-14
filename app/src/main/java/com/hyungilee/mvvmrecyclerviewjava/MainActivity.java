package com.hyungilee.mvvmrecyclerviewjava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.hyungilee.mvvmrecyclerviewjava.adapters.NicePlaceRecycleAdapter;
import com.hyungilee.mvvmrecyclerviewjava.models.NicePlace;
import com.hyungilee.mvvmrecyclerviewjava.viewmodels.MainActivityViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mNicePlacesRecyclerView;
    private NicePlaceRecycleAdapter mNicePlaceRecycleAdapter;
    private MainActivityViewModel mMainActivityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNicePlacesRecyclerView = findViewById(R.id.niceplaces_recycler_view);

        // MainActivityViewModel 초기화 시켜주기.
        mMainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        mMainActivityViewModel.init();
        mMainActivityViewModel.getNicePlaces().observe(this, new Observer<List<NicePlace>>() {
            @Override
            public void onChanged(List<NicePlace> nicePlaces) {
                mNicePlaceRecycleAdapter.notifyDataSetChanged();
            }
        });

        initNicePlacesRecyclerView();
    }

    private void initNicePlacesRecyclerView(){
        mNicePlaceRecycleAdapter = new NicePlaceRecycleAdapter(this, mMainActivityViewModel.getNicePlaces().getValue());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mNicePlacesRecyclerView.setLayoutManager(layoutManager);
        mNicePlacesRecyclerView.setAdapter(mNicePlaceRecycleAdapter);
    }


}
