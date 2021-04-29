package com.karman.bluetoothcnc.model

import com.google.gson.annotations.SerializedName

// Manual: 1=> Yes, 0=> No
// Operation Name:
//     -1=> ALL OPERATION OFF (MANUAL)
//      1=> UNIT FORWARD (MANUAL)
//      2=> UNIT BACKWARD (MANUAL)
//      3=> TOOL FORWARD (MANUAL)
//      4=> TOOL BACKWARD (MANUAL)
//      5=> UNIT FORWARD (AUTO)
//      6=> UNIT BACKWARD (AUTO)
//      7=> TOOL FORWARD (AUTO)
//      8=> TOOL BACKWARD (AUTO)
//      9=> UNIT FORWARD SLOW (AUTO)
//      10=> UNIT BACKWARD SLOW (AUTO)
//      11=> TOOL FORWARD SLOW (AUTO)
//      12=> TOOL BACKWARD SLOW (AUTO)
// Operation Type: 1=> Unit, 2=> Tool
// Operation Start Delay: Start Delay in ms
// Operation Speed: Value=> 0-255
// Operation Direction: 0=> BACKWARD, 1=> FORWARD, -1=> RELEASE
// Operation Duration: Duration in ms
// Operation End Delay: End Delay in ms
class Operation(
        @field:SerializedName("m") var manual: Int,
        @field:SerializedName("sd") var startDelay: Int,
        @field:SerializedName("n") var name: Int,
        @field:SerializedName("sp") var speed: Int,
        @field:SerializedName("du") var duration: Int,
        @field:SerializedName("di") var direction: Int,
        @field:SerializedName("t") private var type: Int,
        @field:SerializedName("ed") var endDelay: Int) {
    enum class OperationType {
        ALL_OPERATION_OFF_MANUAL,
        UNIT_FORWARD_MANUAL,
        UNIT_BACKWARD_MANUAL,
        TOOL_FORWARD_MANUAL,
        TOOL_BACKWARD_MANUAL,
        UNIT_FORWARD_AUTO,
        UNIT_BACKWARD_AUTO,
        TOOL_FORWARD_AUTO,
        TOOL_BACKWARD_AUTO,
        UNIT_FORWARD_SLOW_AUTO,
        UNIT_BACKWARD_SLOW_AUTO,
        TOOL_FORWARD_SLOW_AUTO,
        TOOL_BACKWARD_SLOW_AUTO
    }
}