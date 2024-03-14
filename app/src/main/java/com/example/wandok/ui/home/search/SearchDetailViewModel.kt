package com.example.wandok.ui.home.search

import androidx.lifecycle.ViewModel
import com.example.wandok.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchDetailViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

}