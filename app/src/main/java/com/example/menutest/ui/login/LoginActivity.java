package com.example.menutest.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.menutest.R;
import com.example.menutest.common.Constants;
import com.example.menutest.databinding.ActivityLoginBinding;
import com.example.menutest.ui.venues.VenuesActivity;

public class LoginActivity extends AppCompatActivity {

    private long backPressTime = 0;
    private ActivityLoginBinding activityLoginBinding;
    private LoginViewModel loginViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityLoginBinding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(activityLoginBinding.getRoot());
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        loginViewModel.init(this);
        setSupportActionBar(activityLoginBinding.toolbar.getRoot());
        activityLoginBinding.toolbar.titleScreen.setText(getResources().getText(R.string.login_text));
        activityLoginBinding.toolbar.buttonLogout.setVisibility(View.GONE);
        loginViewModel.getNextScreen().observe(this, goToNext -> {
            if (goToNext) {
                Intent intent = new Intent(this, VenuesActivity.class);
                startActivity(intent);
                finish();
            }
        });

        loginViewModel.getToken().observe(this, stringDataWrapper -> {
            activityLoginBinding.loadingContent.setVisibility(View.GONE);
            if (stringDataWrapper.responseCode == Constants.EMAIL_ERROR) {
                activityLoginBinding.email.setError("Not valid email");
                Toast.makeText(this, "Not valid email", Toast.LENGTH_LONG).show();
            } else if (stringDataWrapper.responseCode == 401) {
                Toast.makeText(this, "This user does not exists or wrong password or mail", Toast.LENGTH_LONG).show();
            } else if (stringDataWrapper.data != null && stringDataWrapper.getResponseCode() == 200) {
                Intent intent = new Intent(this, VenuesActivity.class);
                startActivity(intent);
            }
        });

        activityLoginBinding.buttonLogin.setOnClickListener(v -> {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);
            if (activityLoginBinding.email.getText().toString().isEmpty() || activityLoginBinding.password.getText().toString().isEmpty()) {
                if (activityLoginBinding.email.getText().toString().isEmpty()) {
                    activityLoginBinding.email.setError("Need to input email");
                }
                if (activityLoginBinding.password.getText().toString().isEmpty()) {
                    activityLoginBinding.password.setError("Need to input password");
                }
            } else {
                activityLoginBinding.loadingContent.setVisibility(View.VISIBLE);
                loginViewModel.login(activityLoginBinding.email.getText().toString(),
                        activityLoginBinding.password.getText().toString(), this);
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (backPressTime + 2000 > System.currentTimeMillis()) {
            Intent homeIntent = new Intent(Intent.ACTION_MAIN);
            homeIntent.addCategory(Intent.CATEGORY_HOME);
            homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(homeIntent);
        } else {
            Toast.makeText(this, "Press again to exit app", Toast.LENGTH_LONG).show();
        }
        backPressTime = System.currentTimeMillis();
    }
}
