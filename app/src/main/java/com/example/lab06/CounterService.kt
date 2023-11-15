package com.example.lab06

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log

class CounterService: Service()
{
    val counter = CounterThread()

    inner class CounterBinder : Binder()
    {
            fun  getCounter() : Int
            {
                return counter.counterV;
            }

            fun  startCounter()
            {
                counter.start()
            }
    }

    override fun onBind(p0: Intent?): IBinder?
    {
        return CounterBinder()
    }

    override fun onCreate() {
        counter.start()
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int
    {
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy()
    {
        counter.interrupt()
        super.onDestroy()
    }
    inner class CounterThread : Thread()
    {
        var counterV: Int = 0
        override fun run()
        {
            try{
                while(true)
                {
                    counterV++
                    Log.d("TIMER", "$counterV")
                    sleep(1000)
                }
            }
            catch (ignored :InterruptedException)
            {
                Thread.currentThread().interrupt()
            }
        }
    }

}