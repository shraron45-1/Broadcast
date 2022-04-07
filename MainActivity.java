package com.example.ex7_broadcasttest;

import static androidx.constraintlayout.motion.widget.MotionScene.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.BatteryManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private IntentFilter intentFilter;
    private NetworkChangeReceiver networkChangeReceiver;
//    private BatteryStatusReceiver batteryStatusReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        intentFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
//        intentFilter.addAction("android.intent.action.ACTION_BATTERY_LOW");
//        intentFilter.addAction("android.intent.action.ACTION_BATTERY_OKAY");
//        intentFilter.addAction(Intent.ACTION_POWER_CONNECTED);
//        intentFilter.addAction(Intent.ACTION_POWER_DISCONNECTED);
//        batteryStatusReceiver =  new BatteryStatusReceiver();
//        registerReceiver(batteryStatusReceiver,intentFilter);

        intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        networkChangeReceiver = new NetworkChangeReceiver();
        registerReceiver(networkChangeReceiver, intentFilter);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
//        unregisterReceiver(batteryStatusReceiver);
        unregisterReceiver(networkChangeReceiver);
    }

   /* class  BatteryStatusReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action == Intent.ACTION_BATTERY_CHANGED) {
                int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
                if(level<30){
                    Toast.makeText(context,"电量过低",Toast.LENGTH_LONG).show();
                    Log.v("TAG", "电量过低");
                }
                else if(level<31){
                    Toast.makeText(context,"退出省电模式",Toast.LENGTH_LONG).show();
                    Log.v("TAG", "退出省电模式");
                }
            }else if(action==Intent.ACTION_POWER_CONNECTED){
                Toast.makeText(context,"正在充电",Toast.LENGTH_LONG).show();
                Log.v("TAG", "正在充电");
            }else if(action==Intent.ACTION_POWER_DISCONNECTED){
                Toast.makeText(context,"充电器已移除",Toast.LENGTH_LONG).show();
                Log.v("TAG", "充电器已移除");
            }
        }
    }*/

    class NetworkChangeReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent){
            ConnectivityManager connectivityManager = (ConnectivityManager)
                    getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            /*if (networkInfo != null && networkInfo.isAvailable()) {//没有显示结果
                Toast.makeText(context, "network is available",
                        Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(context, "network is unavailable",
                        Toast.LENGTH_SHORT).show();
            }*/
            if(networkInfo != null&&networkInfo.isConnectedOrConnecting()){
                Toast.makeText(context, "network is available",
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "network is unavailable",
                        Toast.LENGTH_SHORT).show();
            }
//            Toast.makeText(context, "network changes", Toast.LENGTH_SHORT).show();
        }
    }
}