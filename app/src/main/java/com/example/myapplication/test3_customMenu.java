package com.example.myapplication;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class test3_customMenu extends AppCompatActivity {

    private TextView testtext = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test3_custommenu);

        testtext = (TextView) findViewById(R.id.textView15);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.test3_custommenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.font_10:
                testtext.setTextSize(10);
                return true;
            case R.id.font_20:
                testtext.setTextSize(20);
                return true;
            case R.id.font_30:
                testtext.setTextSize(30);
                return true;
            case R.id.font_40:
                testtext.setTextSize(40);
                return true;
            case R.id.commonMenu:
                Toast.makeText(test3_customMenu.this, "普通菜单项", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.red:
                testtext.setTextColor(Color.parseColor("#ff0000"));
                return true;
            case R.id.black:
                testtext.setTextColor(Color.parseColor("#000000"));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
