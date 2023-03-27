package com.smh.szyproject.test.moniLocation;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.SystemClock;
import android.widget.TextView;

import com.smh.szyproject.R;
import com.smh.szyproject.common.base.BaseActivity;

import butterknife.BindView;

/**
 * @Author smh
 * @Date 2022/11/21 13:53
 */
public class MoniLocationActivity extends BaseActivity {
    @BindView(R.id.tv_nextaaa)
    TextView textView;
    LocationManager mLocationManager;

    @Override
    public int getLayoutId() {
        return R.layout.test_activity_test1;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        textView.setOnClickListener(v -> {
            try {
                initSdk();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void initSdk() {
        mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        mLocationManager.addTestProvider(LocationManager.GPS_PROVIDER,
                true,
                true,
                false,
                false,
                true,
                true,
                true,
                Criteria.POWER_HIGH, /* magic */Criteria.ACCURACY_FINE);
        mLocationManager.setTestProviderEnabled(LocationManager.GPS_PROVIDER,
                true);

//        Location loc = createLocation(39.85, 116.28, 50.f);//北京
        Location loc = createLocation(23.17, 113.27, 50.f);//广州
        loc.setElapsedRealtimeNanos(SystemClock.elapsedRealtimeNanos());
        loc.setTime(System.currentTimeMillis());
        mLocationManager.setTestProviderLocation(LocationManager.GPS_PROVIDER, loc);
    }


    private String PROVIDER = "gps";
    private double LAT = 37.377166;
    private double LNG = -122.086966;
    private float ACCURACY = 3.0f;

    public Location createLocation(double lat, double lng, float accuracy) {
        // Create a new Location
        Location newLocation = new Location(PROVIDER);
        newLocation.setLatitude(lat);//纬度
        newLocation.setLongitude(lng);//经度
        newLocation.setAccuracy(accuracy);
        return newLocation;
    }
}
