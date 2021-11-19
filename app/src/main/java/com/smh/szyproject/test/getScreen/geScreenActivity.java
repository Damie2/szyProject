package com.smh.szyproject.test.getScreen;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.speech.tts.TextToSpeech;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

import com.github.lzyzsd.jsbridge.BridgeWebView;
import com.github.lzyzsd.jsbridge.DefaultHandler;
import com.smh.szyproject.R;
import com.smh.szyproject.common.base.BaseActivity;
import com.smh.szyproject.other.utils.L;
import com.smh.szyproject.other.utils.Utils;
import com.smh.szyproject.other.utils.utilCode.ScreenUtils;
import com.smh.szyproject.test.fragment.guide.GuideActivity;
import com.umeng.commonsdk.UMConfigure;

import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import butterknife.BindView;
import butterknife.OnClick;

public class geScreenActivity extends BaseActivity {

    private static final String TALKBACK_SETTING_ACTIVITY_NAME = "com.android.talkback.TalkBackPreferencesActivity";
    private TextToSpeech tts;
    @BindView(R.id.tv_nextaaa)
    public Button tv_nextAA;

    @Override
    public int getLayoutId() {
        return R.layout.test_activity_test1;
    }

    @Override
    public void init(Bundle savedInstanceState) {


        tv_nextAA.setOnClickListener(v -> {
            try {
                String id = getDeviceId(this);
                if(TextUtils.isEmpty(id)){
                    showToast("空的");
                }else{
                    showToast("非空");
                }
            }catch (Exception e){
                e.printStackTrace();
                showToast(e.getMessage());
            }
        });
    }

    public static String getDeviceId(Context context) {


        String id;
        //android.telephony.TelephonyManager
        TelephonyManager mTelephony = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (mTelephony.getDeviceId() != null) {
            id = mTelephony.getDeviceId();
        } else {
            //android.provider.Settings; --解决在android 7.0的情况下，有权限getDeviceId()返回null的情形
            id = Settings.Secure.getString(context.getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
        }
        return id;
    }


    public static boolean accessibilityEnable(Context context) {
        boolean enable = false;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            try {
                AccessibilityManager manager = (AccessibilityManager) context.getSystemService(Context.ACCESSIBILITY_SERVICE);
                List<AccessibilityServiceInfo> serviceList = manager.getEnabledAccessibilityServiceList(AccessibilityServiceInfo.FEEDBACK_SPOKEN);
                for (AccessibilityServiceInfo serviceInfo : serviceList) {
                    String name = serviceInfo.getSettingsActivityName();
                    if (!TextUtils.isEmpty(name) && name.equals(TALKBACK_SETTING_ACTIVITY_NAME)) {
                        enable = true;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return enable;
    }


    public static boolean hasServicePermission(Context ct, Class serviceClass) {
        int ok = 0;
        try {
            ok = Settings.Secure.getInt(ct.getContentResolver(), Settings.Secure.ACCESSIBILITY_ENABLED);
        } catch (Settings.SettingNotFoundException e) {
        }

        TextUtils.SimpleStringSplitter ms = new TextUtils.SimpleStringSplitter(':');
        if (ok == 1) {
            String settingValue = Settings.Secure.getString(ct.getContentResolver(), Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES);
            if (settingValue != null) {
                ms.setString(settingValue);
                while (ms.hasNext()) {
                    String accessibilityService = ms.next();
                    if (accessibilityService.contains(serviceClass.getSimpleName())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static void openServicePermissonCompat(final Context ct, final Class service) {

        try {
            openServicePermission(ct, service);
        } catch (Exception e) {
            e.printStackTrace();
            if (!hasServicePermission(ct, service)) {
                jumpSystemSetting(ct);
            }
        }
    }

    public static void jumpSystemSetting(Context ct) {
        // jump to setting permission
        Intent intent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        ct.startActivity(intent);
    }

    public static void openServicePermission(Context ct, Class serviceClass) {
        Set<ComponentName> enabledServices = getEnabledServicesFromSettings(ct, serviceClass);
        if (null == enabledServices) {
            return;
        }
        ComponentName toggledService = ComponentName.unflattenFromString(ct.getPackageName() + "/" + serviceClass.getName());
        final boolean accessibilityEnabled = true;
        enabledServices.add(toggledService);
        // Update the enabled services setting.
        StringBuilder enabledServicesBuilder = new StringBuilder();
        for (ComponentName enabledService : enabledServices) {
            enabledServicesBuilder.append(enabledService.flattenToString());
            enabledServicesBuilder.append(":");
        }
        final int enabledServicesBuilderLength = enabledServicesBuilder.length();
        if (enabledServicesBuilderLength > 0) {
            enabledServicesBuilder.deleteCharAt(enabledServicesBuilderLength - 1);
        }
        Settings.Secure.putString(ct.getContentResolver(), Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES, enabledServicesBuilder.toString());
        // Update accessibility enabled.
        Settings.Secure.putInt(ct.getContentResolver(), Settings.Secure.ACCESSIBILITY_ENABLED, accessibilityEnabled ? 1 : 0);
    }


    public static Set<ComponentName> getEnabledServicesFromSettings(Context context, Class serviceClass) {
        String enabledServicesSetting = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES);
        if (enabledServicesSetting == null) {
            enabledServicesSetting = "";
        }
        Set<ComponentName> enabledServices = new HashSet<ComponentName>();
        TextUtils.SimpleStringSplitter colonSplitter = new TextUtils.SimpleStringSplitter(':');
        colonSplitter.setString(enabledServicesSetting);
        while (colonSplitter.hasNext()) {
            String componentNameString = colonSplitter.next();
            ComponentName enabledService = ComponentName.unflattenFromString(componentNameString);
            if (enabledService != null) {
                if (enabledService.flattenToString().contains(serviceClass.getSimpleName())) {
                    return null;
                }
                enabledServices.add(enabledService);
            }
        }
        return enabledServices;
    }


}
