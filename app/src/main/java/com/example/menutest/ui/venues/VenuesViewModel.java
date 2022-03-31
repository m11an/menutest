package com.example.menutest.ui.venues;

import android.content.Context;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.menutest.model.VenuesList;
import com.example.menutest.network.repository.VenuesRepository;

import java.util.ArrayList;

public class VenuesViewModel extends ViewModel {
    private VenuesRepository venuesRepository;
    private final MutableLiveData<VenuesList> venuesListMutable = new MutableLiveData<>();

    public void init() {
        venuesRepository = VenuesRepository.getInstance();
    }

    public LiveData<VenuesList> getList() {return venuesListMutable;}

    public void getVenuesList(Context context) {
        venuesRepository.getVenuesList(context).observe((LifecycleOwner) context, venuesListDataWrapper -> {
            venuesListMutable.setValue(venuesListDataWrapper.data);
        });
    }

}
