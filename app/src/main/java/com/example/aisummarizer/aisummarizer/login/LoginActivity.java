package com.example.aisummarizer.aisummarizer.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aisummarizer.aisummarizer.R;
import com.example.aisummarizer.aisummarizer.Registration.RegistrationActivity;
import com.example.aisummarizer.aisummarizer.home.MainActivity;
import com.example.aisummarizer.aisummarizer.service_calls.AISummarizer;
import com.example.aisummarizer.aisummarizer.service_calls.request_builder.LoginRequest;
import com.example.aisummarizer.aisummarizer.service_calls.response_builder.AuthResponse;
import com.example.aisummarizer.aisummarizer.super_class.SuperCompatActivity;
import com.example.aisummarizer.aisummarizer.utils.ApplicationHolder;
import com.example.aisummarizer.aisummarizer.utils.DialogListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class LoginActivity extends SuperCompatActivity {

    EditText userNameInput;
    EditText passwordInput;
    Button loginButton;
    TextView register;
    private ProgressBar mProgressBar;


    //Service interface
    AISummarizer aiSummarizer;

    // Retrofit instance
    Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initializeVariable();
    }

    private void initializeVariable() {
        userNameInput = (EditText) findViewById(R.id.userNameEditText);
        passwordInput = (EditText) findViewById(R.id.passwordEditText);
        loginButton = (Button) findViewById(R.id.loginButton);
        register = (TextView) findViewById(R.id.register);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);

        register.setText(Html.fromHtml("<p><u>Register here</u></p>"));

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences sharedPref = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("username", userNameInput.getText().toString());
                editor.putString("password", passwordInput.getText().toString());
                editor.apply();

                //        Toast.makeText(this,loginButton.getText().toString(),Toast.LENGTH_LONG).show();
//                Toast.makeText(this,R.string.loginCredentialsSaved,Toast.LENGTH_LONG).show();

                if (userNameInput.getText().toString().isEmpty()) {
                    DialogListener.createAlertDialog(LoginActivity.this, getResources().getString(R.string.error_email_required), true);
                } else if (passwordInput.getText().toString().isEmpty()) {
                    DialogListener.createAlertDialog(LoginActivity.this, getResources().getString(R.string.error_required_password), true);
                } else if (checkInternet()) {
                    mProgressBar.setVisibility(ProgressBar.VISIBLE);
                    authenticate(new LoginRequest(userNameInput.getText().toString(), passwordInput.getText().toString()));
                } else {
                    mProgressBar.setVisibility(ProgressBar.INVISIBLE);
                    DialogListener.createAlertDialog(LoginActivity.this, getResources().getString(R.string.oops), true);
                }

            }
        });

        retrofit = new Retrofit.Builder()
                .baseUrl(ApplicationHolder.baseUrl)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        aiSummarizer = retrofit.create(AISummarizer.class);
    }

    /*Service calls*/
    private void authenticate(LoginRequest request) {

        try {

            Call<AuthResponse> authResponse = aiSummarizer.login(request.getEmail(), request.getPassword());

            authResponse.enqueue(new Callback<AuthResponse>() {
                @Override
                public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {

                    AuthResponse body = response.body();
                    if (body.isSuccess())
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                SharedPreferences sharedPref = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPref.edit();

                                editor.putBoolean("isActive", true);
                                editor.apply();

                                Intent home_screen = new Intent(getApplicationContext(), MainActivity.class);
                                home_screen.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(home_screen);
                            }
                        });
                    else {
                        DialogListener.createAlertDialog(LoginActivity.this, body.getDescription(), true);
                    }
                }

                @Override
                public void onFailure(Call<AuthResponse> call, Throwable t) {
                    DialogListener.createAlertDialog(LoginActivity.this, t.getMessage(), true);
                }
            });

        } catch (Exception e) {

            DialogListener.createAlertDialog(LoginActivity.this, e.getMessage(), true);
        }

        mProgressBar.setVisibility(ProgressBar.INVISIBLE);
    }


    public void load(View view) {

        Intent home_screen = new Intent(getApplicationContext(), RegistrationActivity.class);
        startActivity(home_screen);

        /*SharedPreferences sharedPref = getSharedPreferences("userInfo", Context.MODE_PRIVATE);

        String userName = sharedPref.getString("username", "");
        String password = sharedPref.getString("password", "");

        Toast.makeText(this, (userName + " " + password), Toast.LENGTH_LONG).show();*/
    }
}
