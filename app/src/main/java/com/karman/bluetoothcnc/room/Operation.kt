package com.karman.bluetoothcnc.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "operation")
data class Operation(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "type") val type: Int,
    @ColumnInfo(name = "speed") val speed: Int,
    @ColumnInfo(name = "duration") val duration: Int,
    @ColumnInfo(name = "startDelay") val startDelay: Int,
    @ColumnInfo(name = "endDelay") val endDelay: Int
) {
    fun updateOperation(type: Int, speed: Int, duration: Int, startDelay: Int, endDelay: Int) =
        Operation(id, type, speed, duration, startDelay, endDelay)
}