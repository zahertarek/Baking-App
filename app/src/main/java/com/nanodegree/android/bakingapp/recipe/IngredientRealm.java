package com.nanodegree.android.bakingapp.recipe;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by New on 2/4/2018.
 */

public class IngredientRealm extends RealmObject {


    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    private String ingredient;

}
