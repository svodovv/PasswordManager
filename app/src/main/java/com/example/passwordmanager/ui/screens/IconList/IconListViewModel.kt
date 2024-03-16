package com.example.passwordmanager.ui.screens.IconList

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.passwordmanager.data.repositories.repository.IconListRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
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
        repo.getIconUrls().observeForever{
            _urlList.value = _urlList.value.copy(urlList = it)
        }
    }


    fun deleteIcon(name:String){
        val urlList = _urlList.value.urlList
        _urlList.value = _urlList.value.copy(urlList = urlList.filter { it.url == name })
        viewModelScope.launch {
            repo.deleteIconByName(name)
        }

    }
}

