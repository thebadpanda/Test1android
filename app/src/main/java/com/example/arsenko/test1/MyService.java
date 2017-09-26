package com.example.arsenko.test1;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.widget.EditText;
import android.widget.Toast;


public class MyService extends Service {

    IBinder myBinder = new MyBinder();

    MediaPlayer myMediaPlayer;

    public MyService() {
    }

    public class MyBinder extends Binder{

        MyService getService(){
            return MyService.this;
        }

    }

    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(this, "Service created", Toast.LENGTH_SHORT).show();
        myMediaPlayer = MediaPlayer.create(this,R.raw._old_yellow_bricks);
        myMediaPlayer.setLooping(false);

//       | TIMER |


//        final Toast mToast = null;
//
//        final int countStart = 30000;
//        int chastota = 1000;
//        int count = 0;
//
//
//         while(count != (countStart/100)){
//            new CountDownTimer(countStart, chastota) {
//                int count = 0;
//
//                public void onTick(long millisUntilFinished) {
//                    if (mToast != null) mToast.cancel();
//                    Toast.makeText(getApplicationContext(), "seconds remaining: " + millisUntilFinished / 1000, Toast.LENGTH_SHORT).show();
//                    count++;
//                }
//
//                public void onFinish() {
//                    if (mToast != null) mToast.cancel();
//                    Toast.makeText(getApplicationContext(), "Finished", Toast.LENGTH_SHORT).show();
//                }
//            }.start();
//        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "Service starts", Toast.LENGTH_SHORT).show();
        myMediaPlayer.start();

        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Service finished", Toast.LENGTH_SHORT).show();
        myMediaPlayer.stop();
    }

    @Override
    public IBinder onBind(Intent intent) {

        Toast.makeText(this, "binding", Toast.LENGTH_SHORT).show();
        return myBinder;

//        throw new UnsupportedOperationException("Not yet implemented");
    }

}
