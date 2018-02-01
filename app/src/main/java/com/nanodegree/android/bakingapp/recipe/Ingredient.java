package com.nanodegree.android.bakingapp.recipe;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by New on 1/30/2018.
 */

public class Ingredient implements Parcelable {

    double quantity;
    String ingredient;
    String measure;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeDouble(quantity);
        parcel.writeString(ingredient);
        parcel.writeString(measure);
    }
    public static final Parcelable.Creator<Ingredient> CREATOR
            = new Parcelable.Creator<Ingredient>() {
        public Ingredient createFromParcel(Parcel in) {
            return new Ingredient(in);
        }
        public Ingredient[] newArray(int size) {
            return new Ingredient[size];
        }
    };

    private Ingredient (Parcel in){
        quantity = in.readDouble();
        ingredient = in.readString();
        measure = in.readString();
    }

    public double getQuantity() {
        return quantity;
    }

    public String getMeasure() {
        return measure;
    }

    public String getIngredient() {
        return ingredient;
    }


}
