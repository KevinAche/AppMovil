package com.example.g1c2movil.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import com.example.g1c2movil.R;

public class MainActivity extends AppCompatActivity {
    private Button loginbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        loginbtn = (Button) findViewById(R.id.loginbtn);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirMenu();
            }
        });

    }

    private void abrirMenu() {
        Intent mainIntent = new Intent(MainActivity.this, Menu.class);
        finish();
        startActivity(mainIntent);
    }
}