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
class Operation(
        @field:SerializedName("t") var operationType: Int,
        @field:SerializedName("s") var operationSpeed: Int,
        @field:SerializedName("d") var operationDuration: Int,
        @field:SerializedName("sd") var operationStartDelay: Int,
        @field:SerializedName("ed") var operationEndDelay: Int)