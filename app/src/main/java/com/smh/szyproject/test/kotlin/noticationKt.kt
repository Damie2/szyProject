package com.smh.szyproject.test.kotlin

import android.app.PendingIntent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.smh.szyproject.R
import com.smh.szyproject.utils.utilCode.NotificationUtils

class noticationKt : AppCompatActivity(){

    private var id:Int = 0
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_main)

        NotificationUtils.notify(id++) { param ->
            intent.putExtra("id", id);
            param.setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle("title")
                    .setContentText("content text: $id")
                    .setContentIntent(PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT))
                    .setAutoCancel(true)
            null
        }
    }
}