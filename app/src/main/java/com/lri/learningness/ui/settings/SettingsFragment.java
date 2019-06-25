package com.lri.learningness.ui.settings;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

import com.google.android.material.button.MaterialButton;
import com.lri.learningness.R;
import com.lri.learningness.base.fragment.BottomControlFragment;
import com.lri.learningness.model.tutorials.Tutorial;
import com.lri.learningness.model.user.User;
import com.lri.learningness.ui.login.LoginActivity;

import java.util.List;

public class SettingsFragment extends BottomControlFragment {
    private MaterialButton btnLogout;
    private AppCompatTextView tvUser;
    private AppCompatTextView tvCourses;
    private AppCompatTextView tvHelp;
    private AppCompatTextView tvRegisteredDate;
    private MaterialButton btnDeleteAccount;
    private AppCompatTextView tvAdminRights;

    private User user;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        user = prefs().getCurrentUser();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragment = inflater.inflate(R.layout.settings_fragment, container, false);
        bindView(fragment);
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
        handleListeners();
    }

    private void bindView(View view) {
        btnLogout = view.findViewById(R.id.btn_logout);
        tvUser = view.findViewById(R.id.tv_settings_username);
        tvCourses = view.findViewById(R.id.tv_fav_courser);
        tvHelp = view.findViewById(R.id.tv_settings_help);
        tvRegisteredDate = view.findViewById(R.id.tv_registered_date);
        btnDeleteAccount = view.findViewById(R.id.btn_delete_account);
        tvAdminRights = view.findViewById(R.id.tv_admin_rights);
    }

    private void initData() {
        if (user != null) {
            tvUser.setText(user.getUsername());
            tvRegisteredDate.setText(user.getDateRegistered());
            tvAdminRights.setCompoundDrawablesWithIntrinsicBounds(0, 0,
                    user.isAdmin() ? R.drawable.ic_checked_green_24dp : R.drawable.ic_error, 0);
            tvCourses.setCompoundDrawablesWithIntrinsicBounds(0, 0,
                    checkFavorites() ? R.drawable.ic_checked_green_24dp : R.drawable.ic_error, 0);
        }
    }

    private boolean checkFavorites() {
        if (user != null) {
            List<Tutorial> tutorials = tutorialDb().getUserTutorials(user.getUsername());
            for (Tutorial t : tutorials) {
                if (t.isFavorite()) {
                    return true;
                }
            }
        }
        return false;

    }

    private void handleListeners() {
        btnLogout.setOnClickListener(v -> showLogoutAlert());
        btnDeleteAccount.setOnClickListener(v -> showDeleteAccountDialog());
    }

    private void showLogoutAlert() {
        new AlertDialog.Builder(getContext()).setMessage(R.string.want_to_logout)
                .setPositiveButton(R.string.yes, (dialog, which) ->
                        logout()).setNegativeButton(R.string.no, null)
                .show();
    }

    private void showDeleteAccountDialog() {
        new AlertDialog.Builder(getContext()).setMessage(R.string.want_to_delete_account)
                .setPositiveButton(R.string.yes, (dialog, which) ->
                        deleteAccount()).setNegativeButton(R.string.no, null)
                .show();
    }

    private void logout() {
        Activity activity = getActivity();
        if (activity != null) {
            prefs().clear();

            startActivity(new Intent(activity, LoginActivity.class));
            activity.finish();
        }
    }

    private void deleteAccount() {
        userDb().deleteUser(user);
        logout();
    }
}
