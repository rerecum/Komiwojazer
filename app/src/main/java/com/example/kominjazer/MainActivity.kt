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
import java.util.*

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



    }
}