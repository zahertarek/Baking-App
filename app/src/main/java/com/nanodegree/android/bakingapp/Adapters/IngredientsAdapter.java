package com.nanodegree.android.bakingapp.Adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nanodegree.android.bakingapp.R;
import com.nanodegree.android.bakingapp.recipe.Ingredient;

import java.util.List;

/**
 * Created by New on 2/1/2018.
 */

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.IngredientViewHolder> {

    List<Ingredient> ingredients;

    public static class IngredientViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView name;
        TextView measure;

        IngredientViewHolder(View itemView){
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.ingredient_card);
            name = (TextView) itemView.findViewById(R.id.ingredientName);
            measure = (TextView) itemView.findViewById(R.id.ingredient_measure);
        }
    }

    public IngredientsAdapter(List<Ingredient> ingredients){
        this.ingredients = ingredients;
    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    public IngredientViewHolder onCreateViewHolder(ViewGroup viewGroup, final int i){
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.ingredient_card,viewGroup,false);
        IngredientViewHolder ivh = new IngredientViewHolder(v);
        return ivh;
    }

    @Override
    public void onBindViewHolder(IngredientViewHolder holder, int position) {
        holder.measure.setText(""+ingredients.get(position).getQuantity()+" "+ingredients.get(position).getMeasure());
        holder.name.setText(ingredients.get(position).getIngredient());
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
