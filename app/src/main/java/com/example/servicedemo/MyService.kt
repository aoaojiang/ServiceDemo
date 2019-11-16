package com.example.servicedemo

import android.app.Service
import android.content.Intent
import android.content.ServiceConnection
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Binder
import android.os.IBinder
import android.widget.Toast
import java.net.HttpURLConnection
import java.net.URL

class MyService : Service() {

    val mBinder = MyBinder()


    inner class MyBinder : Binder() {
        val service: MyService
            get() = this@MyService
    }

    fun add(a: Long, b: Long): Long {
        return a + b
    }

     override fun onBind(intent: Intent): IBinder {
        Toast.makeText(this, "服务绑定", Toast.LENGTH_SHORT).show()
        return mBinder
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Toast.makeText(this, "取消绑定", Toast.LENGTH_SHORT).show()
        return false
    }

    override fun onCreate() {
        super.onCreate()

        Toast.makeText(this, "MyService.onCreate().", Toast.LENGTH_SHORT).show()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Toast.makeText(this, "MyService.onStartCommand()", Toast.LENGTH_SHORT).show()

        val msg = intent!!.getStringExtra("msg")
        Toast.makeText(this, "Message Received: $msg", Toast.LENGTH_SHORT).show()

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()

        Toast.makeText(this, "MyService.onDestroy()", Toast.LENGTH_SHORT).show()
    }


}
