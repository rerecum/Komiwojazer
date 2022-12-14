/*  Plan dzialania:
- Layout
- Miasta np. w spinner
- Macierz
- Wykrywanie elementów na spinnerze
- Pokazywanie odległości przy zaznaczeniu miast
- Gdy są dwa te same miasta to odleglosc musi byc rowna 0
- Zmiana odległości moze byc domyslna, lub wpisana przez uzytkownika
- Zrzut ekranu zapisywany do galerii
- Algorytm
 */

package com.example.kominjazer

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}