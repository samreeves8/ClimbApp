package com.example.climber;

import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ClimbAdapter extends RecyclerView.Adapter {

    ClimbSQLiteHelper climbSQLiteHelper;

    ClimbAdapter(ClimbSQLiteHelper climbSQLiteHelper){
            this.climbSQLiteHelper = climbSQLiteHelper;
    }
    public interface ClimbAdapterListener{
        public void click(int position);
    }

    class ClimbHolder extends RecyclerView.ViewHolder{

        public ImageView imageView;
        public TextView textViewName;
        public TextView textViewGrade;
        public TextView textViewLocation;

        public ClimbHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textView2);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    Intent intent = new Intent(view.getContext(), DetailActivity.class);
                    intent.putExtra("position", position);
                    view.getContext().startActivity(intent);
                }
            });
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.climb_layout, parent, false);
        return new ClimbHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ClimbHolder climbHolder = (ClimbHolder) holder;
        Climbs climbs = climbSQLiteHelper.getClimbs(position);
        Bitmap bitmap = climbSQLiteHelper.getBitmap(position);
        climbHolder.textViewName.setText(climbs.name);
    }

    @Override
    public int getItemCount() {
        return climbSQLiteHelper.getCount();
    }
}
