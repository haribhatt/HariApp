package com.hariapp

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.LinearLayout
import android.widget.TextView
import org.json.JSONException
import org.json.JSONObject

class MessageActivity : AppCompatActivity() {

    val tag = "MessageActivity";
    var messages = ArrayList<Message>()
    val READ_MESSAGE_CODE = 101
    var tvTotalCount: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_message)
        setToolBar()
        setupPermissions()
    }

    private fun setAdaptor(rc: RecyclerView) {
        //adding a layoutmanager
        rc.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false) as RecyclerView.LayoutManager?
        //creating our adapter
        val adapter = SmsAdaptor(messages)
        //now adding the adapter to recyclerview
        rc.adapter = adapter

    }

    private fun setToolBar() {
        this.supportActionBar?.title = "Messages"
        this.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        this.supportActionBar?.setHomeButtonEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()

    }

    private fun setupPermissions() {
        val permission = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS)

        if (permission != PackageManager.PERMISSION_GRANTED) {
            Log.v(tag, "Permission to record denied")
            makeRequest()
            return
        }

        fetchMessages()
    }

    private fun makeRequest() {
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_SMS), READ_MESSAGE_CODE)

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            READ_MESSAGE_CODE -> {

                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {

                    Log.v(tag, "Permission has been denied by user")
                } else {
                    Log.v(tag, "Permission has been granted by user")
                    fetchMessages()
                }
            }
        }
    }

    private fun fetchMessages() {
        Log.v(tag, "start fetchMessages");
        val uri = Uri.parse("content://sms/inbox")
        if (messages.size > 0) {
            messages.clear()
        }
        messages = ArrayList<Message>()
        val c = contentResolver.query(uri, null, null, null, null)
        if (c != null) {
            while (c.moveToNext()) {
                val body = c.getString(c.getColumnIndexOrThrow("body")).toString()
                val date = c.getString(c.getColumnIndexOrThrow("date")).toString()
                val address = c.getString(c.getColumnIndexOrThrow("address")).toString()
//                val subject:String? = c.getString(c.getColumnIndexOrThrow("subject")).toString()
                val msg = Message(body, date, address)
                messages.add(msg)
//                Log.v(tag, "body " + body);
//                Log.v(tag, "date " + date);
//                Log.v(tag, "address " + address);
////                Log.v(tag, "subject " + subject);
//                Log.v(tag, "size of content resolver " + c.columnCount);
//                Log.v(tag, " c.getColumnName " + c.getColumnName(0));
//                Log.v(tag, " c.getColumnName " + c.getColumnName(1));
//                Log.v(tag, " c.getColumnName " + c.getColumnName(2));
//                Log.v(tag, " c.getColumnName " + c.getColumnName(3));
//                Log.v(tag, " c.getColumnName " + c.getColumnName(4));
//                Log.v(tag, " c.getColumnName " + c.getColumnName(5));
//                Log.v(tag, " c.getColumnName " + c.getColumnName(6));
//                Log.v(tag, " c.getColumnName " + c.getColumnName(7));
//                Log.v(tag, " c.getColumnName " + c.getColumnName(8));
//                Log.v(tag, " c.getColumnName " + c.getColumnName(9));
//                Log.v(tag, " c.getColumnName " + c.getColumnName(10));
//                Log.v(tag, " c.getColumnName " + c.getColumnName(11));
//                Log.v(tag, " c.getColumnName " + c.getColumnName(12));
//                Log.v(tag, " c.getColumnName " + c.getColumnName(13));
//                Log.v(tag, " c.getColumnName " + c.getColumnName(14));
//                Log.v(tag, " c.getColumnName " + c.getColumnName(15));
//                Log.v(tag, " c.getColumnName " + c.getColumnName(16));
//                Log.v(tag, " c.getColumnName " + c.getColumnName(17));
//                Log.v(tag, " c.getColumnName " + c.getColumnName(18));
//                Log.v(tag, " c.getColumnName " + c.getColumnName(19));
//                Log.v(tag, " c.getColumnName " + c.getColumnName(20));
//                Log.v(tag, " c.getColumnName " + c.getColumnName(21));
//                Log.v(tag, " c.getColumnName " + c.getColumnName(22));
//                Log.v(tag, " c.getColumnName " + c.getColumnName(23));
//                Log.v(tag, "c.count " + c.getString(c.count));
//                if (c.columnCount != null) {
//                    for (i in 1..2) {
//                        Log.v(tag, "value " + c.getColumnName(1));
//                    }
//
//                }
            }
            var rvContact = findViewById<RecyclerView>(R.id.rv_message)
            setAdaptor(rvContact)
            c.close()
        }
        c?.close()
    }
}
