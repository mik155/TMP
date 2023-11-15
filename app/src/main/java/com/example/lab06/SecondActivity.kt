package com.example.lab06

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.activity.ComponentActivity

class SecondActivity : ComponentActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.second_activity)
        Toast.makeText(this, "SERVICE CLOSED", Toast.LENGTH_LONG).show()
    }
}