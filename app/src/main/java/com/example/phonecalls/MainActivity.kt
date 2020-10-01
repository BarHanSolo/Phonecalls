package com.example.phonecalls

import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.TelephonyManager
import kotlinx.android.synthetic.main.activity_main.*
import androidx.core.app.ActivityCompat
import android.Manifest.permission.*


class MainActivity : AppCompatActivity() {
    lateinit var myReceiver: CallReceiver
    lateinit var myCustomAdapter: CustomAdapter

    var numberList: MutableList<String> = mutableListOf()

    fun addPhoneNumberToList(phoneNumber: String){
        numberList.add(phoneNumber)
        myCustomAdapter.notifyDataSetChanged()
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //checking permissions
        val ALL_PERMISSIONS = 101

        val permissions = arrayOf<String>(
            READ_PHONE_STATE,
            READ_CALL_LOG
        )
        ActivityCompat.requestPermissions(this, permissions, ALL_PERMISSIONS)

        //define intent
        var filter = IntentFilter(TelephonyManager.ACTION_PHONE_STATE_CHANGED)
        myReceiver = CallReceiver(this)
        registerReceiver(myReceiver, filter)

        //create adapter
        myCustomAdapter = CustomAdapter(numberList)
        my_listview.setAdapter(myCustomAdapter)

    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(myReceiver)
    }

}
