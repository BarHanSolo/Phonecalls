package com.example.phonecalls

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.TelephonyManager

class CallReceiver(mainActivity: MainActivity) : BroadcastReceiver() {
    var mainActivity = mainActivity
    override fun onReceive(context: Context?, intent: Intent?) {
        //var action = intent?.action
        if (intent!!.hasExtra(TelephonyManager.EXTRA_INCOMING_NUMBER) && intent.getStringExtra(TelephonyManager.EXTRA_STATE) == "RINGING"){
            val number = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER)
            mainActivity.addPhoneNumberToList(number)
        }
    }
}