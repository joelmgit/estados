package com.estados.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.estados.data.EstadosDatabase
import com.estados.model.Estado
import com.estados.repository.EstadosRepository
import kotlinx.coroutines.launch

class EstadoViewModel(application: Application) : AndroidViewModel(application) {
    val getStates : LiveData<List<Estado>>
    private val repository: EstadosRepository
    init {
        val stateDAO = EstadosDatabase.getDatabase(application).stateDAO()
        repository = EstadosRepository(stateDAO)
        getStates = repository.getStates
    }
    fun saveState(state: Estado) {
        viewModelScope.launch { repository.saveState(state) }
    }
    fun deleteState(state: Estado) {
        viewModelScope.launch { repository.deleteState(state)}
    }
}