package com.example.kalkulator

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay

class MapActivity : AppCompatActivity() {

    private lateinit var mapView: MapView
    private lateinit var locationOverlay: MyLocationNewOverlay

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inisialisasi konfigurasi OSM
        Configuration.getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this))

        setContentView(R.layout.activity_map)

        // Mengatur teks pada ActionBar
        supportActionBar?.title = "Peta" // Ganti dengan teks yang diinginkan

        // Inisialisasi MapView dan sembunyikan sampai siap
        mapView = findViewById(R.id.mapView)
        mapView.visibility = View.GONE // Setel MapView menjadi GONE pada awalnya
        mapView.setBuiltInZoomControls(true)
        mapView.setMultiTouchControls(true)
        mapView.setTilesScaledToDpi(true) // Menyempurnakan tampilan peta

        // Tambahkan overlay lokasi
        locationOverlay = MyLocationNewOverlay(mapView)
        locationOverlay.enableMyLocation()

        // Minta izin lokasi jika belum diberikan
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 1)
            return
        }

        // Tambahkan listener untuk pembaruan lokasi
        locationOverlay.runOnFirstFix {
            runOnUiThread {
                mapView.visibility = View.VISIBLE // Tampilkan MapView setelah lokasi tersedia
                zoomToCurrentLocation() // Zoom ke lokasi ketika lokasi pertama kali ditemukan
            }
        }

        mapView.setTileSource(TileSourceFactory.MAPNIK) // Set tile source setelah semuanya siap
        mapView.overlays.add(locationOverlay)
    }

    private fun zoomToCurrentLocation() {
        val lastLocation: Location? = locationOverlay.lastFix
        if (lastLocation != null) {
            val currentLatLng = GeoPoint(lastLocation.latitude, lastLocation.longitude)
            mapView.controller.setZoom(18.0) // Set zoom level yang diinginkan
            mapView.controller.setCenter(currentLatLng) // Pusatkan peta pada lokasi pengguna
        } else {
            Toast.makeText(this, "Lokasi tidak tersedia", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        locationOverlay.disableMyLocation()
    }
}
