package com.example.menutest.ui.venues;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.menutest.databinding.ActivityVenuesBinding;
import com.example.menutest.model.Venues;
import com.example.menutest.ui.login.LoginActivity;
import com.example.menutest.ui.venue.VenueActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class VenuesActivity extends AppCompatActivity implements VenuesItemAdapter.OnVenueListener {

    private long backPressTime = 0;
    private final List<Venues> venuesList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityVenuesBinding activityVenuesBinding = ActivityVenuesBinding.inflate(getLayoutInflater());
        setContentView(activityVenuesBinding.getRoot());
        setSupportActionBar(activityVenuesBinding.toolbar.getRoot());
        activityVenuesBinding.toolbar.buttonLogout.setOnClickListener(v -> {
            SharedPreferences sharedPreferences = this.getSharedPreferences("SharedPref", Context.MODE_PRIVATE);
            if (sharedPreferences.contains("token")) {
                sharedPreferences.edit().clear().apply();
            }
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        });
        VenuesViewModel venuesViewModel = new ViewModelProvider(this).get(VenuesViewModel.class);
        venuesViewModel.init();
        venuesViewModel.getVenuesList(this);
        activityVenuesBinding.listVenues.setLayoutManager(new LinearLayoutManager(this));
        VenuesItemAdapter venuesItemAdapter = new VenuesItemAdapter(this, venuesList, this);
        activityVenuesBinding.listVenues.setAdapter(venuesItemAdapter);
        venuesViewModel.getList().observe(this, venuesList -> {
            activityVenuesBinding.loadingContent.getRoot().setVisibility(View.GONE);
            if (activityVenuesBinding.noList.getVisibility() == View.VISIBLE) {
                activityVenuesBinding.noList.setVisibility(View.GONE);
            }
            if (venuesList != null && venuesList.getListOfVenues() != null && !venuesList.getListOfVenues().isEmpty()) {
                this.venuesList.addAll(venuesList.getListOfVenues());
                Objects.requireNonNull(this).runOnUiThread(venuesItemAdapter::notifyDataSetChanged);
            } else {
                activityVenuesBinding.noList.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void onVenueClicked(int position) {
        Intent intent = new Intent(this, VenueActivity.class);
        intent.putExtra("venue", venuesList.get(position).getVenue());
        startActivity(intent);
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
