package com.example.licznikpunktow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.roundToInt


class HistoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        val listView = findViewById<ListView>(R.id.listViewSessions)
        val dbHelper = DatabaseHelper(this)
        val sessionList = dbHelper.getAllSessions().toMutableList()

        val summaryTextView = findViewById<TextView>(R.id.textSummary)

        fun updateSummary() {
            if (sessionList.isEmpty()) {
                summaryTextView.text = "Brak zapisanych sesji."
                return
            }

            val totalPoints = sessionList.sumOf { it.points }
            val avgPoints = (totalPoints.toDouble() / sessionList.size).roundToInt()
            summaryTextView.text = "Liczba sesji: ${sessionList.size} | Suma punktów: $totalPoints | Średnia: $avgPoints"
        }

        val adapter = object : BaseAdapter() {
            override fun getCount() = sessionList.size
            override fun getItem(position: Int) = sessionList[position]
            override fun getItemId(position: Int) = sessionList[position].id.toLong()

            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                val view = convertView ?: LayoutInflater.from(this@HistoryActivity)
                    .inflate(R.layout.session_item, parent, false)

                val textView = view.findViewById<TextView>(R.id.textSession)
                val deleteButton = view.findViewById<Button>(R.id.buttonDelete)
                val session = sessionList[position]

                textView.text = session.toString()

                deleteButton.setOnClickListener {
                    dbHelper.deleteSession(session.id)
                    sessionList.removeAt(position)
                    notifyDataSetChanged()
                    updateSummary()
                    Toast.makeText(this@HistoryActivity, "Usunięto", Toast.LENGTH_SHORT).show()
                }

                return view
            }
        }

        listView.adapter = adapter
        updateSummary()

    }
}
