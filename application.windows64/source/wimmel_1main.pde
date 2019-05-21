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

void mousePressed() {
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
