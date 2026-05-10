package com.barabad.albayreality.frontend.screens

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.barabad.albayreality.R
import com.barabad.albayreality.frontend.components.Button
import com.barabad.albayreality.frontend.components.Header
import com.barabad.albayreality.frontend.components.NavBar
import com.barabad.albayreality.frontend.components.PopUp
import com.barabad.albayreality.frontend.utilities.data.quizzes.QuizRepository
import com.barabad.albayreality.frontend.utilities.data.quizzes.QuizState
import com.barabad.albayreality.frontend.utilities.utils.rememberNetworkStatus
import com.barabad.albayreality.ui.theme.Inter
import com.barabad.albayreality.ui.theme.green
import com.barabad.albayreality.ui.theme.red
import com.barabad.albayreality.ui.theme.strokes
import com.barabad.albayreality.ui.theme.yellow
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

@Composable
fun ARGameSummaryScreen(
    navController: NavController,
    site_id: String,
    site_title: String,
    quiz_state: QuizState
) {
    var active_tab by remember { mutableStateOf(-1) }
    val scope = rememberCoroutineScope()

    var is_processing by remember { mutableStateOf(false) }

    // # pull scores from the state
    val correct = remember { quiz_state.correct_answered_items }
    val incorrect = remember { quiz_state.incorrect_answered_items }
    val missed = remember { quiz_state.missed_items }
    val total = correct + incorrect + missed

    // # calculate percentages
    val correct_percentage = if (total > 0) (correct.toFloat() / total) * 100 else 0f
    val incorrect_percentage = if (total > 0) (incorrect.toFloat() / total) * 100 else 0f
    val missed_percentage = if (total > 0) (missed.toFloat() / total) * 100 else 0f

    // # network checking
    val is_connected by rememberNetworkStatus()
    var display_network_popup by remember { mutableStateOf(false) }

    // # display network popup
    if (display_network_popup) {
        PopUp(
            icon = R.drawable.xmark_icon,
            message = "Please connect to Wi-Fi or mobile data to save your score.",
            button_text = "Okay",
            onButtonClick = {
                display_network_popup = false
            },
            onDismiss = {
                display_network_popup = true
            }
        )
    }

    BackHandler {
    }

    Scaffold() { inner_padding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(inner_padding),
            contentAlignment = Alignment.TopCenter
        ) {
            Column(
                modifier = Modifier
                    .widthIn(max = 700.dp)
                    .fillMaxHeight()
                    .padding(top = 24.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Header(
                    nav_controller = navController,
                    title = site_title,
                    onBackClick = {},
                    show_back = false
                )

                Spacer(modifier = Modifier.height(48.dp))

                // # list of score boxes
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp)
                ) {
                    StatBox(label = "Correct Items: $correct", percentage = correct_percentage, bgColor = green)
                    Spacer(modifier = Modifier.height(16.dp))

                    StatBox(label = "Incorrect Items: $incorrect", percentage = incorrect_percentage, bgColor = red)
                    Spacer(modifier = Modifier.height(16.dp))

                    StatBox(label = "Missed Items: $missed", percentage = missed_percentage, bgColor = yellow)
                }

                // Spacer(modifier = Modifier.weight(1f))
                Spacer(modifier = Modifier.height(48.dp))

                Button(
                    text = "Continue",
                    isPrimary = true,
                    is_enabled = !is_processing,
                    onClick = {
                        // # check network connection first before attempting to save
                        if (!is_connected) {
                            display_network_popup = true
                            return@Button
                        }

                        is_processing = true

                        val userId = FirebaseAuth.getInstance().currentUser?.uid ?: return@Button
                        val totalTime = quiz_state.answerRecords.sumOf { it.timeTaken }
                        val answersSnapshot = quiz_state.answerRecords.toList()
                        val siteId = site_id

                        scope.launch {
                            try {
                                val repo = QuizRepository()
                                repo.saveAttempt(
                                    userId = userId,
                                    siteId = siteId,
                                    totalTimeTaken = totalTime,
                                    answerRecords = answersSnapshot,
                                    correctCount = correct,
                                    incorrectCount = incorrect,
                                    missedCount = missed
                                )
                            } catch (e: Exception) {
                                Log.e("Summary", "Failed to save attempt", e)
                            }
                        }

                        quiz_state.clearSiteId()
                        quiz_state.resetQuiz()
                        navController.popBackStack()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp, vertical = 32.dp),
                )
            }
        }
    }
}

@Composable
fun StatBox(label: String, percentage: Float, bgColor: Color) {
    // # format percentage to drop .00 if it's a whole number, otherwise show 2 decimal places
    val formatted_pct = if (percentage % 1.0 == 0.0) {
        String.format("%.2f%%", percentage)
    } else {
        String.format("%.2f%%", percentage)
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(bgColor)
            .padding(horizontal = 16.dp, vertical = 20.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = label,
                style = TextStyle(
                    fontFamily = Inter,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = strokes
                )
            )

            Text(
                text = formatted_pct,
                style = TextStyle(
                    fontFamily = Inter,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 16.sp,
                    color = strokes
                )
            )
        }
    }
}