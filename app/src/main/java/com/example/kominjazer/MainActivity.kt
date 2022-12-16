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

class MainActivity : AppCompatActivity() {
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
                    //Przypisywanie wygenerowanej losowo liczby do tablicy
                    //Dla odległości między A i B daje taką samą wartość jak między B i A
                    City[i][j]=it
                    City[j][i]=it
                }
            }
        }
        //Wypelnienie spinnera wartosciami przypisanymi w pliki strings.xml
        ArrayAdapter.createFromResource(
            this,
            R.array.City,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerA.adapter = adapter
            spinnerB.adapter = adapter
        }

        //Funkcja ktora sprawdza wybrane elementy z spinner. Wpisuje odleglosc z macierzy miedzy te elementy do txt.
        fun checkDistance(){
            editDistance.setText(City[spinnerA.selectedItemId.toInt()][spinnerB.selectedItemId.toInt()].toString())
        }
        //W przypadku zmiany wartosci wywoluje sie funkcja sprawdzajaca
        spinnerA.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                checkDistance()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }

        change.setOnClickListener {
            if(!editDistance.text.isNullOrEmpty()){
                if(spinnerA.selectedItemId.toInt() == spinnerB.selectedItemId.toInt()){

                }else{
                    if(editDistance.text.toString().toInt()==0){

                    }else{
                        City[spinnerA.selectedItemId.toInt()][spinnerB.selectedItemId.toInt()] = editDistance.getText().toString().toInt()
                        City[spinnerB.selectedItemId.toInt()][spinnerA.selectedItemId.toInt()] = editDistance.getText().toString().toInt()
                    }
                }
            }
        }

        fun countDistance(){
            //Szukanie sasiada.
            val size = 8
            //Odwiedzone miasta.
            val visited = BooleanArray(size)
            //Najkrotsza sciezka.
            val path = IntArray(size)
            //Miasto startowe.
            var currentCity = 0
            //Startowe miasto dodane do tablicy.
            visited[currentCity] = true

            for(i in 0 until size - 1){
                //Zmienna przechowujaca najkrotszy dystans.
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

                //Dodanie miasta do sciezki i oznaczenie jako odwiedzone
                path[i] = cityIndex
                visited[cityIndex] = true

                currentCity = cityIndex
            }
            //Dodanie ostatniego miasta
            path[size - 1] = 0

            //Wypisanie najkrotszej drogi
            for(i in 0 until size){
                result.append("${path[i]}")
            }

            //Wypisanie calkowitej odleglosci od pierwszego do ostatniego
            var cost = 0
            for(i in 0 until size - 1){
                cost += City[path[i]][path[i + 1]]
            }
            result.append("\nSalesman problem no more! : $cost")
        }
    @RequiresApi(Build.VERSION_CODES.Q)
    fun screenshot(){
        //Zrzut ekranu
        val activity = this
        val view = activity.window.decorView.rootView

        //Bitmapa
        val bitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)

        //Canvas
        val canvas = Canvas(bitmap)
        view.draw(canvas)

        //Gdzie zapisac plik?
        val filePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString() + File.separator + "SalesmanStatus.png"

        //Tworzenie zrzutu
        val file = File(filePath)
        try {
            val stream = FileOutputStream(file)

            //Kompresja bitmap
            bitmap.compress(Bitmap.CompressFormat.PNG, 90, stream)
            stream.flush()
            stream.close()
        } catch (e: Exception) {
            //Jesli blad:
            e.printStackTrace()
        }
    }

    }
}