package com.nanodegree.android.bakingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

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
        Type RECiPE_TYPE = new TypeToken<List<Recipe>>() {
        }.getType();
        String json;
        Gson gson = new Gson();
        try{
            InputStream is = getAssets().open("baking.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
            recipeAdapterArray= gson.fromJson(json,RECiPE_TYPE);
            RecipeAdapter recipeAdapter = new RecipeAdapter(recipeAdapterArray, new RecipeAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(int i) {
                    Intent intent = new Intent(getBaseContext(),RecipeActivity.class);
                    intent.putExtra("Recipe",recipeAdapterArray.get(i));
                    startActivity(intent);
                }
            });
            recipeRecyclerView.setAdapter(recipeAdapter);
        }catch (Exception e){
            Log.e("MAIN Activity","excption",e);
        }


    }


}
