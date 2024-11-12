package com.example.kalkulator

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Menyembunyikan ActionBar
        supportActionBar?.hide()

        // Referensikan ImageViews berdasarkan ID-nya dan setel onClickListeners
        val buttonGoToNext: ImageView = findViewById(R.id.buttonGoToNext)
        val buttonGoToNext2: ImageView = findViewById(R.id.buttonGoToNext2)
        val buttonGoToVisiMisi: ImageView = findViewById(R.id.buttonGoToVisiMisi) // Tambahkan tombol ini
        val buttonGoToMap: ImageView = findViewById(R.id.buttonGoToMap)
        val buttonGoToWisata: ImageView = findViewById(R.id.buttonGoToWisata)

        // tombol Klik untuk Kalkulator
        buttonGoToNext.setOnClickListener {
            // Navigasikan ke Aktivitas Kalkulator
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }

        // tombol Klik untuk Scan QR
        buttonGoToNext2.setOnClickListener {
            // Navigasikan ke Aktivitas Scan QR
            val intent = Intent(this, ScanActivity::class.java)
            startActivity(intent)
        }

        // tombol Klik untuk Visi Misi
        buttonGoToVisiMisi.setOnClickListener {
            // Navigasikan ke Aktivitas Visi Misi
            val intent = Intent(this, VisiActivity::class.java)
            startActivity(intent)
        }

        // tombol Klik untuk Peta
        buttonGoToMap.setOnClickListener {
            val intent = Intent(this, MapActivity::class.java)
            startActivity(intent)
        }

        // tombol Klik untuk Info Jogja
        buttonGoToWisata.setOnClickListener{
            val intent = Intent(this, WisataActivity::class.java)
            startActivity(intent)
        }
    }
}
