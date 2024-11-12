package com.example.kalkulator

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class WisataActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wisata)

        // Set listener untuk ikon lokasi pertama
        findViewById<ImageView>(R.id.locationIcon1).setOnClickListener {
            openMapLocation("https://maps.app.goo.gl/GHZ332Y5QW7MZeho8")
        }
        // Set listener untuk ikon lokasi pertama
        findViewById<ImageView>(R.id.locationIcon2).setOnClickListener {
            openMapLocation("https://maps.app.goo.gl/pu7fFdqAnsGqBaPb6")
        }
        // Set listener untuk ikon lokasi pertama
        findViewById<ImageView>(R.id.locationIcon3).setOnClickListener {
            openMapLocation("https://maps.app.goo.gl/47RM22zTSAYfhBsp6")
        }
        // Set listener untuk ikon lokasi pertama
        findViewById<ImageView>(R.id.locationIcon4).setOnClickListener {
            openMapLocation("https://maps.app.goo.gl/oHNBix5mCmaAzuoH9")
        }
        // Set listener untuk ikon lokasi pertama
        findViewById<ImageView>(R.id.locationIcon5).setOnClickListener {
            openMapLocation("https://maps.app.goo.gl/mvP1c7VQ3RBQaugF7")
        }
        // Set listener untuk ikon lokasi pertama
        findViewById<ImageView>(R.id.locationIcon6).setOnClickListener {
            openMapLocation("https://maps.app.goo.gl/2GzJzstAo8QtCSyZ9")
        }
        // Set listener untuk ikon lokasi pertama
        findViewById<ImageView>(R.id.locationIcon7).setOnClickListener {
            openMapLocation("https://maps.app.goo.gl/gtawaKVJXnRiNv1d8")
        }
        // Set listener untuk ikon lokasi pertama
        findViewById<ImageView>(R.id.locationIcon8).setOnClickListener {
            openMapLocation("https://maps.app.goo.gl/BNLJD5DKk7ak7xTM8")
        }
        // Set listener untuk ikon lokasi pertama
        findViewById<ImageView>(R.id.locationIcon9).setOnClickListener {
            openMapLocation("https://maps.app.goo.gl/BfvKmhbxmPuRK23U9")
        }
        // Set listener untuk ikon lokasi pertama
        findViewById<ImageView>(R.id.locationIcon10).setOnClickListener {
            openMapLocation("https://maps.app.goo.gl/42k3JkrPASBViS3Q7")
        }

    }

    private fun openMapLocation(url: String) {
        val uri = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW, uri).apply {
            setPackage("com.google.android.apps.maps")
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        } else {
            // Handle jika aplikasi Google Maps tidak tersedia
            startActivity(Intent(Intent.ACTION_VIEW, uri))
        }
    }
}
