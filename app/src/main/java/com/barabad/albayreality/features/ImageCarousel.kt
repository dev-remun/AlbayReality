package com.barabad.albayreality.features

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.barabad.albayreality.ui.theme.strokes
import kotlinx.coroutines.delay

@Composable
fun ImageCarousel(
    images: List<Int>,
    modifier: Modifier = Modifier,
    auto_slide_duration: Long = 2000L // # 2 seconds per slide
) {
    val pager_state = rememberPagerState(
        initialPage = 0,
        pageCount = { images.size }
    )

    // # auto-slide coroutine
    LaunchedEffect(Unit) {
        while (true) {
            delay(auto_slide_duration)
            val next_page = (pager_state.currentPage + 1) % images.size
            pager_state.animateScrollToPage(next_page)
        }
    }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(220.dp)
            .border(
                width = 2.dp,
                color = strokes,
                shape = RoundedCornerShape(8.dp)
            ),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(6.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        HorizontalPager(
            state = pager_state,
            modifier = Modifier.fillMaxSize()
        ) { page ->
            Image(
                painter = painterResource(id = images[page]),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(8.dp)) // # ensures the image fills the rounded bounds properly
            )
        }
    }
}