package com.example.kominjazer

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val spinnery = listOf(

            findViewById<Spinner>(R.id.spinner),
            findViewById<Spinner>(R.id.spinner2),
            findViewById<Spinner>(R.id.spinner3),
            findViewById<Spinner>(R.id.spinner4),
            findViewById<Spinner>(R.id.spinner5),
            findViewById<Spinner>(R.id.spinner6),
            findViewById<Spinner>(R.id.spinner7),
            findViewById<Spinner>(R.id.spinner8),
            findViewById<Spinner>(R.id.spinner9),
            findViewById<Spinner>(R.id.spinner10),
            findViewById<Spinner>(R.id.spinner11),
            findViewById<Spinner>(R.id.spinner12),
            findViewById<Spinner>(R.id.spinner13),
            findViewById<Spinner>(R.id.spinner14),
            findViewById<Spinner>(R.id.spinner15),
            findViewById<Spinner>(R.id.spinner16),
        )

        val City = resources.getStringArray(R.array.City)

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item, City
        )
        spinnery[0].adapter = adapter
        spinnery[1].adapter = adapter
        spinnery[2].adapter = adapter
        spinnery[3].adapter = adapter
        spinnery[4].adapter = adapter
        spinnery[5].adapter = adapter
        spinnery[6].adapter = adapter
        spinnery[7].adapter = adapter
        spinnery[8].adapter = adapter
        spinnery[9].adapter = adapter
        spinnery[10].adapter = adapter
        spinnery[11].adapter = adapter
        spinnery[12].adapter = adapter
        spinnery[13].adapter = adapter
        spinnery[14].adapter = adapter
        spinnery[15].adapter = adapter

        spinnery[0].onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View, position: Int, id: Long
            ) {
                Toast.makeText(
                 this@MainActivity,
                 getString(R.string.selected_item) + " " +
                         "" + City[position], Toast.LENGTH_SHORT
             ).show()
            }

        override fun onNothingSelected(parent: AdapterView<*>) {
            // write code to perform some action
        }
    }
    }
}