package com.nanodegree.android.bakingapp;

import android.content.Intent;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.nanodegree.android.bakingapp.recipe.Ingredient;
import com.nanodegree.android.bakingapp.recipe.IngredientRealm;
import com.nanodegree.android.bakingapp.recipe.Recipe;
import com.nanodegree.android.bakingapp.recipe.Step;

import java.util.ArrayList;
import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmResults;

public class RecipeActivity extends AppCompatActivity {

    public static boolean mTwoPane;
    Recipe recipe;
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



            recipe = (Recipe) getIntent().getParcelableExtra("Recipe");
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



        Intent intent = new Intent("android.appwidget.action.LIST_UPDATE");
        getApplicationContext().sendBroadcast(intent);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home)
            onBackPressed();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putParcelable("recipe",recipe);
    }

    @Override
    protected void onResume() {

        super.onResume();
        if(recipe==null){
            recipe = (Recipe) getIntent().getParcelableExtra("Recipe");
        }
        Realm.init(getBaseContext());
        Realm realm = Realm.getDefaultInstance();
        final RealmResults<IngredientRealm> results = realm.where(IngredientRealm.class).findAll();

        // All changes to data must happen in a transaction
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                results.deleteAllFromRealm();
            }
        });
        for(int i=0;i<recipe.getIngredients().size();i++){
            realm.beginTransaction();
            IngredientRealm ingredientRealm = realm.createObject(IngredientRealm.class);
            ingredientRealm.setIngredient(recipe.getIngredients().get(i).getIngredient()+" "
                    +recipe.getIngredients().get(i).getQuantity()+" "+
                    recipe.getIngredients().get(i).getMeasure());
            realm.commitTransaction();
        }

        Log.e("AAA",realm.where(IngredientRealm.class).findAll().get(0).getIngredient());
        Intent intent = new Intent("android.appwidget.action.LIST_UPDATE");
        getApplicationContext().sendBroadcast(intent);

    }
}
