package com.example.arsenko.test1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

    public static TextView tv;
    public static EditText et;
    public static Button bt;
    public static Button secondActBt;
    public static Button recycleViewBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //tv = (TextView) findViewById(R.id.myTV);
        //tv.setText("Hello, Android");

        et = (EditText) findViewById(R.id.myET);
        bt = (Button) findViewById(R.id.myBT);
        secondActBt = (Button) findViewById(R.id.myBT2);
        recycleViewBtn = (Button) findViewById(R.id.resycleViewBtn);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv.setText(tv.getText().toString() + " " + et.getText().toString());
            }
        });

        secondActBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("text", et.getText().toString());
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        recycleViewBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, RecycleViewActivity.class);
                startActivity(intent);
            }
        });

    }
}
