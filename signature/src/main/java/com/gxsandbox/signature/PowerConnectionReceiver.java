package com.gxsandbox.signature;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;

import com.artech.base.services.Services;

public class PowerConnectionReceiver extends BroadcastReceiver {

    final static String NAME = PowerConnectionReceiver.class.getName();

    @Override
    public void onReceive(Context context, Intent intent) {

        // https://developer.android.com/reference/android/os/BatteryManager.html

        int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
        boolean isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING ||
                status == BatteryManager.BATTERY_STATUS_FULL;

        int chargePlug = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
        boolean usbCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_USB;
        boolean acCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_AC;

        Services.Log.debug(NAME,"isCharging:'"+isCharging+"' chargePlug:"+chargePlug +"' usbCharge:"+usbCharge+"' acCharge:"+acCharge); //$NON-NLS-1$

    }
}