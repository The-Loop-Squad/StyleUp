package com.loopsquad.styleup.ui.activities

import android.os.Bundle
import android.view.SurfaceView
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.Manifest
import android.content.pm.PackageManager
import android.view.SurfaceHolder
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.loopsquad.styleup.R

class ARTryOnActivity : AppCompatActivity() {

    private lateinit var surfaceView: SurfaceView
    private val cameraPermissionRequestCode = 100


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_artry_on)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        surfaceView = findViewById(R.id.surfaceView)

        if (!hasCameraPermission()) {
            requestCameraPermission()
        } else {
            initializeCameraPreview()
        }
    }

    private fun hasCameraPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            this, Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestCameraPermission(){
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.CAMERA),
            cameraPermissionRequestCode
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == cameraPermissionRequestCode){
            if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                initializeCameraPreview()
            }else{
                Toast.makeText(this, "Camera permission denied", Toast.LENGTH_SHORT).show()
                finish()
            }

        }
    }

    private fun initializeCameraPreview() {
        val surfaceHolder = surfaceView.holder
        surfaceHolder.addCallback(object : SurfaceHolder.Callback{
            override fun surfaceCreated(holder: SurfaceHolder) {
                startCamera(holder)
            }

            override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
            }

            override fun surfaceDestroyed(holder: SurfaceHolder) {

            }

        })
    }

    private fun startCamera(surfaceHolder: SurfaceHolder) {

    }

    fun backButton(view: View){
        finish()
    }
}