package com.boredombabies.charactersheet.activity;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.boredombabies.charactersheet.R;
import com.boredombabies.charactersheet.fragment.CharacterSheetViewPagerFragment;
import com.squareup.picasso.Picasso;

public class CharacterSheetViewPagerActivity extends AppCompatActivity {

    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_charactersheet_app_bar_vp);

        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Picasso.with(this).load(R.drawable.human_barbarian)
                .into((ImageView) findViewById(R.id.header_image));

        if (findViewById(R.id.charactersheet_view_pager_2) != null) {
            mTwoPane = true;
        }

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.charactersheet_view_pager, CharacterSheetViewPagerFragment.newInstance(1))
                    .commit();
            if (mTwoPane) {
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.charactersheet_view_pager_2, CharacterSheetViewPagerFragment.newInstance(2))
                        .add(R.id.charactersheet_view_pager_3, CharacterSheetViewPagerFragment.newInstance(3))
                        .commit();
            }
        }
    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_character_sheet_view_pager, menu);
//        return true;
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
