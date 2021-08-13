package com.example.pawsome;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RequestQueue requestQueue;
    private List<Dog> dogList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        requestQueue = VolleySingleton.getInstance(this).getRequestQueue();
        dogList = new ArrayList<>();
        fetchDogs();
    }

    private void fetchDogs() {
        String url = "https://api.thedogapi.com/v1/breeds";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for(int i = 1; i<response.length(); i++){
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        String breed_name = jsonObject.getString("name");
                        String lifeSpan = jsonObject.getString("life_span");
                        String temperament = jsonObject.getString("temperament");
                        JSONObject imageObject = response.getJSONObject(i);
                        JSONObject image = imageObject.getJSONObject("image");
                        JSONObject weightObject = response.getJSONObject(i);
                        JSONObject weight = weightObject.getJSONObject("weight");
                        String dogWeight = weight.getString("metric");
                        String dogImage = image.getString("url");
                        Dog dog = new Dog(breed_name, dogImage, lifeSpan,dogWeight,temperament);
                        dogList.add(dog);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    DogAdapter adapter = new DogAdapter(MainActivity.this, dogList);
                    recyclerView.setAdapter(adapter);


                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
        requestQueue.add(jsonArrayRequest);
    }
}