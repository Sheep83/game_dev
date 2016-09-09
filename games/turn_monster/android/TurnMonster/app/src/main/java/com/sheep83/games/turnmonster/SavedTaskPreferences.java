package com.sheep83.games.turnmonster;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by user on 03/09/2016.
 */
public class SavedTaskPreferences {
    private static final String PLAYER = "tm_player";

    public static void setStoredPlayer(Context context, String text) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PLAYER, text);
        editor.apply();

    }

    public static String getStoredPlayer(Context context){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        String text = sharedPreferences.getString(PLAYER, null);
        return text;
    }

    public static void clearPrefs(Context context){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
    }

    public static ArrayList<Player> convertToObjectArray(Context context, String json){
        Gson gson = new Gson();
        Type objectType = new TypeToken<ArrayList<Player>>() {
        }.getType();
        ArrayList<Player> taskArray = gson.fromJson(json, objectType);
        Log.d("Saved Player : ", taskArray + " array contents");
        return taskArray;
    }


}
