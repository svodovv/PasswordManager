package com.example.passwordmanager.ui.screens.IconList.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.passwordmanager.ui.Screen
import com.example.passwordmanager.ui.screens.IconList.IconListViewModel

@Composable
fun IconList(
    iconListViewModel: IconListViewModel = hiltViewModel(), navController: NavController
) {
    val stateUrlList = iconListViewModel.urlList.value
    LazyColumn(modifier = Modifier.fillMaxSize()) { // Устанавливаем количество колонок равным 2
        items(stateUrlList.urlList) { siteInfo ->
            SwipeToDeleteContainer(item = siteInfo.name, onDelete = {
                iconListViewModel.deleteIcon(siteInfo.name)
            }) {
                Card(modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp, top = 8.dp, start = 16.dp, end = 16.dp)
                    .clickable {
                        navController.navigate(
                            Screen.IconItemScreen.route + "/{siteName}".replace(
                                oldValue = "{siteName}", newValue = siteInfo.name
                            )
                        )
                    }) {
                    Row(
                        modifier = Modifier.padding(bottom = 8.dp, top = 8.dp),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Box(
                            modifier = Modifier
                                .padding(8.dp)
                                .weight(1f)
                        ) {
                            AsyncImage(
                                model = siteInfo.url,
                                contentDescription = null,
                                modifier = Modifier.clip(CircleShape),
                            )
                        }

                        Text(
                            text = siteInfo.name,
                            style = MaterialTheme.typography.headlineSmall,
                            modifier = Modifier
                                .padding(8.dp)
                                .align(Alignment.CenterVertically)
                                .weight(5f),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}

