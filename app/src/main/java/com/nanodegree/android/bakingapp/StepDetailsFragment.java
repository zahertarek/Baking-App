package com.nanodegree.android.bakingapp;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.nanodegree.android.bakingapp.recipe.Ingredient;
import com.nanodegree.android.bakingapp.recipe.Step;

import java.io.Serializable;
import java.util.ArrayList;

import static android.content.Context.WINDOW_SERVICE;

/**
 * Created by New on 1/31/2018.
 */

public class StepDetailsFragment extends Fragment {

    Step step;
    SimpleExoPlayerView mPlayerView;
    SimpleExoPlayer mExoPlayer;
    FrameLayout playerContainer;
    TextView stepDescription;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_step_details,container,false);

        step = (Step) getArguments().getParcelable("Step");

        playerContainer = (FrameLayout) rootView.findViewById(R.id.player_container);

        mPlayerView = (SimpleExoPlayerView) rootView.findViewById(R.id.player_view);

        mPlayerView.setDefaultArtwork(BitmapFactory.decodeResource(getResources(),R.drawable.arrow));

        initializePlayer(Uri.parse(step.getVideoURL()));

        stepDescription = (TextView) rootView.findViewById(R.id.step_full_description);
        stepDescription.setText(step.getDescription());
        return rootView;
    }





    private void initializePlayer(Uri uri){
        if(mExoPlayer == null) {
            Log.e("new ","new ");
            TrackSelector trackSelector = new DefaultTrackSelector();
            LoadControl loadControl = new DefaultLoadControl();
            mExoPlayer = ExoPlayerFactory.newSimpleInstance(getActivity(), trackSelector, loadControl);
            mPlayerView.setPlayer(mExoPlayer);
            String userAgent = Util.getUserAgent(getActivity(), "BakingApp");
            MediaSource videoSource = new ExtractorMediaSource(uri, new DefaultDataSourceFactory(getActivity(), userAgent),
                    new DefaultExtractorsFactory(), null, null);
            mExoPlayer.prepare(videoSource);
            mExoPlayer.setPlayWhenReady(true);
        }
    }

    private void releasePlayer(){
        mExoPlayer.stop();
        mExoPlayer.release();
        mExoPlayer = null;
    }

    @Override
    public void onPause() {
        super.onPause();
        releasePlayer();
    }



    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if(newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
          //  Log.e("S",""+playerContainer.getLayoutParams().height);
        }else if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE){
            LinearLayout.LayoutParams params= new LinearLayout.LayoutParams(20,20);
            playerContainer.setLayoutParams(params);

           // Log.e("S",""+playerContainer.getLayoutParams().height);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if(getResources().getConfiguration().orientation==Configuration.ORIENTATION_LANDSCAPE){
            LinearLayout.LayoutParams params= new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
            playerContainer.setLayoutParams(params);

        }
    }
}
