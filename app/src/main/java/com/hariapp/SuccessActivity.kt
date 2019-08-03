package com.hariapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.CardView
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text

class SuccessActivity : AppCompatActivity() {

    private val TAG = "SuccessActivity"
    private var successMsg: TextView? = null
    private val ss: String? = null
    private val ss2: String? = null
    var myValue: String? = null
    var myValue2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_success)
        Log.v(TAG, "onCreate")
//        actionBar.title = "Success"
        supportActionBar?.title = "Home"
//        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        findViews()
        getBundleData()
        setText()
        var cardContact = findViewById<CardView>(R.id.cv_contact)
        cardContact.setOnClickListener {
            val intent = Intent(this@SuccessActivity,ContactActivity::class.java)
            startActivity(intent)
        }
        var cardMessage = findViewById<CardView>(R.id.cv_message)
        cardMessage.setOnClickListener {
            val intent = Intent(this@SuccessActivity,MessageActivity::class.java)
            startActivity(intent)
        }
        var cardCalender = findViewById<CardView>(R.id.cv_calender)
        cardCalender.setOnClickListener {
            val intent = Intent(this@SuccessActivity,CalenderActivity::class.java)
            startActivity(intent)
        }
        var cardCalculator = findViewById<CardView>(R.id.cv_calculator)
        cardCalculator.setOnClickListener {
            val intent = Intent(this@SuccessActivity,CalculatorActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onResume() {
        super.onResume()
        Log.v(TAG, "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.v(TAG, "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.v(TAG, "onStop")
    }

    fun findViews() {
        successMsg = findViewById<TextView>(R.id.tv_user_detail)
    }

    fun getBundleData(): Boolean {
        val bundle: Bundle? = intent.extras
        if (bundle != null) {
            val message = bundle.getString("email") // 1
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }
        val intent = getIntent();
        myValue = intent.getStringExtra("email")
        myValue2 = intent.getStringExtra("pass")
        Log.d(TAG, "myValue" + myValue + "myValue2 " + myValue2)
        return true
    }

    fun setText() {
        successMsg?.text = "Hello $myValue@$myValue2"
    }
}
