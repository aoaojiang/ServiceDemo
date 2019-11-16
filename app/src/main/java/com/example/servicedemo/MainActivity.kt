package com.example.servicedemo

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var serviceIntent: Intent
    var myService: MyService? = null
    var isBound = false

    val mConn: ServiceConnection = object : ServiceConnection {
        override fun onServiceDisconnected(p0: ComponentName?) {
            myService = null
        }

        override fun onServiceConnected(p0: ComponentName?, service: IBinder?) {
            myService = (service as MyService.MyBinder).service
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        serviceIntent = Intent(this, MyService::class.java)
    }

    fun onStartService(view: View) {
        serviceIntent.putExtra("msg", editText.text.toString())
        startService(serviceIntent)
    }

    fun onStopService(view: View) {
        stopService(serviceIntent)
    }

    fun onBindService(view: View) {
        if (!isBound) {
            bindService(serviceIntent, mConn, Context.BIND_AUTO_CREATE)
            isBound = true
        }
    }

    fun onUnbindService(view: View) {
        if (isBound) {
            isBound = false
            unbindService(mConn)
            myService = null
        }
    }

    fun onAdd(view: View) {
        if (myService == null) {
            textView.text = "未绑定服务"
            return
        }

        val a = Math.round(Math.random() * 100)
        val b = Math.round(Math.random() * 100)

        val result = myService!!.add(a, b)

        textView.text = "$a + $b = $result"
    }

}
