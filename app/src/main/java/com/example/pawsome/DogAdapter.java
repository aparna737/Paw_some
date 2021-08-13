package com.example.pawsome;

import android.content.Context;
import android.content.Intent;
import android.icu.text.Transliterator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DogAdapter extends RecyclerView.Adapter<DogAdapter.DogHolder> {

    private Context context;
    private List<Dog> dogList;

    public DogAdapter(Context context, List<Dog> dogs){
        this.context = context;
        dogList = dogs;
    }

    @NonNull
    @Override
    public DogHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item , parent, false);

        return new DogHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DogHolder holder, int position) {
        Dog dog = dogList.get(position);
        holder.textView.setText(dog.getDog_name());
        Glide.with(context).load(dog.getDog_image()).into(holder.imageView);

        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                Bundle bundle =  new Bundle();
                bundle.putString("url", dog.getDog_image());
                bundle.putString("life_span", dog.getDog_life_span());
                bundle.putString("weight",dog.getDog_weight());
                bundle.putString("temperament",dog.getDog_temperament());
                bundle.putString("name",dog.getDog_name());
                intent.putExtras(bundle);
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return dogList.size();
    }

    public class DogHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView;
        ConstraintLayout constraintLayout;

        public DogHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.dog_image_view);
            textView = itemView.findViewById(R.id.breed_name_text_view);
            constraintLayout = itemView.findViewById(R.id.main_layout);
        }
    }
}
