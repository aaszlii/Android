package com.example.lab5

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class KierunkiViewModel : ViewModel() {

    var listaKierunkow = mutableStateListOf(
        DaneKierunku(1, "Cyberbezpieczeństwo (CBE)", "Studia z zakresu bezpieczeństwa systemów IT.", 60),
        DaneKierunku(2, "Informatyczne Systemy Automatyki (ISA)", "Systemy automatyki z wykorzystaniem informatyki.", 40),
        DaneKierunku(3, "Informatyka Algorytmiczna (INA)", "Zaawansowane algorytmy i struktury danych.", 35),
        DaneKierunku(4, "Informatyka Stosowana (IST)", "Zastosowania informatyki w przemyśle i usługach.", 50),
        DaneKierunku(5, "Inżynieria Systemów (INS)", "Modelowanie, analiza i projektowanie systemów.", 30),
        DaneKierunku(6, "Sztuczna Inteligencja (SZT)", "Technologie sztucznej inteligencji i uczenia maszynowego.", 70),
        DaneKierunku(7, "Teleinformatyka (TIN)", "Łączenie telekomunikacji i informatyki.", 40)
    )
        private set

    fun dodajKierunek(nazwa: String, opis: String, iloscMiejsc: Int) {
        val noweId = (listaKierunkow.maxOfOrNull { it.id } ?: 0) + 1
        listaKierunkow.add(
            DaneKierunku(noweId, nazwa, opis, iloscMiejsc)
        )
    }

    fun usunKierunek(kierunek: DaneKierunku) {
        listaKierunkow.remove(kierunek)
    }

    fun aktualizujKierunek(id: Int, nowaNazwa: String, nowyOpis: String, nowaIloscMiejsc: Int) {
        val index = listaKierunkow.indexOfFirst { it.id == id }
        if (index != -1) {
            listaKierunkow[index] = DaneKierunku(
                id = id,
                nazwa = nowaNazwa,
                opis = nowyOpis,
                iloscMiejsc = nowaIloscMiejsc
            )
        }
    }
}