package com.comye1.capstone.screens.explore

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.comye1.capstone.R
import com.comye1.capstone.network.Resource

@Composable
fun Explore(
    paddingValues: PaddingValues,
    toGoalDetail: () -> Unit,
    toSearch: () -> Unit
) {
    val (clickedPlayList, setClickedPlayList) = remember {
        mutableStateOf(PlayListThumb(null, "", ""))
    }

    ExploreMainScreen(
        paddingValues = paddingValues,
        toSearchScreen = toSearch,
        onPlayListClicked = { playList ->
//                    setClickedPlayList(playList)
//                    setScreenState(ExploreState.Detail)
            toGoalDetail()
        },
    )
}

@Composable
fun ExploreSearchScreen(
    viewModel: ExploreSearchViewModel = hiltViewModel(),
    toGoalDetail: (Int) -> Unit,
    toMainScreen: () -> Unit
) {

    Scaffold(
        modifier = Modifier.padding(bottom = 96.dp),
        topBar = {
            SearchTopBar(
                placeHolderText = "??????, ?????????, ????????? ????????? ?????? ??? ??????",
                queryString = viewModel.word,
                setQueryString = viewModel::setSearchWord,
                onBackButtonClick = toMainScreen
            ) {
                // onSearchKey
                viewModel.getQueryResult()
            }
        }
    ) {
        when (viewModel.queryResult) {
            is Resource.Success -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    LazyColumn(
                        Modifier
                            .fillMaxWidth()
                    ) {
                        item {
                            PLList(
                                title = "?????? ????????? ?????????",
                                listThumb = listOf(
                                    PlayListThumb(
                                        painterResource(id = R.drawable.toeic),
                                        title = "?????? 900 ??????",
                                        author = "?????????"
                                    ),
                                    PlayListThumb(
                                        painterResource(id = R.drawable.sample),
                                        title = "??????",
                                        author = "?????????"
                                    ),
                                    PlayListThumb(
                                        painterResource(id = R.drawable.sample),
                                        title = "??????",
                                        author = "?????????"
                                    ),
                                    PlayListThumb(
                                        painterResource(id = R.drawable.sample),
                                        title = "??????",
                                        author = "?????????"
                                    ),
                                    PlayListThumb(
                                        painterResource(id = R.drawable.sample),
                                        title = "??????",
                                        author = "?????????"
                                    ),
                                ),
                            ) { playList ->
//                    setClickedPlayList(playList)
//                    setScreenState(ExploreState.Detail)
                                toGoalDetail(-1)
                            }

                        }
                        item {
                            PLList(
                                title = "?????? ?????? : ??????",
                                listThumb = listOf(
                                    PlayListThumb(
                                        painterResource(id = R.drawable.sample),
                                        title = "??????",
                                        author = "?????????"
                                    ),
                                    PlayListThumb(
                                        painterResource(id = R.drawable.sample),
                                        title = "??????",
                                        author = "?????????"
                                    ),
                                    PlayListThumb(
                                        painterResource(id = R.drawable.sample),
                                        title = "??????",
                                        author = "?????????"
                                    ),
                                    PlayListThumb(
                                        painterResource(id = R.drawable.sample),
                                        title = "??????",
                                        author = "?????????"
                                    ),
                                ),
                            ) { playList ->
//                    setClickedPlayList(playList)
//                    setScreenState(ExploreState.Detail)
                                toGoalDetail(-1)
                            }
                        }
                        item {
                            PLList(
                                title = "?????? ?????? : ??????",
                                listThumb = listOf(
                                    PlayListThumb(
                                        painterResource(id = R.drawable.sample),
                                        title = "??????",
                                        author = "?????????"
                                    ),
                                    PlayListThumb(
                                        painterResource(id = R.drawable.sample),
                                        title = "??????",
                                        author = "?????????"
                                    ),
                                    PlayListThumb(
                                        painterResource(id = R.drawable.sample),
                                        title = "??????",
                                        author = "?????????"
                                    ),
                                    PlayListThumb(
                                        painterResource(id = R.drawable.sample),
                                        title = "??????",
                                        author = "?????????"
                                    ),
                                ),
                            ) { playList ->
//                    setClickedPlayList(playList)
//                    setScreenState(ExploreState.Detail)
                                toGoalDetail(-1)
                            }
                        }
                    }
                }

            }
            is Resource.Failure -> {
            }
            is Resource.Loading -> {
            }
        }
    }

}

@Composable
fun ExploreMainScreen(
    paddingValues: PaddingValues,
    onPlayListClicked: (PlayListThumb) -> Unit,
    toSearchScreen: () -> Unit,
) {
    Column(
        modifier = Modifier.padding(bottom = 96.dp),
    ) {
        TopAppBar(
            title = { Text("Explore") },
            actions = {
                IconButton(onClick = toSearchScreen) {
                    Icon(imageVector = Icons.Default.Search, contentDescription = "search icon")
                }
            }
        )
        LazyColumn(
            Modifier
                .fillMaxWidth()
                .padding(paddingValues = paddingValues)
        ) {
            item {
                PLList(
                    title = "?????? ?????? : ??????",
                    listThumb = listOf(
                        PlayListThumb(
                            painterResource(id = R.drawable.toeic),
                            title = "?????? 900 ??????",
                            author = "?????????"
                        ),
                        PlayListThumb(
                            painterResource(id = R.drawable.toeic),
                            title = "????????? 100???",
                            author = "??????"
                        ),
                        PlayListThumb(
                            painterResource(id = R.drawable.toeic),
                            title = "HSK 6?????????",
                            author = "??????"
                        ),
                    ),
                    onItemClick = onPlayListClicked
                )

            }
            item {
//                PLList(
//                    title = "??????????????? ???????????? ??????",
//                    listThumb = listOf(
//                        PlayListThumb(
//                            painterResource(id = R.drawable.toeic),
//                            title = "??????",
//                            author = "?????????"
//                        ),
//                        PlayListThumb(
//                            painterResource(id = R.drawable.toeic),
//                            title = "??????",
//                            author = "?????????"
//                        ),
//                        PlayListThumb(
//                            painterResource(id = R.drawable.toeic),
//                            title = "??????",
//                            author = "?????????"
//                        ),
//                    ),
//                    onPlayListClicked
//                )
            }
            item {
//                PLList(
//                    title = "?????? ?????? : ??????",
//                    listThumb = listOf(
//                        PlayListThumb(
//                            painterResource(id = R.drawable.toeic),
//                            title = "??????",
//                            author = "?????????"
//                        ),
//
//                    ),
//                    onPlayListClicked
//                )
            }
        }
    }
}

@Composable
fun SearchTopBar(
    placeHolderText: String,
    queryString: String,
    setQueryString: (String) -> Unit,
    onBackButtonClick: () -> Unit,
    onSearchKey: () -> Unit
) {
    TopAppBar(
        elevation = 0.dp,
        backgroundColor = Color.White,
        navigationIcon = {
            IconButton(onClick = onBackButtonClick) {
                Icon(
                    imageVector = Icons.Outlined.ArrowBack,
                    contentDescription = "navigate back"
                )
            }
        },
        title = {
            TextField(
                value = queryString,
                onValueChange = setQueryString,
                placeholder = {
                    Text(
                        text = placeHolderText,
                        style = MaterialTheme.typography.body1,
                        fontWeight = FontWeight.Bold,
                        color = Color.Gray
                    )
                },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent

                ),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Search
                ),
                keyboardActions = KeyboardActions(
                    onSearch = { onSearchKey() }
                ),
            )
        },
        actions = {
            if (queryString.isNotBlank()) {
                IconButton(onClick = { setQueryString("") }) {
                    Icon(imageVector = Icons.Default.Close, contentDescription = "delete")
                }
            }
        }
    )
}


@Composable
fun PLList(
    title: String,
    listThumb: List<PlayListThumb>,
    onItemClick: (PlayListThumb) -> Unit
) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(text = title, style = MaterialTheme.typography.h5)
        Spacer(modifier = Modifier.height(8.dp))
        LazyRow {
            for (item in listThumb) {
                item {
                    PLItem(playListThumb = item) { onItemClick(item) }
                }
            }
        }
    }
}

//@Preview
@Composable
fun PLItemPreview() {
    PLItem(
        playListThumb = PlayListThumb(
            painterResource(id = R.drawable.toeic),
            title = "?????? 900 ??????",
            author = "?????????"
        ),
        onItemClick = {}
    )
}

@Composable
fun PLItem(playListThumb: PlayListThumb, onItemClick: () -> Unit) {
    Column(
        Modifier
            .width(150.dp)
            .padding(bottom = 8.dp, end = 16.dp)
            .clip(RoundedCornerShape(4.dp))
            .clickable(onClick = onItemClick)
    ) {
        Image(
            painter = playListThumb.img ?: painterResource(id = R.drawable.sample),
            contentDescription = "playlist image",
            modifier = Modifier
                .width(150.dp)
                .height(150.dp)
                .clip(RoundedCornerShape(4.dp)),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = playListThumb.title, fontWeight = FontWeight.Bold)
        Text(text = playListThumb.author)
    }
}

data class PlayListThumb(
    val img: Painter?,
    val title: String,
    val author: String,
)
