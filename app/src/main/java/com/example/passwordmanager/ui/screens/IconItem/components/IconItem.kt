package com.example.passwordmanager.ui.screens.IconItem.components

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.passwordmanager.ui.screens.IconItem.IconItemViewModel
import dagger.hilt.android.qualifiers.ApplicationContext

@Composable
fun IconItem(
    iconName: String, viewModel: IconItemViewModel = hiltViewModel(),
) {
    viewModel.getSiteInfo(iconName)

    val clipboardManager: ClipboardManager = LocalClipboardManager.current
    val siteInfoState = viewModel.siteInfo.value

    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.size(150.dp))
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            AsyncImage(
                model = siteInfoState.url,
                contentDescription = iconName,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(8.dp),
            )



            Text(
                text = siteInfoState.name, style = MaterialTheme.typography.displayMedium,
                modifier = Modifier.align(Alignment.CenterHorizontally),
            )


            Spacer(modifier = Modifier.size(150.dp))

            Text(
                text = "Пароль: ${siteInfoState.password}",
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .clickable {
                        clipboardManager.setText(AnnotatedString(siteInfoState.password))
                    },
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}