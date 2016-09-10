package com.sheep83.games.turnmonster;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by user on 09/09/2016.
 */

class SkillArrayListAdapter extends ArrayAdapter<Skill>{

    private Context context;
    private ArrayList<Skill> knownSkills, skillTree;
//    Typeface pencil;


    // constructor
    public SkillArrayListAdapter(Context context, int resource, ArrayList<Skill> objects) {
        super(context, resource, objects);
        this.context = context;
        this.knownSkills = objects;
    }
    // overrides default getView called on construction
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Skill skill = knownSkills.get(position);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.skill_layout, null);
        Context context = view.getContext();
//        pencil = Typeface.createFromAsset(context.getAssets(), "fonts/pencil.ttf");
        TextView title = (TextView) view.findViewById(R.id.skill_name);
        TextView text = (TextView) view.findViewById(R.id.skill_description);
//        title.setTypeface(pencil);
//        text.setTypeface(pencil);
        title.setText(skill.getName());
        int textLength = skill.getDescription().length();
        if(textLength >= 100){
            String textTrim = skill.getDescription().substring(0, 100) + "...";
            text.setText(textTrim);
        }else{
            text.setText(skill.getDescription());
        }
        return view;
    }

}


