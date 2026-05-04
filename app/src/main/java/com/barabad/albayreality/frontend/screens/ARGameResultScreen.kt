package com.barabad.albayreality.frontend.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.toLowerCase
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.barabad.albayreality.R
import com.barabad.albayreality.frontend.components.Header
import com.barabad.albayreality.frontend.utilities.data.quizzes.QuizState
import com.barabad.albayreality.ui.theme.Inter
import com.barabad.albayreality.ui.theme.green
import com.barabad.albayreality.ui.theme.orange
import com.barabad.albayreality.ui.theme.primary
import com.barabad.albayreality.ui.theme.strokes

@Composable
fun ARGameResultScreen(
    navController: NavController,
    site_title: String,
    site_id : String,
    result_status: String,
    quiz_state: QuizState
) {

    val current_item = quiz_state.getCurrentItem()
    val has_no_remaining_item = quiz_state.hasNoRemainingItem()

    var feedback_message : String
    var feedback_picture : Int

    if (result_status.lowercase() == "correct_status") {
        feedback_message = "Your answer is correct!"
        feedback_picture = R.drawable.correct_feedback
    } else if (result_status.lowercase() == "incorrect_status") {
        feedback_message = "Your answer is incorrect. Try again :>"
        feedback_picture = R.drawable.incorrect_feedback
    } else {
        feedback_message = "Times up. You did not answer."
        feedback_picture = R.drawable.timesup_feedback
    }

    Scaffold { inner_padding ->

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

            Spacer(modifier = Modifier.height(32.dp))

            // # question holder
            Text(
                text = current_item.question,
                style = TextStyle(
                    fontFamily = Inter,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = strokes
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 24.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))

            Image(
                painter = painterResource(id = feedback_picture),
                contentDescription = "Result Image",
                modifier = Modifier.size(150.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            // # feedback text
            Text(
                text = feedback_message,
                style = TextStyle(
                    fontFamily = Inter,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = strokes
                )
            )

            Spacer(modifier = Modifier.height(48.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
            ) {
                Text(
                    text = "Correct Answer",
                    style = TextStyle(
                        fontFamily = Inter,
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                        color = orange
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                        .background(green)
                        .padding(16.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Text(
                        text = current_item.correctAnswer,
                        style = TextStyle(
                            fontFamily = Inter,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 14.sp,
                            color = strokes
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(80.dp))

            // # next / finish button
            val button_text = if (has_no_remaining_item) "Finish" else "Next"

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 32.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(primary)
                    .border(2.dp, strokes, RoundedCornerShape(8.dp))
                    .clickable {
                        if (has_no_remaining_item) {
                            // # summary screen dito
                            quiz_state.clearSiteId()
                            navController.navigate("argame_summary/$site_id/$site_title")
                        } else {
                            // # update the quiz state to point sa next item niya
                            quiz_state.nextItem()
                            navController.popBackStack() // # navigate back sa playground screen but this time nasa next item na siya ng quoiz
                        }
                    }
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = button_text,
                    style = TextStyle(
                        fontFamily = Inter,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = strokes
                    )
                )
            }
        }
    }
}