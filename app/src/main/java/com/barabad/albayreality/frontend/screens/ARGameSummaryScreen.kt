package com.barabad.albayreality.frontend.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
import com.barabad.albayreality.backend.FirebaseAuthManager
import com.barabad.albayreality.frontend.components.Button
import com.barabad.albayreality.frontend.components.Header
import com.barabad.albayreality.frontend.components.NavBar
import com.barabad.albayreality.frontend.utilities.data.quizzes.QuizRepository
import com.barabad.albayreality.frontend.utilities.data.quizzes.QuizState
import com.barabad.albayreality.ui.theme.Inter
import com.barabad.albayreality.ui.theme.green
import com.barabad.albayreality.ui.theme.orange
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

    // # pull scores from the state
    val correct = quiz_state.correct_answered_items
    val incorrect = quiz_state.incorrect_answered_items
    val missed = quiz_state.missed_items
    val total = correct + incorrect + missed

    // # calculate percentages
    val correct_percentage = if (total > 0) (correct.toFloat() / total) * 100 else 0f
    val incorrect_percentage = if (total > 0) (incorrect.toFloat() / total) * 100 else 0f
    val missed_percentage = if (total > 0) (missed.toFloat() / total) * 100 else 0f

    Scaffold(
        bottomBar = {
            NavBar(
                active_tab = active_tab,
                on_tab_selected = { selected_index ->
                    active_tab = selected_index
                },
                nav_controller = navController
            )
        }
    ) { inner_padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(inner_padding)
                .padding(top = 24.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Header(
                nav_controller = navController,
                title = site_title
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

            Spacer(modifier = Modifier.weight(1f))
            Spacer(modifier = Modifier.height(48.dp))

            Button(
                text = "Continue",
                isPrimary = true,
                onClick = {
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
                    navController.navigate("games") {
                        popUpTo("games") { inclusive = true }
                    }
                },
                modifier = Modifier.padding(bottom = 32.dp)
            )
        }
    }
}

@Composable
fun StatBox(label: String, percentage: Float, bgColor: Color) {
    // # format percentage to drop .00 if it's a whole number, otherwise show 2 decimal places
    val formatted_pct = if (percentage % 1.0 == 0.0) {
        "${percentage.toInt()}%"
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