package com.mohsen.itollhub.search

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TopAppBar
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.mohsen.itollhub.designsystem.LoadingScreen
import com.mohsen.itollhub.model.User

@Composable
fun SearchRoute(onItemClicked: (String) -> Unit, viewModel: SearchViewModel = hiltViewModel()) {
    val scaffoldState = rememberScaffoldState()
    val viewState by viewModel.state.collectAsStateWithLifecycle()
    SearchScreen(scaffoldState = scaffoldState, viewState, onUserCardClicked = onItemClicked) {
        viewModel.searchUser(it)
    }
}

@Composable
private fun SearchScreen(
    scaffoldState: ScaffoldState,
    viewState: SearchScreenState,
    modifier: Modifier = Modifier,
    onUserCardClicked: (String) -> Unit,
    onSearchButtonClicked: (String) -> Unit
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = { TopAppBar(title = { Text(text = stringResource(id = R.string.app_name)) }) },
        scaffoldState = scaffoldState
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            SearchBar(onSearchButtonClicked)
            with(viewState) {
                if (users.isNotEmpty()) {
                    UsersList(
                        users = users,
                        listState = rememberLazyGridState(),
                        onUserCardClicked = onUserCardClicked
                    )
                } else {
                    LoadingScreen(description = description)
                }
            }
        }

    }
}


@Composable
private fun SearchBar(onSearchButtonClicked: (String) -> Unit) {
    var searchQuery by remember {
        mutableStateOf(TextFieldValue(""))
    }
    Row(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(value = searchQuery, onValueChange = {
            searchQuery = it
        }, placeholder = {
            Text(
                text = stringResource(id = R.string.search_hint), fontSize = 12.sp
            )
        }, modifier = Modifier
            .weight(1f)
            .padding(horizontal = 8.dp)
        )
        Button(
            onClick = { onSearchButtonClicked(searchQuery.text) },
            modifier = Modifier.height(IntrinsicSize.Max)
        ) {
            Image(
                painter = painterResource(id = R.drawable.baseline_search_24),
                contentDescription = stringResource(id = R.string.search_desc)
            )
        }
    }
}

@Composable
private fun UsersList(
    users: List<User>, listState: LazyGridState,
    onUserCardClicked: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        modifier = modifier,
        state = listState,
        contentPadding = PaddingValues(16.dp),
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(users, key = { user -> user.id }) {
            UserItem(user = it, onUserCardClicked = onUserCardClicked)
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun UserItem(
    user: User,
    onUserCardClicked: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.clickable {
            onUserCardClicked(user.userName)
        },
        elevation = 4.dp,
        shape = RoundedCornerShape(4.dp),
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current).data(user.avatarUrl)
                    .fallback(R.drawable.placeholder).error(R.drawable.placeholder)
                    .placeholder(R.drawable.placeholder).crossfade(true).build(),
                contentDescription = stringResource(id = R.string.user_avatar_desc),
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .width(120.dp)
                    .height(120.dp)
                    .padding(top = 16.dp, start = 8.dp, end = 8.dp)
                    .border(
                        border = BorderStroke(2.dp, MaterialTheme.colors.onBackground), CircleShape
                    )
                    .clip(CircleShape),
            )
            Divider(
                modifier = Modifier
                    .padding(vertical = 8.dp, horizontal = 16.dp)
                    .height(1.dp)
                    .fillMaxWidth(),
                color = Color.Gray
            )
            Text(
                modifier = Modifier
                    .padding(bottom = 8.dp)
                    .basicMarquee(),
                text = user.userName,
                style = MaterialTheme.typography.body2,
                maxLines = 1,
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Composable
@Preview
private fun SearchBarPreview() {
    SearchBar {}
}

@Preview
@Composable
fun UserItemPreview() {
    UserItem(
        user = User(
            "Mohsen",
            1,
            1.0f,
            "User",
            "https://avatars.githubusercontent.com/u/50930?v=4"
        ),
        onUserCardClicked = {}
    )
}