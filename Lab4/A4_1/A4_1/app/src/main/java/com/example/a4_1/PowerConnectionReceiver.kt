package com.example.a4_1

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.TextView

class PowerConnectionReceiver(private val statusTextView: TextView) : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        when (intent?.action) {
            Intent.ACTION_POWER_CONNECTED -> {
                statusTextView.text = "Power Connected"
            }
            Intent.ACTION_POWER_DISCONNECTED -> {
                statusTextView.text = "Power Disconnected"
            }
            Intent.ACTION_BATTERY_LOW -> {
                statusTextView.text = "Battery Low"
            }
            Intent.ACTION_BATTERY_OKAY -> {
                statusTextView.text = "Battery OK"
            }
        }
    }
}
