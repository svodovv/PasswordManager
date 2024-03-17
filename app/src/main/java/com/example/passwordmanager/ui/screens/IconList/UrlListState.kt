package com.example.passwordmanager.ui.screens.IconList

import com.example.passwordmanager.data.repositories.model.SiteInfoModel

data class UrlListState(
    val urlList: List<SiteInfoModel> = emptyList()
)
