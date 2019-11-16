package com.example.servicedemo

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.widget.Toast

class AddService : Service() {

    private val mBinder = MyBinder()

    internal inner class MyBinder : Binder() {
        val service: AddService
            get() = this@AddService
    }

    override fun onBind(intent: Intent): IBinder? {
        Toast.makeText(this, "服务绑定", Toast.LENGTH_SHORT).show()
        return mBinder
    }

    override fun onUnbind(intent: Intent): Boolean {
        Toast.makeText(this, "服务解绑", Toast.LENGTH_SHORT).show()
        return false
    }

    fun add(i: Long, j: Long): Long {
        return i + j
    }
}
