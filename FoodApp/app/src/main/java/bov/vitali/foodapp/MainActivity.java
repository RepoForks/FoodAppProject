package bov.vitali.foodapp;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import bov.vitali.foodapp.fragments.AboutFragment;
import bov.vitali.foodapp.fragments.CategoriesFragment;
import bov.vitali.foodapp.fragments.OfferFragment;
import bov.vitali.foodapp.models.Offer;

public class MainActivity extends AppCompatActivity {

    private final int CATEGORIES = 0;
    private final int DETAILS = 1;
    private final int ABOUT = 2;
    private int currentFragment = CATEGORIES;

    private DrawerLayout dlMenu;
    private ListView lvMenuDrawer;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    private CategoriesFragment categoriesFragment;
    private AboutFragment aboutFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initDrawer();

        categoriesFragment = new CategoriesFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.container, categoriesFragment);
        ft.commit();
    }

    private void initDrawer() {
        dlMenu = (DrawerLayout) findViewById(R.id.dlMenu);
        lvMenuDrawer = (ListView) findViewById(R.id.lvMenuDrawer);

        ArrayAdapter<String> drawerListAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.menu_items));
        lvMenuDrawer.setAdapter(drawerListAdapter);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, dlMenu, R.string.open_menu, R.string.close_menu) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        actionBarDrawerToggle.syncState();
        dlMenu.addDrawerListener(actionBarDrawerToggle);

        lvMenuDrawer.setOnItemClickListener(offerListener);
    }

    private AdapterView.OnItemClickListener offerListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            switch (position) {
                case 0:
                    if (currentFragment == CATEGORIES)
                        break;
                    if (categoriesFragment == null)
                        categoriesFragment = new CategoriesFragment();
                    fragmentTransaction.replace(R.id.container, categoriesFragment);
                    currentFragment = CATEGORIES;
                    break;

                case 1:
                    if (currentFragment == ABOUT)
                        break;
                    if (aboutFragment == null)
                        aboutFragment = new AboutFragment();
                    fragmentTransaction.replace(R.id.container, aboutFragment);
                    currentFragment = ABOUT;
                    break;
            }
            fragmentTransaction.commit();

            lvMenuDrawer.setItemChecked(position, true);
            lvMenuDrawer.setSelection(position);
            dlMenu.closeDrawer(lvMenuDrawer);
            invalidateOptionsMenu();
        }
    };

    public void offerClick(Offer offer) {
        OfferFragment offerFragment = new OfferFragment();
        offerFragment.setOffer(offer);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        if (!categoriesFragment.isDetailFrameExist()) {
            fragmentTransaction.replace(R.id.container, offerFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            currentFragment = DETAILS;
            actionBarDrawerToggle.setDrawerIndicatorEnabled(false);
        } else
            fragmentTransaction.replace(R.id.aboutOfferFragment, offerFragment);

        fragmentTransaction.commit();
        invalidateOptionsMenu();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item))
            return true;

        onBackPressed();
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        actionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (currentFragment == ABOUT)
            getSupportFragmentManager().beginTransaction().remove(aboutFragment).commit();
        else if (currentFragment == DETAILS) {
            currentFragment = CATEGORIES;
            actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        }
    }


}
