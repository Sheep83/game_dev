package com.sheep83.games.turnmonster;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by user on 10/09/2016.
 */
class LootArrayListAdapter extends ArrayAdapter<Loot> {

    private Context context;
    private ArrayList<Loot> lootList;
//    Typeface pencil;


    // constructor
    public LootArrayListAdapter(Context context, int resource, ArrayList<Loot> objects) {
        super(context, resource, objects);
        this.context = context;
        this.lootList = objects;
    }
    // overrides default getView called on construction
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Loot loot = lootList.get(position);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.loot_layout, null);
        Context context = view.getContext();
//        pencil = Typeface.createFromAsset(context.getAssets(), "fonts/pencil.ttf");
        TextView title = (TextView) view.findViewById(R.id.item_name);
        TextView text = (TextView) view.findViewById(R.id.item_description);
//        title.setTypeface(pencil);
//        text.setTypeface(pencil);
        title.setText(loot.getName());
        text.setText("Level: " + loot.getLevel() + " Intellect: " + loot.getIntellect() + " Vitality: " + loot.getVitality());
//        int textLength = loot.getEnchant().length();
//        if(textLength >= 100){
//            String textTrim = loot.getEnchant().substring(0, 100) + "...";
//            text.setText(textTrim);
//        }else{
//            text.setText(loot.getEnchant());
//        }
        return view;
    }

}
