package com.example.aisummarizer.aisummarizer.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aisummarizer.aisummarizer.R;
import com.example.aisummarizer.aisummarizer.home.MainActivity;

public class LoginActivity extends AppCompatActivity {

    EditText userNameInput;
    EditText passwordInput;
    Button loginButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.initializeVariable();
    }

    private void initializeVariable(){
        Button login_button = (Button) findViewById(R.id.loginButton);
        this.userNameInput = (EditText) findViewById(R.id.userNameEditText);
        this.passwordInput = (EditText) findViewById(R.id.passwordEditText);
        this.loginButton = (Button) findViewById(R.id.loginButton);

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences sharedPref = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("username",userNameInput.getText().toString());
                editor.putString("password",passwordInput.getText().toString());
                editor.apply();
//        Toast.makeText(this,loginButton.getText().toString(),Toast.LENGTH_LONG).show();
//                Toast.makeText(this,R.string.loginCredentialsSaved,Toast.LENGTH_LONG).show();

                Intent home_screen = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(home_screen);
            }
        });
    }



    public void load(View view){
        SharedPreferences sharedPref = getSharedPreferences("userInfo", Context.MODE_PRIVATE);

        String userName = sharedPref.getString("username","");
        String password = sharedPref.getString("password","");

         Toast.makeText(this, (userName + " " + password), Toast.LENGTH_LONG).show();
    }
}
