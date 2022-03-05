package com.example.tiptime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btCalculate.setOnClickListener { calculateTip() }
    }

    fun calculateTip()
    {
        val stringInTextField = binding.costServiceEditText.text.toString()
        val cost = stringInTextField.toDoubleOrNull()
        if(cost==null)
        {
            binding.tipResult.text=""
            return
        }
        val tipPercentage = when (binding.tipOptions.checkedRadioButtonId) {
            R.id.op20percent -> 0.20
            R.id.op80percent -> 0.18
            else -> 0.15
        }
        var tip = tipPercentage * cost
        val roundUp = binding.roundSwitch.isChecked
        if (roundUp) {
            tip = kotlin.math.ceil(tip)
        }
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)

    }
}