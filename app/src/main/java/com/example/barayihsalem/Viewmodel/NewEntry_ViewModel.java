package com.example.barayihsalem.Viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.barayihsalem.UI.Model.List;
import com.example.barayihsalem.UI.Model.Row;

import java.util.ArrayList;

public class NewEntry_ViewModel extends ViewModel {

    MutableLiveData<ArrayList<Row>> userLiveData;
    ArrayList<Row> userArrayList;

    public NewEntry_ViewModel() {
        userLiveData = new MutableLiveData<>();

        // call your Rest API in init method
        init();
    }
    public MutableLiveData<ArrayList<Row>> getUserMutableLiveData() {
        return userLiveData;
    }


    private void init() {
       /* populateList();
        userLiveData.setValue(userArrayList);*/
    }

}
