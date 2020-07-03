package com.smh.szyproject.other.utils;

import android.app.ActivityManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationCompat;

import com.smh.szyproject.net.RetrofitUtil;

import java.util.List;

import static android.app.Notification.VISIBILITY_SECRET;
import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Created by Administrator on 2018/7/23.
 */

public class NotificationHelp {

    private static NotificationHelp ourInstance;
    private Context context;
    private int id;

    public static NotificationHelp getInstance(Context context) {

        if (ourInstance == null) {
            synchronized (NotificationHelp.class) {
                if (ourInstance == null) {
                    ourInstance = new NotificationHelp(context);
                }
            }
        }
        return ourInstance;
    }




    public static NotificationHelp getInstance() {
        return ourInstance;
    }

    private NotificationHelp(Context context) {
        this.context = context;
    }

    public void createNotification(Context context, String title, String content, Intent intent, int iconID) {
//        boolean isPush = SPUtil.getBoolean("isPush", true, context);
//        if (!isPush) {
//            return;
//        }


//        Notification notification = null;
//        NotificationManager notificationManager = (NotificationManager) context.getSystemService
//                (NOTIFICATION_SERVICE);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);
//        /**
//         *  实例化通知栏构造器
//         */
//        Notification.Builder mBuilder = new Notification.Builder(context);
//        //设置标题
//        mBuilder.setContentTitle(title)
//                //设置内容
//                .setContentText(content)
//                //设置小图标
//                .setSmallIcon(iconID)
//                //让它被点击后自动清除
//                .setAutoCancel(true)
//                //设置通知时间
//                .setContentIntent(pendingIntent)
//                .setWhen(System.currentTimeMillis())
//                //设置通知方式，声音，震动，呼吸灯等效果，这里通知方式为声音
//                .setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            mBuilder.setChannelId(context.getPackageName());
//            NotificationChannel mChannel = new NotificationChannel(id, name, NotificationManager.IMPORTANCE_LOW);
//            notificationManager.createNotificationChannel(mChannel);
//            mBuilder.setChannelId(id).setContentTitle("活动").setContentText("您有一项新活动");
//
//        }
//
//        //发送通知请求
//        notificationManager.notify(++id, mBuilder.build());


        String name = "牛股通";
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        Notification notification = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel mChannel = new NotificationChannel(String.valueOf(id), name, NotificationManager.IMPORTANCE_LOW);
            mChannel .enableVibration(true);
            mChannel.getAudioAttributes();//获取系统通知响铃声音的配置
            mChannel.setVibrationPattern(new long[]{100, 100, 200});//设置震动模式
           // mChannel.setImportance(NotificationCompat.PRIORITY_DEFAULT);
            mChannel.canShowBadge();//桌面launcher的消息角标
            mChannel.setLockscreenVisibility(VISIBILITY_SECRET);//锁屏显示通知
            notificationManager.createNotificationChannel(mChannel);
            notification =new Notification.Builder(context)
                    .setChannelId(String.valueOf(id))
                    .setShowWhen(true)
                    .setContentTitle(title)
                    .setContentText(content)
                    .setAutoCancel(true)
                    .setWhen(System.currentTimeMillis())
                    .setContentIntent(pendingIntent)
                    .setSmallIcon(iconID).build();
        } else {
            Notification.Builder mBuilder = new Notification.Builder(context)
                    .setContentTitle(title)
                    .setContentText(content)
                    .setSmallIcon(iconID)
                    .setContentIntent(pendingIntent)
                    .setOngoing(true)
                    .setWhen(System.currentTimeMillis())
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setAutoCancel(true)
                    .setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE);
            notification = mBuilder.build();
        }
        notificationManager.notify(++id, notification);


}

    /**
     * @param context 上下文
     * @param intent  intent携带activity
     * @return boolean true为在最顶层，false为否
     * @Description: TODO 判断activity是否在应用的最顶层
     * @author Sunday
     * @date 2016年3月15日
     */
    public static boolean isTop(Context context, Intent intent) {

        ActivityManager am = (ActivityManager) context.getSystemService(context.ACTIVITY_SERVICE);

        List<ActivityManager.RunningTaskInfo> appTask = am.getRunningTasks(1);

        if (appTask.size() > 0 && appTask.get(0).topActivity.equals(intent.getComponent())) {

            return true;

        } else {

            return false;
        }
    }

    public boolean isBackground() {
        ActivityManager activityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager
                .getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
            if (appProcess.processName.equals(context.getPackageName())) {
                if (appProcess.importance != ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                    //后台
                    return true;
                } else {
                    //前台
                    return false;
                }
            }
        }
        return false;
    }


}
