package com.barabad.albayreality.features

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.barabad.albayreality.components.ButtonTypeB
import com.barabad.albayreality.data.DatabaseProvider
import com.barabad.albayreality.ui.theme.Inter
import com.google.ar.core.Config
import com.google.ar.core.Frame
import com.google.ar.core.TrackingFailureReason
import io.github.sceneview.ar.ARScene
import io.github.sceneview.ar.arcore.createAnchor
import io.github.sceneview.ar.arcore.hitTest
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
fun ArSuccessScan(navController: NavController) {
    val globeVal: GlobalVar? = LocalContext.current.applicationContext as? GlobalVar
    val qrContent = globeVal?.content
    val location_sites = remember(qrContent) {
        qrContent?.let { DatabaseProvider.database.getModelByQRCode(it) }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFFEFEFEF)
    ) {

        // Main container
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 22.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Spacer(modifier = Modifier.height(60.dp))

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "AR Scanner",
                        fontSize = 24.sp,
                        fontFamily = Inter,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = "QR Code scanned successfully!",
                        fontSize = 14.sp,
                        fontFamily = Inter,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0x99000000)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Camera area box
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(480.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color.Green.copy(alpha = 0.1f)),
                    contentAlignment = Alignment.Center
                ) {

                    if (qrContent?.contains("albayreality") == true ) {
                        ModelDisplay(modelName = qrContent)
                    } else {
                        Text("No 3D model found for this QR code. Scanned: $qrContent")
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                if(location_sites != null) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp)
                    ) {
                        Text(
                            text = location_sites.getName().toString(),
                            fontSize = 24.sp,
                            fontFamily = Inter,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )

                        Spacer(modifier = Modifier.height(6.dp))

                        Text(
                            text = "${location_sites.getLocation()}",
                            fontSize = 14.sp,
                            fontFamily = Inter,
                            fontWeight = FontWeight.Medium,
                            color = Color.Black,
                            maxLines = 2
                        )

                        Spacer(modifier = Modifier.height(20.dp))

                        Text(
                            text = location_sites.getDescription().toString(),
                            fontSize = 14.sp,
                            fontFamily = Inter,
                            fontWeight = FontWeight.Normal,
                            color = Color(0xFF444444)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))
            }

            // Fixed bottom button
            ButtonTypeB(
                text = "Scan Again",
                onClick = {
                    navController.navigate("ar") {
                        popUpTo("ar_success_scan") { inclusive = true }
                        launchSingleTop = true
                    }
                },
            )

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@RequiresApi(Build.VERSION_CODES.VANILLA_ICE_CREAM)
@Composable
fun ModelDisplay(modelName: String?) {

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
        onSessionCreated = {session -> session.resume()},
        onGestureListener = rememberOnGestureListener(
            onSingleTapConfirmed = { motionEvent, node ->
                if (node == null) {
                    model?.let { loadedModel ->
                        frame.value?.hitTest(motionEvent.x, motionEvent.y)?.firstOrNull {
                            it.isValid(depthPoint = false)
                        }?.createAnchor()?.let { anchor ->
                            planeRenderer.value = false
                            val anchorNode = AnchorNode(engine = engine, anchor = anchor)
                            val modelNode = ModelNode(
                                modelInstance = modelLoader.createInstance(loadedModel)!!,
                                scaleToUnits = 0.2f
                            ).apply { isEditable = true }
                            anchorNode.addChildNode(modelNode)
                            childNodes.add(anchorNode)
                        }
                    }
                }
            }
        )
    )
}

