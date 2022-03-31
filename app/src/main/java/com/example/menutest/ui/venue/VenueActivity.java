package com.example.menutest.ui.venue;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.menutest.R;
import com.example.menutest.databinding.ActivityVenueBinding;
import com.example.menutest.model.Venue;
import com.squareup.picasso.Picasso;

public class VenueActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityVenueBinding activityVenueBinding = ActivityVenueBinding.inflate(getLayoutInflater());
        setContentView(activityVenueBinding.getRoot());
        Bundle data = getIntent().getExtras();
        Venue venue = data.getParcelable("venue");
        if(venue != null) {
            if (venue.getImagesVenue() != null && venue.getImagesVenue().getMedium() != null) {
                Picasso.get()
                        .load(venue.getImagesVenue().getMedium())
                        .error(R.drawable.ic_baseline_error_outline_24)
                        .fit()
                        .into(activityVenueBinding.venueImage);
            } else {
                activityVenueBinding.venueImage.setVisibility(View.GONE);
                activityVenueBinding.guideline8.setVisibility(View.GONE);
                activityVenueBinding.guideline8.setGuidelinePercent(0);
                activityVenueBinding.noImage.setVisibility(View.VISIBLE);
            }
            if(venue.getName() != null && !venue.getName().isEmpty())
                activityVenueBinding.venueTitle.setText(venue.getName());
            else
                activityVenueBinding.venueTitle.setVisibility(View.GONE);
            if(venue.getWelcomeMsg() != null && !venue.getWelcomeMsg().isEmpty())
                activityVenueBinding.welcomMsg.setText(venue.getWelcomeMsg());
            else
                activityVenueBinding.welcomMsg.setVisibility(View.GONE);

            if(venue.getDesc() != null && !venue.getDesc().isEmpty())
                activityVenueBinding.intro.setText(venue.getDesc());
            else
                activityVenueBinding.intro.setVisibility(View.GONE);
            activityVenueBinding.openClose.setText(venue.isOpen() ? "Open" : "Close");
        } else {
            activityVenueBinding.venueImage.setVisibility(View.GONE);
            activityVenueBinding.guideline8.setVisibility(View.GONE);
            activityVenueBinding.guideline8.setGuidelinePercent(0);
            activityVenueBinding.noImage.setVisibility(View.VISIBLE);
            activityVenueBinding.venueTitle.setVisibility(View.GONE);
            activityVenueBinding.welcomMsg.setVisibility(View.GONE);
            activityVenueBinding.intro.setVisibility(View.GONE);
            activityVenueBinding.openClose.setVisibility(View.GONE);
            activityVenueBinding.noImage.setText(R.string.something_wrong);
        }
    }
}
