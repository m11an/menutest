package com.example.menutest.network.repository;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.menutest.model.DataForUser;
import com.example.menutest.model.DataWrapper;
import com.example.menutest.network.data.ApiUtils;
import com.example.menutest.network.data.ApplicationService;
import com.example.menutest.network.dto.UserDto;
import com.example.menutest.network.dto.WrapperDto;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginRepository {

    private static LoginRepository loginRepository;

    public static LoginRepository getInstance() {
        if (loginRepository == null) {
            loginRepository = new LoginRepository();
        }
        return loginRepository;
    }

    public MutableLiveData<DataWrapper<DataForUser>> loginUser(Context context, UserDto userDto) {
        MutableLiveData<DataWrapper<DataForUser>> userToken = new MutableLiveData<>();
        DataWrapper<DataForUser> responseData = new DataWrapper<>();
        ApplicationService applicationService = ApiUtils.appService(context);
        Call<WrapperDto<DataForUser>> call = applicationService.loginUser(userDto);
        call.enqueue(new Callback<WrapperDto<DataForUser>>() {
            @Override
            public void onResponse(@NonNull Call<WrapperDto<DataForUser>> call, @NonNull Response<WrapperDto<DataForUser>> response) {
                responseData.responseCode = response.code();
                responseData.responseMsg = response.message();
                WrapperDto<DataForUser> body = response.body();
                responseData.data = body != null ? body.data : null;
                userToken.postValue(responseData);
            }

            @Override
            public void onFailure(@NonNull Call<WrapperDto<DataForUser>> call, @NonNull Throwable t) {
                responseData.responseCode = -1;
                responseData.responseMsg = t.getMessage();
                responseData.data = null;
                userToken.postValue(responseData);
            }
        });
        return userToken;
    }
}
