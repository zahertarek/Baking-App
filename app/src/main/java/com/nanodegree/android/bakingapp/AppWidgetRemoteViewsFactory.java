package com.nanodegree.android.bakingapp;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.nanodegree.android.bakingapp.recipe.Ingredient;
import com.nanodegree.android.bakingapp.recipe.IngredientRealm;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * Created by New on 2/3/2018.
 */

public class AppWidgetRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {

    private Context mContext;
    RealmResults<IngredientRealm> results;




    public AppWidgetRemoteViewsFactory(Context context, Intent intent) {
        mContext = context;


    }
    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {

    }

    @Override
    public void onDestroy() {

    }


    @Override
    public int getCount() {

        Realm.init(mContext);
        Realm realm = Realm.getDefaultInstance();
        RealmResults<IngredientRealm> results = realm.where(IngredientRealm.class).findAll();
        return results.size();
    }

    @Override
    public RemoteViews getViewAt(int i) {
        Realm.init(mContext);
        Realm realm = Realm.getDefaultInstance();
        results = realm.where(IngredientRealm.class).findAll();
        if (i < results.size()) {
            RemoteViews remoteView = new RemoteViews(
                    mContext.getPackageName(), R.layout.widget_list_item);
            remoteView.setTextViewText(R.id.list_text_item, results.get(i).getIngredient());
            return remoteView;
        }
        return null;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }
}
