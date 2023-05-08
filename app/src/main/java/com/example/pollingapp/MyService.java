package com.example.pollingapp;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MyService extends Service {

    final int INTERVAL_PERIOD = 3000;
    Timer timer = new Timer();
    static int num = 0;


    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate(){
        super.onCreate();
        Log.d("debug", "onCreate");
    }

    /*
     * onStartCommandはstartServiceを呼んだスレッドで実行されるので、
     * 直接長時間かかる処理を行うとUIがフリーズする
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        Log.d("debug", "onStartCommand");
        /*
         * TimerTask.run()のなかで実行されるTimerはTimerのThreadなので
         * UIパーツやUIスレッドで実行が必要な操作をするにはHandlerを使わなければいけない
         */
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                // ポーリングする内容
                Log.d("debug", "from_Timer_Hello");
                num++;
            }
        }, 0, INTERVAL_PERIOD);

        return START_STICKY;
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        if(timer != null){
            timer.cancel();
        }
        Log.d("debug", "onDestroy");
    }

}