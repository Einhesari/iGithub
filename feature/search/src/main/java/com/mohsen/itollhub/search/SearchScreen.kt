package com.mohsen.itollhub.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SearchRoute(onItemClicked: (Int) -> Unit) {
    val scaffoldState = rememberScaffoldState()
    SearchScreen(scaffoldState = scaffoldState)
}

@Composable
private fun SearchScreen(
    modifier: Modifier = Modifier,
    scaffoldState: ScaffoldState
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = { TopAppBar(title = { Text(text = stringResource(id = R.string.app_name)) }) },
        scaffoldState = scaffoldState
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            SearchBar()
        }
    }
}

@Composable
private fun SearchBar() {
    var searchQuery by remember {
        mutableStateOf(TextFieldValue(""))
    }
    Row(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            value = searchQuery,
            onValueChange = {
                searchQuery = it
            },
            placeholder = {
                Text(
                    text = stringResource(id = R.string.search_hint),
                    fontSize = 12.sp
                )
            },
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 8.dp)
        )
        Button(onClick = { /*TODO*/ }, modifier = Modifier.height(IntrinsicSize.Max)) {
            Image(
                painter = painterResource(id = R.drawable.baseline_search_24),
                contentDescription = "search"
            )
        }
    }
}

@Composable
@Preview
fun SearchBarPreview() {
    SearchBar()
}