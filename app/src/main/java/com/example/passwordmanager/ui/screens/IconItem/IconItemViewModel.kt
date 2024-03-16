package com.example.passwordmanager.ui.screens.IconItem

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.passwordmanager.data.repositories.repository.IconItemRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class IconItemViewModel @Inject constructor(
    private val repo: IconItemRepositoryImpl
) : ViewModel() {

    private val _siteInfo = mutableStateOf(SiteInfoState())
    val siteInfo: State<SiteInfoState> = _siteInfo

    fun getSiteInfo(name: String){
       repo.getSiteInfo(name).observeForever{
           _siteInfo.value = _siteInfo.value.copy(
               url = it.url,
               name = it.name,
               password = it.password
           )
       }
    }
}