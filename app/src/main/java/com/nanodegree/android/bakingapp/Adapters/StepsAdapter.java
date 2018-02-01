package com.nanodegree.android.bakingapp.Adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;

import com.nanodegree.android.bakingapp.R;
import com.nanodegree.android.bakingapp.recipe.Step;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by New on 1/31/2018.
 */

public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.StepViewHolder> {

    List<Step> steps;
    OnItemClickListener listener;

    public interface OnItemClickListener{
        void onItemClick(int i);
    }
    public static class StepViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView stepNumber;
        TextView stepDescription;


        StepViewHolder(View itemView){
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.step_card_view);
            stepNumber = (TextView) itemView.findViewById(R.id.step_number_text);
            stepDescription = (TextView) itemView.findViewById(R.id.step_description);
        }
    }

    public StepsAdapter(List<Step> steps, OnItemClickListener listener){
        this.steps = steps;
        this.listener = listener;
    }

    public int getItemCount(){
        return steps.size();
    }

    public StepViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.step_list_card,viewGroup,false);
       StepViewHolder svh = new StepViewHolder(v);
        return svh;

    }

    @Override
    public void onBindViewHolder(StepViewHolder holder, final int position) {
        holder.stepNumber.setText("Step "+(position+1));
        holder.stepDescription.setText(steps.get(position).getShortDescription());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(position);
            }
        });
    }

    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
