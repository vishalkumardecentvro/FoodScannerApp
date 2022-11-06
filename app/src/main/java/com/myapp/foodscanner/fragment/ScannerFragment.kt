package com.myapp.foodscanner.fragment

import android.Manifest
import android.content.pm.PackageManager
import android.media.AudioManager
import android.media.ToneGenerator
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.SurfaceHolder
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.FragmentContainerView
import com.google.android.gms.vision.CameraSource
import com.google.android.gms.vision.Detector
import com.google.android.gms.vision.barcode.Barcode
import com.google.android.gms.vision.barcode.BarcodeDetector
import com.myapp.foodscanner.ArchitecturalFunctions
import com.myapp.foodscanner.R
import com.myapp.foodscanner.databinding.FragmentScannerBinding
import java.io.IOException

class ScannerFragment : Fragment() , ArchitecturalFunctions {
    lateinit var binding : FragmentScannerBinding
    lateinit var toneGen1 : ToneGenerator
    val REQUEST_CAMERA_PERMISSION = 201


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentScannerBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        instantiate()
        initialize()
        listen()
        load()

    }

    override fun instantiate() {

    }

    override fun initialize() {
        toneGen1 = ToneGenerator(AudioManager.STREAM_MUSIC, 100)
        initialiseDetectorsAndSources()

    }

    override fun listen() {

    }

    override fun load() {
    }

    fun initialiseDetectorsAndSources() {
        //Toast.makeText(getApplicationContext(), "Barcode scanner started", Toast.LENGTH_SHORT).show();
        val barcodeDetector = BarcodeDetector.Builder(requireContext())
            .setBarcodeFormats(Barcode.ALL_FORMATS)
            .build()
        val cameraSource = CameraSource.Builder(requireContext(), barcodeDetector)
            .setRequestedPreviewSize(1920, 1080)
            .setAutoFocusEnabled(true) //you should add this feature
            .build()


        binding.surfaceView.holder.addCallback(object : SurfaceHolder.Callback {
            override fun surfaceCreated(holder: SurfaceHolder) {
                try {
                    if (ActivityCompat.checkSelfPermission(
                            requireActivity(),
                            Manifest.permission.CAMERA
                        ) == PackageManager.PERMISSION_GRANTED
                    ) {
                        cameraSource.start(binding.surfaceView.holder)
                    } else {
                        ActivityCompat.requestPermissions(
                            requireActivity(),
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

            override fun receiveDetections(detections: Detector.Detections<Barcode>) {
                val barcodes = detections.detectedItems
                if (barcodes.size() != 0) {

                    var barcodeData = ""
                    binding.barcodeText.post(Runnable {

                        if (barcodes.valueAt(0).email != null) {
                            binding.barcodeText.removeCallbacks(null)
                            barcodeData = barcodes.valueAt(0).email.address
                            binding.barcodeText.text = barcodeData
                            toneGen1.startTone(ToneGenerator.TONE_CDMA_PIP, 150)
                            navigateToProductFragment(barcodeData);
                        } else {
                            barcodeData = barcodes.valueAt(0).displayValue
                            binding.barcodeText.text = barcodeData
                            toneGen1.startTone(ToneGenerator.TONE_CDMA_PIP, 150)
                            navigateToProductFragment(barcodeData)
                        }

                    })


                }
            }
        })
    }

    fun navigateToProductFragment(barcodeData: String) {
        val bundle = Bundle()
        Log.i("--TAG--", "barcode code code- $barcodeData")
        bundle.putString("barcode",barcodeData)
        val productFragment = ProductFragment()
        productFragment.arguments = bundle
        val fragmentManager = requireActivity().supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(requireActivity().findViewById<FragmentContainerView>(R.id.fragmentContainer).id,productFragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

}