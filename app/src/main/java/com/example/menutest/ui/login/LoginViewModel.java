package com.example.menutest.ui.login;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Patterns;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.menutest.common.Constants;
import com.example.menutest.model.DataForUser;
import com.example.menutest.model.DataWrapper;
import com.example.menutest.model.TokenModel;
import com.example.menutest.network.dto.UserDto;
import com.example.menutest.network.repository.LoginRepository;

public class LoginViewModel extends ViewModel {

    private LoginRepository loginRepository;
    private final MutableLiveData<DataWrapper<DataForUser>> userLogin = new MutableLiveData<>();
    private final MutableLiveData<Boolean> goToNext = new MutableLiveData<>();
    private SharedPreferences sharedPreferences;

    public void init(Context context) {
        loginRepository = LoginRepository.getInstance();
        sharedPreferences = context.getSharedPreferences("SharedPref", Context.MODE_PRIVATE);
        if (sharedPreferences.contains("token") && sharedPreferences.getString("token", "") != null) {
            String token = sharedPreferences.getString("token", "");
            if (!token.isEmpty()) {
                goToNext.setValue(true);
            } else {
                goToNext.setValue(false);
            }
        } else {
            goToNext.setValue(false);
        }
    }

    public LiveData<DataWrapper<DataForUser>> getToken() {
        return userLogin;
    }

    public LiveData<Boolean> getNextScreen() {
        return goToNext;
    }

    public void login(String email, String password, Context context) {
        DataWrapper<DataForUser> dataWrapper = new DataWrapper<>();
        if (!checkMail(email)) {
            dataWrapper.responseMsg = "Not valid email";
            dataWrapper.responseCode = Constants.EMAIL_ERROR;
            dataWrapper.data = null;
            userLogin.setValue(dataWrapper);
        } else {
            UserDto userDto = new UserDto(email, password);
            loginRepository.loginUser(context, userDto).observe((LifecycleOwner) context, stringDataWrapper -> {
                if (stringDataWrapper.getData() != null && stringDataWrapper.getResponseCode() >= 200 && stringDataWrapper.getResponseCode() <= 299) {
                    DataForUser dataForUser = stringDataWrapper.getData();
                    TokenModel tokenModel = dataForUser.getTokenModel();
                    if (tokenModel != null && !tokenModel.getToken().isEmpty()) {
                        sharedPreferences.edit().putString("token",
                                tokenModel.getToken()).apply();
                        dataWrapper.responseMsg = "";
                        dataWrapper.responseCode = stringDataWrapper.responseCode;
                        dataWrapper.data = stringDataWrapper.data;
                    } else {
                        dataWrapper.responseMsg = "";
                        dataWrapper.responseCode = stringDataWrapper.responseCode;
                        dataWrapper.data = null;
                    }
                } else {
                    dataWrapper.responseMsg = stringDataWrapper.getResponseMsg();
                    dataWrapper.responseCode = stringDataWrapper.getResponseCode();
                    dataWrapper.data = null;
                }
                userLogin.setValue(dataWrapper);
            });
        }
    }

    private boolean checkMail(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

}
