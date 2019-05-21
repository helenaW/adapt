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
  
void draw(){// Zeichnet Bilder des Levels
    image(obj1, x1,y1, w1, h1);
    image(obj2, x2, y2, w2, h2);
}
 
 /////////////////////////////////////
 /////////////Getter und Setter///////
 /////////////////////////////////////
  String getHint(){
    return hint;
  }
  
  int getWidth1(){
    return w1;
  }
  int getWidth2(){
    return w2;
  }
  int getHeight1(){
    return h1;
  }
  int getHeight2(){
    return h2;
  }
  int getX1(){
    return x1;
  }
  int getX2(){
    return x2;
  }
  int getY1(){
    return y1;
  }
  int getY2(){
    return y2;
  }

}
