/////Adapt
/////by Helena Winkler
/////17.03.2018

///Start Klasse

IntroW wimmelIntr;//Intro Klasse
boolean playWimmelIntr;
PFont font;


public void setup(){
  
  size(1300, 900);
  frameRate(60);
  smooth();
  wimmelIntr = new IntroW();
  playWimmelIntr = false;
  font = createFont("RobotoCondensed-Regular.ttf", 59);
  
}//end const

void draw(){
 background(0);
 //Startbildschirm mit Text
 textFont(font, 59);
 text("EXPLORE THE EXTINCT", 367, 316);
 textSize(24);
 text("discover", 385, 374);
 text("protect", 607, 374);
 stroke(255);
 noFill();
 rect(824, 347, 69, 37);
 text("adapt", 831, 374);
 
 if(mousePressed){
   this.mouseClicked();
 }
 
 if(playWimmelIntr){//wenn boolean true, Intro wird gestartet
   wimmelIntr.draw();
 }
 
}//end draw

void mouseClicked(){
  
 if((mouseX>824 && mouseX<893)&&(mouseY>347 && mouseY<384)){
    playWimmelIntr = true;//wenn auf Wort adapt geklickt wurde, setze boolean true
  }
  
}
