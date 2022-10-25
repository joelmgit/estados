package com.estados.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.estados.model.Estado

@Dao
interface EstadosDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addState(state: Estado)
    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun updateState(state: Estado)
    @Delete
    suspend fun deleteState(state: Estado)
    @Query("SELECT * FROM ESTADO")
    fun getStates() : LiveData<List<Estado>>
}