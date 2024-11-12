package com.example.kalkulator

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.journeyapps.barcodescanner.BarcodeCallback
import com.journeyapps.barcodescanner.BarcodeResult
import com.journeyapps.barcodescanner.DecoratedBarcodeView

class ScanActivity : AppCompatActivity() {

    private lateinit var barcodeView: DecoratedBarcodeView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_three)

        // Mengatur teks pada ActionBar
        supportActionBar?.title = "Scan QR" // Ganti dengan teks yang diinginkan

        // Meminta izin kamera jika belum diberikan
        if (checkSelfPermission(android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(arrayOf(android.Manifest.permission.CAMERA), 101)
        } else {
            initializeBarcodeView()
        }
    }

    private fun initializeBarcodeView() {
        // Menginisialisasi tampilan barcode
        barcodeView = findViewById(R.id.barcode_scanner)
        barcodeView.decodeContinuous(callback)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 101 && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            initializeBarcodeView()
        } else {
            Log.d("ScanActivity", "Camera permission denied")
        }
    }

    // Callback untuk hasil pemindaian
    private val callback = BarcodeCallback { result: BarcodeResult ->
        val scannedText = result.text
        Log.d("ScanActivity", "Scanned text: $scannedText")

        // Langsung buka browser jika hasil pemindaian adalah URL
        if (scannedText.startsWith("http://") || scannedText.startsWith("https://")) {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(scannedText))
            startActivity(browserIntent)
        } else {
            Log.d("ScanActivity", "Not a valid URL")
        }

        // Pause scanning setelah membuka browser dan resume pemindaian
        barcodeView.pause()
        barcodeView.resume() // Menambahkan ini untuk melanjutkan pemindaian
    }

    override fun onResume() {
        super.onResume()
        barcodeView.resume()
    }

    override fun onPause() {
        super.onPause()
        barcodeView.pause()
    }
}
