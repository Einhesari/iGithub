package com.mohsen.itollhub.detail

import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.mohsen.itollhub.designsystem.LoadingScreen
import com.mohsen.itollhub.model.UserDetail

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserDetailScreen(
    userName: String,
    modifier: Modifier = Modifier,
    viewModel: UserDetailViewModel = hiltViewModel()
) {
    val viewState by viewModel.state.collectAsStateWithLifecycle()
    LaunchedEffect(userName) {
        viewModel.getUserDetail(userName)
    }
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = { TopAppBar(title = { Text(text = stringResource(id = R.string.app_name)) }) },
    ) {
        Column(modifier = Modifier
            .padding(it)
            .verticalScroll(rememberScrollState())) {
            viewState.user?.let {
                UserDetailCard(it)
            } ?: run {
                LoadingScreen(viewState.description, viewState.isLoading)
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun UserDetailCard(user: UserDetail, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter,
    ) {
        Card(
            modifier = Modifier.padding(horizontal = 32.dp, vertical = 64.dp),
            elevation = CardDefaults.elevatedCardElevation(),
            shape = RoundedCornerShape(4.dp),
        ) {
            Column(
                modifier = Modifier
                    .padding(
                        start = 16.dp, top = 96.dp, end = 16.dp, bottom = 32.dp
                    )
                    .fillMaxWidth()
            ) {
                UserDetailRow(
                    key = R.string.name,
                    value = user.name,
                    imageVector = Icons.Default.Person
                )
                UserDetailRow(
                    key = R.string.bio,
                    value = user.bio,
                    imageVector = ImageVector.vectorResource(id = R.drawable.baseline_menu_book_24)
                )
                UserDetailRow(
                    key = R.string.company,
                    value = user.company,
                    imageVector = ImageVector.vectorResource(id = R.drawable.baseline_work_24)
                )
                UserDetailRow(
                    key = R.string.location,
                    value = user.location,
                    imageVector = Icons.Default.LocationOn
                )
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
                        border = BorderStroke(2.dp, MaterialTheme.colorScheme.onBackground),
                        CircleShape
                    )
                    .clip(CircleShape),
            )
            Text(
                modifier = Modifier
                    .basicMarquee()
                    .padding(bottom = 8.dp),
                text = "@${user.userName}",
                style = MaterialTheme.typography.bodyMedium,
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun UserDetailRow(
    @StringRes key: Int,
    value: String,
    imageVector: ImageVector,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.padding(top = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = ""
        )
        Text(
            modifier = Modifier.padding(4.dp),
            text = stringResource(id = key)
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            modifier = Modifier.basicMarquee(),
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            maxLines = 1
        )
    }
}

@Preview
@Composable
private fun UserDetailCardPreview() {
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