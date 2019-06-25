package com.lri.learningness.base.fragment;

import androidx.fragment.app.Fragment;

import com.lri.learningness.database.tutorial.TutorialDatabase;
import com.lri.learningness.database.user.UserDatabase;
import com.lri.learningness.navigation.FragNavigator;
import com.lri.learningness.tools.prefs.SharedPrefs;
import com.lri.learningness.ui.main.MainActivity;

public class BaseFragment extends Fragment {

    protected FragNavigator fragNav() {
        if (getActivity() instanceof MainActivity) {
            return ((MainActivity) getActivity()).fragNav();
        }
        return new FragNavigator(getFragmentManager());
    }

    protected UserDatabase userDb() {
        if (getActivity() instanceof MainActivity) {
            return ((MainActivity) getActivity()).userDb();
        }
        return new UserDatabase(getContext());
    }

    protected TutorialDatabase tutorialDb() {
        if (getActivity() instanceof MainActivity) {
            return ((MainActivity) getActivity()).tutorialDb();
        }
        return new TutorialDatabase(getContext());
    }

    protected SharedPrefs prefs() {
        if (getActivity() instanceof MainActivity) {
            return ((MainActivity) getActivity()).prefs();
        }
        return new SharedPrefs();
    }
}
