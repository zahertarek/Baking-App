package com.nanodegree.android.bakingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nanodegree.android.bakingapp.Adapters.RecipeAdapter;
import com.nanodegree.android.bakingapp.recipe.Recipe;

import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recipeRecyclerView;
    RecipeAdapter recipeAdapter;
    ArrayList<Recipe> recipeAdapterArray;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recipeAdapterArray = new ArrayList<>();
        recipeRecyclerView = (RecyclerView) findViewById(R.id.recipe_rv);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recipeRecyclerView.setLayoutManager(linearLayoutManager);
        final Type RECiPE_TYPE = new TypeToken<List<Recipe>>() {
        }.getType();


        RequestQueue queue = Volley.newRequestQueue(this);
        String url  = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    Gson gson = new Gson();
                    recipeAdapterArray= gson.fromJson(response,RECiPE_TYPE);
                    recipeAdapter = new RecipeAdapter(recipeAdapterArray, new RecipeAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(int i) {
                            Intent intent = new Intent(getBaseContext(),RecipeActivity.class);
                            intent.putExtra("Recipe",recipeAdapterArray.get(i));
                            startActivity(intent);
                        }
                    });
                    recipeRecyclerView.setAdapter(recipeAdapter);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(),"No INternet Connection",Toast.LENGTH_LONG).show();
                }
            });
            queue.add(stringRequest);




    }


}
