package com.smh.szyproject.test.ble;

import android.os.Bundle;
import android.view.View;

import com.clj.fastble.BleManager;
import com.clj.fastble.callback.BleScanCallback;
import com.clj.fastble.data.BleDevice;
import com.clj.fastble.scan.BleScanRuleConfig;
import com.hjq.permissions.Permission;
import com.smh.szyproject.R;
import com.smh.szyproject.aop.Permissions;
import com.smh.szyproject.common.base.BaseActivity;
import com.smh.szyproject.other.utils.L;

import java.util.List;

/**
 * author : smh
 * date   : 2020/11/4 9:44
 * desc   : https://blog.csdn.net/gaoxiaoweiandy/article/details/82958204
 */
public class FastBleActivity extends BaseActivity {

    LifeCircle chromoeter;

    @Override
    public int getLayoutId() {
        return R.layout.test_activity_test;
    }

    @Permissions({Permission.ACCESS_COARSE_LOCATION, Permission.ACCESS_FINE_LOCATION})
    @Override
    public void init(Bundle savedInstanceState) {
        getLifecycle().addObserver(new LifeCircle(this));

        //规则配置
        BleScanRuleConfig scanRuleConfig = new BleScanRuleConfig.Builder()
//                    .setServiceUuids(serviceUuids)      // 只扫描指定的服务的设备，可选
//                    .setDeviceName(true, names)         // 只扫描指定广播名的设备，可选
//                    .setDeviceMac(mac)                  // 只扫描指定mac的设备，可选
//                    .setAutoConnect(isAutoConnect)      // 连接时的autoConnect参数，可选，默认false
                .setScanTimeOut(10000)              // 扫描超时时间，可选，默认10秒
                .build();
        BleManager.getInstance().initScanRule(scanRuleConfig);


        findViewById(R.id.tv_next).setOnClickListener((View v) -> {
            if (BleManager.getInstance().isSupportBle()) {
                //主动打开蓝牙
                BleManager.getInstance().enableBluetooth();

                findViewById(R.id.tv_next).postDelayed(() -> {
                    //伪代码  阻塞线程几秒后再链接

                }, 1000);
//                if(BleManager.getInstance().isBlueEnable()){
//                }else{
//                    L.e("蓝牙没打开");
//                }
            } else {
                L.e("不支持BLE");
            }
        });

        //开始连接
        findViewById(R.id.scan).setOnClickListener((View v) -> {

            BleManager.getInstance().scan(new BleScanCallback() {
                @Override
                public void onScanStarted(boolean success) {
//                    L.e("扫描动作是否开启成功:"+success);
                }

                @Override
                public void onLeScan(BleDevice bleDevice) {
                    //扫描过程中所有被扫描到的结果回调。由于扫描及过滤的过程是在工作线程中的，此方法也处于工作线程中。同一个设备会在不同的时间，携带自身不同的状态（比如信号强度等），出现在这个回调方法中，出现次数取决于周围的设备量及外围设备的广播间隔。l.
//                   L.e("扫到的:"+bleDevice.getName());
                }

                @Override
                public void onScanning(BleDevice bleDevice) {
                    //扫描过程中的所有过滤后的结果回调。与onLeScan区别之处在于：它会回到主线程；同一个设备只会出现一次；出现的设备是经过扫描过滤规  则过滤后的设备
                }

                @Override
                public void onScanFinished(List<BleDevice> scanResultList) {
                    //本次扫描时段内所有被扫描且过滤后的设备集合。它会回到主线程，相当于onScanning设备之和。
                    for (BleDevice info : scanResultList) {
                        L.e("=======================");
                        L.e("蓝牙广播名:" + info.getName());
                        L.e("key:" + info.getKey());
                        L.e("蓝牙Mac地址:" + info.getMac());
                        L.e("Device:" + info.getDevice());
                        L.e("信号强度:" + info.getRssi());
                        L.e("被扫描到时候携带的广播数据:" + info.getScanRecord());
                        L.e("TimestampNanos:" + info.getTimestampNanos());
                    }
                }
            });
        });


//        //先点击点我 扫描打开蓝牙
//        findViewById(R.id.scan).setOnClickListener((View v)->{
//            BleManager.getInstance().scan(new BleScanCallback() {
//                @Override
//                public void onScanStarted(boolean success) {
//                    L.e("扫描动作是否开启成功:"+success);
//                }
//
//                @Override
//                public void onLeScan(BleDevice bleDevice) {
//                //扫描过程中所有被扫描到的结果回调。由于扫描及过滤的过程是在工作线程中的，此方法也处于工作线程中。同一个设备会在不同的时间，携带自身不同的状态（比如信号强度等），出现在这个回调方法中，出现次数取决于周围的设备量及外围设备的广播间隔。l.
//                    L.e("扫到的:"+bleDevice.getName());
//                }
//
//                @Override
//                public void onScanning(BleDevice bleDevice) {
//                    //扫描过程中的所有过滤后的结果回调。与onLeScan区别之处在于：它会回到主线程；同一个设备只会出现一次；出现的设备是经过扫描过滤规则过滤后的设备
//                }
//
//                @Override
//                public void onScanFinished(List<BleDevice> scanResultList) {
//                }
//        });


    }
}
