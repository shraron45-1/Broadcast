package com.example.ex7_broadcasttest;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.util.Log;

public class BatteryLevelReceiver extends Activity {
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//设置意图过滤器
        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
//注册广播接收者，因为BatteryManager发送的是sticky形式的intent，所以接收者可以为空
        Intent batteryStatus = registerReceiver(null, intentFilter);
        int status = batteryStatus.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
        //是否处于充电状态
        boolean isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING;
        //充电方式
        int chargePlug = batteryStatus.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
        boolean usbCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_USB;
        boolean acCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_AC;
        //判断当前电池电量
        int level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        int scale = batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
        float batteryPct = level / (float) scale;
//打印相关信息
        Log.i("BatteryLevelReceiver", "isCharging:" + isCharging + "；"+ "usbCharge:" + usbCharge + "；" + "acCharge:" + acCharge);
        if (isCharging == true) {
//            Toast.makeText(context,"正在充电",Toast.LENGTH_LONG).show();
            Log.d("BatteryLevelReceiver", "正在充电");
        }
//        Toast.makeText(context,"电量："+batteryPct,Toast.LENGTH_LONG).show();
        Log.i("BatteryLevelReceiver", "电量：" + batteryPct);
        if (batteryPct <= 0.2) {
//            Toast.makeText(context,"电量较低",Toast.LENGTH_LONG).show();
            Log.d("BatteryLevelReceiver", "电量较低");
        }
        if (batteryPct > 0.2) {
//            Toast.makeText(context,"电量充足",Toast.LENGTH_LONG).show();
            Log.d("BatteryLevelReceiver", "电量充足");
        }
    }
}
