package com.example.arsenko.test1;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Bundle;
import android.os.RemoteException;
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
    public Button aidlStartBtn;
    public Button aidlStopBtn;
    public Intent serviceIntent;
    public Button syncBtn;
    public TextView textView;

    AIDLService aidlService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et = findViewById(R.id.myET);
        bt = findViewById(R.id.myBT);
        secondActBt = findViewById(R.id.myBT2);
        recycleViewBtn = findViewById(R.id.resycleViewBtn);
        startServiceBtn = findViewById(R.id.startServiceBtn);
        stopServiceBtn = findViewById(R.id.stopServiceBtn);
        aidlStartBtn = findViewById(R.id.aidlStartBtn);
        aidlStopBtn = findViewById(R.id.aidlStopBtn);
        syncBtn = findViewById(R.id.syncBtn);
        textView = findViewById(R.id.textView);


        serviceIntent = new Intent();
        serviceIntent.setComponent(new ComponentName(MainActivity.this, AIDLService.class));


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

        aidlStartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startService(serviceIntent);
                Toast.makeText(MainActivity.this, "started", Toast.LENGTH_SHORT).show();

                bindService(serviceIntent, serviceConnection, Context.BIND_AUTO_CREATE);
                Toast.makeText(MainActivity.this, "bound", Toast.LENGTH_SHORT).show();

            }
        });

        aidlStopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopService(serviceIntent);
                Toast.makeText(MainActivity.this, "stopped", Toast.LENGTH_SHORT).show();

                if(binder != null){
                    unbindService(serviceConnection);
                    Toast.makeText(MainActivity.this, "unbind", Toast.LENGTH_SHORT).show();
                    binder = null;

                }

            }
        });

        syncBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(binder != null){
                    try{
                        binder.setData(et.getText().toString());
                        Toast.makeText(MainActivity.this, "sync", Toast.LENGTH_SHORT).show();
                        } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
//    public void StartOnClick(View v){
//        startService(serviceIntent);
//        Toast.makeText(MainActivity.this, "start", Toast.LENGTH_SHORT).show();
//
//        bindService(serviceIntent, serviceConnection, Context.BIND_AUTO_CREATE);
//        Toast.makeText(MainActivity.this, "bound", Toast.LENGTH_SHORT).show();
//
//    }
//
//    public void StopOnClick(View v){
//        stopService(serviceIntent);
//        Toast.makeText(MainActivity.this, "service stopped", Toast.LENGTH_SHORT).show();
//        if(binder != null) {
//            unbindService(serviceConnection);
//            binder = null;
//            wasBound = false;
//            Toast.makeText(MainActivity.this, "service disconnected", Toast.LENGTH_SHORT).show();
//        }
//    }
//
//    public void SyncOnClick(View v){
//        if(binder!=null){
//            try{
//                binder.setData(et.getText().toString());
//                Toast.makeText(MainActivity.this, "synchronized", Toast.LENGTH_SHORT).show();
//            }catch(RemoteException e){
//                e.printStackTrace();
//            }
//        }
//    }

    boolean wasBound;   // флег, чи законекчені сервіс і актівіті


    private ServiceConnection connection  = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName className, IBinder service) {

            Toast.makeText(MainActivity.this, "Service connected", Toast.LENGTH_SHORT).show();
            wasBound = true;

        }

        @Override
        public void onServiceDisconnected(ComponentName className) {
            Toast.makeText(MainActivity.this, "Service disconnected", Toast.LENGTH_SHORT).show();
            wasBound = false;

        }
    };

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder mService) {
            System.out.println("Connected"+mService);
            binder = IMyAidlInterface.Stub.asInterface(mService);
           // aidlService  = AIDLService.Stub.asInterface(mService);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

           // aidlService = null;

        }
    };  IMyAidlInterface binder = null;


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(wasBound) {
            unbindService(connection);
            wasBound = false;
        }
        if (binder != null){
            unbindService(serviceConnection);
            binder = null;
        }
    }
}
