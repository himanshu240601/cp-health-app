package com.example.healthapp_collegeproject.home.fragments.healthfragment.adpaters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthapp_collegeproject.home.fragments.healthfragment.ShowExerciseDietActivity;
import com.example.healthapp_collegeproject.home.fragments.healthfragment.models.ExerciseDietModel;
import com.example.healthapp_collegeproject.R;

import java.util.ArrayList;

public class DietClassAdapter extends RecyclerView.Adapter<DietClassAdapter.ViewHolder> {

    private final ArrayList<ExerciseDietModel> arrayList;
    private final Context context;

    public DietClassAdapter(ArrayList<ExerciseDietModel> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public DietClassAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.diet_card, parent, false);
        return new DietClassAdapter.ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull DietClassAdapter.ViewHolder holder, int position) {
        holder.imageView.setImageResource(arrayList.get(position).getImage());
        holder.textView.setText(arrayList.get(position).getTitle());
        holder.textView2.setText(arrayList.get(position).getTime_reps());
        holder.cardView.setOnClickListener(view -> {
            Intent intent = new Intent(context, ShowExerciseDietActivity.class);
            intent.putExtra("image", arrayList.get(holder.getAdapterPosition()).getImage());
            intent.putExtra("title", arrayList.get(holder.getAdapterPosition()).getTitle());
            intent.putExtra("subtitle", arrayList.get(holder.getAdapterPosition()).getTime_reps());
            intent.putExtra("description", arrayList.get(holder.getAdapterPosition()).getDesc());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout cardView;
        public ImageView imageView;
        public TextView textView, textView2;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.linearLayoutExerciseDiet);
            imageView = itemView.findViewById(R.id.imageViewExe);
            textView = itemView.findViewById(R.id.textViewExe);
            textView2 = itemView.findViewById(R.id.textViewExeDesc);
        }
    }
}
