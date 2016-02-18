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
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.boredombabies.charactersheet.R;
import com.boredombabies.charactersheet.fragment.CharacterProfileFragment;
import com.boredombabies.charactersheet.fragment.CharacterSheetListFragment;
import com.boredombabies.charactersheet.helper.PlayerCharacterHelper;
import com.boredombabies.charactersheet.model.PlayerCharacter;
import com.squareup.picasso.Picasso;

import io.realm.Realm;

public class CharacterSheetListActivity extends AppCompatActivity
        implements CharacterSheetListFragment.Callbacks,
                    NfcAdapter.CreateNdefMessageCallback,
                    NfcAdapter.OnNdefPushCompleteCallback {

    private Realm realm;
    // Whether or not the activity is in two-pane mode, i.e. running on a tablet device.
    private boolean mTwoPane;

    NfcAdapter nfcAdapter;
    String messageToSend;
    private static final int MESSAGE_SENT = 1;

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

        nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        if (nfcAdapter == null) {
            Toast.makeText(this, "NFC is not available", Toast.LENGTH_LONG).show();
            finish();
            return;
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
    public void onCharacterToShare(String playerCharacter) {

    }

    // implements CreateNdefMessageCallback
    @Override
    public NdefMessage createNdefMessage(NfcEvent event) {
        String text = ("Beam me up, Android!\n\n" +
                "Beam Time: " + System.currentTimeMillis());
        NdefMessage msg = new NdefMessage(
                new NdefRecord[] {
                        //createNdefRecord(text.getBytes())
                        NdefRecord.createMime("application/com.boredombabies.charactersheet", text.getBytes())
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

    /** This handler receives a message from onNdefPushComplete */
    private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MESSAGE_SENT:
                    Toast.makeText(getApplicationContext(), "Message sent!", Toast.LENGTH_LONG).show();
                    break;
            }
        }
    };


    private void refreshListAdapter() {
        ((CharacterSheetListFragment) getSupportFragmentManager()
                .findFragmentById(R.id.charactersheet_list))
                .getListAdapter()
                .notifyDataSetChanged();
    }

    private NdefRecord createNdefRecord(byte[] payload) {
        // http://developer.android.com/guide/topics/connectivity/nfc/nfc.html#p2p
        //byte[] payload; //assign to your data
        String domain = "com.boredombabies.charactersheet";
        String type = "externalType";
        NdefRecord extRecord = NdefRecord.createExternal(domain, type, payload);
        return extRecord;
    }
}
