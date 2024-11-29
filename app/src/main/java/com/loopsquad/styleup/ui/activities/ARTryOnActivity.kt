package com.loopsquad.styleup.ui.activities

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.annotation.OptIn
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.CameraSelector
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.LifecycleOwner
import com.google.mlkit.vision.pose.Pose
import com.google.mlkit.vision.pose.PoseDetection
import com.google.mlkit.vision.pose.PoseLandmark
import com.google.mlkit.vision.pose.defaults.PoseDetectorOptions
import com.loopsquad.styleup.R
import com.google.mlkit.vision.common.InputImage

class ARTryOnActivity : AppCompatActivity() {

    private lateinit var previewView: PreviewView // Replace SurfaceView with PreviewView
    private val cameraPermissionRequestCode = 100
    val poseDetectorOption = PoseDetectorOptions.Builder().setDetectorMode(PoseDetectorOptions.STREAM_MODE).build()
    val poseDetector = PoseDetection.getClient(poseDetectorOption)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_artry_on)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        previewView = findViewById<PreviewView>(R.id.surfaceView) // Initialize PreviewView

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

    private fun requestCameraPermission() {
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
        if (requestCode == cameraPermissionRequestCode) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                initializeCameraPreview()
            } else {
                Toast.makeText(this, "Camera permission denied", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }

    private fun initializeCameraPreview() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(this)
        cameraProviderFuture.addListener({
            val cameraProvider = cameraProviderFuture.get()

            val cameraSelector = CameraSelector.Builder()
                .requireLensFacing(CameraSelector.LENS_FACING_BACK) // Use front camera
                .build()

            val preview = Preview.Builder()
                .build()

            // Set PreviewView surface provider for rendering the camera feed
            preview.setSurfaceProvider(previewView.surfaceProvider)

            // Use ImageAnalysis for processing frames
            val imageAnalysis = ImageAnalysis.Builder()
                .setTargetResolution(android.util.Size(1280, 720)) // Adjust resolution as needed
                .build()

            imageAnalysis.setAnalyzer(ContextCompat.getMainExecutor(this)) { imageProxy ->
                processImage(imageProxy) // Process the camera feed for pose detection
            }

            try {
                // Unbind use cases if already bound
                cameraProvider.unbindAll()

                // Bind the camera use cases to the lifecycle
                cameraProvider.bindToLifecycle(
                    this as LifecycleOwner,
                    cameraSelector,
                    preview,
                    imageAnalysis
                )

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }, ContextCompat.getMainExecutor(this))
    }

    @OptIn(ExperimentalGetImage::class)
    private fun processImage(imageProxy: ImageProxy) {
        val mediaImage = imageProxy.image
        if (mediaImage != null) {
            val rotationDegrees = imageProxy.imageInfo.rotationDegrees
            val inputImage = InputImage.fromMediaImage(mediaImage, rotationDegrees)

            // Run pose detection
            poseDetector.process(inputImage)
                .addOnSuccessListener { pose ->
                    handlePoseDetection(pose)
                }
                .addOnFailureListener { e ->
                    e.printStackTrace()
                }
                .addOnCompleteListener {
                    imageProxy.close() // Close the image to release resources
                }
        }
    }

    private fun handlePoseDetection(pose: Pose) {
        // Log all detected pose landmarks for debugging
        for (landmark in pose.allPoseLandmarks) {
            Log.d("PoseLandmark", "Landmark ${landmark.landmarkType}: ${landmark.position}")
        }

        // Access specific landmarks for the shoulder
        val leftShoulder = pose.getPoseLandmark(PoseLandmark.LEFT_SHOULDER)
        val rightShoulder = pose.getPoseLandmark(PoseLandmark.RIGHT_SHOULDER)

        if (leftShoulder != null && rightShoulder != null) {
            Log.d("Pose..", "Left Shoulder: ${leftShoulder.position.x}, ${leftShoulder.position.y}")
            Log.d("Pose..", "Right Shoulder: ${rightShoulder.position.x}, ${rightShoulder.position.y}")
            Toast.makeText(this, "Shoulder detected left shoulder at ${leftShoulder.position.x}, ${leftShoulder.position.y} and right shoulder at ${rightShoulder.position.x}, ${rightShoulder.position.y}", Toast.LENGTH_SHORT).show()

        } else {
            Log.d("Pose..", "Shoulder not detected")
            Toast.makeText(this, "Shoulder not detected", Toast.LENGTH_SHORT).show()
        }
    }
}
