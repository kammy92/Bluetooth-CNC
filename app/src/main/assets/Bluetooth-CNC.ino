#include<AFMotor.h>
#include <ArduinoJson.h>

AF_DCMotor mUnit(1, MOTOR12_64KHZ);
AF_DCMotor mTool(2, MOTOR12_64KHZ);

String msg;

void setup() {
  Serial.begin(9600);
  Serial.println("MACHINE READY");
  mUnit.setSpeed(255);
  mTool.setSpeed(255);
}

void loop() {
  while (Serial.available() > 0){
    char mByte = Serial.read();
    msg += mByte;
    if (mByte == ';') {
      msg = msg.substring(0, msg.length()-1);
      Serial.println("Message: " + msg);  

      DynamicJsonDocument doc(1024);
      deserializeJson(doc, msg);
      
      JsonArray ops = doc["op"];
      for (JsonObject op: ops) {
        runMotor(op["m"],op["n"],op["t"],op["sd"],
        op["sp"],op["di"],op["du"],op["ed"]);
      }
      msg = "";
    }
  }
}

void runMotor(int manual, int opName, int opType, int opStartDelay, 
int opSpeed, int opDirection, int opDuration, int opEndDelay){
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

  switch(manual){
    case 0:
      // Automatic Operation
      switch(opName) {
        case 5:
          Serial.println("UNIT FORWARD");
          break;
        case 6:
          Serial.println("UNIT BACKWARD");
          break;
        case 7:
          Serial.println("TOOL FORWARD");
          break;
        case 8:
          Serial.println("TOOL BACKWARD");
          break;
        case 9:
          Serial.println("UNIT FORWARD SLOW");
          break;
        case 10:
          Serial.println("UNIT BACKWARD SLOW");
          break;
        case 11:
          Serial.println("TOOL FORWARD SLOW");
          break;
        case 12:
          Serial.println("TOOL BACKWARD SLOW");
          break;
        default:
          Serial.println("ALL OFF");
          mUnit.run(RELEASE);
          mTool.run(RELEASE);
          break;
      }
      delay(opStartDelay);
      switch(opType){
        case 1:
          mUnit.setSpeed(opSpeed);
          switch(opDirection){
            case 0:
              mUnit.run(BACKWARD);      
              break;
            case 1:
              mUnit.run(FORWARD);
              break;
            default:
              mUnit.run(RELEASE);
              break;
          }
          delay(opDuration);
          mUnit.run(RELEASE);
          break;
        case 2:
          mTool.setSpeed(opSpeed);
          switch(opDirection){
            case 0:
              mTool.run(BACKWARD);      
              break;
            case 1:
              mTool.run(FORWARD);
              break;
            default:
              mTool.run(RELEASE);
              break;
          }
          delay(opDuration);
          mTool.run(RELEASE);
        break;
      }
      delay(opEndDelay);
      delay(100);
      break;
    case 1:
      // Manual Operation
      switch(opName) {
        case 1:
          Serial.println("UNIT FORWARD");
          break;
        case 2:
          Serial.println("UNIT BACKWARD");
          break;
        case 3:
          Serial.println("TOOL FORWARD");
          break;
        case 4:
          Serial.println("TOOL BACKWARD");
          break;
        default:
          Serial.println("ALL OFF");
          mUnit.run(RELEASE);
          mTool.run(RELEASE);
          break;
      }

      switch(opType){
        case 1:
          mUnit.setSpeed(opSpeed);
          switch(opDirection){
            case 0:
              mUnit.run(BACKWARD);      
              break;
            case 1:
              mUnit.run(FORWARD);
              break;
            default:
              mUnit.run(RELEASE);
              break;
          }
          break;
        case 2:
          mTool.setSpeed(opSpeed);
          switch(opDirection) {
            case 0:
              mTool.run(BACKWARD);      
              break;
            case 1:
              mTool.run(FORWARD);
              break;
            default:
              mTool.run(RELEASE);
              break;
          }
          break;
      }
      break;
  }
}
