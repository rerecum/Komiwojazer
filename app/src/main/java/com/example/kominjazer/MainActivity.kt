package com.example.kominjazer

import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.util.Random
import androidx.annotation.RequiresApi
import java.io.File
import java.io.FileOutputStream

// Zaczne od zapisu "val", aby zapobiec zmianie danych.

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val spinnerA : Spinner = findViewById(R.id.spinnerA)

        val spinnerB : Spinner = findViewById(R.id.spinnerB)

        val editDistance : EditText = findViewById(R.id.editTextDistance)

        val way : Button = findViewById(R.id.buttonWay)

        val result : TextView = findViewById(R.id.textResults)

        val MyLayout : View = findViewById(R.id.MyLayout)

        // Macierz odleglosci miedzy miastami
        val City = List<MutableList<Int>>(8){
            MutableList<Int>(8){0}
        }

        // Petla wypelnijaca macierz losowymi elemantami
        // Petla w postaci trojkata, ktora powoduje ze miedzy punktami odleglosc to 0.
        for(i in 0..7){
            for(j in i+1..7){
                // Generowanie losowych liczb w przedziale 1,1089
                Random().nextInt(1089).let{
                    // Przypisywanie wygenerowanej losowo liczby do tablicy
                    // Dla odległości między A i B daje taką samą wartość jak między B i A
                    City[i][j]=it
                    City[j][i]=it
                }
            }
        }
        // Wypelnienie spinnera wartosciami przypisanymi w pliki strings.xml
        ArrayAdapter.createFromResource(
            this,
            R.array.City,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerA.adapter = adapter
            spinnerB.adapter = adapter
        }

        // Funkcja "checkDistance", ktora sprawdza wybrane elementy z spinner.
        // Wpisuje odleglosc z macierzy miedzy te elementy do txt.
        // W przypadku zmiany wartosci wywoluje sie funkcja sprawdzajaca

        fun checkDistance(){
            editDistance.setText(City[spinnerA.selectedItemId.toInt()][spinnerB.selectedItemId.toInt()].toString())
        }

        spinnerA.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                checkDistance()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }

        // Funkcja "countDistance", ktora szuka sasiada, zlicza odwiedzone miasta, znajduje najkrotsza sciezke
        // Wyznacza miasto startowe, ktore jest rowne 0 oraz dodaje je do tablicy.

        fun countDistance(){
            val size = 8
            val visited = BooleanArray(size)
            val path = IntArray(size)
            var currentCity = 0
            visited[currentCity] = true

            for(i in 0 until size - 1){
                // Zmienna przechowujaca najkrotszy dystans.
                var min = Int.MAX_VALUE
                // Zmienna przechowująca indeks miasta z najkrótszym dystansem
                var cityIndex = 0
                // Szukanie miasta z najkrótszym dystansem
                for (j in 0 until size) {
                    if (!visited[j] && City[currentCity][j] < min) {
                        min = City[currentCity][j]
                        cityIndex = j
                    }
                }

                // Dodanie miasta do sciezki i oznaczenie jako odwiedzone
                path[i] = cityIndex
                visited[cityIndex] = true

                currentCity = cityIndex
            }
            // Dodanie ostatniego miasta
            path[size - 1] = 0

            // Wypisanie najkrotszej drogi
            for(i in 0 until size){
                result.append("${path[i]}")
            }

            // Wypisanie calkowitej odleglosci od pierwszego do ostatniego
            var cost = 0
            for(i in 0 until size - 1){
                cost += City[path[i]][path[i + 1]]
            }
            result.append("\nSalesman problem no more! : $cost")
        }
    @RequiresApi(Build.VERSION_CODES.Q)
    fun screenshot(){
        // Zrzut ekranu
        val activity = this
        val view = activity.window.decorView.rootView

        // Bitmapa
        val bitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)

        // Canvas
        val canvas = Canvas(bitmap)
        view.draw(canvas)

        // Gdzie zapisac plik?
        val filePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString() + File.separator + "SalesmanStatus.png"

        // Tworzenie zrzutu
        val file = File(filePath)
        try {
            val stream = FileOutputStream(file)

            // Kompresja bitmap
            bitmap.compress(Bitmap.CompressFormat.PNG, 90, stream)
            stream.flush()
            stream.close()
        } catch (e: Exception) {
            // Jesli blad:
            e.printStackTrace()
        }
    }
    way.setOnClickListener(View.OnClickListener {
        // Cleaner zapisu poprzedniego rezultatu
        result.setText("")
        countDistance()
        android.os.Handler().postDelayed({
            screenshot()
        },50)
    })
    }
}