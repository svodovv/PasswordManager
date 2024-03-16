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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SwipeToDeleteContainer(
    item: String,
    onDelete: () -> Unit,
    animationDuration: Int = 500,
    content: @Composable RowScope.() -> Unit
) {
    var isRemoved by remember {
        mutableStateOf(false)
    }
    val state = rememberDismissState(confirmValueChange = { value ->
        if (value == DismissValue.DismissedToStart) {
            isRemoved = true
            true
        } else {
            false
        }
    })

    AnimatedVisibility(
        visible = !isRemoved,
        exit = shrinkVertically(
            animationSpec = tween(durationMillis = animationDuration),
            shrinkTowards = Alignment.Top
        ) + fadeOut()
    ) {

        if (isRemoved) {
            onDelete()
        }


        SwipeToDismiss(state = state, background = {
            DeleteBackground(swipeDismissState = state)
        }, dismissContent = { content() },
            directions = setOf(DismissDirection.EndToStart)
        )
    }
}