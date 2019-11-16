package com.example.aidlcall

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.view.View

import com.example.shnuservice.IShnuAidlInterface
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var myService: IShnuAidlInterface? = null
    var isBound = false
    var serviceIntent: Intent? = null

    val mConn = object : ServiceConnection {
        override fun onServiceDisconnected(p0: ComponentName?) {
            myService = null
        }

        override fun onServiceConnected(p0: ComponentName?, p1: IBinder?) {
            myService = IShnuAidlInterface.Stub.asInterface(p1)
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        serviceIntent = Intent("shnu.service.add")
        serviceIntent!!.setPackage("com.example.shnuservice")
    }

    fun onBindAidl(view: View) {
        bindService(serviceIntent, mConn, Context.BIND_AUTO_CREATE)
        isBound = true
    }

    fun onUnbindAidl(view: View) {
        unbindService(mConn)
        myService = null
        isBound = false
    }

    fun onAidlCall(view: View) {
        if (myService == null) {
            textView.text = "未绑定远程服务"
            return
        }

        val a = Math.round(Math.random() * 100)
        val b = Math.round(Math.random() * 100)

        val result = myService!!.add(a, b)

        textView.text = "$a + $b = $result"
    }
}
