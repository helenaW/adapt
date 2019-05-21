//Intro Klasse

Wimmel wimmel;//Spielklasse
boolean playWimmel;
PImage bg1;//Hintergrundbild


class IntroW{

IntroW(){
    bg1 = loadImage("clipgr.png");
    frameRate(60);
    smooth();
    wimmel = new Wimmel();
    playWimmel = false;
}//end const


public void draw(){
  //Setup Hintergrund mit Einleitungstext
     background(bg1);
     cursor(CROSS);// Cursor zu einem Kreuz
     textSize(21);
     fill(0); 
     textAlign(CENTER);
     text("I am so hungry but I can't find food in the forest.", width/2, 292);
     text("It always smells so good in the city.", width/2, 341);
     text("Help me find the food I'm looking for.", width/2, 395);
     textSize(30);
     stroke(0);
     noFill();
     rect(592, 576, 118, 37);
     text("START", width/2, 607);
     
     
     if(mousePressed){
       this.mousePressed();
     }
  
    if(playWimmel){
       wimmel.draw();//wenn Boolean true, starte Spiel
       
     }
  }//end draw

void mousePressed(){
  if((mouseX>592 && mouseX<710)&&(mouseY>576 && mouseY<613)){ 
    playWimmel = true;//wenn auf Start gedrückt wird, setze Boolean true
  }
 }//end mousePressed

}//end class
