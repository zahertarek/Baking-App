package com.nanodegree.android.bakingapp.recipe;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by New on 1/30/2018.
 */

public class Recipe implements Parcelable {

    int id;
    String name;
    List<Ingredient> ingredients;
    List<Step> steps;
    int servings;
    String Image;

    @Override
    public int describeContents() {
        return 0;
    }
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(id);
        out.writeString(name);
        out.writeList(ingredients);
        out.writeList(steps);
        out.writeInt(servings);
        out.writeString(Image);
    }
    private Recipe(Parcel in){
       id = in.readInt();
        name= in.readString();
        ingredients = new ArrayList<Ingredient>();
        in.readList(ingredients,Ingredient.class.getClassLoader());
        steps = new ArrayList<>();
        in.readList(steps,Step.class.getClassLoader());
        servings = in.readInt();
        Image = in.readString();
    }
    public static final Parcelable.Creator<Recipe> CREATOR
            = new Parcelable.Creator<Recipe>() {
        public Recipe createFromParcel(Parcel in) {
            return new Recipe(in);
        }

        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public List<Step> getSteps() {
        return steps;
    }

    public int getServings() {
        return servings;
    }

    public String getImage() {
        return Image;
    }



}
