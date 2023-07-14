package com.mohsen.itollhub.designsystem

import androidx.compose.animation.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp


@Composable
fun LoadingScreen(description: String, isLoading: Boolean) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val color = remember { Animatable(Color.Gray) }
        val primaryColor = MaterialTheme.colorScheme.primary
        LaunchedEffect(isLoading) {
            if (isLoading)
                color.animateTo(
                    primaryColor, animationSpec = infiniteRepeatable(
                        animation = tween(500, easing = FastOutSlowInEasing),
                        repeatMode = RepeatMode.Reverse
                    )
                )
        }
        Image(
            modifier = Modifier
                .padding(bottom = 8.dp)
                .height(150.dp)
                .width(150.dp),
            painter = painterResource(id = R.drawable.github),
            colorFilter = ColorFilter.tint(color.value),
            contentDescription = stringResource(id = R.string.github_icon_desc)
        )
        Text(text = description, color = Color.Gray)
    }
}
