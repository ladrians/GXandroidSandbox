package com.gxsandbox.sandbox;

// Snippets from https://developer.android.com/training/monitoring-device-state/battery-monitoring.html

import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.artech.actions.ApiAction;
import com.artech.base.services.Services;
import com.artech.externalapi.ExternalApi;
import com.artech.externalapi.ExternalApiResult;

import java.util.List;

public class BatteryWrapper extends ExternalApi {

    final static String NAME = "BatteryEO";

    // API Method Names
    private static final String METHOD_STATUS = "Status";
    private static final String METHOD_CHARGING = "IsCharging";
    private static final String METHOD_USB_CHARGING = "UsbCharging";
    private static final String METHOD_AC_CHARGING = "AcCharging";
    private static final String METHOD_HEALTH = "Health";

    private Intent batteryStatus;

    public BatteryWrapper(ApiAction action) {
        super(action);

        IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        batteryStatus = getContext().registerReceiver(null, ifilter);
    }

    @Override
    public @NonNull ExternalApiResult execute(String method, List<Object> params) {

        switch (method) {
            case METHOD_STATUS:
                float status = status();
                return ExternalApiResult.success(status);
            case METHOD_CHARGING:
                boolean isCrg = isCharging();
                return ExternalApiResult.success(isCrg);
            case METHOD_USB_CHARGING:
                boolean isUsb = usbCharging();
                return ExternalApiResult.success(isUsb);
            case METHOD_AC_CHARGING:
                boolean isAc = acCharging();
                return ExternalApiResult.success(isAc);
            case METHOD_HEALTH:
                int health = health();
                return ExternalApiResult.success(health);
            default:
                return ExternalApiResult.failureUnknownMethod(this, method);
        }
    }

    private float status()
    {
        int level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        int scale = batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1);

        float batteryPct = level / (float)scale * 100;

        return batteryPct;
    }

    private boolean isCharging()
    {
        int status = batteryStatus.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
        boolean isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING ||
                status == BatteryManager.BATTERY_STATUS_FULL;
        return isCharging;
    }

    private boolean usbCharging()
    {
        int chargePlug = batteryStatus.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
        boolean usbCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_USB;
        return usbCharge;
    }

    private boolean acCharging()
    {
        int chargePlug = batteryStatus.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
        boolean acCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_AC;
        return acCharge;
    }

    private int health()
    {
        int health = batteryStatus.getIntExtra(BatteryManager.EXTRA_HEALTH,0);
        int result = 0;
        switch(health) {
            case BatteryManager.BATTERY_HEALTH_DEAD:
                result = 1;
                break;
            case BatteryManager.BATTERY_HEALTH_GOOD:
                result = 2;
                break;
            case BatteryManager.BATTERY_HEALTH_OVERHEAT:
                result = 4;
                break;
            case BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE:
                result = 3;
                break;
            case BatteryManager.BATTERY_HEALTH_UNKNOWN:
                result = 0;
                break;
            case BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE:
                result = 5;
                break;
        }
        return result;
    }

}
