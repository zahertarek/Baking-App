package com.nanodegree.android.bakingapp;

import android.content.Intent;
import android.widget.RemoteViewsService;

import com.nanodegree.android.bakingapp.recipe.Ingredient;

import java.util.ArrayList;

/**
 * Created by New on 2/3/2018.
 */

public class ListViewWidgetService extends RemoteViewsService {

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
       return new AppWidgetRemoteViewsFactory(this.getApplicationContext(),intent);
    }
}
