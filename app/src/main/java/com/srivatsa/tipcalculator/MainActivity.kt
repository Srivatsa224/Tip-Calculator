package com.srivatsa.tipcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.SeekBar
import androidx.core.widget.addTextChangedListener
import kotlinx.android.synthetic.main.activity_main.*

private const val TAG="MainActivity"
private const val INITIAL_TIP_PERCENT =10
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        seekBarTip.progress=INITIAL_TIP_PERCENT
        tvTipPercent.text="$INITIAL_TIP_PERCENT%"


        seekBarTip.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean)
            {
                Log.i(TAG, "onProgressChanged $p1")
                tvTipPercent.text="$p1%"
                computeTipAndTotal()
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {}

            override fun onStopTrackingTouch(p0: SeekBar?) {}

        })

        etBase.addTextChangedListener(object:TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                Log.i(TAG,"afterTextChanged $s")
                computeTipAndTotal()

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        })
    }

    private fun computeTipAndTotal() {
        // Get base value and Tip %
        if(etBase.text.isEmpty())
        {
            tvTipAmount.text=""
            tvTotalAmount.text=""
            return
        }
        val baseAmount= etBase.text.toString().toDouble()
        val tipPercentage=seekBarTip.progress
        val tipAmount=baseAmount * tipPercentage /100
        val totalAmount = baseAmount + tipAmount

        tvTipAmount.text="%.2f".format(tipAmount)
        tvTotalAmount.text="%.2f".format(totalAmount)

    }
}
