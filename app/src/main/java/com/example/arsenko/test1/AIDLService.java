package com.example.arsenko.test1;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

public class AIDLService extends Service {
    public AIDLService() {
    }

    @Override
    public IBinder onBind(Intent intent) {

        return new IMyAidlInterface.Stub() {
            @Override
            public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

            }

            @Override
            public void setData(String data) throws RemoteException {
                AIDLService.this.data = data;

            }
        };
    }

    @Override
    public void onCreate(){
        super.onCreate();

        System.out.println("AIDL Service started");

        new Thread(){
            @Override
            public void run() {
                super.run();

                running= true;
                while(running){

                    System.out.println(data);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        System.out.println("Service destroyed");
        running=false;
    }
    private String data ="default";
    private boolean running = false;

}