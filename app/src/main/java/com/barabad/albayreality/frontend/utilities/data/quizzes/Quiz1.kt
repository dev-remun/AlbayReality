package com.barabad.albayreality.frontend.utilities.data.quizzes

//borrowing the method of the historical sites for now, couldnt think of another method of creating one

val Quiz1 = listOf(
    QuizzesModel(
        question = "The original Cagsawa church was established by which religious group?",
        option1 = "Jesuits",
        option2 = "Franciscans",
        option3 = "Dominicans",
        option4 = "Augustinians",
        correctAnswer = "Franciscans",
    ),
    QuizzesModel(
        question = "Before it was buried, Cagsawa was considered what type of settlement?",
        option1 = "A prosperous town",
        option2 = "A small fishing village",
        option3 = "A military outpost",
        option4 = "A mining community",
        correctAnswer = "A prosperous town",
    ),
    QuizzesModel(
        question = "What was one major reason people stayed in Cagsawa despite Mayon’s activity?",
        option1 = "Lack of knowledge about volcanoes",
        option2 = "Fertile volcanic soil",
        option3 = "Government orders",
        option4 = "Religious restrictions",
        correctAnswer = "Fertile volcanic soil",
    ),
    QuizzesModel(
        question = "During the 1814 eruption of Mayon Volcano, what primarily caused the high death toll?",
        option1 = "Lava flows",
        option2 = "Pyroclastic flows and ash",
        option3 = "Earthquakes",
        option4 = "Tsunamis",
        correctAnswer = "Pyroclastic flows and ash",
    ),
    QuizzesModel(
        question = "The bell tower remains visible today mainly because:",
        option1 = "It was rebuilt later",
        option2 = "It was made of stronger materials",
        option3 = "It stood higher than the rest of the structure",
        option4 = "It was relocated",
        correctAnswer = "It stood higher than the rest of the structure",
    ),
    QuizzesModel(
        question = "Which term best describes the type of volcano Mayon is?",
        option1 = "Stratovolcano",
        option2 = "Shield volcano",
        option3 = "Cinder cone",
        option4 = "Caldera",
        correctAnswer = "Stratovolcano",
    ),
    QuizzesModel(
        question = "The burial of Cagsawa is an example of which geological process?",
        option1 = "Erosion",
        option2 = "Weathering",
        option3 = "Subduction",
        option4 = "Deposition",
        correctAnswer = "Deposition",
    ),
    QuizzesModel(
        question = "Which hazard from volcanic eruptions can resemble fast-moving avalanches of hot gas and ash?",
        option1 = "Lahars",
        option2 = "Fumaroles",
        option3 = "Lava domes",
        option4 = "Pyroclastic flows",
        correctAnswer = "Pyroclastic flows",
    ),
    QuizzesModel(
        question = "What is the primary material that buried the town of Cagsawa?",
        option1 = "Ash and volcanic debris",
        option2 = "Water and mud",
        option3 = "Solidified lava rock only",
        option4 = "Sand and soil",
        correctAnswer = "Ash and volcanic debris",
    ),
    QuizzesModel(
        question = "Which factor contributes to Mayon Volcano’s frequent eruptions?",
        option1 = "Climate change",
        option2 = "Its proximity to the ocean only",
        option3 = "Its location along a tectonic plate boundary",
        option4 = "Human activity",
        correctAnswer = "Its location along a tectonic plate boundary",
    ),
)

fun getQuiz1List(): List<QuizzesModel> = Quiz1