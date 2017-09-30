package com.example.arsenko.test1;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    public TextView tv;
    public EditText et;
    public Button bt;
    public Button secondActBt;
    public Button recycleViewBtn;
    public Button startServiceBtn;
    public Button stopServiceBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //tv = (TextView) findViewById(R.id.myTV);
        //tv.setText("Hello, Android");

        et = findViewById(R.id.myET);
        bt = findViewById(R.id.myBT);
        secondActBt = findViewById(R.id.myBT2);
        recycleViewBtn = findViewById(R.id.resycleViewBtn);
        startServiceBtn = findViewById(R.id.startServiceBtn);
        stopServiceBtn = findViewById(R.id.stopServiceBtn);


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

        startServiceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startService(new Intent(MainActivity.this, MyService.class));
                bindService(new Intent(MainActivity.this, MyService.class), connection ,Context.BIND_AUTO_CREATE );
            }

        });

        stopServiceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = (new Intent(MainActivity.this, MyService.class));
                stopService(intent);
                if(wasBound) {
                    unbindService(connection);
                    wasBound = false;
                    Toast.makeText(MainActivity.this, "disconnected", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    //MyService myService = null;
    boolean wasBound;   // флег, чи законекчені сервіс і актівіті


    private ServiceConnection connection  = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName className, IBinder service) {

           // MyService.MyBinder binder = (MyService.MyBinder) service;
            //myService = binder.getService();
            Toast.makeText(MainActivity.this, "Service connected", Toast.LENGTH_SHORT).show();
            wasBound = true;

        }

        @Override
        public void onServiceDisconnected(ComponentName className) {
            Toast.makeText(MainActivity.this, "Service disconnected", Toast.LENGTH_SHORT).show();
            wasBound = false;

        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(wasBound) {
            unbindService(connection);
            wasBound = false;
        }
    }
}
