package com.example.licznikpunktow

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.Calendar

class NewSessionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_session)

        val buttonSave = findViewById<Button>(R.id.buttonSaveSession)
        val nameField = findViewById<EditText>(R.id.editCompetitionName)
        val dateField = findViewById<EditText>(R.id.editDate)
        val pointsField = findViewById<EditText>(R.id.editPoints)

        // Blokuje ręczne pisanie i otwiera DatePickerDialog
        dateField.apply {
            isFocusable = false
            isClickable = true
            setOnClickListener {
                val calendar = Calendar.getInstance()
                val year = calendar.get(Calendar.YEAR)
                val month = calendar.get(Calendar.MONTH)
                val day = calendar.get(Calendar.DAY_OF_MONTH)

                val datePicker = DatePickerDialog(this@NewSessionActivity, { _, selectedYear, selectedMonth, selectedDay ->
                    val selectedDate = "%02d.%02d.%d".format(selectedDay, selectedMonth + 1, selectedYear)
                    setText(selectedDate)
                }, year, month, day)

                datePicker.show()
            }
        }

        buttonSave.setOnClickListener {
            val name = nameField.text.toString()
            val date = dateField.text.toString()
            val points = pointsField.text.toString().toIntOrNull() ?: 0

            Toast.makeText(this, "Sesja zapisana: $name, $date, $points pkt", Toast.LENGTH_LONG).show()

            val dbHelper = DatabaseHelper(this)
            val success = dbHelper.insertSession(date, name, points)

            if (success) {
                Toast.makeText(this, "Sesja zapisana do bazy!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Błąd zapisu!", Toast.LENGTH_SHORT).show()
            }

            finish() // wraca do MainActivity
        }
    }
}
