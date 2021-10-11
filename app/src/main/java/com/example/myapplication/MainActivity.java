package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.widget.Button;

import android.view.View;

public class MainActivity extends Activity {

    private Button btn = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button)findViewById(R.id.button1);
        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent_test3 = new Intent();
                intent_test3.setClass(MainActivity.this, test3_simpleAdapter.class);
                MainActivity.this.startActivity(intent_test3);
            }
        });

        btn = (Button)findViewById(R.id.button2);
        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent_test3_alertDialog = new Intent();
                intent_test3_alertDialog.setClass(MainActivity.this, test3_alertDialog.class);
                MainActivity.this.startActivity(intent_test3_alertDialog);
            }
        });

        btn = (Button)findViewById(R.id.button3);
        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent_test3_customMenu = new Intent();
                intent_test3_customMenu.setClass(MainActivity.this, test3_customMenu.class);
                MainActivity.this.startActivity(intent_test3_customMenu);
            }
        });
    }
}