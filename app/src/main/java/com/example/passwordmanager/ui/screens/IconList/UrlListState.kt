package com.example.passwordmanager.ui.screens.IconList

import com.example.passwordmanager.data.repositories.model.IconUrlToNameModel

data class UrlListState(
    val urlList: List<IconUrlToNameModel> = emptyList()
)
