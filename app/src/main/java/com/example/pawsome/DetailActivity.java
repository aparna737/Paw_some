package com.example.pawsome;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ImageView imageView = findViewById(R.id.dog_detail_image_view);
        TextView textView = findViewById(R.id.life_span_text_view);
        TextView textView1 = findViewById(R.id.weight_text_view);
        TextView textView2 = findViewById(R.id.name_text_view);
        TextView textView3 = findViewById(R.id.temperament_text_view);



        Bundle bundle =  getIntent().getExtras();
        String mLifeSpan = bundle.getString("life_span");
        String mImage = bundle.getString("url");
        String mWeight= bundle.getString("weight");
        String mTemperament = bundle.getString("temperament");
        String mName = bundle.getString("name");

        Glide.with(this).load(mImage).into(imageView);
        textView.setText("Life span: "+mLifeSpan +" years");
        textView1.setText("Weight: "+mWeight+" kgs");
        textView3.setText(mTemperament);
        textView2.setText(mName);


    }
}