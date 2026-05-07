package com.barabad.albayreality.frontend.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.barabad.albayreality.frontend.components.Header
import com.barabad.albayreality.frontend.components.NavBar
import com.barabad.albayreality.frontend.utilities.data.quizzes.QuizAttempt
import com.barabad.albayreality.frontend.utilities.data.quizzes.QuizRepository
import com.barabad.albayreality.frontend.utilities.data.quizzes.getPastAttempts
import com.barabad.albayreality.ui.theme.Inter
import com.barabad.albayreality.ui.theme.green
import com.barabad.albayreality.ui.theme.red
import com.barabad.albayreality.ui.theme.strokes
import com.barabad.albayreality.ui.theme.yellow
import com.google.firebase.auth.FirebaseAuth

@Composable
fun ARGameShowScoreScreen(
    navController: NavController,
    site_id: String,
    site_title: String
) {
    var active_tab by remember { mutableStateOf(-1) }
    var attempt_list by remember { mutableStateOf<List<QuizAttempt>>(emptyList()) }
    var is_loading by remember { mutableStateOf(true) }

    BackHandler {
        navController.navigate("games") {
            popUpTo("games") { inclusive = false }
        }
    }

    // # fetch the past scores when the screen loads
    LaunchedEffect(site_id) {
        val user_id = FirebaseAuth.getInstance().currentUser?.uid
        if (user_id != null) {
            val repo = QuizRepository()
            attempt_list = getPastAttempts(user_id, site_id)
        }
        is_loading = false
    }

    Scaffold (
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
                title = "$site_title Scores",
                onBackClick = {
                    navController.navigate("games") {
                        popUpTo("games") { inclusive = false }
                    }
                }
            )

            Spacer(modifier = Modifier.height(48.dp))

            if (is_loading) {
                Text(
                    text = "Loading scores...",
                    style = TextStyle(fontFamily = Inter, color = strokes)
                )
            } else if (attempt_list.isEmpty()) {
                Text(
                    text = "No attempts found.",
                    style = TextStyle(fontFamily = Inter, color = strokes)
                )
            } else {
                // # loop through each attempt and display it
                attempt_list.forEachIndexed { index, attempt ->
                    AttemptScoreCard(attempt_number = index + 1, attempt = attempt)
                }
            }

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
fun AttemptScoreCard(attempt_number: Int, attempt: QuizAttempt) {
    // # calculate percentages for this specific attempt
    val total_items = attempt.correct_count + attempt.incorrect_count + attempt.missed_count

    val correct_percentage = if (total_items > 0) (attempt.correct_count.toFloat() / total_items) * 100 else 0f
    val incorrect_percentage = if (total_items > 0) (attempt.incorrect_count.toFloat() / total_items) * 100 else 0f
    val missed_percentage = if (total_items > 0) (attempt.missed_count.toFloat() / total_items) * 100 else 0f

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
    ) {
        Text(
            text = "Attempt $attempt_number",
            style = TextStyle(
                fontFamily = Inter,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = strokes
            ),
            modifier = Modifier.padding(bottom = 16.dp)
        )

        ScoreStatBox(label = "Correct Items: ${attempt.correct_count}", percentage = correct_percentage, bg_color = green)
        Spacer(modifier = Modifier.height(16.dp))

        ScoreStatBox(label = "Incorrect Items: ${attempt.incorrect_count}", percentage = incorrect_percentage, bg_color = red)
        Spacer(modifier = Modifier.height(16.dp))

        ScoreStatBox(label = "Missed Items: ${attempt.missed_count}", percentage = missed_percentage, bg_color = yellow)

        Spacer(modifier = Modifier.height(32.dp))
    }
}

@Composable
fun ScoreStatBox(label: String, percentage: Float, bg_color: Color) {
    // # format percentage to drop .00 if it's a whole number
    val formatted_pct = if (percentage % 1.0 == 0.0) {
        "${percentage.toInt()}%"
    } else {
        String.format("%.2f%%", percentage)
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(bg_color)
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