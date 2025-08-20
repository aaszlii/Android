package com.example.a4_2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.a4_2.ui.theme.A4_2Theme
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            A4_2Theme {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    DemoPrzelaczanePrzyciski()
                    DemoNapiwek()

                }
            }
            }
        }
    }
@Composable
fun DemoPrzelaczanePrzyciski() {
    var activeButton by remember { mutableStateOf(1) }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Button(
            onClick = { activeButton = 2 },
            enabled = activeButton == 1,
            modifier = Modifier.padding(8.dp)
        ) {
            Text(text = "Button 1")
        }

        Button(
            onClick = { activeButton = 3 },
            enabled = activeButton == 2,
            modifier = Modifier.padding(8.dp)
        ) {
            Text(text = "Button 2")
        }

        Button(
            onClick = { activeButton = 1 },
            enabled = activeButton == 3,
            modifier = Modifier.padding(8.dp)
        ) {
            Text(text = "Button 3")
        }
    }
}
@Composable
fun DemoNapiwek() {
    var amountInput by remember { mutableStateOf("") }
    var tipPercentage by remember { mutableStateOf(15) } // domyślnie 15%

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.padding(16.dp)
    ) {
        Text(text = "Enter Bill Amount:")

        androidx.compose.material3.OutlinedTextField(
            value = amountInput,
            onValueChange = { amountInput = it },
            label = { Text("Amount") }
        )

        Text(text = "Select Tip %:")

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = { tipPercentage = 10 }) { Text("10%") }
            Button(onClick = { tipPercentage = 15 }) { Text("15%") }
            Button(onClick = { tipPercentage = 20 }) { Text("20%") }
        }

        val tipAmount = (amountInput.toFloatOrNull() ?: 0f) * (tipPercentage / 100f)

        Text(
            text = "Tip Amount: $${"%.2f".format(tipAmount)}",
            modifier = Modifier.padding(top = 16.dp)
        )
    }
}

