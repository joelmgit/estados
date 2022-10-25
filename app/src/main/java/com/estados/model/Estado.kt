package com.estados.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName="estado")
data class Estado(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name="name")
    val name: String,
    @ColumnInfo(name="capital")
    val capital: String?,
    @ColumnInfo(name="population")
    val population: Int?,
    @ColumnInfo(name="hasCoast")
    val hasCoast: Int?,
) : Parcelable