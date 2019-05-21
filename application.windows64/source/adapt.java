import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class adapt extends PApplet {

/////Adapt
/////by Helena Winkler
/////17.03.2018

///Start Klasse

IntroW wimmelIntr;//Intro Klasse
boolean playWimmelIntr;
PFont font;


public void setup(){
  
  
  frameRate(60);
  
  wimmelIntr = new IntroW();
  playWimmelIntr = false;
  font = createFont("RobotoCondensed-Regular.ttf", 59);
  
}//end const

public void draw(){
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

public void mouseClicked(){
  
 if((mouseX>824 && mouseX<893)&&(mouseY>347 && mouseY<384)){
    playWimmelIntr = true;//wenn auf Wort adapt geklickt wurde, setze boolean true
  }
  
}
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

public void mousePressed(){
  if((mouseX>592 && mouseX<710)&&(mouseY>576 && mouseY<613)){ 
    playWimmel = true;//wenn auf Start gedrückt wird, setze Boolean true
  }
 }//end mousePressed

}//end class
//Spielklasse


int timeBeforeNextSpawn = 0;
int MAX_TIME_DELAY = 60*16;
int place;// Aktueller Auslesepunkt der ArrayList
 
 HealthBar healthy;//Neue Gesundheitsanzeige
 UserInterface userInterface;//Neue HUD
 ArrayList<Level> levelList;//Liste der Level
 OutroW out;//neues Outro
 
 boolean started;//Ist das Spiel schon gestartet
 boolean gameOv;
 
 
 //Bilder für das Spiel
 PImage bg;
 PImage banana;
 PImage pepper;
 PImage water;
 PImage chips;
 PImage peanut;
 PImage apple;
 PImage beetroot;
 PImage cucumber;
 PImage bonbon;
 PImage cupcake;
 PImage celery;
 PImage coconut;
 PImage cola;
 PImage pizza;
 PImage hotdog;
 PImage monkey;

 
 
class Wimmel{

Wimmel()
{
    // Set up graphics.
    //
    
    bg = loadImage("backgr.png");
    frameRate(60);
    smooth();
    
     banana = loadImage("banana.png");
     pepper = loadImage("pepper.png");
     water = loadImage("Bottle.png");
     chips = loadImage("chips.png");
     peanut = loadImage("erdnuss.png");
     apple = loadImage("apple.png");
     beetroot = loadImage("beetr.png");
     cucumber = loadImage("cucumber.png");
     bonbon = loadImage("Bonbon.png");
     cupcake = loadImage("cupcake.png");
     celery = loadImage("celery.png");
     coconut = loadImage("coconut.png");
     cola = loadImage("cola.png");
     pizza = loadImage("pizza.png");
     hotdog = loadImage("hotdog.png");
     monkey = loadImage("monkeywalking.png");
  
 
    
    //Set up instances
    levelList = new ArrayList<Level>();
    started = true;
    gameOv = false;
    healthy = new HealthBar(1); 
    place = 0;
    userInterface = new UserInterface(healthy);
    out = new OutroW(healthy);
    
    // Set up Levellist
    levelList.add(new Level(" ", pizza, monkey, 155,128,100,100,600,650,200,200));
    levelList.add(new Level("yellow", banana, pepper, 402, 405, 100, 100, 270,130, 100, 100));
    levelList.add(new Level("nut like", peanut, coconut, 1060, 310, 50, 50, 333,540, 100, 100));
    levelList.add(new Level("crunchy", chips, celery, 258, 393, 100, 100, 596, 182, 100, 100));
    levelList.add(new Level("round & sweet", bonbon, beetroot, 21, 296, 80, 80, 684,420, 100, 100));
    levelList.add(new Level("long", hotdog, cucumber, 913, 500, 100, 100, 180,236, 100, 100));
    levelList.add(new Level("red to bite", cupcake, apple, 470, 207, 100, 100, 721,538, 100, 100));
    levelList.add(new Level("to drink", cola, water, 268, 143, 50, 50, 132,78, 150, 150));
   
}

public void draw()
{
   background(bg);
   cursor(CROSS);
   userInterface.show();
   
   
   if (started){
     //GameOver Bedingungen
     if(place >= levelList.size()){//Gameover wenn letztes Level beendet
       gameOv = true;
       out.draw();
     }
     
     if(healthy.getHealth()>= 7 || healthy.getHealth()<=-7){//Gameover wenn Gesundhei zu schlecht
       gameOv = true;
       out.draw();
     }
     
     if(!gameOv){
     //Zeitgeschichten
      if(timeBeforeNextSpawn <= 0){//wenn keine Antwort vor Ablauf der Zeit ausgewählt wurde, ziehe Gesundheit ab und starte neues Level
        healthy.setHealth(healthy.getHealth() - 1);
        this.respawn();
      }else{
        timeBeforeNextSpawn--;//Zähle Zeit runter
      }
    
      for( int i = 0; i<levelList.size(); i++){// malt alle Gegenstände in das Bild
        levelList.get(i).draw();
      }
      
      if(mousePressed){
        this.mousePressed();
      }
    }//end !gameover
   }//end if started
}//end draw

//Startet das Spiel  mit neutralen Werten
public void startGame(){
    started = true;
    timeBeforeNextSpawn = MAX_TIME_DELAY;
    healthy.setHealth(0); 
    place = 0;
}


// Startet neues Level
public void respawn(){
  if(place >= levelList.size()){
    gameOv = true;
    out.draw();
  }else{
    timeBeforeNextSpawn = MAX_TIME_DELAY;
    place++;
  }
}


// Beendet das Spiel
public void gameOver(){
  started = false;
}

public void mousePressed() {
//wenn innerhalb eines Bildes des aktuellen Levels geklickt wurde, starte neues Level
  if(place < levelList.size()){
      Level lev =levelList.get(place);
    
    if ((mouseX>lev.getX1()) && (mouseX<(lev.getX1() + lev.getWidth1()))
    && (mouseY>lev.getY1()) &&(mouseY<(lev.getY1()+lev.getHeight1())))
     {
       healthy.setHealth(healthy.getHealth() + 1);//ungesundes Essen wurde ausgewählt, Tier wird fetter
       this.respawn();
      
    }else if((mouseX>lev.getX2()) && (mouseX< (lev.getX2() + lev.getWidth2()))
            && (mouseY>lev.getY2()) && (mouseY<(lev.getY2()+lev.getHeight2())))
   {
      this.respawn();
   }
  }
}
}

//Klasse HUD
class UserInterface
{
    HealthBar healthy;
    String hintLevel;
   
public UserInterface(HealthBar healt){
       healthy = healt;
       hintLevel = " ";
    }
    
public void show(){
     textSize(20);
     //wenn das Spiel Läuft zeige Interface an
     stroke(0);
     strokeWeight(2);
     line(0, 40, width, 40);
        
        
     // Zeitanzeige
     //
     fill(0);
     text("Time: " + (int)(timeBeforeNextSpawn / 60), width-100, 30);
        
     // Zeige Hinweis an
     if(place+1<= levelList.size()){       
       hintLevel = levelList.get(place).getHint();
       textSize(14);
       textAlign(CENTER);
       text(hintLevel, 123, 470 );
      }
     // zeigt fat-o-meter
     healthy.draw();
}//end show
  
}//end class
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

public void draw(){
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

public void setHealth(float setter){
   health = setter;
 }
 
public float getHealth(){
   return health;
 }
}
//LevelKlasse
class Level
{
  String hint;//Hinweis
  PImage obj1;//Bild 1 ungesund!
  PImage obj2;// Bild 2
  //Koord Bild1
  int x1;
  int y1;
  int w1;
  int h1;
  //Koord Bild2
  int x2;
  int y2;
  int w2;
  int h2;
  
public Level(String hi, PImage ob1, PImage ob2, int xx1, int yy1, int ww1,
  int hh1, int xx2, int yy2, int ww2, int hh2){
    hint = hi;
    obj1 = ob1;
    obj2 = ob2;
    x1 = xx1;
    x2 =xx2;
    w1 = ww1;
    h1 = hh1;
    y1 =yy1;
    y2 =yy2;
    w2 = ww2;
    h2 =hh2;
  }//end constr
  
public void draw(){// Zeichnet Bilder des Levels
    image(obj1, x1,y1, w1, h1);
    image(obj2, x2, y2, w2, h2);
}
 
 /////////////////////////////////////
 /////////////Getter und Setter///////
 /////////////////////////////////////
  public String getHint(){
    return hint;
  }
  
  public int getWidth1(){
    return w1;
  }
  public int getWidth2(){
    return w2;
  }
  public int getHeight1(){
    return h1;
  }
  public int getHeight2(){
    return h2;
  }
  public int getX1(){
    return x1;
  }
  public int getX2(){
    return x2;
  }
  public int getY1(){
    return y1;
  }
  public int getY2(){
    return y2;
  }

}
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

public void draw(){
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
public void mousePressed(){
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
public void draw(){
  
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
  public void settings() {  size(1300, 900);  smooth(); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "adapt" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
