package com.example.signup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.shashank.sony.fancytoastlib.FancyToast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
private EditText edtLoginEmail,edtLoginPassword;
private Button btnLogin,btnLoginSignUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setTitle("Login");

        edtLoginEmail = findViewById(R.id.edtLoginEmail);
        edtLoginPassword = findViewById(R.id.edtLoginPassword);

        btnLogin = findViewById(R.id.btnLogin);
        btnLoginSignUp = findViewById(R.id.btnLoginSignUp);

        btnLoginSignUp.setOnClickListener(this);
        btnLogin.setOnClickListener(this);

        if (ParseUser.getCurrentUser() != null){
            ParseUser.getCurrentUser().logOut();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnLoginSignUp:

                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
                break;

            case R.id.btnLogin:
                ParseUser.logInInBackground(edtLoginEmail.getText().toString(), edtLoginPassword.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if (user != null && e == null){
                            FancyToast.makeText(LoginActivity.this,user.get("username")+ " is Login Successfully",
                                    FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show();
                            transitionToSocialMediaActivity();
                           // Intent intent= new Intent(LoginActivity.this,WelcomActivity.class);
                           // startActivity(intent);
                        }else
                            FancyToast.makeText(LoginActivity.this,e.getMessage(),
                                    FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();

                    }
                });

                break;
        }

    }

    private void transitionToSocialMediaActivity() {
        Intent intent = new Intent(this,SocialMediaActivity.class);
        startActivity(intent);
    }
}
