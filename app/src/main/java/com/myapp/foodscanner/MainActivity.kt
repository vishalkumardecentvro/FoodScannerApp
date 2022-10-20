package com.myapp.foodscanner

import android.Manifest
import android.content.pm.PackageManager
import android.media.AudioManager
import android.media.ToneGenerator
import android.os.Bundle
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.vision.CameraSource
import com.google.android.gms.vision.Detector
import com.google.android.gms.vision.Detector.Detections
import com.google.android.gms.vision.barcode.Barcode
import com.google.android.gms.vision.barcode.BarcodeDetector
import java.io.IOException


class MainActivity : AppCompatActivity() {

  lateinit var  toneGen1 : ToneGenerator
  lateinit var surfaceView : SurfaceView
  lateinit  var barcodeText : TextView
   var REQUEST_CAMERA_PERMISSION : Int = 201

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)


    toneGen1 = ToneGenerator(AudioManager.STREAM_MUSIC, 100)
    surfaceView  = findViewById<SurfaceView>(R.id.surface_view)
    barcodeText = findViewById<TextView>(R.id.barcode_text)
    initialiseDetectorsAndSources()



  }

  fun initialiseDetectorsAndSources() {
    //Toast.makeText(getApplicationContext(), "Barcode scanner started", Toast.LENGTH_SHORT).show();
    val barcodeDetector = BarcodeDetector.Builder(this)
      .setBarcodeFormats(Barcode.ALL_FORMATS)
      .build()
    val cameraSource = CameraSource.Builder(this, barcodeDetector)
      .setRequestedPreviewSize(1920, 1080)
      .setAutoFocusEnabled(true) //you should add this feature
      .build()


    surfaceView.getHolder().addCallback(object : SurfaceHolder.Callback {
      override fun surfaceCreated(holder: SurfaceHolder) {
        try {
          if (ActivityCompat.checkSelfPermission(
              this@MainActivity,
              Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
          ) {
            cameraSource.start(surfaceView.getHolder())
          } else {
            ActivityCompat.requestPermissions(
              this@MainActivity,
              arrayOf(Manifest.permission.CAMERA),
              REQUEST_CAMERA_PERMISSION
            )
          }
        } catch (e: IOException) {
          e.printStackTrace()
        }
      }

      override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {}
      override fun surfaceDestroyed(holder: SurfaceHolder) {
        cameraSource.stop()
      }
    })




    barcodeDetector.setProcessor(object : Detector.Processor<Barcode> {
      override fun release() {
        // Toast.makeText(getApplicationContext(), "To prevent memory leaks barcode scanner has been stopped", Toast.LENGTH_SHORT).show();
      }

      override fun receiveDetections(detections: Detections<Barcode>) {
        val barcodes = detections.detectedItems
        if (barcodes.size() != 0) {
          barcodeText.post(Runnable {
            if (barcodes.valueAt(0).email != null) {
              barcodeText.removeCallbacks(null)
              val barcodeData = barcodes.valueAt(0).email.address
              barcodeText.text = barcodeData
              toneGen1.startTone(ToneGenerator.TONE_CDMA_PIP, 150)
            } else {
              val barcodeData = barcodes.valueAt(0).displayValue
              barcodeText.text = barcodeData
              toneGen1.startTone(ToneGenerator.TONE_CDMA_PIP, 150)
            }
          })
        }
      }
    })
  }

}