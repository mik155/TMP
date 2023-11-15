package com.example.lab06

import android.content.ComponentName
import android.content.Intent
import android.content.IntentFilter
import android.content.ServiceConnection
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.IBinder
import android.provider.Telephony
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.core.app.ActivityCompLS
at
import java.lang.Thread.sleep

class MainActivity : ComponentActivity() {
    lateinit var counterBinder: CounterService.CounterBinder
    var isConnected = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.RECEIVE_SMS
            ) == PackageManager.PERMISSION_DENIED
        )
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.RECEIVE_SMS),
                2
            );

        val receiver = SmsReceiver()
        val intentFilter = IntentFilter(Telephony.Sms.Intents.SMS_RECEIVED_ACTION)
        registerReceiver(receiver, intentFilter)

        //SERVICE
        val serviceConnection = object : ServiceConnection {
            override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
                counterBinder = service as CounterService.CounterBinder
            }

            override fun onServiceDisconnected(name: ComponentName?) {
                isConnected = false
            }
        }

        bindService(Intent(this, CounterService::class.java), serviceConnection, BIND_AUTO_CREATE)

        val button0 = findViewById<Button>(R.id.list_button)
        button0.setOnClickListener {
            if (isConnected) {
                unbindService(serviceConnection)
                isConnected = false
            }

            startActivity(Intent(this, SecondActivity::class.java))
        }


        val button1 = findViewById<Button>(R.id.counter_button)
        button1.setOnClickListener {
            if (isConnected)
            {
                val counter = counterBinder.getCounter()
                Toast.makeText(this, "COUNTER = $counter", Toast.LENGTH_SHORT).show()
            }
        }

    }

}
