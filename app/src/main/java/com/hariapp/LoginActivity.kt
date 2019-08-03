package com.hariapp

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import android.widget.Toolbar

import kotlinx.android.synthetic.main.activity_login.*
import java.util.*
import android.support.annotation.NonNull
import com.truecaller.android.sdk.*


class LoginActivity : AppCompatActivity() {

    var TAG = "LoginActivity";
//    private var mTrueClient: TrueClient? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
//        val toolbar = findViewById<Toolbar>(R.id.toolbar)
       setSupportActionBar(toolbar)
        supportActionBar?.title = "Login"
//        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        var editText = findViewById<EditText>(R.id.et_email);
        var password = findViewById<EditText>(R.id.et_password)
        var loginButton = findViewById<Button>(R.id.btn_Login)
        loginButton.setOnClickListener {
            val email = editText.text.toString()
            val pass = password.text.toString()
            Log.v(TAG, "email " + email)
            Log.v(TAG, "pass " + pass)
            var intent = Intent(this@LoginActivity, SuccessActivity::class.java)
            intent.putExtra("email", email)
            intent.putExtra("pass", pass)
            startActivity(intent)
        }
        fab.setOnClickListener { view ->
            Snackbar.make(view, "This feature is coming soon", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        setUpTrueCaller()
        val trueButton = findViewById<TrueButton>(R.id.com_truecaller_android_sdk_truebutton);
        if(TrueSDK.getInstance().isUsable()){
            Log.d("true","true caller usable")
            TrueSDK.getInstance().getUserProfile(this)
            // To display the user's Truecaller profile in a popup view
            TrueSdkScope.CONSENT_MODE_POPUP

            // To display the user's Truecaller profile in a full screen view
            TrueSdkScope.CONSENT_MODE_FULLSCREEN

            // To use "USE DIFFERENT NUMBER" CTA at the bottom of the user profile view
            TrueSdkScope.FOOTER_TYPE_CONTINUE

            // To use "SKIP" CTA at the bottom of the user profile view
            TrueSdkScope.FOOTER_TYPE_SKIP

            // To use "Login" as the contextual text in the user profile view title
            TrueSdkScope.SDK_CONSENT_TITLE_LOG_IN

            // To use "Signup" as the contextual text in the user profile view title
            TrueSdkScope.SDK_CONSENT_TITLE_SIGN_UP

            // To use "Sign in" as the contextual text in the user profile view title
            TrueSdkScope.SDK_CONSENT_TITLE_SIGN_IN

            // To use "Verify" as the contextual text in the user profile view title
            TrueSdkScope.SDK_CONSENT_TITLE_VERIFY

            // To use "Register" as the contextual text in the user profile view title
            TrueSdkScope.SDK_CONSENT_TITLE_REGISTER

            // To use "Get Started" as the contextual text in the user profile view title
            TrueSdkScope.SDK_CONSENT_TITLE_GET_STARTED
            val locale = Locale("en")
            TrueSDK.getInstance().setLocale(locale)
        }else{
            trueButton?.visibility = View.GONE
            Log.d("true","true caller un usable")
            Toast.makeText(applicationContext,"True caller not present",Toast.LENGTH_SHORT).show()

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        TrueSDK.getInstance().onActivityResultObtained( this,resultCode, data);
        Log.v("true","onActivityResult "+" ");
    }

    private val sdkCallback = object : ITrueCallback {
        override fun onVerificationRequired() {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            Log.d("true", "onVerificationRequired: " )
        }

        override fun onSuccessProfileShared(trueProfile: TrueProfile) {

            // This method is invoked when the truecaller app is installed on the device and the user gives his
            // consent to share his truecaller profile

            Log.d("true", "Verified Successfully : " + trueProfile.firstName)
        }

        override fun onFailureProfileShared(trueError: TrueError) {
            // This method is invoked when some error occurs or if an invalid request for verification is made

            Log.d("true", "onFailureProfileShared: " + trueError.errorType)
            when(trueError.errorType){
                10 -> Toast.makeText(applicationContext,"Please login first",Toast.LENGTH_SHORT).show()
                else ->
                    Toast.makeText(applicationContext,"Somthing went wrong",Toast.LENGTH_SHORT).show()
            }
        }

    }

    fun setUpTrueCaller(){
        val trueScope = TrueSdkScope.Builder(this, sdkCallback)
                .consentMode(TrueSdkScope.CONSENT_MODE_FULLSCREEN)
                .consentTitleOption(TrueSdkScope.SDK_CONSENT_TITLE_VERIFY)
                .footerType(TrueSdkScope.FOOTER_TYPE_SKIP)
                .build()

        TrueSDK.init(trueScope)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}
