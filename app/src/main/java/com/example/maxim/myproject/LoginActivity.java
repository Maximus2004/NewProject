package com.example.maxim.myproject;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;

public class LoginActivity extends AppCompatActivity {
    Button btnLogin;
    EditText pass, login;
    // Attempt to invoke virtual method 'android.content.SharedPreferences android.content.Context.getSharedPreference
    // выше не полная информация об ошибки, нужно весь текст:
    // Caused by: java.lang.NullPointerException: Attempt to invoke virtual method 'android.content.SharedPreferences android.content.Context.getSharedPreferences(java.lang.String, int)' on a null object
    // Что примерно: ошибка нулового указателя: пытаешься вызвать метод у объекта, который null.
    // Причина: объект context еще не готов к использованию. Надо перенести этот код в место вызыва или в onCreate
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // вот так
        sharedPreferences = getSharedPreferences("ALL_APP", MODE_PRIVATE);

        View.OnClickListener oclBtnReg = new View.OnClickListener() {
            @SuppressLint("WrongViewCast")
            @Override
            public void onClick(View v) {
                pass = findViewById(R.id.password);
                login = findViewById(R.id.email);
                String passT = pass.getText().toString();
                String loginT = login.getText().toString();
                // или так
                // SharedPreferences sharedPreferences = getSharedPreferences("ALL_APP", MODE_PRIVATE);
                String savedLogin = sharedPreferences.getString(ActivityReg.SAVED_LOGIN, "");
                String savedPassword = sharedPreferences.getString(ActivityReg.SAVED_PASSWORD, "");
                //pass.setText(savedLogin);
                //login.setText(savedPassword);
                if (loginT.equals(savedLogin) && passT.equals(savedPassword)) {
                    Toast toast2 = Toast.makeText(getApplicationContext(),
                            "Авторизация успешно пройдена!", Toast.LENGTH_LONG);
                    toast2.show();
                } else {
                    Toast toast3 = Toast.makeText(getApplicationContext(),
                            "Такого пароля или имени пользователя не существует в системе", Toast.LENGTH_LONG);
                    toast3.show();
                }
            }
        };
        btnLogin = findViewById(R.id.email_sign_in_button);
        btnLogin.setOnClickListener(oclBtnReg);
    }
}


