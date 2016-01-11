package com.boredombabies.charactersheet.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.ImageView;

import com.boredombabies.charactersheet.R;
import com.boredombabies.charactersheet.fragment.CharacterProfileFragment;
import com.boredombabies.charactersheet.fragment.CharacterSheetListFragment;
import com.boredombabies.charactersheet.helper.PlayerCharacterHelper;
import com.boredombabies.charactersheet.model.PlayerCharacter;
import com.squareup.picasso.Picasso;

import io.realm.Realm;

/**
 *  CHARACTER SHEET FRAGMENTS
 *  - Profile (Languages?)
 *  - Attributes (Abilities?) (Ability Scores?)
 *  - Equipment
 *  - Skills (Proficiencies? )
 *  - Combat Workspace? (Attacks?)
 *  - Inventory
 *  - Feats
 *  - Attacks?
 *  - Spells
 *  - Notes (companions?  Allies and Organizations? Background?)
 *
 */

/**
 * An activity representing a list of CharacterSheets. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link CharacterProfileActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 * <p/>
 * The activity makes heavy use of fragments. The list of items is a
 * {@link CharacterSheetListFragment} and the item details
 * (if present) is a {@link CharacterProfileFragment}.
 * <p/>
 * This activity also implements the required
 * {@link CharacterSheetListFragment.Callbacks} interface
 * to listen for item selections.
 */
public class CharacterSheetListActivity extends AppCompatActivity
        implements CharacterSheetListFragment.Callbacks {

    private Realm realm;

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        realm = Realm.getInstance(this);
        setContentView(R.layout.activity_charactersheet_app_bar);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        if (findViewById(R.id.charactersheet_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-large and
            // res/values-sw600dp). If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;

            // In two-pane mode, list items should be given the
            // 'activated' state when touched.
            ((CharacterSheetListFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.charactersheet_list))
                    .setActivateOnItemClick(true);
        }

        ImageView splash = (ImageView) findViewById(R.id.main_splash);
        Picasso.with(this).load((mTwoPane ? R.drawable.main : R.drawable.main_small)).into(splash);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final PlayerCharacter newPlayerCharacter = PlayerCharacterHelper.newCharacter(realm);
                refreshListAdapter();
                Snackbar.make(view, "Undo New Character", Snackbar.LENGTH_LONG)
                        .setAction("Undo", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                PlayerCharacterHelper.killCharacter(realm, newPlayerCharacter);
                                refreshListAdapter();
                            }
                        }).show();
            }
        });

        // TODO: If exposing deep links into your app, handle intents here.
    }

    /**
     * Callback method from {@link CharacterSheetListFragment.Callbacks}
     * indicating that the item with the given ID was selected.
     */
    @Override
    public void onItemSelected(String id) {
        Intent detailIntent = new Intent(this, CharacterSheetViewPagerActivity.class);
        startActivity(detailIntent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }

    private void refreshListAdapter() {
        ((CharacterSheetListFragment) getSupportFragmentManager()
                .findFragmentById(R.id.charactersheet_list))
                .getListAdapter()
                .notifyDataSetChanged();
    }
}
