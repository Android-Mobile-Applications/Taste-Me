package com.example.nitro.tasteme;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class RegisterActivity  extends AppCompatActivity  {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Button register = (Button) findViewById(R.id.sign_up_button);
        //register.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View v) {
        //        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
        //        RegisterActivity.this.startActivity(intent);
        //    }
        //});

    }
}
