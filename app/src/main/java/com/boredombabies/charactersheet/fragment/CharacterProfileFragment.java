package com.boredombabies.charactersheet.fragment;

import android.app.Activity;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.boredombabies.charactersheet.R;
import com.boredombabies.charactersheet.activity.CharacterProfileActivity;
import com.boredombabies.charactersheet.activity.CharacterSheetListActivity;
import com.boredombabies.charactersheet.dummy.DummyContent;
import com.boredombabies.charactersheet.helper.PlayerCharacterHelper;
import com.boredombabies.charactersheet.model.PlayerCharacter;

/**
 * A fragment representing a single CharacterSheet detail screen.
 * This fragment is either contained in a {@link CharacterSheetListActivity}
 * in two-pane mode (on tablets) or a {@link CharacterProfileActivity}
 * on handsets.
 */
public class CharacterProfileFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The Player Character this fragment is presenting.
     */
    //private DummyContent.DummyItem mItem;
    PlayerCharacter playerCharacter;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public CharacterProfileFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        playerCharacter = PlayerCharacterHelper.getActiveCharacter();
        Activity activity = this.getActivity();
        CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
        if (appBarLayout != null) {
            appBarLayout.setTitle(playerCharacter.getName());
        }
        /*
        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            mItem = DummyContent.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(mItem.content);
            }
        }
        */
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_character_profile, container, false);
        LinearLayout profile = (LinearLayout) rootView.findViewById(R.id.charactersheet_detail);

        // Show the dummy content as text in a TextView.
        if (playerCharacter != null) {
            for (int i = 0; i < 10; i++) {
                View abilityScoreComponent = inflater.inflate(R.layout.component_ability_score, container, false);
                EditText abilityModifier = (EditText) abilityScoreComponent.findViewById(R.id.ability_modifier);
                EditText abilityScore = (EditText) abilityScoreComponent.findViewById(R.id.ability_score);
                abilityModifier.setText("mod");
                abilityScore.setText("10");
                profile.addView(abilityScoreComponent);
            }
        }

        return rootView;
    }
}
