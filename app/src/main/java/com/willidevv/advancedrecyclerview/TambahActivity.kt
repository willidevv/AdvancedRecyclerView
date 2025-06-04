package com.willidevv.advancedrecyclerview

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class TambahActivity : AppCompatActivity() {
    private val tanamanviewmodel : TanamanViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_tambah)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val etnama = findViewById<EditText>(R.id.entryNama)
        val etdeskripsi = findViewById<EditText>(R.id.entryDeskripsi)
        val btntambah = findViewById<Button>(R.id.btnTambah)

        val spinner : Spinner = findViewById(R.id.spinner)
        ArrayAdapter.createFromResource(
            this,
            R.array.tanaman_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }



        btntambah.setOnClickListener{
            val namatanaman = etnama.text.toString()
            val deskTanaman = etdeskripsi.text.toString()
            val categoryTanaman = spinner.selectedItem.toString()


            if (namatanaman.isNotEmpty() && deskTanaman.isNotEmpty() && categoryTanaman.isNotEmpty()) {
                tanamanviewmodel.setDatabase(TanamanDatabase.getInstance(this))
                val newTanaman = Tanaman(namaTanaman = namatanaman, deskripsiTanaman = deskTanaman, category = categoryTanaman)
                tanamanviewmodel.insertTanaman(newTanaman)
                etnama.text.clear()
                etdeskripsi.text.clear()

                tanamanviewmodel.getTanaman()
            }
        }


    }


}