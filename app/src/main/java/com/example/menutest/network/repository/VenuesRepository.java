package com.example.menutest.network.repository;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.menutest.model.DataWrapper;
import com.example.menutest.model.VenuesList;
import com.example.menutest.network.data.ApiUtils;
import com.example.menutest.network.data.ApplicationService;
import com.example.menutest.network.dto.LocationDto;
import com.example.menutest.network.dto.WrapperDto;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VenuesRepository {

    private static VenuesRepository venuesRepository;

    public static VenuesRepository getInstance() {
        if (venuesRepository == null) {
            venuesRepository = new VenuesRepository();
        }
        return venuesRepository;
    }

    public MutableLiveData<DataWrapper<VenuesList>> getVenuesList(Context context) {
        MutableLiveData<DataWrapper<VenuesList>> venusList = new MutableLiveData<>();
        DataWrapper<VenuesList> responseData = new DataWrapper<>();
        LocationDto locationDto = new LocationDto(44.001783, 21.26907);
        ApplicationService applicationService = ApiUtils.appService(context);
        Call<WrapperDto<VenuesList>> call = applicationService.getVenuesList(locationDto);
        call.enqueue(new Callback<WrapperDto<VenuesList>>() {
            @Override
            public void onResponse(@NonNull Call<WrapperDto<VenuesList>> call, @NonNull Response<WrapperDto<VenuesList>> response) {
                responseData.responseCode = response.code();
                responseData.responseMsg = response.message();
                responseData.data = response.body() != null ? response.body().data : null;
                venusList.postValue(responseData);
            }

            @Override
            public void onFailure(@NonNull Call<WrapperDto<VenuesList>> call, @NonNull Throwable t) {
                responseData.responseCode = -1;
                responseData.responseMsg = t.getMessage();
                responseData.data = null;
                venusList.postValue(responseData);
            }
        });
        return venusList;
    }
}
