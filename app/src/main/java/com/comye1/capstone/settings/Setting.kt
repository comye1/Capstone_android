package com.comye1.capstone.settings

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Bottom
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.comye1.capstone.R

@Composable
fun SettingsScreen(viewModel: SettingViewModel = hiltViewModel()) {

    var openCategoryDialog by remember {
        mutableStateOf(false)
    }

    Scaffold(topBar = {
        TopAppBar(
            title = { Text("Settings") },
        )
    }) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            ProfileSection(
                userName = viewModel.user.nickname,
                logInStatus = true,
                logIn = { /*TODO*/ },
                logOut = {})
            Spacer(modifier = Modifier.height(32.dp))
            PreferenceSection(openCategoryDialog = { openCategoryDialog = true })
        }
    }

    if (openCategoryDialog) {
        Dialog(onDismissRequest = { openCategoryDialog = false }) {
            var category by remember {
                mutableStateOf(viewModel.selectedCategory)
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxSize(.8f)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.White)
            ) {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text("??????????????? ???????????????")
                }
                Divider()
                Column {
                    CategoryRadioRow(
                        categoryName = "???????????? ??????",
                        selected = category == ""
                    ) {
                        category = it
                    }
                    CategoryRadioRow(
                        categoryName = "??????",
                        selected = category == "??????"
                    ) {
                        category = it
                    }
                    CategoryRadioRow(
                        categoryName = "??????",
                        selected = category == "??????"
                    ) {
                        category = it
                    }
                    CategoryRadioRow(
                        categoryName = "??????",
                        selected = category == "??????"
                    ) {
                        category = it
                    }
                    CategoryRadioRow(
                        categoryName = "??????",
                        selected = category == "??????"
                    ) {
                        category = it
                    }
                    CategoryRadioRow(
                        categoryName = "IT",
                        selected = category == "IT"
                    ) {
                        category = it
                    }
                    CategoryRadioRow(
                        categoryName = "??????",
                        selected = category == "??????"
                    ) {
                        category = it
                    }
                }
                Divider()
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.End
                ) {
                    Text(
                        text = "??????",
                        modifier = Modifier.clickable {
                            openCategoryDialog = false
                        },
                        color = Color.Red
                    )
                    Spacer(modifier = Modifier.width(32.dp))
                    Text(
                        text = "??????",
                        modifier = Modifier.clickable {
                            openCategoryDialog = false
                            viewModel.saveCategory(category)
                        },
                        color = Color.Red
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                }
            }
        }
    }
}


@Composable
fun CategoryRadioRow(
    categoryName: String,
    selected: Boolean,
    onClick: (String) -> Unit
) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .clickable { onClick(categoryName) }
        .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = selected,
            enabled = false,
            onClick = {},
            colors = RadioButtonDefaults.colors(
                disabledColor = Color.Black
            )
        )
        Spacer(modifier = Modifier.width(24.dp))
        Text(text = categoryName)
    }
}


@Composable
fun ProfileSection(
    profileImage: Painter = painterResource(id = R.drawable.sample),
    userName: String,
    logInStatus: Boolean,
    logIn: () -> Unit = {},
    logOut: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(align = Bottom)
    ) {
        Column(
            Modifier
                .size(128.dp)
        ) {
            Image(
                painter = profileImage,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(CircleShape),
                contentDescription = "profile image"
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.height(128.dp)
        ) {
            Text(text = userName, style = MaterialTheme.typography.h5)
            Spacer(modifier = Modifier.height(16.dp))
            Row {
                TextButton(
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.textButtonColors(
                        backgroundColor = MaterialTheme.colors.primary,
                        contentColor = Color.White
                    )
                ) {
                    Text(text = "?????? ??????")
                }
                Spacer(modifier = Modifier.width(16.dp))
                if (logInStatus) {
                    TextButton(
                        onClick = logOut,
                        colors = ButtonDefaults.textButtonColors(
                            backgroundColor = MaterialTheme.colors.primary,
                            contentColor = Color.White
                        )
                    ) {
                        Text(text = "????????????")
                    }
                } else {
                    TextButton(
                        onClick = logIn,
                        colors = ButtonDefaults.textButtonColors(
                            backgroundColor = MaterialTheme.colors.primary,
                            contentColor = Color.White
                        )
                    ) {
                        Text(text = "?????????")
                    }
                }
            }
        }
    }
}

@Composable
fun PreferenceSection(
    openCategoryDialog: () -> Unit
) {
    Column(
        Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .border(width = 1.dp, color = Color.LightGray, shape = RoundedCornerShape(8.dp))
    ) {
        PreferenceItem(text = "?????? ??????", onClick = {})
        Divider()
        PreferenceItem(text = "?????? ?????? ??????", onClick = openCategoryDialog)
        Divider()
        PreferenceItem(text = "?????? ??????", onClick = {})
    }
}

@Composable
fun PreferenceItem(text: String, onClick: () -> Unit) {
    Text(
        text = text, modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(16.dp)
    )
}