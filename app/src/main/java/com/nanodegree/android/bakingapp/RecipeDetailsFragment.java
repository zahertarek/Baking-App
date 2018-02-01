package com.nanodegree.android.bakingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nanodegree.android.bakingapp.Adapters.IngredientsAdapter;
import com.nanodegree.android.bakingapp.Adapters.StepsAdapter;
import com.nanodegree.android.bakingapp.recipe.Ingredient;
import com.nanodegree.android.bakingapp.recipe.Recipe;
import com.nanodegree.android.bakingapp.recipe.Step;

import java.util.ArrayList;

/**
 * Created by New on 1/31/2018.
 */

public class RecipeDetailsFragment extends Fragment {
    Recipe recipe;
    TextView recipeName;


    RecyclerView stepsRecyclerView;
    ArrayList<Step> stepAdapterArray;
    StepsAdapter stepsAdapter;

    RecyclerView ingredientsRecyclerView;
    ArrayList<Ingredient> ingredientsAdapterArray;
    IngredientsAdapter ingredientsAdapter;

    public RecipeDetailsFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_recipe_details,container,false);
        recipe = (Recipe) getArguments().getParcelable("Recipe");
        stepAdapterArray= (ArrayList<Step>) recipe.getSteps();
        ingredientsAdapterArray = (ArrayList<Ingredient>) recipe.getIngredients();
        recipeName = (TextView) rootView.findViewById(R.id.recipe_name);


        ingredientsRecyclerView = (RecyclerView) rootView.findViewById(R.id.ingredient_list_rv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        ingredientsRecyclerView.setLayoutManager(linearLayoutManager);
        ingredientsAdapter = new IngredientsAdapter(ingredientsAdapterArray);
        ingredientsRecyclerView.setAdapter(ingredientsAdapter);

        stepsRecyclerView = (RecyclerView) rootView.findViewById(R.id.step_list_rv);
        GridLayoutManager gridLayoutManager= new GridLayoutManager(getContext(),2);
        stepsRecyclerView.setLayoutManager(gridLayoutManager);
        stepsAdapter = new StepsAdapter(stepAdapterArray, new StepsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int i) {
                Intent intent = new Intent(getActivity(),StepActivity.class);
                intent.putExtra("Step",i);
                intent.putParcelableArrayListExtra("Steps",stepAdapterArray);
                startActivity(intent);
            }
        });
        stepsRecyclerView.setAdapter(stepsAdapter);
        return rootView;
    }
}
