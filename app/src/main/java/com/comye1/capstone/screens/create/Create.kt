package com.comye1.capstone.screens.create

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.Absolute.SpaceBetween
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Check
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.comye1.capstone.network.Resource

@ExperimentalMaterialApi
@Composable
fun CreateScreen(
    viewModel: CreateViewModel = hiltViewModel(),
    toBack: () -> Unit = {}
) {

    when (viewModel.playList) {
        is Resource.Success -> {
            (viewModel.playList as Resource.Success).data.let { playList ->

                when {
                    viewModel.showPlayItemEditor -> {

                        Dialog(onDismissRequest = { viewModel.closePlayItemEditor() }) {
                            Column(

                                Modifier
                                    .clip(RoundedCornerShape(size = 12.dp))
                                    .background(Color.White)
                                    .padding(16.dp)
                            ) {
                                if (viewModel.currentPlayItemPosition == -1)
                                    Text(text = "${playList.playItems.size + 1} ?????? ?????? ????????????")
                                else
                                    Text(text = "${viewModel.currentPlayItemPosition} ?????? ?????? ????????????")
                                Spacer(modifier = Modifier.height(16.dp))
                                TextField(
                                    value = viewModel.currentPlayItem,
                                    onValueChange = viewModel::setPlayItemContent,
                                    placeholder = { Text("?????? ??????") },
                                    modifier = Modifier.height(300.dp)
                                )
                                Spacer(modifier = Modifier.height(16.dp))
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Button(onClick = { viewModel.closePlayItemEditor() }) {
                                        Text("??????")
                                    }
                                    Button(onClick = { viewModel.addPlayItem() }) {
                                        Text("??????")
                                    }
                                }
                            }
                        }
                    }
                    viewModel.showTitleEditor -> {

                        Dialog(onDismissRequest = { viewModel.closeTitleEditor() }) {
                            var title by remember {
                                mutableStateOf(viewModel.currentTitle)
                            }
                            Column(

                                Modifier
                                    .clip(RoundedCornerShape(size = 12.dp))
                                    .background(Color.White)
                                    .padding(16.dp)
                            ) {
                                Text(text = "?????? ????????????")
                                Spacer(modifier = Modifier.height(16.dp))
                                TextField(
                                    value = title,
                                    onValueChange = { title = it },
                                    placeholder = { Text("?????? ??????") },
                                    modifier = Modifier.height(150.dp),
                                    trailingIcon = {
                                        Icon(
                                            imageVector = Icons.Default.Clear,
                                            contentDescription = "clear",
                                            modifier = Modifier.clickable {
                                                title = ""
                                            })
                                    }
                                )
                                Spacer(modifier = Modifier.height(16.dp))
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Button(onClick = { viewModel.closeTitleEditor() }) {
                                        Text("??????")
                                    }
                                    Button(onClick = { viewModel.saveTitle(title) }) {
                                        Text("??????")
                                    }
                                }
                            }
                        }
                    }

                }

                Scaffold(topBar = {
                    TopAppBar(
                        title = { },
                        navigationIcon = {
                            IconButton(onClick = toBack) {
                                Icon(
                                    imageVector = Icons.Outlined.ArrowBack,
                                    contentDescription = "back"
                                )
                            }
                        },
                        actions = {
                            IconButton(onClick = viewModel::savePlayList) {
                                Icon(
                                    imageVector = Icons.Outlined.Check,
                                    contentDescription = "save"
                                )
                            }
                        })
                }) {
                    Column(Modifier.padding(16.dp)) {
                        //TitleSection
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
//                                .border(
//                                    border = BorderStroke(1.dp, MaterialTheme.colors.primary),
//                                    RoundedCornerShape(5.dp)
//                                )
                                .clickable(onClick = viewModel::openTitleEditor)
                                .padding(8.dp),
                            verticalAlignment = CenterVertically
                        ) {
                            Text(
                                text = viewModel.currentTitle,
                                style = MaterialTheme.typography.h5,
                                fontWeight = FontWeight.ExtraBold,
                                modifier = Modifier.weight(1f)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Icon(imageVector = Icons.Default.Edit, contentDescription = "edit")
                            Spacer(modifier = Modifier.width(8.dp))
                        }
                        Divider()
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .clickable(onClick = viewModel::openDropdownMenu)
                                .padding(8.dp),
                            verticalAlignment = CenterVertically,
                            horizontalArrangement = SpaceBetween
                        ) {
                            Text(text = viewModel.selectedCategory)
                            DropdownMenu(
                                expanded = viewModel.showDropdownMenu,
                                onDismissRequest = viewModel::closeDropdownMenu
                            ) {
                                DropdownMenuItem(onClick = { viewModel.setCategory("??????") }) {
                                    Text(text = "??????")
                                }
                                DropdownMenuItem(onClick = { viewModel.setCategory("??????") }) {
                                    Text(text = "??????")
                                }
                                DropdownMenuItem(onClick = { viewModel.setCategory("??????") }) {
                                    Text(text = "??????")
                                }
                                DropdownMenuItem(onClick = { viewModel.setCategory("??????") }) {
                                    Text(text = "??????")
                                }
                                DropdownMenuItem(onClick = { viewModel.setCategory("IT") }) {
                                    Text(text = "IT")
                                }
                                DropdownMenuItem(onClick = { viewModel.setCategory("??????") }) {
                                    Text(text = "??????")
                                }
                            }
                            Icon(
                                imageVector = if (viewModel.showDropdownMenu) Icons.Default.ArrowDropUp else Icons.Default.ArrowDropDown,
                                contentDescription = "dropdown",
                                modifier = Modifier.padding(end = 8.dp)
                            )
                        }
                        Divider()
                        Text(
                            text = "??? ${playList.playItems.size}?????? ??????",
                            modifier = Modifier.padding(8.dp)
                        )
                        LazyColumn {
                            item {
                                Spacer(modifier = Modifier.height(8.dp))
                            }
                            itemsIndexed(playList.playItems) { index, item ->
                                ListEditCard(index = index + 1, title = item.title) {
                                    viewModel.openPlayItemEditor(index)
                                }
                                Spacer(modifier = Modifier.height(8.dp))
                            }
                            item {
                                CreateListCard(
                                    index = playList.playItems.size,
                                    onClick = { viewModel.openPlayItemEditor(-1) })
                            }
                            item {
                                Spacer(modifier = Modifier.height(56.dp))
                            }
                        }
                    }
                }

            }
        }
        is Resource.Loading -> {

        }
        is Resource.Failure -> {

        }
    }

}

@Composable
fun CreateListCard(index: Int, onClick: () -> Unit) {
    Card(
        elevation = 5.dp,
        shape = RoundedCornerShape(5.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        backgroundColor = MaterialTheme.colors.primary
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "${index + 1} ??????",
                fontWeight = FontWeight.Bold,
            )
            Spacer(modifier = Modifier.width(8.dp))
            Divider(
                modifier = Modifier
                    .height(50.dp)
                    .width(1.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(text = "????????????", style = MaterialTheme.typography.h6)
            }
        }
    }
}

@Composable
fun ListEditCard(index: Int, title: String, onClick: () -> Unit) {
    Card(
        elevation = 5.dp,
        shape = RoundedCornerShape(5.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = onClick)
                .padding(vertical = 8.dp)
        ) {
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "$index ??????",
                fontWeight = FontWeight.Bold,
            )
            Spacer(modifier = Modifier.width(8.dp))
            Divider(
                modifier = Modifier
                    .height(50.dp)
                    .width(1.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = title, modifier = Modifier.weight(1f))
            Spacer(modifier = Modifier.width(8.dp))
            Icon(imageVector = Icons.Default.Edit, contentDescription = "edit")
            Spacer(modifier = Modifier.width(16.dp))
        }
    }
}