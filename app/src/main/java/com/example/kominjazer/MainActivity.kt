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
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val spinnerA : Spinner = findViewById(R.id.spinnerA)
        val spinnerB : Spinner = findViewById(R.id.spinnerB)
        val editDistance : EditText = findViewById(R.id.editTextDistance)
        val change : Button = findViewById(R.id.buttonChange)
        val way : Button = findViewById(R.id.buttonWay)
        val result : TextView = findViewById(R.id.textResults)
        val MyLayout : View = findViewById(R.id.MyLayout)

        // Macierz odleglosci miedzy miastami
        val City = List<MutableList<Int>>(8){
            MutableList<Int>(8){0}
        }

        //Petla wypelnijaca macierz losowymi elemantami
        //Petla w postaci trojkata, ktora powoduje ze miedzy punktami odleglosc to 0.
        for(i in 0..7){
            for(j in i+1..7){
                //Generowanie losowych liczb w przedziale 1,1089
                Random().nextInt(1089).let{

                }
            }
        }
    }
}