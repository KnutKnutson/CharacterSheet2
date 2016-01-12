package com.boredombabies.charactersheet.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.boredombabies.charactersheet.R;
import com.boredombabies.charactersheet.helper.Constants;
import com.boredombabies.charactersheet.helper.EditTextTextWatcher;
import com.boredombabies.charactersheet.helper.Formulas;
import com.boredombabies.charactersheet.helper.PlayerCharacterHelper;
import com.boredombabies.charactersheet.interfaces.CharacterSheetFragmentCallbacks;
import com.boredombabies.charactersheet.model.PlayerCharacter;

import io.realm.Realm;


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

    Realm realm;

    CharacterSheetFragmentCallbacks callbacks;

    private int viewPagerPreferencesNumber;

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

        realm = Realm.getInstance(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_character_profile, container, false);

        EditText name = (EditText) rootView.findViewById(R.id.characterName);
        name.setText(playerCharacter.getName());
        name.addTextChangedListener(new EditTextTextWatcher(getActivity()) {
            @Override
            public void inTransactionCallback(Editable s) {
                playerCharacter.setName(s.toString());
            }
            @Override
            public void afterTextChangedCallback(Editable s) {
                //callbacks.refreshFragments(viewPagerPreferencesNumber);
                callbacks.setHeaderText(s.toString());
            }
        });

        EditText characterClass = (EditText) rootView.findViewById(R.id.characterClass);
        characterClass.setText(playerCharacter.getCharacterClass());
        characterClass.addTextChangedListener(new EditTextTextWatcher(getActivity()) {
            @Override
            public void inTransactionCallback(Editable s) {
                playerCharacter.setCharacterClass(s.toString());
            }
            @Override
            public void afterTextChangedCallback(Editable s) {
                int headerImage = Formulas.getClassImage(playerCharacter.getCharacterClass());
                if (headerImage != 0) {
                    playerCharacter.setHeaderImage(headerImage);
                    callbacks.setHeaderImage(headerImage);
                }
            }
        });

        EditText characterRace = (EditText) rootView.findViewById(R.id.characterRace);
        characterRace.setText(playerCharacter.getCharacterRace());
        characterRace.addTextChangedListener(new EditTextTextWatcher(getActivity()) {
            @Override
            public void inTransactionCallback(Editable s) {
                playerCharacter.setCharacterRace(s.toString());
            }
        });

        EditText level = (EditText) rootView.findViewById(R.id.characterLevel);
        level.setText(Integer.toString(playerCharacter.getProfile().getLevel()));
        level.addTextChangedListener(new EditTextTextWatcher(getActivity()) {
            @Override
            public void inTransactionCallback(Editable s) {
                int lvl = (s.toString().isEmpty() ? 0 : Integer.parseInt(s.toString()));
                playerCharacter.getProfile().setLevel(lvl);
            }
        });

        EditText xp = (EditText) rootView.findViewById(R.id.characterXP);
        xp.setText(Integer.toString(playerCharacter.getProfile().getExperiencePoints()));
        xp.addTextChangedListener(new EditTextTextWatcher(getActivity()) {
            @Override
            public void inTransactionCallback(Editable s) {
                int xp = (s.toString().isEmpty() ? 0 : Integer.parseInt(s.toString()));
                playerCharacter.getProfile().setExperiencePoints(xp);
            }
        });

        EditText background = (EditText) rootView.findViewById(R.id.characterBackground);
        background.setText(playerCharacter.getProfile().getBackground());
        background.addTextChangedListener(new EditTextTextWatcher(getActivity()) {
            @Override
            public void inTransactionCallback(Editable s) {
                playerCharacter.getProfile().setBackground(s.toString());
            }
        });

        EditText alignment = (EditText) rootView.findViewById(R.id.characterAlignment);
        alignment.setText(playerCharacter.getProfile().getAlignment());
        alignment.addTextChangedListener(new EditTextTextWatcher(getActivity()) {
            @Override
            public void inTransactionCallback(Editable s) {
                playerCharacter.getProfile().setAlignment(s.toString());
            }
        });

        EditText playerName = (EditText) rootView.findViewById(R.id.playerName);
        playerName.setText(playerCharacter.getProfile().getPlayerName());
        playerName.addTextChangedListener(new EditTextTextWatcher(getActivity()) {
            @Override
            public void inTransactionCallback(Editable s) {
                playerCharacter.getProfile().setPlayerName(s.toString());
            }
        });

        EditText age = (EditText) rootView.findViewById(R.id.age);
        age.setText(playerCharacter.getProfile().getAge());
        age.addTextChangedListener(new EditTextTextWatcher(getActivity()) {
            @Override
            public void inTransactionCallback(Editable s) {
                playerCharacter.getProfile().setAge(s.toString());
            }
        });

        EditText height = (EditText) rootView.findViewById(R.id.height);
        height.setText(playerCharacter.getProfile().getHeight());
        height.addTextChangedListener(new EditTextTextWatcher(getActivity()) {
            @Override
            public void inTransactionCallback(Editable s) {
                playerCharacter.getProfile().setHeight(s.toString());
            }
        });

        EditText weight = (EditText) rootView.findViewById(R.id.weight);
        weight.setText(playerCharacter.getProfile().getWeight());
        weight.addTextChangedListener(new EditTextTextWatcher(getActivity()) {
            @Override
            public void inTransactionCallback(Editable s) {
                playerCharacter.getProfile().setWeight(s.toString());
            }
        });

        EditText eyes = (EditText) rootView.findViewById(R.id.eyes);
        eyes.setText(playerCharacter.getProfile().getEyes());
        eyes.addTextChangedListener(new EditTextTextWatcher(getActivity()) {
            @Override
            public void inTransactionCallback(Editable s) {
                playerCharacter.getProfile().setEyes(s.toString());
            }
        });

        EditText skin = (EditText) rootView.findViewById(R.id.skin);
        skin.setText(playerCharacter.getProfile().getSkin());
        skin.addTextChangedListener(new EditTextTextWatcher(getActivity()) {
            @Override
            public void inTransactionCallback(Editable s) {
                playerCharacter.getProfile().setSkin(s.toString());
            }
        });

        EditText hair = (EditText) rootView.findViewById(R.id.hair);
        hair.setText(playerCharacter.getProfile().getHair());
        hair.addTextChangedListener(new EditTextTextWatcher(getActivity()) {
            @Override
            public void inTransactionCallback(Editable s) {
                playerCharacter.getProfile().setHair(s.toString());
            }
        });

        EditText backstory = (EditText) rootView.findViewById(R.id.backstory);
        backstory.setText(playerCharacter.getProfile().getBackstory());
        backstory.addTextChangedListener(new EditTextTextWatcher(getActivity()) {
            @Override
            public void inTransactionCallback(Editable s) {
                playerCharacter.getProfile().setBackstory(s.toString());
            }
        });

        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // Activities containing this fragment must implement its callbacks.
        if (!(context instanceof CharacterSheetFragmentCallbacks)) {
            throw new IllegalStateException("Activity must implement fragment's callbacks.");
        }

        callbacks = (CharacterSheetFragmentCallbacks) context;
    }
}
