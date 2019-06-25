package com.lri.learningness.navigation;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.lri.learningness.R;
import com.ncapdevi.fragnav.FragNavController;

public class FragNavigator {
    private FragNavController navController;

    public FragNavigator(FragmentManager supportFragmentManager) {
        navController = new FragNavController(supportFragmentManager, R.id.fragment_container);
    }

    public void replace(Fragment fragment) {
        navController.replaceFragment(fragment);
    }

    public void replace(Fragment fragment, Bundle args) {
        fragment.setArguments(args);
        replace(fragment);
    }

    public void navigate(Fragment fragment) {
        navController.pushFragment(fragment);
    }

    public void navigate(Fragment fragment, Bundle args) {
        fragment.setArguments(args);
        navigate(fragment);
    }

    public void pop() {
        navController.popFragment();
    }

    public int size() {
        return navController.getSize();
    }

    public FragNavController navController() {
        return this.navController;
    }

}