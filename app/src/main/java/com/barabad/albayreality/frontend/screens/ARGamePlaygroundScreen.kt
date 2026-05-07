package com.barabad.albayreality.frontend.screens

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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.barabad.albayreality.frontend.components.Header
import com.barabad.albayreality.frontend.components.NavBar
import com.barabad.albayreality.frontend.utilities.data.quizzes.AnswerRecord
import com.barabad.albayreality.frontend.utilities.data.quizzes.Quiz1
import com.barabad.albayreality.frontend.utilities.data.quizzes.QuizState
import com.barabad.albayreality.ui.theme.Inter
import com.barabad.albayreality.ui.theme.blue
import com.barabad.albayreality.ui.theme.green
import com.barabad.albayreality.ui.theme.orange
import com.barabad.albayreality.ui.theme.strokes
import com.barabad.albayreality.ui.theme.yellow
import kotlinx.coroutines.delay

@Composable
fun ARGamePlaygroundScreen(
    navController: NavController,
    site_id: String,
    site_title: String,
    quiz_state: QuizState
) {
    var active_tab by remember { mutableStateOf(-1) }
    var timer = 4

    LaunchedEffect(site_id) {
        quiz_state.loadQuizForSite(site_id)
    }

    // # get ung current item sa active quiz sa quiz state and its infos
    val current_item = quiz_state.getCurrentItem()
    val has_no_remaining_item = quiz_state.hasNoRemainingItem()

    // # quiz state variables, para tos sa UI
    val quiz_question = current_item.question
    val correct_answer = current_item.correctAnswer
    var quiz_choices = listOf(
        current_item.option1,
        current_item.option2,
        current_item.option3,
        current_item.option4
    )

    val choice_colors = listOf(orange, yellow, green, blue)

    // # timer and selection state
    var timer_value by remember { mutableIntStateOf(timer) }
    var is_answered by remember { mutableStateOf(false) }
    var selected_option by remember { mutableStateOf("") }

    // # reset the local states when a new question loads
    LaunchedEffect(current_item.question) {
        timer_value = timer
        is_answered = false
        selected_option = ""
    }

    // # timer logic
    LaunchedEffect(timer_value, is_answered) {
        if (!is_answered && timer_value > 0) {
            // # if the user has no answer and there is still time remaining then decrement the time
            delay(1000L)
            timer_value -= 1
        } else if (timer_value == 0 && !is_answered) {
            // # if the user has no answer and there is no time remaining then it is joever
            is_answered = true
            val status = "times_up_status"
            quiz_state.recordScore(status)
            // # display the feedback to the user and move

            // Store attempt in firestore when time is up
            val timeTaken = timer
            quiz_state.addAnswerRecord(
                AnswerRecord(
                    questionIndex = quiz_state.current_item_number,
                    choiceText = "",
                    timeTaken = timeTaken,
                    isCorrect = false
                )
            )

            navController.navigate("argame_result/${site_id}/${status}")
            println("Times up")
        }
    }

    LaunchedEffect(selected_option) {
        if (selected_option.isNotEmpty()) {
            // # if selected_option is not empty (meaning may answer si user), then evaluate it
            delay(500L)
            val is_correct = selected_option == correct_answer

            println("Answer of user: " + selected_option)
            println("Correct answer: " + correct_answer)
            println("Is Correct: " + is_correct)
            println("Is Last Item: " + has_no_remaining_item)

            // # check kung tama ung selected option ni user sa current item ng active quiz
            val status = if (selected_option == current_item.correctAnswer) {
                "correct_status"
            } else {
                "incorrect_status"
            }

            // # record the score in the quiz state
            quiz_state.recordScore(status)

            // Store attempt in firestore
            val timeTaken = timer - timer_value
            val isCorrect = selected_option == correct_answer
            quiz_state.addAnswerRecord(
                AnswerRecord(
                    questionIndex = quiz_state.current_item_number,
                    choiceText = selected_option,
                    timeTaken = timeTaken,
                    isCorrect = isCorrect
                )
            )
            // # display the feedback sa user
            navController.navigate("argame_result/${site_id}/${status}")
        }
    }

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

            Spacer(modifier = Modifier.height(16.dp))

            // # question text
            Text(
                text = quiz_question,
                style = TextStyle(
                    fontFamily = Inter,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = strokes
                ),
                modifier = Modifier.padding(horizontal = 24.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
                    .height(220.dp)
                    .shadow(elevation = 4.dp, shape = RoundedCornerShape(12.dp))
                    .background(Color(0xFFEEEEEE), RoundedCornerShape(12.dp))
                    .border(1.dp, Color(0xFF6B4226), RoundedCornerShape(12.dp))
            ) {

                Text(
                    text = "Picture",
                    style = TextStyle(
                        fontFamily = Inter,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = strokes
                    ),
                    modifier = Modifier.align(Alignment.Center)
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(12.dp)
                ) {
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = timer_value.toString(),
                        style = TextStyle(
                            fontFamily = Inter,
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 16.sp,
                            color = Color(0xFFFDB067)
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // # choices list
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
            ) {
                quiz_choices.forEachIndexed { index, option ->

                    val is_selected = selected_option == option

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 12.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(choice_colors[index])
                            .border(
                                width = if (is_selected) 2.dp else 0.dp,
                                color = if (is_selected) strokes else Color.Transparent,
                                shape = RoundedCornerShape(8.dp)
                            )
                            .clickable(enabled = !is_answered) {
                                is_answered = true
                                selected_option = option
                            }
                            .padding(16.dp),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Text(
                            text = option,
                            style = TextStyle(
                                fontFamily = Inter,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 14.sp,
                                color = strokes,
                                lineHeight = 20.sp
                            )
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}