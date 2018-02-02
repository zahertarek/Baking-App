package com.nanodegree.android.bakingapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.nanodegree.android.bakingapp.recipe.Recipe;
import com.nanodegree.android.bakingapp.recipe.Step;

public class RecipeActivity extends AppCompatActivity {

    public static boolean mTwoPane;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if(findViewById(R.id.two_pane_layout) != null)
            mTwoPane = true;
        else
            mTwoPane = false;

        if (savedInstanceState == null) {



            Recipe recipe = (Recipe) getIntent().getParcelableExtra("Recipe");
            Bundle args = new Bundle();
            args.putParcelable("Recipe", recipe);
            RecipeDetailsFragment recipeDetailsFragment = new RecipeDetailsFragment();
            recipeDetailsFragment.setArguments(args);
            FragmentManager fragmentManager = getSupportFragmentManager();

            StepDetailsFragment stepDetailsFragment = new StepDetailsFragment();
            Bundle args1 = new Bundle();
            Step step = recipe.getSteps().get(0);
            args1.putParcelable("Step",step);
            stepDetailsFragment.setArguments(args1);

            if(mTwoPane) {
                fragmentManager.beginTransaction().add(R.id.master_steps_list,recipeDetailsFragment).commit();
                fragmentManager.beginTransaction().add(R.id.detail_step,stepDetailsFragment).commit();
            }else{
                fragmentManager.beginTransaction().add(R.id.recipe_details_container, recipeDetailsFragment).commit();

            }

        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home)
            onBackPressed();
        return super.onOptionsItemSelected(item);
    }
}
