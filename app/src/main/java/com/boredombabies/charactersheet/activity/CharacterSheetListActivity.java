package com.boredombabies.charactersheet.activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.NfcEvent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.boredombabies.charactersheet.R;
import com.boredombabies.charactersheet.db.RealmHelper;
import com.boredombabies.charactersheet.fragment.CharacterProfileFragment;
import com.boredombabies.charactersheet.fragment.CharacterSheetListFragment;
import com.boredombabies.charactersheet.helper.PlayerCharacterHelper;
import com.boredombabies.charactersheet.model.PlayerCharacter;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;

import java.lang.ref.WeakReference;

import io.realm.Realm;

public class CharacterSheetListActivity extends AppCompatActivity
        implements CharacterSheetListFragment.Callbacks,
                    NfcAdapter.CreateNdefMessageCallback,
                    NfcAdapter.OnNdefPushCompleteCallback {

    private Realm realm;
    // Whether or not the activity is in two-pane mode, i.e. running on a tablet device.
    private boolean mTwoPane;

    NfcAdapter nfcAdapter;
    String characterToSend;
    private static final int MESSAGE_SENT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        realm = RealmHelper.getRealm(this);
        setContentView(R.layout.activity_charactersheet_app_bar);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        loadCharacterSheetListFragment();

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

        nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        if (nfcAdapter == null) {
            //Toast.makeText(this, "NFC is not available", Toast.LENGTH_LONG).show();
        } else {
            // Register callback
            nfcAdapter.setNdefPushMessageCallback(this, this);
            // Register callback to listen for message-sent success
            nfcAdapter.setOnNdefPushCompleteCallback(this, this);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(getIntent().getAction())) {
            Parcelable[] rawMsgs = getIntent().getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
            if (rawMsgs != null) {
                NdefMessage[] msgs = new NdefMessage[rawMsgs.length];
                for (int i = 0; i < rawMsgs.length; i++) {
                    msgs[i] = (NdefMessage) rawMsgs[i];
                }
                // only using first message (json string)
                String importedCharacterJson = new String(msgs[0].getRecords()[0].getPayload());
                try {
                    realm.beginTransaction();
                    realm.copyToRealmOrUpdate(realm.createObjectFromJson(PlayerCharacter.class, importedCharacterJson));
                    realm.commitTransaction();
                    Toast.makeText(this, "Character Imported!", Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    Toast.makeText(this, "Error: Character Not Imported", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    @Override
    public void onNewIntent(Intent intent) {
        // onResume gets called after this to handle the intent
        setIntent(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
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
    public void onCharacterToShare(int playerCharacter) {
        if (nfcAdapter == null) {
            Toast.makeText(this, "NFC is not available", Toast.LENGTH_LONG).show();
        } else {
            PlayerCharacter characterToShare = realm.copyFromRealm(PlayerCharacterHelper.getCharacter(realm, playerCharacter));
            characterToSend = serializeCharacter(characterToShare);
        }
    }

    // implements CreateNdefMessageCallback
    @Override
    public NdefMessage createNdefMessage(NfcEvent event) {
        //String text = ("Beam me up, Android!\n\n" + "Beam Time: " + System.currentTimeMillis());
        if (characterToSend == null) { return null; }
        NdefMessage msg = new NdefMessage(
                new NdefRecord[] {
                        //createNdefRecord(text.getBytes())
                        NdefRecord.createMime("application/com.boredombabies.charactersheet", characterToSend.getBytes())
                });
        return msg;
    }

    /**
     * Implementation for the OnNdefPushCompleteCallback interface
     */
    @Override
    public void onNdefPushComplete(NfcEvent arg0) {
        // A handler is needed to send messages to the activity when this
        // callback occurs, because it happens from a binder thread
        mHandler.obtainMessage(MESSAGE_SENT).sendToTarget();
    }

    public String serializeCharacter(PlayerCharacter playerCharacter) {
        return new GsonBuilder().create().toJson(playerCharacter);
    }

    /** This handler receives a message from onNdefPushComplete */

    private final NdefMessageHandler mHandler = new NdefMessageHandler(this);
//    private final Handler mHandler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            switch (msg.what) {
//                case MESSAGE_SENT:
//                    characterToSend = null;
//                    Toast.makeText(getApplicationContext(), "Character Transferred!", Toast.LENGTH_LONG).show();
//                    break;
//            }
//        }
//    };

    /**
     * Instances of static inner classes do not hold an implicit
     * reference to their outer class.
     */
    private static class NdefMessageHandler extends Handler {
        private final WeakReference<CharacterSheetListActivity> mActivity;

        public NdefMessageHandler(CharacterSheetListActivity activity) {
            mActivity = new WeakReference<CharacterSheetListActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            CharacterSheetListActivity activity = mActivity.get();
            if (activity != null) {
                switch (msg.what) {
                    case MESSAGE_SENT:
                        activity.characterToSend = null;
                        Toast.makeText(activity.getApplicationContext(), "Character Transferred!", Toast.LENGTH_LONG).show();
                        break;
                }
            }
        }
    }

    /**
     * Instances of anonymous classes do not hold an implicit
     * reference to their outer class when they are "static".
     */
    private static final Runnable sRunnable = new Runnable() {
        @Override
        public void run() { /* ... */ }
    };

    private void loadCharacterSheetListFragment() {
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
    }


    private void refreshListAdapter() {
        ((CharacterSheetListFragment) getSupportFragmentManager()
                .findFragmentById(R.id.charactersheet_list))
                .getListAdapter()
                .notifyDataSetChanged();
    }
}
