package com.example.signup;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
private EditText edtxtEmail,edtxtUserName,edtxtPassword;
private Button btnSignUp,btnLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Sign Up");


        edtxtEmail = findViewById(R.id.edtxtEmail);
        edtxtPassword = findViewById(R.id.edtxtPassword);

        edtxtPassword.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                if(keyCode == keyEvent.KEYCODE_ENTER && keyEvent.getAction() == KeyEvent.ACTION_DOWN){
                    onClick(btnSignUp);
                }
                return false;
            }
        });

        edtxtUserName = findViewById(R.id.edtxtUserName);

        btnLogin= findViewById(R.id.btnLogin);
        btnSignUp= findViewById(R.id.btnSignUp);

        btnLogin.setOnClickListener(this);
        btnSignUp.setOnClickListener(this);

        if (ParseUser.getCurrentUser() != null){
            ParseUser.getCurrentUser().logOut();
            //transitionToSocialMediaActivity();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnSignUp:
                if (edtxtEmail.getText().toString().equals("") ||
                edtxtUserName.getText().toString().equals("")||
                edtxtPassword.getText().toString().equals("")){
                    FancyToast.makeText(MainActivity.this,   "Email, User Name, Password is Required!!",
                            FancyToast.LENGTH_SHORT, FancyToast.INFO, true).show();

                }else {
                    final ParseUser appUser = new ParseUser();
                    appUser.setEmail(edtxtEmail.getText().toString());
                    appUser.setUsername(edtxtUserName.getText().toString());
                    appUser.setPassword(edtxtPassword.getText().toString());

                    final ProgressDialog progressDialog = new ProgressDialog(this);
                    progressDialog.setMessage("Signing Up " + edtxtUserName.getText().toString());
                    progressDialog.show();
                    transitionToSocialMediaActivity();

                    appUser.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e == null) {
                                FancyToast.makeText(MainActivity.this, appUser.get("username") + " Sign Up Successfully",
                                        FancyToast.LENGTH_SHORT, FancyToast.SUCCESS, true).show();

                            } else {
                                FancyToast.makeText(MainActivity.this, "There was an Error " + e.getMessage(),
                                        FancyToast.LENGTH_SHORT, FancyToast.ERROR, true).show();
                            }
                            progressDialog.dismiss();
                        }

                    });
                }

                break;

            case R.id.btnLogin:
                Intent intent= new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);

                break;
        }
    }

    private void transitionToSocialMediaActivity() {
        Intent intent = new Intent(MainActivity.this,SocialMediaActivity.class);
        startActivity(intent);
    }

    public void tappedOnRootLayout(View view){
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
