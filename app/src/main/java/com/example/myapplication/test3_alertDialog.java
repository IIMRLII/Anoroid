package com.example.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class test3_alertDialog extends Activity {

    private Button btn = null;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test3_buttons);

        btn = (Button)findViewById(R.id.button6);
        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(test3_alertDialog.this);

                AlertDialog dialog = builder.create();
                View dialogView = View.inflate(test3_alertDialog.this, R.layout.test3_alertdialog, null);
                dialog.setView(dialogView);
                dialog.show();

            }
        });
    }
}
