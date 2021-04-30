#include<AFMotor.h>
#include <ArduinoJson.h>
AF_DCMotor mUnit(1, MOTOR12_64KHZ);
AF_DCMotor mTool(2, MOTOR12_64KHZ);
String msg;
void setup() {
  Serial.begin(9600);
  Serial.println("MACHINE READY");
}
void loop() {
  while (Serial.available() > 0) {
    char mByte = Serial.read();
    msg += mByte;
    if (mByte == ';') {
      msg = msg.substring(0, msg.length()-1);
//      Serial.println("Message: " + msg);  
      DynamicJsonDocument doc(1024);
      deserializeJson(doc, msg);
      JsonArray ops = doc["op"];
      for (JsonObject op: ops) {
        runMotor(op["t"],op["s"],op["d"],op["sd"],op["ed"]);
      }
      msg = "";
    }
  }
}

void runMotor(int opType, int opSpeed, int opDuration, int opStartDelay, int opEndDelay) {
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

  if (opDuration >= 0) {
    delay(opStartDelay);
  }
  switch (opType) {
    case 1:
      Serial.println("UNIT FORWARD");
      mUnit.setSpeed(opSpeed);
      mUnit.run(FORWARD);
      break;
    case 2:
      Serial.println("UNIT BACKWARD");
      mUnit.setSpeed(opSpeed);
      mUnit.run(BACKWARD);
      break;
    case 3:
      Serial.println("TOOL FORWARD");
      mTool.setSpeed(opSpeed);
      mTool.run(FORWARD);
    break;
    case 4:
      Serial.println("TOOL BACKWARD");
      mTool.setSpeed(opSpeed);
      mTool.run(BACKWARD);
    break;
    default:
      Serial.println("ALL OFF");
      mUnit.run(RELEASE);
      mTool.run(RELEASE);
      break;
  }
  if (opDuration >= 0) {
    delay(opDuration);
    mUnit.run(RELEASE);
    mTool.run(RELEASE);
    delay(opEndDelay);
  }
}