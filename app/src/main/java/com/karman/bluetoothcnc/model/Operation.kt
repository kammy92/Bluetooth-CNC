package com.karman.bluetoothcnc.model

import com.google.gson.annotations.SerializedName

class Operation(
        @field:SerializedName("sd") var startDelay: Int,
        @field:SerializedName("n") var name: Int,
        @field:SerializedName("sp") var speed: Int,
        @field:SerializedName("du") var duration: Int,
        @field:SerializedName("di") var direction: Int,
        @field:SerializedName("t") private var type: Int,
        @field:SerializedName("ed") var endDelay: Int)