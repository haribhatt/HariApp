package com.hariapp

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.SyncStateContract
import android.support.v7.app.AppCompatDelegate
import android.support.v7.widget.SwitchCompat
import android.util.Log
import android.widget.Toast

class SettingActivity : AppCompatActivity() {

    var switchBtn: SwitchCompat? = null
    var activity: SettingActivity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        this.supportActionBar?.title = "Settings"
        this.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        this.supportActionBar?.setHomeButtonEnabled(true)

//        val sharedPref = getPreferences(Context.MODE_PRIVATE)
        val sharedPref =  getSharedPreferences("PREFERENCE_NAME",Context.MODE_PRIVATE)
        val darkString = sharedPref?.getString("dark_theme","")
        val darkBoolean = sharedPref?.getBoolean("dark_theme_bool",false)
        Log.d("dark theme","darkString "+darkString)
        Log.d("dark theme","darkBoolean "+darkBoolean)

        switchBtn = findViewById(R.id.switch_btn);

        switchBtn?.setOnCheckedChangeListener { _, isChecked ->
            val msg = if (isChecked) "ON" else "OFF"
            Log.d("onChecked","msg "+msg)
            Toast.makeText(this@SettingActivity, msg, Toast.LENGTH_SHORT).show()
            if (msg == "ON") {
                Log.d("onChecked","ON "+msg)
                val editor = sharedPref?.edit()
                editor?.putString("dark_theme","true")
                editor?.putBoolean("dark_theme_bool",true)
                editor?.apply()
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                val darkString2 = sharedPref?.getString("dark_theme","")
                val darkBoolean2 = sharedPref?.getBoolean("dark_theme_bool",false)
                Log.d("dark theme","darkString "+darkString2)
                Log.d("dark theme","darkBoolean "+darkBoolean2)
            } else {
                Log.d("onChecked","OFF "+msg)
                val editor = sharedPref?.edit()
                editor?.putString("dark_theme","false")
                editor?.putBoolean("dark_theme_bool",false)
                editor?.apply()
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO);
                val darkString2 = sharedPref?.getString("dark_theme","")
                val darkBoolean2 = sharedPref?.getBoolean("dark_theme_bool",false)
                Log.d("dark theme","darkString "+darkString2)
                Log.d("dark theme","darkBoolean "+darkBoolean2)
            }
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()

    }

}

