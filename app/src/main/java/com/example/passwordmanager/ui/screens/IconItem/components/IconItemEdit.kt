package com.example.passwordmanager.ui.screens.IconItem.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign

@Composable
fun IconItemEdit(name: String) {
    Column(modifier = Modifier.fillMaxSize()
        ) {


        Text(
            text = name, textAlign = TextAlign.Center, modifier = Modifier
                .fillMaxSize()
                .align(Alignment.CenterHorizontally)
        )
    }
}