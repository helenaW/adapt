//Ourto Klasse
class OutroW{
  HealthBar healthy;
  PImage bg2;//Hintergrundbild
  TextOut txt;
  boolean readMore;

OutroW(HealthBar healt){
  bg2 = loadImage("clipgr.png");
  healthy = healt;
  txt = new TextOut();
  readMore = false;
}//end constr

void draw(){
  background(bg2);
  if(mousePressed){
          this.mousePressed();
  }
  
  if(healthy.getHealth()>= 7){//wenn Affe zu fett
          fill(0);
          textAlign(CENTER);
          text("Game Over", width/2, 233);
          text("Oh no, too much bad food.",width/2,273);
          text("Next time overthink your choices", width/2, 302);
          stroke(0);
          noFill();
          rect(587, 474, 121, 37);
          text("READ MORE", width/2, 500);
   }else if(healthy.getHealth() <= -7){//wennn Affe zu dünn
          fill(0);
          textAlign(CENTER);
          text("Game Over", width/2, 229);
          text("Oh no, that was not enough food.", width/2,259); 
          text("You made me starve", width/2, 281);
          stroke(0);
          noFill();
          rect(587, 474, 121, 37);
          text("READ MORE", width/2, 500);
   }else{//wenn Spiel erfolgreich durchgespielt
          fill(0);
          textAlign(CENTER);
          text("Game Over", width/2, 237);
          text("Good job, you found the food I needed.",width/2,271);
          text("Thank you!", width/2, 298);
          stroke(0);
          noFill();
          rect(587, 474, 121, 37);
          text("READ MORE", width/2, 500);
   }
  if(readMore){
          txt.draw();
  }
}//end draw
void mousePressed(){
  if((mouseX>587 && mouseX<708)&&(mouseY>474 && mouseY<511)){ 
    readMore = true;//wenn auf Read More gedrückt wird, setze Boolean true
  }
 }//end mousePressed
}//end class

/////////
////////
//Klasse Abschlusstext
////////

class TextOut{

TextOut(){

}
void draw(){
  
  background(0);
  fill(255);
  textAlign(CENTER);
  textSize(25);
  text("Due to the urbanization, deforestation is increasing and more animals are forced out of their natural habitat and into the cities.", width/2, 160);
  text("In Delhi alone over 30 000 primates are living among humans. Reason for that is food.", width/2, 210);
  text("The decreasing forest doesn’t allow every indiviual enough food.", width/2, 260);
  text(" In the city whereas, it’s like a free buffet with food in abundance.", width/2, 310);
  text("Who wouldn’t choose that over starving?", width/2, 360);
  text("But the consequences for humans and primates are severe.", width/2, 450);
  text("Serious injuries or substantial material damage is making humans choose more aggressive methods against the animals.", width/2, 510);
  text("For the monkeys on the other hand malnutrition, obesity and behavioural changes are the results of this lifestyle change.", width/2, 570);
  text("With disadvantages for both sides, shouldn’t we change something?", width/2, 620);

}//end draw

}//end class text
