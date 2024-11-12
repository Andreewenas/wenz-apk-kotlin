package com.example.kalkulator

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class VisiActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_visi)

        // Mengatur judul ActionBar
        supportActionBar?.title = "Adiwiyata"

        // Tombol untuk menuju link
        val buttonLink: Button = findViewById(R.id.buttonLink)
        buttonLink.setOnClickListener {
            val url = "https://smk2-yk.sch.id/berita/adiwiyata-smk-n-2-yogyakarta-mendapat-bantuan-tree-planting-dari-pt-komatsu-indonesia"  // Ganti dengan URL yang diinginkan
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }

        // Tombol Kembali
        val buttonBack: Button = findViewById(R.id.buttonBack)
        buttonBack.setOnClickListener {
            finish() // Kembali ke halaman sebelumnya
        }
    }
}
