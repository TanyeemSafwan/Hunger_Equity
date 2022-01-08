package com.example.myapplication.hunger_equity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*RelativeLayout relativeLay= (RelativeLayout) findViewById(R.id.rel);
        relativeLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Login.class);
                startActivity(intent);
            }
        });*/
        NextActivity();


    }
    public void NextActivity()
    {
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {

                Intent Intent = new Intent(MainActivity.this, CharityRegister.class);
                MainActivity.this.startActivity(Intent);
                MainActivity.this.finish();
            }
        }, 1300);

    }
}