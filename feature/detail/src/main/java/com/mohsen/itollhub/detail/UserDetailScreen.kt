package com.mohsen.itollhub.detail

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.mohsen.itollhub.designsystem.LoadingScreen
import com.mohsen.itollhub.model.UserDetail

@Composable
fun UserDetailRoute(userName: String, viewModel: UserDetailViewModel = hiltViewModel()) {
    val viewState by viewModel.state.collectAsStateWithLifecycle()
    LaunchedEffect(userName) {
        viewModel.getUserDetail(userName)
    }
    viewState.user?.let {
        UserDetailCard(it)
    } ?: run {
        LoadingScreen(description = viewState.description)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun UserDetailCard(user: UserDetail) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter,
    ) {
        Card(
            modifier = Modifier.padding(horizontal = 32.dp, vertical = 64.dp),
            elevation = 4.dp,
            shape = RoundedCornerShape(4.dp),
        ) {
            Column(
                modifier = Modifier
                    .padding(
                        start = 16.dp, top = 96.dp, end = 16.dp, bottom = 32.dp
                    )
                    .fillMaxWidth()
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.baseline_person_24),
                        colorFilter = ColorFilter.tint(Color.Gray),
                        contentDescription = ""
                    )
                    Text(
                        modifier = Modifier.padding(4.dp),
                        text = stringResource(id = R.string.name)
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        modifier = Modifier.basicMarquee(),
                        text = user.name,
                        style = MaterialTheme.typography.body2,
                        maxLines = 1
                    )
                }
                Row(
                    modifier = Modifier.padding(top = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.baseline_menu_book_24),
                        colorFilter = ColorFilter.tint(Color.Gray),
                        contentDescription = ""
                    )
                    Text(
                        modifier = Modifier.padding(4.dp),
                        text = stringResource(id = R.string.bio)
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        modifier = Modifier.basicMarquee(),
                        text = user.bio,
                        style = MaterialTheme.typography.body2,
                        maxLines = 1
                    )
                }
                Row(
                    modifier = Modifier.padding(top = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.baseline_work_24),
                        colorFilter = ColorFilter.tint(Color.Gray),
                        contentDescription = ""
                    )
                    Text(
                        modifier = Modifier.padding(4.dp),
                        text = stringResource(id = R.string.company)
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        modifier = Modifier.basicMarquee(),
                        text = user.company,
                        style = MaterialTheme.typography.body2,
                        maxLines = 1
                    )
                }
                Row(
                    modifier = Modifier.padding(top = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.baseline_location_on_24),
                        colorFilter = ColorFilter.tint(Color.Gray),
                        contentDescription = ""
                    )
                    Text(
                        modifier = Modifier.padding(4.dp),
                        text = stringResource(id = R.string.location)
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        modifier = Modifier.basicMarquee(),
                        text = user.location,
                        style = MaterialTheme.typography.body2,
                        maxLines = 1
                    )
                }
            }
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.width(
                IntrinsicSize.Max
            )
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current).data(user.avatarUrl)
                    .fallback(R.drawable.placeholder).error(R.drawable.placeholder)
                    .placeholder(R.drawable.placeholder).crossfade(true).build(),
                contentDescription = "",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .width(120.dp)
                    .height(120.dp)
                    .padding(16.dp)
                    .border(
                        border = BorderStroke(2.dp, MaterialTheme.colors.onBackground),
                        CircleShape
                    )
                    .clip(CircleShape),
            )
            Text(
                modifier = Modifier
                    .basicMarquee()
                    .padding(bottom = 8.dp),
                text = "@${user.userName}",
                style = MaterialTheme.typography.body2,
                maxLines = 1
            )
            Divider(
                modifier = Modifier
                    .height(1.dp)
                    .fillMaxWidth(),
                color = Color.Gray
            )
        }
    }
}

@Preview
@Composable
fun UserDetailCardPreview() {
    UserDetailCard(
        UserDetail(
            userName = "Einhesari",
            name = "Mohsen",
            avatarUrl = "",
            bio = "mohsen bio",
            location = "Tehran",
            company = "mohsen company",
            followersCount = 2,
            followingCount = 1
        )
    )
}