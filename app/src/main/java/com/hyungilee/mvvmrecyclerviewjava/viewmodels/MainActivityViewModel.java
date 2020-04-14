package com.hyungilee.mvvmrecyclerviewjava.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.hyungilee.mvvmrecyclerviewjava.models.NicePlace;
import com.hyungilee.mvvmrecyclerviewjava.repositories.NicePlaceRepository;

import java.util.List;

public class MainActivityViewModel extends ViewModel {

    private MutableLiveData<List<NicePlace>> mNicePlaces;
    private NicePlaceRepository mRepo;

    public LiveData<List<NicePlace>> getNicePlaces(){return mNicePlaces;}

    public void init(){
        if(mNicePlaces != null){
            return;
        }
        mRepo = NicePlaceRepository.getInstance();
        mNicePlaces = mRepo.getNicePlaces();
    }

}
