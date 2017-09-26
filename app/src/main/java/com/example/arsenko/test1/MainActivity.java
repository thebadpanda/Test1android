package com.example.arsenko.test1;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    public static TextView tv;
    public static EditText et;
    public static Button bt;
    public static Button secondActBt;
    public static Button recycleViewBtn;
    public static Button startServiceBtn;
    public static Button stopServiceBtn;




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
        startServiceBtn = (Button) findViewById(R.id.startServiceBtn);
        stopServiceBtn = (Button) findViewById(R.id.stopServiceBtn);




//        bindService(new Intent(MainActivity.this, MyService.class), connection ,Context.BIND_AUTO_CREATE );


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
                startService(new Intent(MainActivity.this, MyService.class));
            }

        });

        stopServiceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = (new Intent(MainActivity.this, MyService.class));
                stopService(intent);
                if(bindFlag) {
                    unbindService(connection);
                    bindFlag = false;
                    Toast.makeText(MainActivity.this, "disconnected", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    MyService myService = null;
    boolean bindFlag;   // флег. чи виклакали бінд в сервісі


    private ServiceConnection connection  = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName className, IBinder service) {

            MyService.MyBinder binder = (MyService.MyBinder) service;
            myService = binder.getService();
            bindFlag = true;

        }

        @Override
        public void onServiceDisconnected(ComponentName className) {
            Toast.makeText(MainActivity.this, "Service disconnected", Toast.LENGTH_SHORT).show();
            bindFlag = false;

        }
    };

    @Override
    protected void onStart(){
        super.onStart();
        bindService(new Intent(MainActivity.this, MyService.class), connection ,Context.BIND_AUTO_CREATE );

    }

    @Override
    protected void onStop(){
        super.onStop();
        if(bindFlag) {
            unbindService(connection);
            bindFlag = false;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(bindFlag) {
            unbindService(connection);
            bindFlag = false;
        }
    }
}
