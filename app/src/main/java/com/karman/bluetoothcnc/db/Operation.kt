package com.karman.bluetoothcnc.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Operation(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "type") val type: Int?,
    @ColumnInfo(name = "speed") val speed: Int?,
    @ColumnInfo(name = "duration") val duration: Int?,
    @ColumnInfo(name = "startDelay") val startDelay: Int?,
    @ColumnInfo(name = "endDelay") val endDelay: Int?
)