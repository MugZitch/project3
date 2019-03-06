void setup() {
  Serial.begin(9600); //opens serial port, sets data rate to 9600 bps
}

void loop() {
  Serial.print("Hello From Arduino!"); //send some string to our Java application
  delay(1000); //wait 1000 ms
}
