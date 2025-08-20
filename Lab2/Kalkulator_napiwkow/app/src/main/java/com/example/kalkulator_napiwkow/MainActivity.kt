package com.example.kalkulator_napiwkow

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editCost = findViewById<EditText>(R.id.editCost)
        val editTip = findViewById<EditText>(R.id.editTip)
        val ratingBar = findViewById<RatingBar>(R.id.ratingBar)
        val radioGroup = findViewById<RadioGroup>(R.id.foodRadioGroup)
        val checkHelpful = findViewById<CheckBox>(R.id.checkHelpful)
        val buttonCalculate = findViewById<Button>(R.id.buttonCalculate)
        val textTipResult = findViewById<TextView>(R.id.textTipResult)
        val textTotal = findViewById<TextView>(R.id.textTotal)

        buttonCalculate.setOnClickListener {
            val cost = editCost.text.toString().toDoubleOrNull() ?: 0.0
            var tipPercent = editTip.text.toString().toDoubleOrNull() ?: 0.0

            // Zwiększenie napiwku w zależności od ratingu
            val serviceRating = ratingBar.rating
            if (serviceRating >= 4) tipPercent += 2
            else if (serviceRating <= 2) tipPercent -= 2

            // Modyfikacja w zależności od jakości jedzenia
            when (radioGroup.checkedRadioButtonId) {
                R.id.radioYes -> tipPercent += 1
                R.id.radioNo -> tipPercent -= 1
            }

            // Czy kelner był wyjątkowo pomocny?
            if (checkHelpful.isChecked) {
                tipPercent *= 1.5
            }

            val tip = (cost * tipPercent) / 100
            val total = cost + tip

            textTipResult.text = "Napiwek: %.2f zł".format(tip)
            textTotal.text = "Suma do zapłaty: %.2f zł".format(total)
        }
    }
}
