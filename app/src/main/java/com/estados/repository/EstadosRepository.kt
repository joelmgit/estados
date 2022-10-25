package com.estados.repository

import androidx.lifecycle.LiveData
import com.estados.data.EstadosDAO
import com.estados.model.Estado

class EstadosRepository(private val stateDAO: EstadosDAO) {
    suspend fun saveState(state: Estado) {
        if (state.id==0) {
            stateDAO.addState(state)
        } else {
            stateDAO.updateState(state)
        }
    }
    suspend fun deleteState(state: Estado) {
        stateDAO.deleteState(state)
    }
    val getStates : LiveData<List<Estado>> = stateDAO.getStates()
}