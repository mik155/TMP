package com.example.lab06

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class SmsReceiver : BroadcastReceiver()
{
    override fun onReceive(context: Context?, intent: Intent?)
    {
        Toast.makeText(context, "RECEIVED SMS!", Toast.LENGTH_LONG).show();
    }
}