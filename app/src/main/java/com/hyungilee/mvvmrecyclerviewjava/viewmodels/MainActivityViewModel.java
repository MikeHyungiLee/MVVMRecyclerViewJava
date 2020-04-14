package com.hyungilee.mvvmrecyclerviewjava.viewmodels;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.hyungilee.mvvmrecyclerviewjava.models.NicePlace;
import com.hyungilee.mvvmrecyclerviewjava.repositories.NicePlaceRepository;

import java.util.List;

public class MainActivityViewModel extends ViewModel {

    private MutableLiveData<List<NicePlace>> mNicePlaces;
    private NicePlaceRepository mRepo;
    private MutableLiveData<Boolean> mIsUpdating = new MutableLiveData<>();

    public LiveData<List<NicePlace>> getNicePlaces(){return mNicePlaces;}

    public LiveData<Boolean> getIsUpdating(){return mIsUpdating;}

    public void init(){
        if(mNicePlaces != null){
            return;
        }
        mRepo = NicePlaceRepository.getInstance();
        mNicePlaces = mRepo.getNicePlaces();
    }

    public void addNewValue(final NicePlace nicePlace){
        // 데이터가 추가되었으므로 mIsUpdating　MutableLiveData타입의 데이터를 'true'로 업데이트 하기.
        mIsUpdating.setValue(true);

        new AsyncTask<Void, Void, Void>(){

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                List<NicePlace> currentPlaces = mNicePlaces.getValue();
                currentPlaces.add(nicePlace);
                mNicePlaces.postValue(currentPlaces);
                mIsUpdating.setValue(false);
            }

            @Override
            protected Void doInBackground(Void... voids) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute();

    }

}
