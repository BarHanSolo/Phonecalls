package com.example.phonecalls

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.custom_list_item.view.*
import android.net.Uri
import android.content.Intent
import android.media.session.PlaybackState
import android.os.Build
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.core.content.ContextCompat.startActivity
import com.example.phonecalls.MainActivity as PhonecallsMainActivity
import androidx.core.content.ContextCompat.startActivity
import androidx.core.content.ContextCompat.startActivity

class CustomAdapter(var objects: List<String>) : BaseAdapter() {

    @RequiresApi(Build.VERSION_CODES.M)
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        if (convertView == null) {
            val layoutInflater = LayoutInflater.from(parent?.context)
            view = layoutInflater.inflate(R.layout.custom_list_item, parent, false)
        } else {
            view = convertView
        }
        view.phonenumber_textview.text = getItem(position)
        view.findViewById<Button>(R.id.my_button).setOnClickListener{
            val number = Uri.parse("tel:${getItem(position)}")
            val callIntent = Intent(Intent.ACTION_DIAL, number)
            view.context.startActivity(callIntent)
        }
        return view
    }

    override fun getItem(position: Int): String {
        return objects[position]
    }

    override fun getItemId(position: Int): Long {
        return objects[position].hashCode().toLong()
    }

    override fun getCount(): Int {
        return objects.size
    }
}
