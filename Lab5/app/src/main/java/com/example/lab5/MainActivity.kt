package com.example.lab5

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp



data class DaneKierunku(
    val id: Int,
    val nazwa: String,
    val opis: String,
    val iloscMiejsc: Int
)

class MainActivity : ComponentActivity() {
    private val kierunkiViewModel by viewModels<KierunkiViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KierunkiScreen(viewModel = kierunkiViewModel)
        }
    }
}
@Composable
fun KierunkiScreen(viewModel: KierunkiViewModel) {
    val listaKierunkow = viewModel.listaKierunkow

    var noweNazwa by remember { mutableStateOf("") }
    var noweOpis by remember { mutableStateOf("") }
    var noweIloscMiejsc by remember { mutableStateOf("") }
    var edytowanyKierunekId by remember { mutableStateOf<Int?>(null) }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        // FORMULARZ
        OutlinedTextField(
            value = noweNazwa,
            onValueChange = { noweNazwa = it },
            label = { Text("Nazwa kierunku") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = noweOpis,
            onValueChange = { noweOpis = it },
            label = { Text("Opis kierunku") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = noweIloscMiejsc,
            onValueChange = { noweIloscMiejsc = it },
            label = { Text("Ilość miejsc") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                if (noweNazwa.isNotBlank() && noweOpis.isNotBlank() && noweIloscMiejsc.isNotBlank() && noweIloscMiejsc.toIntOrNull() != null) {
                    if (edytowanyKierunekId == null) {
                        viewModel.dodajKierunek(noweNazwa, noweOpis, noweIloscMiejsc.toInt())
                    } else {
                        viewModel.aktualizujKierunek(
                            id = edytowanyKierunekId!!,
                            nowaNazwa = noweNazwa,
                            nowyOpis = noweOpis,
                            nowaIloscMiejsc = noweIloscMiejsc.toInt()
                        )
                        edytowanyKierunekId = null
                    }
                    noweNazwa = ""
                    noweOpis = ""
                    noweIloscMiejsc = ""
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(if (edytowanyKierunekId == null) "Dodaj kierunek" else "Zapisz zmiany")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // LISTA KIERUNKÓW
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(listaKierunkow, key = { it.id }) { kierunek ->
                Card(
                    modifier = Modifier
                        .padding(vertical = 6.dp)
                        .fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(12.dp)
                    ) {
                        Text(
                            text = "${kierunek.id}. ${kierunek.nazwa}",
                            style = MaterialTheme.typography.titleMedium
                        )
                        Text(
                            text = kierunek.opis,
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Text(
                            text = "Ilość miejsc: ${kierunek.iloscMiejsc}",
                            style = MaterialTheme.typography.bodySmall
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Button(onClick = {
                                viewModel.usunKierunek(kierunek)
                            }) {
                                Text("Usuń")
                            }
                            Button(onClick = {
                                noweNazwa = kierunek.nazwa
                                noweOpis = kierunek.opis
                                noweIloscMiejsc = kierunek.iloscMiejsc.toString()
                                edytowanyKierunekId = kierunek.id
                            }) {
                                Text("Edytuj")
                            }
                        }
                    }
                }
            }
        }
    }
}
