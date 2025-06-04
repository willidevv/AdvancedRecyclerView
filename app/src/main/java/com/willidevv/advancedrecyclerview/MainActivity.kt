package com.willidevv.advancedrecyclerview

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private val tanamanViewModel :  TanamanViewModel by viewModels()
    private lateinit var adapter: TanamanAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        adapter = TanamanAdapter { tanaman ->
            val detailFragment = DetailTanaman.newInstance(tanaman.id)
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, detailFragment)
                .addToBackStack(null)
                .commit()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        tanamanViewModel.setDatabase(TanamanDatabase.getInstance(this))

        val rvtanaman = findViewById<RecyclerView>(R.id.rvtumbuhan)
        rvtanaman.adapter = adapter
        rvtanaman.layoutManager = LinearLayoutManager(this)

        val numberofcolumn = 2
        rvtanaman.layoutManager = GridLayoutManager(this, numberofcolumn)

        tanamanViewModel.tanaman.observe(this) { list -> adapter.submitList(list) }

        val tombol = findViewById<Button>(R.id.button)
        tombol.setOnClickListener {
            val intent = Intent(this, TambahActivity::class.java)
            startActivity(intent)
        }


    }
    override fun onResume() {
        super.onResume()
        tanamanViewModel.getTanaman()
    }


}