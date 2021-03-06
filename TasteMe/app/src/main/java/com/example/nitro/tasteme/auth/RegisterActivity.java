package com.example.nitro.tasteme.auth;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nitro.tasteme.R;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class RegisterActivity  extends AppCompatActivity  {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button register = (Button) findViewById(R.id.registerButton);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText email = (EditText) findViewById(R.id.emailField);
                EditText username = (EditText) findViewById(R.id.usernameField);
                EditText password = (EditText) findViewById(R.id.passwordField);
                EditText confirmPassword = (EditText) findViewById(R.id.confirmPasswordField);

                if (!password.getText().toString().equals(confirmPassword.getText().toString())) {
                    Context context = getApplicationContext();
                    CharSequence text = "Passwords doesn't match";
                    showToastrMessage(context, text);
                    return;
                } else if (username.getText().length() < 6 || username.getText().length() > 20) {
                    Context context = getApplicationContext();
                    CharSequence text = "Username must be between 6 and 20 character long";
                    showToastrMessage(context, text);
                    return;
                }

                ParseUser user = new ParseUser();

                user.setUsername(email.getText().toString());
                user.setPassword(password.getText().toString());
                user.setEmail(email.getText().toString());
                user.put("name", username.getText().toString());

                user.signUpInBackground(new SignUpCallback() {
                    public void done(ParseException e) {
                        if (e == null) {
                            Context context = getApplicationContext();
                            CharSequence text = "Sign up successful";

                            showToastrMessage(context, text);

                            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                            startActivity(intent);
                        } else {
                            Context context = getApplicationContext();
                            CharSequence text = "Ops, cannot sign up right now";
                            showToastrMessage(context, text);
                        }
                    }
                });
            }
        });

    }

    private void showToastrMessage(Context context, CharSequence message) {
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, message, duration);
        toast.setGravity(Gravity.TOP|Gravity.LEFT, 0, 0);
        toast.show();
    }
}
