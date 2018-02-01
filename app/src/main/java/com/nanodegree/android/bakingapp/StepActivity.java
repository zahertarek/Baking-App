package com.nanodegree.android.bakingapp;

import android.content.res.Configuration;
import android.opengl.Visibility;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nanodegree.android.bakingapp.recipe.Step;

import java.util.ArrayList;

public class StepActivity extends AppCompatActivity {
        ArrayList steps;
        Step step;
        int stepIndex;
        ImageView nextIcon;
        ImageView previousIcon;
        TextView nextText;
        TextView previousText;
        LinearLayout navigationPanel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step);
        steps     = (ArrayList) getIntent().getParcelableArrayListExtra("Steps");
        stepIndex = getIntent().getExtras().getInt("Step");
        step      = (Step) steps.get(stepIndex);

        nextIcon     = (ImageView) findViewById(R.id.next_icon);
        previousIcon = (ImageView) findViewById(R.id.previous_icon);

        nextText     = (TextView) findViewById(R.id.next_text);
        previousText = (TextView) findViewById(R.id.previous_text);

        navigationPanel = (LinearLayout) findViewById(R.id.navigation_panel);
        if(savedInstanceState == null) {
            Bundle args = new Bundle();
            args.putParcelable("Step", step);
            StepDetailsFragment stepDetailsFragment = new StepDetailsFragment();
            stepDetailsFragment.setArguments(args);

            FragmentManager fragmentManager = getSupportFragmentManager();

            fragmentManager.beginTransaction().add(R.id.fragment_step_container, stepDetailsFragment).commit();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            navigationPanel.setVisibility(View.GONE);
            getSupportActionBar().hide();
        }
        else if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            navigationPanel.setVisibility(View.VISIBLE);
            getSupportActionBar().show();
        }
    }

    void nextStep(View view){
        if(stepIndex != steps.size()-1){
            stepIndex++;
            Bundle args = new Bundle();
            step = (Step) steps.get(stepIndex);
            args.putParcelable("Step", step);
            StepDetailsFragment stepDetailsFragment = new StepDetailsFragment();
            stepDetailsFragment.setArguments(args);
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.fragment_step_container, stepDetailsFragment).commit();
        }
    }

    void previousStep(View view){
        if(stepIndex != 0){
            stepIndex--;
            Bundle args = new Bundle();
            step = (Step) steps.get(stepIndex);
            args.putParcelable("Step", step);
            StepDetailsFragment stepDetailsFragment = new StepDetailsFragment();
            stepDetailsFragment.setArguments(args);
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.fragment_step_container, stepDetailsFragment).commit();
        }
    }


}
