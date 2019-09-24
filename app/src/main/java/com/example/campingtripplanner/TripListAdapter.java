package com.example.campingtripplanner;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TripListAdapter extends RecyclerView.Adapter<TripListAdapter.MyViewHolder> {
    private String[] mDataset;
    private int[] tids;
    private final TripListAdapterOnClickHandler mClickHandler;

    public TripListAdapter(String[] mDataset, int[] tids, TripListAdapterOnClickHandler mClickHandler) {
        this.mDataset = mDataset;
        this.tids = tids;
        this.mClickHandler = mClickHandler;
    }

    @NonNull
    @Override
    public TripListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        MyViewHolder vh = new MyViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull TripListAdapter.MyViewHolder holder, int position) {
        TextView textView = holder.textView.findViewById(R.id.my_text_view);
        textView.setText(mDataset[position]);
    }

    @Override
    public int getItemCount() {
        return mDataset.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public View textView;
        public MyViewHolder(@NonNull View v) {
            super(v);
            textView = v;
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            String name = mDataset[adapterPosition];
            int tid = tids[adapterPosition];
            mClickHandler.onClick(name, tid, adapterPosition);
        }
    }

    public interface TripListAdapterOnClickHandler {
        void onClick(String name, int tid, int adapterPosition);
    }
}
