package com.boredombabies.charactersheet.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.boredombabies.charactersheet.R;
import com.boredombabies.charactersheet.helper.Formulas;
import com.boredombabies.charactersheet.model.PlayerCharacter;
import com.boredombabies.charactersheet.model.Weapon;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created on 3/6/16.
 * TODO: IMPLEMENT
 */
public class WeaponListAdapter extends ArrayAdapter<Weapon> {

    private static class ViewHolder {
        ImageView weaponIcon;
        TextView weaponName;
        TextView cost;
        TextView damage;
        TextView weight;
        TextView properties;
    }

    public WeaponListAdapter(Context context, List<Weapon> weapons) {
        this(context, R.layout.adapter_item_character, weapons);
    }

    public WeaponListAdapter(Context context, int layout, List<Weapon> characters) {
        super(context, layout, characters);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Weapon weapon = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.adapter_item_character, parent, false);

            viewHolder.weaponIcon  = (ImageView) convertView.findViewById(R.id.characterIcon);
            viewHolder.weaponName  = (TextView) convertView.findViewById(R.id.characterName);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // Populate the data into the template view using the data object
//        int headerImage = Formulas.getHeadShotImage(character.getCharacterClass());
//        Picasso.with(getContext()).load((headerImage != 0 ? headerImage : R.drawable.headshot_barbarian))
//                .resize(100, 100)
//                .centerCrop()
//                .into(viewHolder.weaponIcon);
        //viewHolder.characterIcon.setImageResource(R.drawable.ic_person_outline_24dp);
        // Return the completed view to render on screen
        return convertView;
    }

}
