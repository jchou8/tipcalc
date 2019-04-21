package edu.washington.jchou8.tipcalc

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*


class MainActivity : AppCompatActivity() {
    private var amountStr = ""
    private var amount = 0
    private var percent = 15


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnTip = findViewById<Button>(R.id.btnTip)
        val edtAmount = findViewById<EditText>(R.id.edtAmount)
        val spnTipPct = findViewById<Spinner>(R.id.spnTipPct)

        btnTip.isEnabled = false
        btnTip.setOnClickListener{
            Toast.makeText(applicationContext, calcTip(), Toast.LENGTH_LONG).show()
        }

        edtAmount.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(s.toString() != amountStr){
                    edtAmount.removeTextChangedListener(this)
                    val cleanString = s.toString().replace("[\$.]".toRegex(), "")
                    amount = if (cleanString.toIntOrNull() == null) 0 else cleanString.toInt()
                    val formatted = "$%.2f".format(amount * 0.01)
                    amountStr = formatted
                    edtAmount.setText(formatted)
                    edtAmount.setSelection(formatted.length)
                    edtAmount.addTextChangedListener(this)

                    btnTip.isEnabled = amount > 0
                }
            }

            override fun afterTextChanged(s: Editable) {
            }
        })

        spnTipPct.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, pos: Int, id: Long) {
                val selection = parent.getItemAtPosition(pos)
                percent = selection.toString().replace("%", "").toInt()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
            }
        }
        spnTipPct.setSelection(1)
    }

    private fun calcTip() = "$%.2f".format(amount * (percent * 0.01) * 0.01)
}
