package com.example.myapplication;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.arsenko.test1.IMyAidlInterface;


public class MainActivity extends AppCompatActivity{

    public Button btnStart;
    public Button btnStop;
    public Button syncBtn;
    private Intent serviceIntent;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStart = (Button) findViewById(R.id.aidlStartBtn);
        btnStop = (Button) findViewById(R.id.aidlStopBtn);
        editText = (EditText) findViewById(R.id.eText);
        syncBtn = (Button) findViewById(R.id.syncBtn);

        serviceIntent = new Intent();
        serviceIntent.setComponent(new ComponentName("com.example.arsenko.test1", "com.example.arsenko.test1.AIDLService"));



//        btnStart.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startService(serviceIntent);
//                bindService(serviceIntent, serviceConnection, Context.BIND_AUTO_CREATE);
//                Toast.makeText(MainActivity.this, "start", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        btnStop.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                stopService(serviceIntent);
//                unbindService(serviceConnection);
//                binder = null;
//            }
//        });
//
//        syncBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(binder!=null){
//                    try{
//                        binder.setData(editText.getText().toString());
//                        Toast.makeText(MainActivity.this, "synchronized", Toast.LENGTH_SHORT).show();
//                    }catch(RemoteException e){
//                        e.printStackTrace();
//                    }
//                }
//            }
//        });
    }


    public void StartOnClick(View v){
        startService(serviceIntent);
        Toast.makeText(MainActivity.this, "start", Toast.LENGTH_SHORT).show();

        bindService(serviceIntent, serviceConnection, Context.BIND_AUTO_CREATE);
        Toast.makeText(MainActivity.this, "bound", Toast.LENGTH_SHORT).show();

    }

    public void StopOnClick(View v){
        stopService(serviceIntent);
        Toast.makeText(MainActivity.this, "service stopped", Toast.LENGTH_SHORT).show();
        if(wasBound) {
            unbindService(serviceConnection);
            wasBound = false;
            Toast.makeText(MainActivity.this, "service disconnected", Toast.LENGTH_SHORT).show();
        }



    }

    public void SyncOnClick(View v){
        if(binder!=null){
            try{
                binder.setData(editText.getText().toString());
                Toast.makeText(MainActivity.this, "synchronized", Toast.LENGTH_SHORT).show();
            }catch(RemoteException e){
                e.printStackTrace();
            }
        }
    }

    boolean wasBound;

    public ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            System.out.println("Connected"+service);
            binder = IMyAidlInterface.Stub.asInterface(service);
            wasBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            System.out.println("Disconnected");
            wasBound = false;
        }
    };

    private IMyAidlInterface binder = null;
}
