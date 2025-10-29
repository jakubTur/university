#define RED_BUTTON 2
#define GREEN_BUTTON 4
//Lib for LCD
#include <LiquidCrystal.h>   
LiquidCrystal lcd = LiquidCrystal(7, 8, 9, 10, 11, 12);  // LCD address LCD 0x27.
bool lastRed;
bool lastGreen;
unsigned long pressTime;
unsigned long stoppedTime;
bool running;
void setup() {
  pinMode(RED_BUTTON, INPUT_PULLUP);
  pinMode(GREEN_BUTTON, INPUT_PULLUP);
  lastGreen = digitalRead(GREEN_BUTTON);
  lastRed = digitalRead(RED_BUTTON);
  running = false;
  pressTime = 0;
  stoppedTime = 0;
  //position the cursor
  lcd.setCursor(0,1);
  lcd.print("TIMER");
}
 
void loop() {
  bool currentRed = digitalRead(RED_BUTTON);
  bool currentGreen = digitalRead(GREEN_BUTTON);
  bool redPressed = currentRed == LOW
    && lastRed == HIGH;
  bool greenPressed = currentGreen == LOW
    && lastGreen == HIGH;
  if(greenPressed && !running)
  {
    lcd.clear();
    running = true;
  }
  else if(running)
  {
    if(redPressed)
    {
      running = false;
      lcd.clear();
      pressTime = 0;
      lcd.print("00min00sec000mil");
    }
    else if(greenPressed)
    {
      running = false;
    }
    else
    {
  	  pressTime = millis() - stoppedTime;
      unsigned long milis = pressTime % 1000;
      unsigned long inSeconds = (unsigned long) pressTime / 1000;
      unsigned long minutes = (inSeconds / 60) % 60;
      unsigned long seconds = (unsigned long) inSeconds % 60;
      lcd.clear();
      lcd.print(minutes);
      lcd.print("min");
      lcd.print(seconds);
      lcd.print("sec");
      lcd.print(milis);
      lcd.print("ms");
    }

  }
  else
  {
    stoppedTime = millis() - pressTime;
  }
  lastRed = currentRed;
  lastGreen = currentGreen;
  delay(50);
}