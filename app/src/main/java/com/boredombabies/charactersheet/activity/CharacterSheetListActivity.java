package com.boredombabies.charactersheet.activity;

import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.os.Parcelable;
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

public class CharacterSheetListActivity extends AppCompatActivity
        implements CharacterSheetListFragment.Callbacks {

    private Realm realm;
    // Whether or not the activity is in two-pane mode, i.e. running on a tablet device.
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

        // If exposing deep links into your app, handle intents here.
    }

    public void onResume() {
        super.onResume();
        if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(getIntent().getAction())) {
            Parcelable[] rawMsgs = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
            if (rawMsgs != null) {
                msgs = new NdefMessage[rawMsgs.length];
                for (int i = 0; i < rawMsgs.length; i++) {
                    msgs[i] = (NdefMessage) rawMsgs[i];
                }
            }
        }
        //process the msgs array
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
