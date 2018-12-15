package com.example.yigitcan.EventManager;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.VenueViewHolder> {

    public static class VenueViewHolder extends RecyclerView.ViewHolder {

        CardView cv;
        TextView venuename;
        TextView venuedetail;
        TextView venueaddress;
        TextView venuephone;
        ImageView venuephoto;


        VenueViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            venuename = (TextView)itemView.findViewById(R.id.venuename);
            venuedetail = (TextView)itemView.findViewById(R.id.venuedetail);
            venuephoto = (ImageView)itemView.findViewById(R.id.venuephoto);
            venueaddress=(TextView)itemView.findViewById(R.id.venueaddress);
            venuephone=(TextView)itemView.findViewById(R.id.venuephone);
        }
    }

    List<Venue> venues;

    RVAdapter(List<Venue> venues){
        this.venues = venues;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public VenueViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_carddesign, viewGroup, false);
        VenueViewHolder pvh = new VenueViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(VenueViewHolder venueViewHolder, int i) {
        venueViewHolder.venuename.setText(venues.get(i).getName());
        venueViewHolder.venueaddress.setText(venues.get(i).getAddress());
        venueViewHolder.venuephone.setText(venues.get(i).getPhone());
        venueViewHolder.venuedetail.setText(venues.get(i).getDetails());

        venueViewHolder.venuephoto.setImageBitmap(venues.get(i).getPicture());
        venueViewHolder.venuephoto.setScaleType(ImageView.ScaleType.CENTER_CROP);
    }

    @Override
    public int getItemCount() {
        return venues.size();
    }
}
