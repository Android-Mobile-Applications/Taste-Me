package com.example.nitro.tasteme;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.nitro.tasteme.fragments.HomeRecipeFragment;
import com.example.nitro.tasteme.fragments.RecipeFragment;
import com.example.nitro.tasteme.maps.MapsActivity;

import com.example.nitro.tasteme.fragments.FavouritesFragment;
import com.example.nitro.tasteme.fragments.HomePageFragment;
import com.example.nitro.tasteme.fragments.ShoppingCartFragment;
import com.example.nitro.tasteme.services.ReminderService;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        FavouritesFragment.OnItemClickedListener,
        HomePageFragment.OnGridViewItemClickedListener,
        MapsActivity.OnInfoWindowCustomClickListener {

    ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        startService(new Intent(this, ReminderService.class));
        // [Optional] Power your app with Local Datastore. For more info, go to
        // https://parse.com/docs/android/guide#local-datastore

        //TODO: add in new method
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(0);
    }

    @Override
    public void onBackPressed() {

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        } else if (id == R.id.action_map) {
            Intent intent = new Intent(this, MapsActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            viewPager.setCurrentItem(0);
            setTitle(item.getTitle());
        } else if (id == R.id.nav_gallery) {
            viewPager.setCurrentItem(1);
            setTitle(item.getTitle());

        } else if (id == R.id.nav_slideshow) {
            viewPager.setCurrentItem(2);
            setTitle(item.getTitle());

        } else if (id == R.id.nav_share) {
            setTitle(item.getTitle());

        } else if (id == R.id.nav_send) {
            setTitle(item.getTitle());

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }

    @Override
    public void onItemClicked(int recipeId) {
        RecipeFragment newRecipeFragment = new RecipeFragment();
        Bundle args = new Bundle();
        args.putInt("recipeId", recipeId);
        newRecipeFragment.setArguments(args);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.mainContainer, newRecipeFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onGridViewItemClicked() {
        HomeRecipeFragment newHomeRecipeFragment = new HomeRecipeFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.mainContainer, newHomeRecipeFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onInfoWindowCustomClick() {
        HomeRecipeFragment newHomeRecipeFragment = new HomeRecipeFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.mainContainer, newHomeRecipeFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private class MyPagerAdapter extends FragmentPagerAdapter {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new HomePageFragment();
                case 1:
                    return new FavouritesFragment();
                case 2:
                    return new ShoppingCartFragment();
                default:
                    return null;
            }
        }
    }

    public void saveItemInDb(View v){
        Toast.makeText(this, "Saved in Db",
                Toast.LENGTH_SHORT).show();

    }

}
