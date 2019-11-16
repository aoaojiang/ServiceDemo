package com.example.shnuservice

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.widget.Toast
import com.example.shnuservice.IShnuAidlInterface.Stub

class ShnuService : Service() {

    val mBinder = object : Stub() {
        override fun add(a: Long, b: Long): Long {
            return a + b
        }
    }

    override fun onBind(intent: Intent): IBinder {
        Toast.makeText(this, "远程绑定", Toast.LENGTH_SHORT).show()

        return mBinder
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Toast.makeText(this, "取消远程绑定", Toast.LENGTH_SHORT).show()

        return false
    }
}
