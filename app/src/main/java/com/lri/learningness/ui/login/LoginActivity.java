package com.lri.learningness.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.button.MaterialButton;
import com.lri.learningness.R;
import com.lri.learningness.database.user.UserDatabase;
import com.lri.learningness.model.user.User;
import com.lri.learningness.model.user.UserType;
import com.lri.learningness.tools.prefs.SharedPrefs;
import com.lri.learningness.ui.main.MainActivity;

import java.util.Date;

public class LoginActivity extends AppCompatActivity {
    private UserDatabase userDb;
    private SharedPrefs prefs;
    private MaterialButton btnLogin;
    private AppCompatEditText etUsername;
    private AppCompatEditText etPassword;
    private AppCompatTextView tvRegister;
    private AppCompatEditText etRegisterUsername;
    private AppCompatEditText etRegisterPassword;
    private AppCompatEditText etRegisterConfirmPassword;
    private MaterialButton btnRegister;
    private ConstraintLayout clRegister;
    private ConstraintLayout clLogin;

    private User user;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        bind();
        bindView();
        handleListeners();
    }

    private void bind() {
        userDb = new UserDatabase(this);
        prefs = new SharedPrefs();
    }

    private void bindView() {
        btnLogin = findViewById(R.id.btn_login);
        etPassword = findViewById(R.id.et_password);
        etUsername = findViewById(R.id.et_username);
        etRegisterUsername = findViewById(R.id.et_register_username);
        etRegisterPassword = findViewById(R.id.et_register_password);
        etRegisterConfirmPassword = findViewById(R.id.et_register_confirm_password);
        btnRegister = findViewById(R.id.btn_register);
        tvRegister = findViewById(R.id.tv_register);
        clRegister = findViewById(R.id.cl_register);
        clLogin = findViewById(R.id.cl_login);
    }

    private void handleListeners() {
        btnLogin.setOnClickListener(v -> login());
        tvRegister.setOnClickListener(v -> showRegister(true));
        btnRegister.setOnClickListener(v -> register());
    }

    private void login() {
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();
        if (!username.isEmpty() && !password.isEmpty()) {
            user = userDb.getUserByUsernameAndPassword(username, password);
            if (user != null) {
                prefs.saveLoggedIn(user);
                startActivity(new Intent(this, MainActivity.class));
                finish();
            } else {
                Toast.makeText(this, R.string.user_doesnt_exists, Toast.LENGTH_SHORT).show();

            }
        } else {
            Toast.makeText(this, R.string.invalid_credentials, Toast.LENGTH_SHORT).show();
        }
    }

    private void register() {
        String username = etRegisterUsername.getText().toString();
        String password = etRegisterPassword.getText().toString();
        String confirmPassword = etRegisterConfirmPassword.getText().toString();

        if (!username.isEmpty()) {
            if (!password.isEmpty() && password.equals(confirmPassword)) {
                user = new User()
                        .setUsername(username)
                        .setPassword(password)
                        .setDateRegistered(new Date())
                        .setUserType(username.contains("admin") ? UserType.ADMIN : UserType.DEFAULT);

                userDb.insertUser(user);
                showRegister(false);
                completeLoginData(username, password);
            } else {
                Toast.makeText(this, R.string.password_not_match, Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, R.string.invalid_credentials, Toast.LENGTH_SHORT).show();
        }
    }

    private void showRegister(boolean val) {
        clLogin.setVisibility(val ? View.GONE : View.VISIBLE);
        clRegister.setVisibility(val ? View.VISIBLE : View.GONE);
    }

    private void completeLoginData(String username, String password) {
        etUsername.setText(username);
        etPassword.setText(password);
    }
}
