package com.hariapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.hariapp.calculator.ExpressionBuilder

class CalculatorActivity : AppCompatActivity() {

    var tvExpression: TextView? = null
    var tvResult: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)

        //Numbers
        var tvOne = findViewById<TextView>(R.id.tvOne)
        tvOne.setOnClickListener {
            appendOnExpresstion("1", true)
            //Toast.makeText(applicationContext,"tvOne",Toast.LENGTH_SHORT).show()
        }
        var tvTwo = findViewById<TextView>(R.id.tvTwo)
        tvTwo.setOnClickListener {
            //oast.makeText(applicationContext,"tvTwo",Toast.LENGTH_SHORT).show()
            appendOnExpresstion("2", true)
        }
        var tvThree = findViewById<TextView>(R.id.tvThree)
        tvThree.setOnClickListener { appendOnExpresstion("3", true) }
        var tvFour = findViewById<TextView>(R.id.tvFour)
        tvFour.setOnClickListener { appendOnExpresstion("4", true) }
        var tvFive = findViewById<TextView>(R.id.tvFive)
        tvFive.setOnClickListener { appendOnExpresstion("5", true) }
        var tvSix = findViewById<TextView>(R.id.tvSix)
        tvSix.setOnClickListener { appendOnExpresstion("6", true) }
        var tvSeven = findViewById<TextView>(R.id.tvSeven)
        tvSeven.setOnClickListener { appendOnExpresstion("7", true) }
        var tvEight = findViewById<TextView>(R.id.tvEight)
        tvEight.setOnClickListener { appendOnExpresstion("8", true) }
        var tvNine = findViewById<TextView>(R.id.tvNine)
        tvNine.setOnClickListener { appendOnExpresstion("9", true) }
        var tvZero = findViewById<TextView>(R.id.tvZero)
        tvZero.setOnClickListener { appendOnExpresstion("0", true) }
        var tvDot = findViewById<TextView>(R.id.tvDot)
        tvDot.setOnClickListener { appendOnExpresstion(".", true) }

        //Operators
        var tvPlus = findViewById<TextView>(R.id.tvPlus)
        tvPlus.setOnClickListener { appendOnExpresstion("+", false) }
        var tvMinus = findViewById<TextView>(R.id.tvMinus)
        tvMinus.setOnClickListener { appendOnExpresstion("-", false) }
        var tvMul = findViewById<TextView>(R.id.tvMul)
        tvMul.setOnClickListener { appendOnExpresstion("*", false) }
        var tvDivide = findViewById<TextView>(R.id.tvDivide)
        tvDivide.setOnClickListener { appendOnExpresstion("/", false) }
        var tvOpen = findViewById<TextView>(R.id.tvOpen)
        tvOpen.setOnClickListener { appendOnExpresstion("(", false) }
        var tvClose = findViewById<TextView>(R.id.tvClose)
        tvClose.setOnClickListener { appendOnExpresstion(")", false) }

        var tvClear = findViewById<TextView>(R.id.tvClear)
        tvExpression = findViewById<TextView>(R.id.tvExpression)
        tvResult = findViewById<TextView>(R.id.tvResult)
        tvClear.setOnClickListener {

            tvExpression?.text = ""
            tvResult?.text = ""
        }

        var tvBack = findViewById<ImageView>(R.id.tvBack)
        tvBack.setOnClickListener {
            val string = tvExpression?.text.toString()
            if (string.isNotEmpty()) {
                tvExpression?.text = string.substring(0, string.length - 1)
            }
            tvResult?.text = ""
        }

        var tvEquals = findViewById<TextView>(R.id.tvEquals)
        tvEquals.setOnClickListener {
            try {

                val expression = ExpressionBuilder(tvExpression?.text.toString()).build()
                val result = expression.evaluate()
                val longResult = result.toLong()
                if (result == longResult.toDouble())
                    tvResult?.text = longResult.toString()
                else
                    tvResult?.text = result.toString()

            } catch (e: Exception) {
                Log.d("Exception", " message : " + e.message)
            }
        }

    }

    fun appendOnExpresstion(string: String, canClear: Boolean) {

        if (tvResult?.text.toString().isNotEmpty()) {
            tvExpression?.text = ""
        }

        if (canClear) {
            tvResult?.text = ""
            tvExpression?.append(string)
        } else {
            tvExpression?.append(tvResult?.text)
            tvExpression?.append(string)
            tvResult?.text = ""
        }

    }
}
