package com.lri.learningness.ui.splash;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.lri.learningness.database.user.UserDatabase;
import com.lri.learningness.model.user.User;
import com.lri.learningness.tools.prefs.SharedPrefs;
import com.lri.learningness.ui.login.LoginActivity;
import com.lri.learningness.ui.main.MainActivity;

public class SplashActivity extends AppCompatActivity {
    private SharedPrefs prefs;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefs = new SharedPrefs();
        checkUser();
    }

    private void checkUser() {
        User user = prefs.getCurrentUser();
        if (user != null) {
            startActivity(new Intent(this, MainActivity.class));
        } else {
            startActivity(new Intent(this, LoginActivity.class));
        }
        finish();
    }
}
