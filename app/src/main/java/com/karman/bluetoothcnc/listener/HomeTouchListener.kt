package com.karman.bluetoothcnc.listener

interface HomeTouchListener {
    fun onUnitForwardTouchDown()
    fun onUnitForwardTouchUp()
    fun onUnitBackwardTouchDown()
    fun onUnitBackwardTouchUp()
    fun onToolForwardTouchDown()
    fun onToolForwardTouchUp()
    fun onToolBackwardTouchDown()
    fun onToolBackwardTouchUp()
}
