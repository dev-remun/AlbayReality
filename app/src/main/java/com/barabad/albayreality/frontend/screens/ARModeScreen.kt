package com.barabad.albayreality.frontend.screens

import android.app.Activity
import android.content.ContentValues.TAG
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.barabad.albayreality.frontend.components.Header
import com.google.ar.core.ArCoreApk
import com.google.ar.core.ArCoreApk.Availability
import com.google.ar.core.Config
import com.google.ar.core.Frame
import com.google.ar.core.TrackingFailureReason
import com.google.ar.core.exceptions.UnavailableException
import io.github.sceneview.ar.ARScene
import io.github.sceneview.ar.arcore.isValid
import io.github.sceneview.ar.node.AnchorNode
import io.github.sceneview.ar.rememberARCameraNode
import io.github.sceneview.ar.rememberARCameraStream
import io.github.sceneview.model.Model
import io.github.sceneview.node.ModelNode
import io.github.sceneview.rememberCollisionSystem
import io.github.sceneview.rememberEngine
import io.github.sceneview.rememberMaterialLoader
import io.github.sceneview.rememberModelLoader
import io.github.sceneview.rememberNodes
import io.github.sceneview.rememberOnGestureListener
import io.github.sceneview.rememberView


@RequiresApi(Build.VERSION_CODES.VANILLA_ICE_CREAM)
@Composable
fun ARModeScreen(
    navController: NavController,
    site_id: String,
    site_title: String
) {
    val context = LocalContext.current
    Scaffold(
        containerColor = Color.Black
    ) { inner_padding ->

        Box(
            modifier = Modifier.fillMaxSize()
        ) {

            val compat = isARCoreSupportedAndUpToDate()
            if (compat){
                ModelDisplay(site_id)
            }
            else{
                Toast.makeText(context, "Device does not support AR Mode!", Toast.LENGTH_LONG).show()
                //for now just added a toast to show na hindi supported, we can add a locking of the button/ backing out afterwards
            }


            // # Floating Header
            Header(
                nav_controller = navController,
                title = site_title,
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = inner_padding.calculateTopPadding() + 16.dp)
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.VANILLA_ICE_CREAM)
@Composable
fun ModelDisplay(modelName: String?) {

    val context = LocalContext.current
    val engine = rememberEngine()
    val modelLoader = rememberModelLoader(engine = engine)
    val materialLoader = rememberMaterialLoader(engine = engine)
    val childNodes = rememberNodes()
    val cameraNode = rememberARCameraNode(engine = engine)
    val view = rememberView(engine = engine)
    val collisionSystem = rememberCollisionSystem(view = view)
    val planeRenderer = remember { mutableStateOf(true) }

    val trackingFailureReason = remember { mutableStateOf<TrackingFailureReason?>(null) }
    val frame = remember { mutableStateOf<Frame?>(null) }

    var model by remember { mutableStateOf<Model?>(null) }

    LaunchedEffect(modelName) {
        if (modelName != null) {
            try {
                model = modelLoader.createModel("models/${modelName}.glb")
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    ARScene(
        modifier = Modifier.fillMaxSize(),
        childNodes = childNodes,
        cameraNode = cameraNode,
        engine = engine,
        view = view,
        modelLoader = modelLoader,
        materialLoader = materialLoader,
        collisionSystem = collisionSystem,
        sessionConfiguration = { session, config ->
            config.depthMode =
                when (session.isDepthModeSupported(Config.DepthMode.AUTOMATIC)) {
                    true -> Config.DepthMode.AUTOMATIC
                    else -> Config.DepthMode.DISABLED
                }
            config.instantPlacementMode = Config.InstantPlacementMode.LOCAL_Y_UP
            config.lightEstimationMode = Config.LightEstimationMode.ENVIRONMENTAL_HDR
        },
        planeRenderer = planeRenderer.value,
        cameraStream = rememberARCameraStream(materialLoader),
        onSessionUpdated = { _, updatedFrame ->
            frame.value = updatedFrame
        },
        onTrackingFailureChanged = {
            trackingFailureReason.value = it
        },
        onSessionPaused = ({
            childNodes.clear()
            planeRenderer.value = true
        }),
        onSessionCreated = {session ->
            session.resume()
            Toast.makeText(context, "Tap to place the model when the white dots appear", Toast.LENGTH_SHORT).show()
        },
        onGestureListener = rememberOnGestureListener(
            onSingleTapConfirmed = { motionEvent, node ->
                if (node == null) {
                    model?.let { loadedModel ->
                        frame.value?.hitTest(motionEvent.x, motionEvent.y)?.firstOrNull {
                            it.isValid(depthPoint = true)
                        }?.createAnchor()?.let { anchor ->
                            planeRenderer.value = false
                            val anchorNode = AnchorNode(engine = engine, anchor = anchor)
                            val modelNode = ModelNode(
                                modelInstance = modelLoader.createInstance(loadedModel)!!,
                                scaleToUnits = 3f
                            ).apply { isEditable = true }
                            anchorNode.addChildNode(modelNode)
                            childNodes.add(anchorNode)
                        }
                    }
                }
            },
            onScale = { detector,_ ,node ->
                if (node is ModelNode) {
                    node.scale = node.scale * detector.scaleFactor
                }
            }
        )
    )
}

@Composable
private fun isARCoreSupportedAndUpToDate(): Boolean {
    //AR compatibility check, main part is courtesy of ARCore website
    //returns true if ARCore is supported and updated, false if otherwise, defaults to false
    val context = LocalContext.current
    val availability = ArCoreApk.getInstance().checkAvailability(context)
    var result = false
    when (availability) {
        Availability.SUPPORTED_INSTALLED -> result = true

        Availability.SUPPORTED_APK_TOO_OLD, Availability.SUPPORTED_NOT_INSTALLED -> {
            try {
                // Request ARCore installation or update if needed.
                val installStatus = ArCoreApk.getInstance().requestInstall(context as Activity?, true)
                when (installStatus) {
                    ArCoreApk.InstallStatus.INSTALL_REQUESTED -> {
                        Log.i(TAG, "ARCore installation requested.")
                        return false
                    }

                    ArCoreApk.InstallStatus.INSTALLED -> return true
                }
            } catch (e: UnavailableException) {
                Log.e(TAG, "ARCore not installed", e)
            }
            result = false
        }

        Availability.UNSUPPORTED_DEVICE_NOT_CAPABLE ->       // This device is not supported for AR.
            result =  false

        Availability.UNKNOWN_CHECKING, Availability.UNKNOWN_ERROR, Availability.UNKNOWN_TIMED_OUT -> {
            result = false
        }
    }
    return result
}