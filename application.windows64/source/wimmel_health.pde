//Klasse der Gesundheitsanzeige

class HealthBar {
  float health;
  float rectWidth = 200;
  PImage fatM;
  PImage thinM;
  
HealthBar(float healt){
    health = healt;
    fatM= loadImage("fat.png");
    thinM = loadImage("thin.png");
}

void draw(){
// Farbe ändern der Anzeige
  if (health < 4 && health > -4)
  {
    fill(0,255, 0);//grün
  }  
  else if (health >= 6 || health <= -6)
  {
    fill(255, 0, 0);//orange
  }
  else 
  {
    fill(255, 200, 0);//rot
  }
  
// Kasten anzeigen
  noStroke();
  float drawWidth = (rectWidth/7)*health;
  rect(width/2, 8, drawWidth, 20);
  stroke(0);
  noFill();
  rect(width/2, 8, rectWidth, 20);
  stroke(0);
  noFill();
  rect(width/2-rectWidth, 8, rectWidth, 20);
  image(thinM, width/2-rectWidth-30, 6);
  image(fatM, width/2+rectWidth+5,6);
}//end draw

void setHealth(float setter){
   health = setter;
 }
 
float getHealth(){
   return health;
 }
}
