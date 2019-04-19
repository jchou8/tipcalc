package edu.washington.jchou8.tipcalc

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private var amount = 12.34
    private var percent = 0.15


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tipBtn = findViewById<Button>(R.id.btnTip)
        tipBtn.setOnClickListener{
            Toast.makeText(applicationContext, "$%.2f".format(calcTip()), Toast.LENGTH_LONG).show()
        }
    }

    private fun calcTip() = amount * (1 + percent)
}
