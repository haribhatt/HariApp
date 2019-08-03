package com.hariapp

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.Menu
import android.widget.LinearLayout
import org.json.JSONException
import android.widget.TextView
import android.provider.ContactsContract
import android.provider.ContactsContract.PhoneLookup

class ContactActivity : AppCompatActivity() {

    private val tag = "ContactActivity"
    private val READ_CONTACT_CODE = 101
    var users = ArrayList<Contact>()
    private var menu: Menu? = null
    var tvTotalCount: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact)
        setupPermissions()
        setToolBar()

    }

    private fun setAdaptor(rc: RecyclerView) {
        //adding a layoutmanager
        rc.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false) as RecyclerView.LayoutManager?
        //creating our adapter
        val adapter = ContactAdaptor(users)
        //now adding the adapter to recyclerview
        rc.adapter = adapter

    }

    private fun setToolBar() {
        this.supportActionBar?.title = "Contacts"
        this.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        this.supportActionBar?.setHomeButtonEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()

    }

    private fun setupPermissions() {
        val permission = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)

        if (permission != PackageManager.PERMISSION_GRANTED) {
            Log.v(tag, "Permission to record denied")
            makeRequest()
            return
        }

        fetchContact()
    }

    private fun makeRequest() {
        ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.READ_CONTACTS),
                READ_CONTACT_CODE)

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            READ_CONTACT_CODE -> {

                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {

                    Log.v(tag, "Permission has been denied by user")
                } else {
                    Log.v(tag, "Permission has been granted by user")
                    fetchContact()
                }
            }
        }
    }

//    fun deleteContact(ctx: Context, phone: String, name: String): Boolean {
//        val contactUri = Uri.withAppendedPath(PhoneLookup.CONTENT_FILTER_URI, Uri.encode(phone))
//        val cur = ctx.getContentResolver().query(contactUri, null, null, null, null)
//        try {
//            if (cur.moveToFirst()) {
//                do {
//                    if (cur.getString(cur.getColumnIndex(PhoneLookup.DISPLAY_NAME)).equals(name, ignoreCase = true)) {
//                        val lookupKey = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.LOOKUP_KEY))
//                        val uri = Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_LOOKUP_URI, lookupKey)
//                        ctx.getContentResolver().delete(uri, null, null)
//                        return true
//                    }
//
//                } while (cur.moveToNext())
//            }
//
//        } catch (e: Exception) {
//            println(e.stackTrace)
//        } finally {
//            cur.close()
//        }
//        return false
//    }

    private fun fetchContact() {
        Log.v(tag, "fetchContact() users.size " + users.size)
        if (users.size > 0) {
            users.clear()
        }
        users = ArrayList<Contact>()
        val phones = contentResolver.query(android.provider.ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null)
        if (phones != null) {
            while (phones.moveToNext()) {
                val name = phones.getString(phones.getColumnIndex(android.provider.ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
                val phoneNumber = phones.getString(phones.getColumnIndex(android.provider.ContactsContract.CommonDataKinds.Phone.NUMBER))
                try {
                    users.add(Contact(name, phoneNumber))

                } catch (e: JSONException) {
                    Log.e(tag, "JSONException$e")
                }

            }
            var rvContact = findViewById<RecyclerView>(R.id.rv_contact)
            setAdaptor(rvContact)
            phones.close()
        }
        phones?.close()
        Log.v(tag, "getContacts() users.size " + users.size)
        tvTotalCount = findViewById(R.id.tv_total_count)
        tvTotalCount?.text = "" + users.size
    }

}
