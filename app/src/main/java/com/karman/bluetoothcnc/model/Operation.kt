package com.karman.bluetoothcnc.model

import com.google.gson.annotations.SerializedName

// Operation Type:
//     -1=> ALL OPERATION OFF (MANUAL)
//      1=> UNIT FORWARD
//      2=> UNIT BACKWARD
//      3=> TOOL FORWARD
//      4=> TOOL BACKWARD
// Operation Speed: Value=> 0-255
// Operation Duration: Duration in ms, -1 if continuous manual
// Operation Start Delay: Start Delay in ms
// Operation End Delay: End Delay in ms
data class Operation(
        @field:SerializedName("t") var operationType: Int,
        @field:SerializedName("s") var operationSpeed: Int,
        @field:SerializedName("d") var operationDuration: Int,
        @field:SerializedName("sd") var operationStartDelay: Int,
        @field:SerializedName("ed") var operationEndDelay: Int) {
    constructor(operationType: Int, operationSpeed: Int) :
            this(operationType, operationSpeed, -1, 0, 0)

    constructor(operationType: Int, operationSpeed: Int, operationDuration: Int) :
            this(operationType, operationSpeed, operationDuration, 0, 0)

    companion object {
        const val ALL_OPERATION_OFF = -1
        const val UNIT_FORWARD = 1
        const val UNIT_BACKWARD = 2
        const val TOOL_FORWARD = 3
        const val TOOL_BACKWARD = 4
    }
}