#include <IRremote.h>
IRsend irsend;

void setup(){
   Serial.begin(9600); 
}

void loop(){
   while(Serial.available()>0) {
     char bluetoothChar = Serial.read();
     Serial.print(bluetoothChar);
     
     switch(bluetoothChar){
       case 'p':
         sendToSony(0xA90, 0xA90, 0xA90, 12);  // Power 
       break;
       
        case 'i':
         sendToSony(0xA50, 0xA50, 0xA50, 12);  // Input            
       break;
       
       case 'U':
         sendToSony(0x490, 0x490, 0x490, 12);  // Volume Up       
       break;
   
       case 'D':
         sendToSony(0xC90, 0xC90, 0xC90, 12);  // Volume Down
        break;
        
        case 'M':
          sendToSony(0x290, 0x290, 0x290, 12);  // Mute
        break;
        
        case 'u':
         sendToSony(0x2F0, 0x2F0, 0x2F0, 12);  // Up
        break;
        
        case 'l':
         sendToSony(0x2D0, 0x2D0, 0x2D0, 12);  // Left
        break;
        
        case 'd':
         sendToSony(0xAF0 , 0xAF0, 0xAF0, 12);  // Down
        break;
        
        case 'r':
         sendToSony(0xCD0 , 0xCD0, 0xCD0, 12);  // Right
        break;
        
        case 'o':
         sendToSony(0xA70 , 0xA70, 0xA70, 12);  // Ok
        break;
        
        case 'm':
         sendToSony(0x70 , 0x70, 0x70, 12);  // Menu
        break;
        
        case 'R':
         sendToSony(0x62E9 , 0x62E9, 0x62E9, 15);  // Return
        break;
     }
     
     delay(40);
   }
   
   
}


void sendToSony(int signal1, int signal2, int signal3, int bits){
  irsend.sendSony(signal1, bits);
  delay(40);
  irsend.sendSony(signal2, bits);
  delay(40);
  irsend.sendSony(signal3, bits);
  delay(40);
  
}
