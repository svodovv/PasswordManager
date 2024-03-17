package com.example.passwordmanager.ui.screens.IconList

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.passwordmanager.data.repositories.repository.IconListRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class IconListViewModel @Inject constructor(
    private val repo: IconListRepositoryImpl
) : ViewModel() {

    private val _urlList = mutableStateOf(UrlListState())
    val urlList: State<UrlListState> = _urlList

    init {
        initState()
    }

    private fun initState() {
        repo.getIconUrls().observeForever {
            _urlList.value = _urlList.value.copy(urlList = it)
        }
    }


}

