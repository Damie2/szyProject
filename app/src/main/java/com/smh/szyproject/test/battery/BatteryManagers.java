package com.smh.szyproject.test.battery;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;

import com.smh.szyproject.R;
import com.smh.szyproject.common.base.BaseActivity;
import com.smh.szyproject.other.utils.L;

/**
 * author : smh
 * date   : 2020/10/12 9:35
 * desc   : 电池管理信息
 */
public class BatteryManagers extends BaseActivity {
    private BroadcastReceiver batteryLevelRcvr;
    private IntentFilter batteryLevelFilter;

    @Override
    public int getLayoutId() {
        return R.layout.test_activity_battery;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        monitorBatteryState();
    }

    private void monitorBatteryState(){
        batteryLevelRcvr = new BroadcastReceiver(){

            @Override
            public void onReceive(Context context, Intent intent) {
                // TODO Auto-generated method stub
                StringBuilder sb = new StringBuilder();
                int rawlevel = intent.getIntExtra("level", -1);
                int scale = intent.getIntExtra("scale", -1);
                int status = intent.getIntExtra("status", -1);
                int health = intent.getIntExtra("health", -1);
                int level = -1;
                if(rawlevel >= 0 && scale > 0){
                    level = (rawlevel*100)/scale;
                }
                sb.append("电池电量: ");
                sb.append(level + "%\n");

                String healthStatus = "";
                switch (health) {
                    case BatteryManager.BATTERY_HEALTH_UNKNOWN:
                        healthStatus = "UNKNOWN";
                        break;
                    case BatteryManager.BATTERY_HEALTH_GOOD:
                        healthStatus = "GOOD";
                        break;
                    case BatteryManager.BATTERY_HEALTH_OVERHEAT:
                        healthStatus = "OVERHEAT";
                        break;
                    case BatteryManager.BATTERY_HEALTH_DEAD:
                        healthStatus = "DEAD";
                        break;
                    case BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE:
                        healthStatus = "OVER VOLTAGE";
                        break;
                    case BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE:
                        healthStatus = "UNSPECIFIED FAILURE";
                        break;
                    case BatteryManager.BATTERY_HEALTH_COLD:
                        healthStatus = "COLD";
                        break;

                }
                sb.append("健康度:" + healthStatus + "\n");

                String batteryStatus ="";
                switch (status) {
                    case BatteryManager.BATTERY_STATUS_UNKNOWN:
                        batteryStatus="[没有安装电池]";
                        break;
                    case BatteryManager.BATTERY_STATUS_CHARGING:
                        batteryStatus="[正在充电]";
                        break;
                    case BatteryManager.BATTERY_STATUS_FULL:
                        batteryStatus="[已经充满]";
                        break;
                    case BatteryManager.BATTERY_STATUS_DISCHARGING:
                        batteryStatus="[放电中]";
                        break;
                    case BatteryManager.BATTERY_STATUS_NOT_CHARGING:
                        batteryStatus="[未充电]";
                        break;
                    default:
                        if(level <= 10)
                            sb.append("[电量过低，请充电]");
                        else if (level <= 100) {
                            sb.append("[未连接充电器]");
                        }
                        break;

                }
                sb.append(batteryStatus);

                L.e("结果是:"+sb.toString());
            }

        };
        batteryLevelFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(batteryLevelRcvr, batteryLevelFilter);
    }


    private class BatteryReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            L.e("电池状态:" + intent.getStringExtra(BatteryManager.EXTRA_STATUS));
            L.e("电池健康状态:" + intent.getStringExtra(BatteryManager.EXTRA_HEALTH));
            L.e("电池电量:" + intent.getStringExtra(BatteryManager.EXTRA_LEVEL));
            L.e("充电方式:" + intent.getStringExtra(BatteryManager.EXTRA_PLUGGED));
            L.e("电池温度:" + intent.getStringExtra(BatteryManager.EXTRA_TEMPERATURE));
            L.e("电池技术:" + intent.getStringExtra(BatteryManager.EXTRA_TECHNOLOGY));
        }
    }
}
