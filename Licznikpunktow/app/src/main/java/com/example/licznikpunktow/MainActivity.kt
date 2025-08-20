package com.example.licznikpunktow

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Intent
import android.net.Uri



class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val newSessionButton = findViewById<Button>(R.id.buttonNewSession)
        val historyButton = findViewById<Button>(R.id.buttonHistory)

        newSessionButton.setOnClickListener {
            Toast.makeText(this, "Przejdź do nowej sesji", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, NewSessionActivity::class.java)
            startActivity(intent)
        }

        historyButton.setOnClickListener {
            Toast.makeText(this, "Przejdź do historii", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, HistoryActivity::class.java)
            startActivity(intent)
        }

        val mapButton = findViewById<Button>(R.id.buttonFindShootingRanges)

        mapButton.setOnClickListener {
            val gmmIntentUri = Uri.parse("https://www.google.com/maps/search/strzelnica")
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            startActivity(mapIntent)

        }
    }
}