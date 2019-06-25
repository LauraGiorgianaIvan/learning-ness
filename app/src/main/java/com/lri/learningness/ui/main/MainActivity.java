package com.lri.learningness.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.lri.learningness.R;
import com.lri.learningness.base.fragment.BottomControlFragment;
import com.lri.learningness.database.tutorial.TutorialDatabase;
import com.lri.learningness.database.user.UserDatabase;
import com.lri.learningness.navigation.FragNavigator;
import com.lri.learningness.tools.prefs.SharedPrefs;
import com.lri.learningness.ui.favorites.FavoritesFragment;
import com.lri.learningness.ui.home.HomeFragment;
import com.lri.learningness.ui.settings.SettingsFragment;
import com.ncapdevi.fragnav.FragNavController;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private MainRepository repository;
    private FragNavigator navController;
    private BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bind();
        initRootFragments();
        setupBottomNavItemListener();
    }

    private void bind() {
        repository = new MainRepository(this);
        navController = new FragNavigator(getSupportFragmentManager());
        bottomNavigationView = findViewById(R.id.bottom_navigation_view);

    }

    private void initRootFragments() {
        ArrayList<Fragment> rootFragments = new ArrayList<>();
        rootFragments.add(new HomeFragment());

        navController.navController().setRootFragments(rootFragments);
        navController.navController().initialize(FragNavController.TAB1, null);
    }

    private void setupBottomNavItemListener() {
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            Fragment currentFragment = navController.navController().getCurrentFrag();
            switch (item.getItemId()) {
                case R.id.menu_item_home: {
                    if (!(currentFragment instanceof HomeFragment)) {
                        navController.replace(new HomeFragment());
                    }
                    return true;
                }

                case R.id.menu_item_favorites: {
                    if (!(currentFragment instanceof FavoritesFragment)) {
                        navController.replace(new FavoritesFragment());
                    }
                    return true;
                }

                case R.id.menu_item_settings: {
                    if (!(currentFragment instanceof SettingsFragment)) {
                        navController.replace(new SettingsFragment());
                    }
                    return true;
                }

                default: {
                    if (!(currentFragment instanceof HomeFragment)) {
                        navController.replace(new HomeFragment());
                    }
                    return true;
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        Fragment currentFrag = navController.navController().getCurrentFrag();

        if (currentFrag instanceof HomeFragment) {
            super.onBackPressed();
        } else if (currentFrag instanceof BottomControlFragment) {
            navController.replace(new HomeFragment());
            bottomNavigationView.setSelectedItemId(R.id.menu_item_home);
        } else {
            navController.pop();
        }
    }

    public FragNavigator fragNav() {
        return navController;
    }


    public TutorialDatabase tutorialDb() {
        return repository.tutorialDb();
    }

    public UserDatabase userDb() {
        return repository.userDb();
    }

    public SharedPrefs prefs() {
        return repository.prefs();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        repository.closeDb();
    }
}
