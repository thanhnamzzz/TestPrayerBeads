package com.gambi.testprayerbeads;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {
    private List<ImageObject> mListImage;
    private final Context context;

    public ImageAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<ImageObject> mListImage) {
        this.mListImage = mListImage;
    }

    @NonNull
    @Override
    public ImageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_bead, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ImageAdapter.ViewHolder holder, int position) {
        holder.image.setImageResource(mListImage.get(position).getImage());
        holder.value.setText((Math.abs(mListImage.get(position).getValue()) % 5) + "");
    }

    @Override
    public int getItemCount() {
        if (mListImage != null) return mListImage.size();
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView value;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.image);
            value = itemView.findViewById(R.id.value);
        }
    }

    public int getValue(int position) {
        return mListImage.get(position).getValue();
    }

    public int getLastValue() {
        return mListImage.get(mListImage.size() - 1).getValue();
    }

    public int getFirstValue() {
        return mListImage.get(0).getValue();
    }

    public void addPreviousItem() {
//        for (int i = 0; i < 10; i++) {
            mListImage.add(0, new ImageObject(getFirstValue() - 1, R.drawable.bead_small));
            notifyItemInserted(0);

            notifyItemRemoved(mListImage.size() - 1);
            mListImage.remove(mListImage.size() - 1);
//        }
    }

    public void addLastItem() {
//        for (int i = 0; i < 10; i++) {
            mListImage.add(new ImageObject(getLastValue() + 1, R.drawable.bead_small));
            notifyItemInserted(getItemCount());

            notifyItemRemoved(0);
            mListImage.remove(0);
//        }
    }
}
