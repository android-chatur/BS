package com.example.barayihsalem.Viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.barayihsalem.UI.Model.Get_Membership_DashboardPojo;
import com.example.barayihsalem.UI.Model.List;

import java.util.ArrayList;

public class HomeViewModel extends ViewModel {


    MutableLiveData<ArrayList<List>> userLiveData;
    ArrayList<List> userArrayList;

    public HomeViewModel() {
        userLiveData = new MutableLiveData<>();

        // call your Rest API in init method
        init();
    }
    public MutableLiveData<ArrayList<List>> getUserMutableLiveData() {
        return userLiveData;
    }


    private void init() {
       /* populateList();
        userLiveData.setValue(userArrayList);*/
    }

}