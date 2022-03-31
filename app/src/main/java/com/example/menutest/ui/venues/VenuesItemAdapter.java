package com.example.menutest.ui.venues;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.menutest.R;
import com.example.menutest.model.Venues;

import java.util.List;

public class VenuesItemAdapter extends RecyclerView.Adapter<VenuesItemAdapter.VenuesItemHolder>{

    private final Context context;
    private final List<Venues> list;
    private final OnVenueListener onVenueListener;

    public VenuesItemAdapter(Context context, List<Venues> list, OnVenueListener onVenueListener){
        this.context = context;
        this.list = list;
        this.onVenueListener = onVenueListener;
    }

    public static class VenuesItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textureView;
        OnVenueListener onVenueListener;
        public VenuesItemHolder(View itemView, OnVenueListener onVenueListener){
            super(itemView);
            textureView = itemView.findViewById(R.id.venue_name);
            this.onVenueListener = onVenueListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onVenueListener.onVenueClicked(getAdapterPosition());
        }
    }

    @NonNull
    @Override
    public VenuesItemAdapter.VenuesItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View row = inflater.inflate(R.layout.venues_item, parent, false);
        return new VenuesItemHolder(row, onVenueListener);
    }

    @Override
    public void onBindViewHolder(@NonNull VenuesItemHolder holder, int position) {
        Venues venues = list.get(position);
        holder.textureView.setText(venues.getVenue().getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public interface OnVenueListener {
        void onVenueClicked(int position);
    }
}
