package com.boredombabies.charactersheet.activity;

import android.app.Activity;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;

import com.boredombabies.charactersheet.R;
import com.boredombabies.charactersheet.fragment.AlliesFragment;
import com.boredombabies.charactersheet.fragment.AllySelectDialogFragment;
import com.boredombabies.charactersheet.fragment.CharacterSheetViewPagerFragment;
import com.boredombabies.charactersheet.helper.Formulas;
import com.boredombabies.charactersheet.helper.PlayerCharacterHelper;
import com.boredombabies.charactersheet.interfaces.CharacterSheetFragmentCallbacks;
import com.boredombabies.charactersheet.model.PlayerCharacter;
import com.squareup.picasso.Picasso;

import io.realm.Realm;

public class CharacterSheetViewPagerActivity extends AppCompatActivity
        implements CharacterSheetFragmentCallbacks {

    private boolean mTwoPane;

    private int numViewPagerFragments = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_charactersheet_app_bar_vp);

        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        PlayerCharacter character = PlayerCharacterHelper.getActiveCharacter();
        setHeaderText(character.getName());
        int headerImage = character.getHeaderImage();
        setHeaderImage((headerImage == 0 ? R.drawable.header_barbarian : headerImage));

        if (findViewById(getFragmentId(2)) != null) {
            mTwoPane = true;
            numViewPagerFragments = 3;
        }

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(getFragmentId(1), CharacterSheetViewPagerFragment.newInstance(1))
                    .commit();
            if (mTwoPane) {
                getSupportFragmentManager().beginTransaction()
                        .add(getFragmentId(2), CharacterSheetViewPagerFragment.newInstance(2))
                        .add(getFragmentId(3), CharacterSheetViewPagerFragment.newInstance(3))
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

    @Override
    public void setHeaderText(String s) {
        CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        if (appBarLayout != null) {
            appBarLayout.setTitle(s);
        }
    }

    @Override
    public void setHeaderImage(int drawable) {
        Picasso.with(this).load(drawable)
                .fit()
                .centerInside()
                .into((ImageView) findViewById(R.id.header_image));
    }

    @Override
    public void refreshFragments(int callingFragmentId) {
        //  adapterViewPager.getRegisteredFragment(0);
//        if (mTwoPane) {
//            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//            for (int i = 1; i <= numViewPagerFragments; i++){
//                if (i != callingFragmentId) {
//                    ft.detach(getSupportFragmentManager().findFragmentById(getFragmentId(i)));
//                    ft.attach(getSupportFragmentManager().findFragmentById(getFragmentId(i)));
//                }
//            }
//            ft.commit();
//        }
    }

    public void refreshChildFragment(int childId) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment child = fragmentManager.findFragmentById(getFragmentId(childId));
        fragmentManager.beginTransaction()
                .detach(child)
                .attach(child)
                .commit();
    }

    public void refreshSiblingFragments(int childId) {
        for (int i = 1; i <= numViewPagerFragments; i++) {
            if (i == childId) { continue; }
            refreshChildFragment(i);
        }
    }

    private int getFragmentId(int fragment) {
        switch (fragment) {
            case 1:
                return R.id.charactersheet_view_pager;
            case 2:
                return R.id.charactersheet_view_pager_2;
            case 3:
                return R.id.charactersheet_view_pager_3;
            default:
                return 0;
        }
    }
}
