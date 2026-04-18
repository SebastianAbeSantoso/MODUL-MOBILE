package com.example.modul2xml

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.doAfterTextChanged
import com.google.android.material.materialswitch.MaterialSwitch
import com.google.android.material.textfield.TextInputEditText
import java.text.NumberFormat
import kotlin.math.ceil
import kotlin.toString

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val tipOptions = arrayOf("15%", "18%", "20%")
        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, tipOptions)
        val dropDownMenu = findViewById<AutoCompleteTextView>(R.id.tipPercentageDropdown)
        dropDownMenu.setAdapter(adapter)

        val billInput = findViewById<TextInputEditText>(R.id.billInputAmount)
        val roundUpSwitch = findViewById<MaterialSwitch>(R.id.roundUpSwitch)

        billInput.doAfterTextChanged { calculateTip() }

        dropDownMenu.setOnItemClickListener { _, _, _, _ -> calculateTip() }

        roundUpSwitch.setOnCheckedChangeListener { _, _ -> calculateTip() }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun calculateTip () {
        val billInput = findViewById<TextInputEditText>(R.id.billInputAmount)
        val dropDownMenu = findViewById<AutoCompleteTextView>(R.id.tipPercentageDropdown)
        val roundUpSwitch = findViewById<MaterialSwitch>(R.id.roundUpSwitch)
        val tipResult = findViewById<TextView>(R.id.tipAmountTextView)

        val stringInTextField = billInput.text.toString()
        val cost = stringInTextField.toDoubleOrNull()

        if (cost == null) {
            tipResult.text = "Tip Amount: $0.00"
            return
        }

        val tipPercentageString = dropDownMenu.text.toString().replace("%", "")
        val tipPercentage = (tipPercentageString.toDoubleOrNull() ?: 15.0) / 100.0

        var tip = cost * tipPercentage

        if (roundUpSwitch.isChecked) {
            tip = ceil(tip)
        }

        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)

        tipResult.text = "Tip Amount: $formattedTip"

    }
}

