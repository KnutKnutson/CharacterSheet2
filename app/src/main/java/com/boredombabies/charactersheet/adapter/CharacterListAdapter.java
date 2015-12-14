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
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by mark.knutson on 3/22/15.
 */
public class CharacterListAdapter extends ArrayAdapter<PlayerCharacter> {

    private static class ViewHolder {
        ImageView characterIcon;
        TextView characterName;
        TextView characterLevel;
        TextView characterRace;
        TextView characterClass;
    }

    public CharacterListAdapter(Context context, List<PlayerCharacter> characters) {
        super(context, R.layout.adapter_item_character, characters);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        PlayerCharacter character = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.adapter_item_character, parent, false);

            viewHolder.characterIcon  = (ImageView) convertView.findViewById(R.id.characterIcon);
            viewHolder.characterName  = (TextView) convertView.findViewById(R.id.characterName);
            viewHolder.characterLevel = (TextView) convertView.findViewById(R.id.characterLevel);
            viewHolder.characterRace  = (TextView) convertView.findViewById(R.id.characterRace);
            viewHolder.characterClass = (TextView) convertView.findViewById(R.id.characterClass);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // Populate the data into the template view using the data object
        int headerImage = Formulas.getClassImage(character.getCharacterClass());
        Picasso.with(getContext()).load((headerImage != 0 ? headerImage : R.drawable.barbarian_small))
                .placeholder(R.drawable.ic_person_outline_24dp)
                .resize(100, 100)
                .centerCrop()
                .into(viewHolder.characterIcon);
        //viewHolder.characterIcon.setImageResource(R.drawable.ic_person_outline_24dp);
        viewHolder.characterName.setText(character.getName());
        viewHolder.characterLevel.setText("lvl: " + character.getProfile().getLevel());
        viewHolder.characterRace.setText(character.getCharacterRace());
        viewHolder.characterClass.setText(character.getCharacterClass());
        // Return the completed view to render on screen
        return convertView;
    }

}
