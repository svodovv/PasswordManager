package com.example.passwordmanager.ui.screens.IconList.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.DismissValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SwipeToDeleteContainer(
    item: String,
    onDelete: () -> Unit,
    animationDuration: Int = 1500,
    content: @Composable RowScope.(String) -> Unit
) {
    val isRemoved = remember { mutableStateOf(false) }

    val state = rememberDismissState(confirmValueChange = { value ->
        if (value == DismissValue.DismissedToStart) {
            isRemoved.value = true
            true
        } else {
            false
        }
    })

    val isVisible = !isRemoved.value

    LaunchedEffect(isRemoved.value) {
        if (isRemoved.value) {
            delay(animationDuration.toLong())
            onDelete()
        }
    }
    AnimatedVisibility(
        visible = isVisible,
        exit = shrinkVertically(
            animationSpec = tween(durationMillis = animationDuration-500),
            shrinkTowards = Alignment.Top
        ) + fadeOut()
    ) {


        SwipeToDismiss(
            state = state,
            background = { DeleteBackground(swipeDismissState = state) },
            dismissContent = { content(item) },
            directions = setOf(DismissDirection.EndToStart)
        )
    }
}