package com.upt.cti.vlad.udrescu.booking.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.upt.cti.vlad.udrescu.booking.model.RecentHotels;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class RecentHotelsAdapter extends RecyclerView.Adapter<RecentHotelsAdapter.RecentViewHolder> {


    Context context;
    List<RecentHotels> recentHotelsList;

    @NonNull
    @Override
    public RecentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @NonNull
//    public RecentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
////        View view = LayoutInflater.from(context).inflate(RecentHotelsAdapter.layout);
//    }

    @Override
    public void onBindViewHolder(@NonNull RecentViewHolder holder, int position) {

    }



    @Override
    public int getItemCount() {
        return recentHotelsList.size();
    }


    public static final class RecentViewHolder extends RecyclerView.ViewHolder{

        public RecentViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
