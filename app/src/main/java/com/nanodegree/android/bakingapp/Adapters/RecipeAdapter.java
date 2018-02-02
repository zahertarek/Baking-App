package com.nanodegree.android.bakingapp.Adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nanodegree.android.bakingapp.R;
import com.nanodegree.android.bakingapp.recipe.Recipe;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by New on 1/30/2018.
 */

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {

    List<Recipe> recipes;
    OnItemClickListener listener;

    public interface OnItemClickListener{
        void onItemClick(int i);
    }

    public static class RecipeViewHolder extends RecyclerView.ViewHolder{
        CardView cv;
        TextView recipeName;
        TextView serving;
        ImageView recipeThumbnail;

        RecipeViewHolder(View itemView){
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.recipe_card);
            recipeName = (TextView) itemView.findViewById(R.id.recipe_name);
            serving = (TextView) itemView.findViewById(R.id.recipe_serving);
            recipeThumbnail = (ImageView) itemView.findViewById(R.id.recipe_thumbnail);

        }
    }

    public RecipeAdapter(List<Recipe> recipes,OnItemClickListener listener){
        this.recipes = recipes;
        this.listener=listener;
    }

    public int getItemCount(){
        return recipes.size();
    }

    public RecipeViewHolder onCreateViewHolder(ViewGroup viewGroup,int i){
    View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recipe_card,viewGroup,false);
    RecipeViewHolder rvh = new RecipeViewHolder(v);
    return rvh;

    }

    public void onBindViewHolder(RecipeViewHolder recipeViewHolder, final int i){
        recipeViewHolder.recipeName.setText(recipes.get(i).getName());
        recipeViewHolder.serving.setText("Serves "+recipes.get(i).getServings()+" people.");
        if(recipes.get(i).getImage() == null || recipes.get(i).getImage().isEmpty()) {
            int imageId = 0;
            switch (i) {
                case 0:
                    imageId = R.drawable.rsz_r1;
                    break;
                case 1:
                    imageId = R.drawable.rsz_r2;
                    break;
                case 2:
                    imageId = R.drawable.rsz_r3;
                    break;
                case 3:
                    imageId = R.drawable.rsz_r4;
                    break;
            }
            recipeViewHolder.recipeThumbnail.setImageResource(imageId);
        }else{
            Picasso.with(recipeViewHolder.recipeThumbnail.getContext())
                    .load(recipes.get(i).getImage()).into(recipeViewHolder.recipeThumbnail);

        }
        recipeViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                listener.onItemClick(i);
            }
        });

    }

    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
